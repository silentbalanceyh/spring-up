package io.spring.up.log;

import io.spring.up.exception.AbstractException;
import io.vertx.up.fn.Fn;
import org.slf4j.Logger;

import java.text.MessageFormat;
import java.util.function.Consumer;

@FunctionalInterface
interface Evaluater {
    boolean test();
}

@SuppressWarnings("all")
public class Log {

    public static void jvm(final Logger logger, final Throwable ex) {
        error(logger, Tpl.E_JVM, Fn.getNull("None", () -> ex.getMessage(), ex));
    }

    public static void debug(final Logger logger, final String message, final Object... args) {
        output(logger::isDebugEnabled, logger::debug, message, args);
    }

    public static void error(final Logger logger, final AbstractException ex) {
        error(logger, ex.getMessage());
    }

    public static void error(final Logger logger, final String message, final Object... args) {
        output(logger::isErrorEnabled, logger::error, message, args);
    }

    public static void warn(final Logger logger, final String message, final Object... args) {
        output(logger::isWarnEnabled, logger::warn, message, args);
    }

    public static void warn(final Logger logger, final AbstractException ex) {
        warn(logger, ex.getMessage());
    }

    public static void info(final Logger logger, final String message, final Object... args) {
        output(logger::isInfoEnabled, logger::info, message, args);
    }

    public static void up(final Logger logger,
                          final String message, final Object... args) {
        info(logger, "[ UP ] " + message, args);
    }

    public static void upt(final Logger logger,
                           final String message, final Object... args) {
        info(logger, "[ UP TS ] " + message, args);
    }


    public static void updg(final Logger logger,
                            final String message, final Object... args) {
        debug(logger, "[ UP DG ] " + message, args);
    }

    public static void uperr(final Logger logger,
                             final String message, final Object... args) {
        error(logger, "[ UP ] " + message, args);
    }

    public static void upw(final Logger logger,
                           final String message, final Object... args) {
        warn(logger, "[ UP ] " + message, args);
    }

    public static void upfix(final Logger logger,
                             final String prefix, final String message,
                             final Object... args) {
        info(logger, "[ UP ] (" + prefix + ") " + message, args);
    }

    private static void output(final Evaluater evaluater, final Consumer<String> fnLog,
                               final String message, final Object... params) {
        if (evaluater.test()) {
            fnLog.accept(format(message, params));
        }
    }

    private static String format(final String pattern, final Object... args) {
        String message = pattern;
        if (0 < args.length) {
            message = MessageFormat.format(message, args);
        }
        return message;
    }
}
