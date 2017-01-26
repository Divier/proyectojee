package co.com.datatools.datatimer.interfaces;

public interface ILogger {

    public void setLog(LevelLog level, String message, Object... params);

    public enum LevelLog {

        Fatal, Error, Warn, Info, Debug, Trace;

    }

}
