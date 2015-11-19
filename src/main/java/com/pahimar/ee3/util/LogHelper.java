package com.pahimar.ee3.util;

import com.pahimar.ee3.reference.Reference;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.message.Message;

public class LogHelper
{
    public static final Marker MOD_MARKER = MarkerManager.getMarker(Reference.MOD_ID);
    private static Logger logger = LogManager.getLogger(Reference.MOD_ID);

    public static void log(Level level, Marker marker, Message message) {
        logger.log(level, marker, message);
    }

    public static void log(Level level, Marker marker, Object object) {
        logger.log(level, marker, object);
    }

    public static void log(Level level, Marker marker, String message) {
        logger.log(level, marker, message);
    }

    public static void log(Level level, Marker marker, String format, Object... params) {
        logger.log(level, marker, format, params);
    }

    public static void log(Level level, Message message) {
        log(level, MOD_MARKER, message);
    }

    public static void log(Level level, Object object) {
        log(level, MOD_MARKER, object);
    }

    public static void log(Level level, String message) {
        log(level, MOD_MARKER, message);
    }

    public static void log(Level level, String format, Object... params) {
        log(level, MOD_MARKER, format, params);
    }

    // ALL

    public static void all(Marker marker, Message message) {
        log(Level.ALL, marker, message);
    }

    public static void all(Marker marker, Object object) {
        log(Level.ALL, marker, object);
    }

    public static void all(Marker marker, String message) {
        log(Level.ALL, marker, message);
    }

    public static void all(Marker marker, String format, Object... params) {
        log(Level.ALL, marker, format, params);
    }

    public static void all(Message message) {
        all(MOD_MARKER, message);
    }

    public static void all(Object object) {
        all(MOD_MARKER, object);
    }

    public static void all(String message) {
        all(MOD_MARKER, message);
    }

    public static void all(String format, Object... pararms) {
        all(MOD_MARKER, format, pararms);
    }

    // DEBUG

    public static void debug(Marker marker, Message message) {
        log(Level.DEBUG, marker, message);
    }

    public static void debug(Marker marker, Object object) {
        log(Level.DEBUG, marker, object);
    }

    public static void debug(Marker marker, String message) {
        log(Level.DEBUG, marker, message);
    }

    public static void debug(Marker marker, String format, Object... params) {
        log(Level.DEBUG, marker, format, params);
    }

    public static void debug(Message message) {
        debug(MOD_MARKER, message);
    }

    public static void debug(Object object) {
        debug(MOD_MARKER, object);
    }

    public static void debug(String message) {
        debug(MOD_MARKER, message);
    }

    public static void debug(String format, Object... params) {
        debug(MOD_MARKER, format, params);
    }

    // ERROR

    public static void error(Marker marker, Message message) {
        log(Level.ERROR, marker, message);
    }

    public static void error(Marker marker, Object object) {
        log(Level.ERROR, marker, object);
    }

    public static void error(Marker marker, String message) {
        log(Level.ERROR, marker, message);
    }

    public static void error(Marker marker, String format, Object... params) {
        log(Level.ERROR, marker, format, params);
    }

    public static void error(Message message) {
        error(MOD_MARKER, message);
    }

    public static void error(Object object) {
        error(MOD_MARKER, object);
    }

    public static void error(String message) {
        error(MOD_MARKER, message);
    }

    public static void error(String format, Object... params) {
        error(MOD_MARKER, format, params);
    }

    // FATAL

    public static void fatal(Marker marker, Message message) {
        log(Level.FATAL, marker, message);
    }

    public static void fatal(Marker marker, Object object) {
        log(Level.FATAL, marker, object);
    }

    public static void fatal(Marker marker, String message) {
        log(Level.FATAL, marker, message);
    }

    public static void fatal(Marker marker, String format, Object... params) {
        log(Level.FATAL, marker, format, params);
    }

    public static void fatal(Message message) {
        fatal(MOD_MARKER, message);
    }

    public static void fatal(Object object) {
        fatal(MOD_MARKER, object);
    }

    public static void fatal(String message) {
        fatal(MOD_MARKER, message);
    }

    public static void fatal(String format, Object... params) {
        fatal(MOD_MARKER, format, params);
    }

    // INFO

    public static void info(Marker marker, Message message) {
        log(Level.INFO, marker, message);
    }

    public static void info(Marker marker, Object object) {
        log(Level.INFO, marker, object);
    }

    public static void info(Marker marker, String message) {
        log(Level.INFO, marker, message);
    }

    public static void info(Marker marker, String format, Object... params) {
        log(Level.INFO, marker, format, params);
    }

    public static void info(Message message) {
        info(MOD_MARKER, message);
    }

    public static void info(Object object) {
        info(MOD_MARKER, object);
    }

    public static void info(String message) {
        info(MOD_MARKER, message);
    }

    public static void info(String format, Object... params) {
        info(MOD_MARKER, format, params);
    }

    // OFF

    public static void off(Marker marker, Message message) {
        log(Level.OFF, marker, message);
    }

    public static void off(Marker marker, Object object) {
        log(Level.OFF, marker, object);
    }

    public static void off(Marker marker, String message) {
        log(Level.OFF, marker, message);
    }

    public static void off(Marker marker, String format, Object... params) {
        log(Level.OFF, marker, format, params);
    }

    public static void off(Message message) {
        off(MOD_MARKER, message);
    }

    public static void off(Object object) {
        off(MOD_MARKER, object);
    }

    public static void off(String message) {
        off(MOD_MARKER, message);
    }

    public static void off(String format, Object... params) {
        off(MOD_MARKER, format, params);
    }

    // TRACE

    public static void trace(Marker marker, Message message) {
        log(Level.TRACE, marker, message);
    }

    public static void trace(Marker marker, Object object) {
        log(Level.TRACE, marker, object);
    }

    public static void trace(Marker marker, String message) {
        log(Level.TRACE, marker, message);
    }

    public static void trace(Marker marker, String format, Object... params) {
        log(Level.TRACE, marker, format, params);
    }

    public static void trace(Message message) {
        trace(MOD_MARKER, message);
    }

    public static void trace(Object object) {
        trace(MOD_MARKER, object);
    }

    public static void trace(String message) {
        trace(MOD_MARKER, message);
    }

    public static void trace(String format, Object... params) {
        trace(MOD_MARKER, format, params);
    }

    // WARN

    public static void warn(Marker marker, Message message) {
        log(Level.WARN, marker, message);
    }

    public static void warn(Marker marker, Object object) {
        log(Level.WARN, marker, object);
    }

    public static void warn(Marker marker, String message) {
        log(Level.WARN, marker, message);
    }

    public static void warn(Marker marker, String format, Object... params) {
        log(Level.WARN, marker, format, params);
    }

    public static void warn(Message message) {
        warn(MOD_MARKER, message);
    }

    public static void warn(Object object) {
        warn(MOD_MARKER, object);
    }

    public static void warn(String message) {
        warn(MOD_MARKER, message);
    }

    public static void warn(String format, Object... params) {
        warn(MOD_MARKER, format, params);
    }
}
