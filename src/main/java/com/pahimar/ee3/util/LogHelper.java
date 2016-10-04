package com.pahimar.ee3.util;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.handler.ConfigurationHandler;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.message.Message;

import static org.apache.logging.log4j.Level.*;

public class LogHelper {

    public static final Marker MOD_MARKER = MarkerManager.getMarker(EquivalentExchange3.MOD_ID);
    private static final Logger LOGGER = LogManager.getLogger(EquivalentExchange3.MOD_ID);

    public static void log(Level level, Marker marker, Message message) {
        if (ConfigurationHandler.Settings.debugEnabled && (level == Level.DEBUG || level == Level.TRACE)) {
            LOGGER.log(Level.INFO, marker, message);
        }
        else {
            LOGGER.log(level, marker, message);
        }
    }

    public static void log(Level level, Marker marker, Message message, Throwable throwable) {
        if (ConfigurationHandler.Settings.debugEnabled && (level == Level.DEBUG || level == Level.TRACE)) {
            LOGGER.log(Level.INFO, marker, message, throwable);
        }
        else {
            LOGGER.log(level, marker, message, throwable);
        }
    }

    public static void log(Level level, Marker marker, Object object) {
        if (ConfigurationHandler.Settings.debugEnabled && (level == Level.DEBUG || level == Level.TRACE)) {
            LOGGER.log(Level.INFO, marker, object);
        }
        else {
            LOGGER.log(level, marker, object);
        }
    }

    public static void log(Level level, Marker marker, Object object, Throwable throwable) {
        if (ConfigurationHandler.Settings.debugEnabled && (level == Level.DEBUG || level == Level.TRACE)) {
            LOGGER.log(Level.INFO, marker, object, throwable);
        }
        else {
            LOGGER.log(level, marker, object, throwable);
        }
    }

    public static void log(Level level, Marker marker, String message) {
        if (ConfigurationHandler.Settings.debugEnabled && (level == Level.DEBUG || level == Level.TRACE)) {
            LOGGER.log(Level.INFO, marker, message);
        }
        else {
            LOGGER.log(level, marker, message);
        }
    }

    public static void log(Level level, Marker marker, String format, Object... params) {
        if (ConfigurationHandler.Settings.debugEnabled && (level == Level.DEBUG || level == Level.TRACE)) {
            LOGGER.log(Level.INFO, marker, format, params);
        }
        else {
            LOGGER.log(level, marker, format, params);
        }
    }

    public static void log(Level level, Marker marker, String message, Throwable throwable) {
        if (ConfigurationHandler.Settings.debugEnabled && (level == Level.DEBUG || level == Level.TRACE)) {
            LOGGER.log(Level.INFO, marker, message, throwable);
        }
        else {
            LOGGER.log(level, marker, message, throwable);
        }
    }

    public static void log(Level level, Message message) {
        log(level, MOD_MARKER, message);
    }

    public static void log(Level level, Message message, Throwable throwable) {
        log(level, MOD_MARKER, message, throwable);
    }

    public static void log(Level level, Object object) {
        log(level, MOD_MARKER, object);
    }

    public static void log(Level level, Object object, Throwable throwable) {
        log(level, MOD_MARKER, object, throwable);
    }

    public static void log(Level level, String message) {
        log(level, MOD_MARKER, message);
    }

    public static void log(Level level, String format, Object... params) {
        log(level, MOD_MARKER, format, params);
    }

    public static void log(Level level, String message, Throwable throwable) {
        log(level, MOD_MARKER, message, throwable);
    }

    // ALL
    public static void all(Marker marker, Message message) {
        log(ALL, marker, message);
    }

    public static void all(Marker marker, Message message, Throwable throwable) {
        log(ALL, marker, message, throwable);
    }

    public static void all(Marker marker, Object object) {
        log(ALL, marker, object);
    }

    public static void all(Marker marker, Object object, Throwable throwable) {
        log(ALL, marker, object, throwable);
    }

    public static void all(Marker marker, String message) {
        log(ALL, marker, message);
    }

    public static void all(Marker marker, String format, Object... params) {
        log(ALL, marker, format, params);
    }

    public static void all(Marker marker, String message, Throwable throwable) {
        log(ALL, marker, message, throwable);
    }

    public static void all(Message message) {
        all(MOD_MARKER, message);
    }

    public static void all(Message message, Throwable throwable) {
        all(MOD_MARKER, message, throwable);
    }

    public static void all(Object object) {
        all(MOD_MARKER, object);
    }

    public static void all(Object object, Throwable throwable) {
        all(MOD_MARKER, object, throwable);
    }

    public static void all(String message) {
        all(MOD_MARKER, message);
    }

    public static void all(String format, Object... params) {
        all(MOD_MARKER, format, params);
    }

    public static void all(String message, Throwable throwable) {
        all(MOD_MARKER, message, throwable);
    }

