package io.spring.up.plugin.jhipster;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import io.spring.up.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.core.env.Environment;

public class HazelcastCluster {

    private static final Logger LOGGER = LoggerFactory.getLogger(HazelcastCluster.class);

    private Environment env;

    private DiscoveryClient discoveryClient;

    private Registration registration;

    private ServerProperties serverProperties;

    private HazelcastCluster() {
    }

    public static HazelcastCluster create() {
        return new HazelcastCluster();
    }

    public HazelcastCluster on(final ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
        return this;
    }

    public HazelcastCluster on(final Registration registration) {
        this.registration = registration;
        return this;
    }

    public HazelcastCluster on(final Environment env) {
        this.env = env;
        return this;
    }

    public HazelcastCluster on(final DiscoveryClient client) {
        this.discoveryClient = client;
        return this;
    }

    public HazelcastInstance getInstance(final String name,
                                         final String profile,
                                         final String prefix,
                                         final MapConfig defaultConfig,
                                         final MapConfig domainConfig,
                                         final ManagementCenterConfig centerConfig) {
        Log.updg(LOGGER, "Configuring Hazelcast");
        final HazelcastInstance hazelCastInstance = Hazelcast.getHazelcastInstanceByName(name);
        if (hazelCastInstance != null) {
            Log.updg(LOGGER, "Hazelcast already initialized");
            return hazelCastInstance;
        }
        final Config config = new Config();
        config.setInstanceName(name);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        if (null == this.registration) {
            Log.updg(LOGGER, "No discovery service is set up, Hazelcast cannot create a cluster.");
        } else {
            // The serviceId is by default the application's name,
            // see the "spring.application.name" standard Spring property
            final String serviceId = this.registration.getServiceId();
            Log.updg(LOGGER, "Configuring Hazelcast clustering for instanceId: {}", serviceId);
            // In development, everything goes through 127.0.0.1, with a different port
            if (this.env.acceptsProfiles(profile)) {
                Log.updg(LOGGER, "Application is running with the \"dev\" profile, Hazelcast " +
                        "cluster will only work with localhost instances");

                System.setProperty("hazelcast.local.localAddress", "127.0.0.1");
                config.getNetworkConfig().setPort(this.serverProperties.getPort() + 5701);
                config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
                for (final ServiceInstance instance : this.discoveryClient.getInstances(serviceId)) {
                    final String clusterMember = "127.0.0.1:" + (instance.getPort() + 5701);
                    Log.updg(LOGGER, "Adding Hazelcast (dev) cluster member " + clusterMember);
                    config.getNetworkConfig().getJoin().getTcpIpConfig().addMember(clusterMember);
                }
            } else { // Production configuration, one host per instance all using port 5701
                config.getNetworkConfig().setPort(5701);
                config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
                for (final ServiceInstance instance : this.discoveryClient.getInstances(serviceId)) {
                    final String clusterMember = instance.getHost() + ":5701";
                    Log.updg(LOGGER, "Adding Hazelcast (prod) cluster member " + clusterMember);
                    config.getNetworkConfig().getJoin().getTcpIpConfig().addMember(clusterMember);
                }
            }
        }
        config.getMapConfigs().put("default", defaultConfig);

        // Full reference is available at: http://docs.hazelcast.org/docs/management-center/3.9/manual/html/Deploying_and_Starting.html
        config.setManagementCenterConfig(centerConfig);
        config.getMapConfigs().put(prefix + ".*", domainConfig);
        return Hazelcast.getOrCreateHazelcastInstance(config);
    }
}
