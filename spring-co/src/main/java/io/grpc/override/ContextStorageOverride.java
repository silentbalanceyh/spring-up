package io.grpc.override;

import io.grpc.Context;
import io.spring.up.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextStorageOverride extends Context.Storage {
    private static final Logger log = LoggerFactory.getLogger(ContextStorageOverride.class);

    /**
     * Currently bound context.
     */
    private static final ThreadLocal<Context> localContext = new ThreadLocal<>();

    @Override
    public Context doAttach(final Context toAttach) {
        final Context current = this.current();
        localContext.set(toAttach);
        return current;
    }

    @Override
    public void detach(final Context toDetach, final Context toRestore) {
        if (this.current() != toDetach) {
            // Log a severe message instead of throwing an exception as the context to attach is assumed
            // to be the correct one and the unbalanced state represents a coding mistake in a lower
            // layer in the stack that cannot be recovered from here.
            Log.error(log, "[ UP] Grpc Context was not attached when detaching",
                    new Throwable().fillInStackTrace());
        }
        this.doAttach(toRestore);
    }

    @Override
    public Context current() {
        return localContext.get();
    }
}