    // DEBUG
    public static void debug(Marker marker, Message message) {
        log(DEBUG, marker, message);
    }

    public static void debug(Marker marker, Message message, Throwable throwable) {
        log(DEBUG, marker, message, throwable);
    }

    public static void debug(Marker marker, Object object) {
        log(DEBUG, marker, object);
    }

    public static void debug(Marker marker, Object object, Throwable throwable) {
        log(DEBUG, marker, object, throwable);
    }

    public static void debug(Marker marker, String message) {
        log(DEBUG, marker, message);
    }

    public static void debug(Marker marker, String format, Object... params) {
        log(DEBUG, marker, format, params);
    }

    public static void debug(Marker marker, String message, Throwable throwable) {
        log(DEBUG, marker, message, throwable);
    }

    public static void debug(Message message) {
        debug(MOD_MARKER, message);
    }

    public static void debug(Message message, Throwable throwable) {
        debug(MOD_MARKER, message, throwable);
    }

    public static void debug(Object object) {
        debug(MOD_MARKER, object);
    }

    public static void debug(Object object, Throwable throwable) {
        debug(MOD_MARKER, object, throwable);
    }

    public static void debug(String message) {
        debug(MOD_MARKER, message);
    }

    public static void debug(String format, Object... params) {
        debug(MOD_MARKER, format, params);
    }

    public static void debug(String message, Throwable throwable) {
        debug(MOD_MARKER, message, throwable);
    }

    // ERROR
    public static void error(Marker marker, Message message) {
        log(ERROR, marker, message);
    }

    public static void error(Marker marker, Message message, Throwable throwable) {
        log(ERROR, marker, message, throwable);
    }

    public static void error(Marker marker, Object object) {
        log(ERROR, marker, object);
    }

    public static void error(Marker marker, Object object, Throwable throwable) {
        log(ERROR, marker, object, throwable);
    }

    public static void error(Marker marker, String message) {
        log(ERROR, marker, message);
    }

    public static void error(Marker marker, String format, Object... params) {
        log(ERROR, marker, format, params);
    }

    public static void error(Marker marker, String message, Throwable throwable) {
        log(ERROR, marker, message, throwable);
    }

    public static void error(Message message) {
        error(MOD_MARKER, message);
    }

    public static void error(Message message, Throwable throwable) {
        error(MOD_MARKER, message, throwable);
    }

    public static void error(Object object) {
        error(MOD_MARKER, object);
    }

    public static void error(Object object, Throwable throwable) {
        error(MOD_MARKER, object, throwable);
    }

    public static void error(String message) {
        error(MOD_MARKER, message);
    }

    public static void error(String format, Object... params) {
        error(MOD_MARKER, format, params);
    }

    public static void error(String message, Throwable throwable) {
        error(MOD_MARKER, message, throwable);
    }

    // FATAL
    public static void fatal(Marker marker, Message message) {
        log(FATAL, marker, message);
    }

    public static void fatal(Marker marker, Message message, Throwable throwable) {
        log(FATAL, marker, message, throwable);
    }

    public static void fatal(Marker marker, Object object) {
        log(FATAL, marker, object);
    }

    public static void fatal(Marker marker, Object object, Throwable throwable) {
        log(FATAL, marker, object, throwable);
    }

    public static void fatal(Marker marker, String message) {
        log(FATAL, marker, message);
    }

    public static void fatal(Marker marker, String format, Object... params) {
        log(FATAL, marker, format, params);
    }

    public static void fatal(Marker marker, String message, Throwable throwable) {
        log(FATAL, marker, message, throwable);
    }

    public static void fatal(Message message) {
        fatal(MOD_MARKER, message);
    }

    public static void fatal(Message message, Throwable throwable) {
        fatal(MOD_MARKER, message, throwable);
    }

    public static void fatal(Object object) {
        fatal(MOD_MARKER, object);
    }

    public static void fatal(Object object, Throwable throwable) {
        fatal(MOD_MARKER, object, throwable);
    }

    public static void fatal(String message) {
        fatal(MOD_MARKER, message);
    }

    public static void fatal(String format, Object... params) {
        fatal(MOD_MARKER, format, params);
    }

    public static void fatal(String message, Throwable throwable) {
        fatal(MOD_MARKER, message, throwable);
    }

    // INFO
    public static void info(Marker marker, Message message) {
        log(INFO, marker, message);
    }

    public static void info(Marker marker, Message message, Throwable throwable) {
        log(INFO, marker, message, throwable);
    }

    public static void info(Marker marker, Object object) {
        log(INFO, marker, object);
    }

    public static void info(Marker marker, Object object, Throwable throwable) {
        log(INFO, marker, object, throwable);
    }

    public static void info(Marker marker, String message) {
        log(INFO, marker, message);
    }

    public static void info(Marker marker, String format, Object... params) {
        log(INFO, marker, format, params);
    }

