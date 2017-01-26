package co.com.datatools.datatimer.c2.impl;

import org.jboss.logging.Logger;

import co.com.datatools.datatimer.interfaces.ILogger;

public class LoggerImpl implements ILogger {

    private static final Logger Log = Logger.getLogger(LoggerImpl.class);

    public LoggerImpl() {
        super();
    }

    @Override
    public void setLog(LevelLog level, String message, Object... params) {

        message = message.replaceAll("\\{}", "%s");
        switch (level) {
        case Debug:
            Log.debugf(message, params);
            break;
        case Error:
            Log.errorf(message, params);
            break;
        case Fatal:
            Log.fatalf(message, params);
            break;
        case Info:
            Log.infof(message, params);
            break;
        case Trace:
            Log.tracef(message, params);
            break;
        case Warn:
            Log.warnf(message, params);
            break;
        default:
            Log.errorf(message, params);
            break;
        }
    }
}