    public static void info(Marker marker, String message, Throwable throwable) {
        log(INFO, marker, message, throwable);
    }

    public static void info(Message message) {
        info(MOD_MARKER, message);
    }

    public static void info(Message message, Throwable throwable) {
        info(MOD_MARKER, message, throwable);
    }

    public static void info(Object object) {
        info(MOD_MARKER, object);
    }

    public static void info(Object object, Throwable throwable) {
        info(MOD_MARKER, object, throwable);
    }

    public static void info(String message) {
        info(MOD_MARKER, message);
    }

    public static void info(String format, Object... params) {
        info(MOD_MARKER, format, params);
    }

    public static void info(String message, Throwable throwable) {
        info(MOD_MARKER, message, throwable);
    }

    // OFF
    public static void off(Marker marker, Message message) {
        log(OFF, marker, message);
    }

    public static void off(Marker marker, Message message, Throwable throwable) {
        log(OFF, marker, message, throwable);
    }

    public static void off(Marker marker, Object object) {
        log(OFF, marker, object);
    }

    public static void off(Marker marker, Object object, Throwable throwable) {
        log(OFF, marker, object, throwable);
    }

    public static void off(Marker marker, String message) {
        log(OFF, marker, message);
    }

    public static void off(Marker marker, String format, Object... params) {
        log(OFF, marker, format, params);
    }

    public static void off(Marker marker, String message, Throwable throwable) {
        log(OFF, marker, message, throwable);
    }

    public static void off(Message message) {
        off(MOD_MARKER, message);
    }

    public static void off(Message message, Throwable throwable) {
        off(MOD_MARKER, message, throwable);
    }

    public static void off(Object object) {
        off(MOD_MARKER, object);
    }

    public static void off(Object object, Throwable throwable) {
        off(MOD_MARKER, object, throwable);
    }

    public static void off(String message) {
        off(MOD_MARKER, message);
    }

    public static void off(String format, Object... params) {
        off(MOD_MARKER, format, params);
    }

    public static void off(String message, Throwable throwable) {
        off(MOD_MARKER, message, throwable);
    }

    // TRACE
    public static void trace(Marker marker, Message message) {
        log(TRACE, marker, message);
    }

    public static void trace(Marker marker, Message message, Throwable throwable) {
        log(TRACE, marker, message, throwable);
    }

    public static void trace(Marker marker, Object object) {
        log(TRACE, marker, object);
    }

    public static void trace(Marker marker, Object object, Throwable throwable) {
        log(TRACE, marker, object, throwable);
    }

    public static void trace(Marker marker, String message) {
        log(TRACE, marker, message);
    }

    public static void trace(Marker marker, String format, Object... params) {
        log(TRACE, marker, format, params);
    }

    public static void trace(Marker marker, String message, Throwable throwable) {
        log(TRACE, marker, message, throwable);
    }

    public static void trace(Message message) {
        trace(MOD_MARKER, message);
    }

    public static void trace(Message message, Throwable throwable) {
        trace(MOD_MARKER, message, throwable);
    }

    public static void trace(Object object) {
        trace(MOD_MARKER, object);
    }

    public static void trace(Object object, Throwable throwable) {
        trace(MOD_MARKER, object, throwable);
    }

    public static void trace(String message) {
        trace(MOD_MARKER, message);
    }

    public static void trace(String format, Object... params) {
        trace(MOD_MARKER, format, params);
    }

    public static void trace(String message, Throwable throwable) {
        trace(MOD_MARKER, message, throwable);
    }

    // WARN
    public static void warn(Marker marker, Message message) {
        log(WARN, marker, message);
    }

    public static void warn(Marker marker, Message message, Throwable throwable) {
        log(WARN, marker, message, throwable);
    }

    public static void warn(Marker marker, Object object) {
        log(WARN, marker, object);
    }

    public static void warn(Marker marker, Object object, Throwable throwable) {
        log(WARN, marker, object, throwable);
    }

    public static void warn(Marker marker, String message) {
        log(WARN, marker, message);
    }

    public static void warn(Marker marker, String format, Object... params) {
        log(WARN, marker, format, params);
    }

    public static void warn(Marker marker, String message, Throwable throwable) {
        log(WARN, marker, message, throwable);
    }

    public static void warn(Message message) {
        warn(MOD_MARKER, message);
    }

    public static void warn(Message message, Throwable throwable) {
        warn(MOD_MARKER, message, throwable);
    }

    public static void warn(Object object) {
        warn(MOD_MARKER, object);
    }

    public static void warn(Object object, Throwable throwable) {
        warn(MOD_MARKER, object, throwable);
    }

    public static void warn(String message) {
        warn(MOD_MARKER, message);
    }

    public static void warn(String format, Object... params) {
        warn(MOD_MARKER, format, params);
    }

    public static void warn(String message, Throwable throwable) {
        warn(MOD_MARKER, message, throwable);
    }
}
