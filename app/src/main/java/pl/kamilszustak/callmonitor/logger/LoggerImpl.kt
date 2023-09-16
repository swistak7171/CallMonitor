package pl.kamilszustak.callmonitor.logger

import logcat.LogPriority
import logcat.logcat

class LoggerImpl : Logger {

    override fun log(level: LogLevel, tag: String, message: () -> String) {
        logcat(
            priority = level.toLogPriority(),
            tag = tag,
            message = message
        )
    }

    override fun verbose(tag: String, message: () -> String) {
        log(LogLevel.VERBOSE, tag, message)
    }

    override fun debug(tag: String, message: () -> String) {
        log(LogLevel.DEBUG, tag, message)
    }

    override fun info(tag: String, message: () -> String) {
        log(LogLevel.INFO, tag, message)
    }

    override fun warn(tag: String, message: () -> String) {
        log(LogLevel.WARN, tag, message)
    }

    override fun error(tag: String, message: () -> String) {
        log(LogLevel.ERROR, tag, message)
    }

    override fun assert(tag: String, message: () -> String) {
        log(LogLevel.ASSERT, tag, message)
    }

    private fun LogLevel.toLogPriority(): LogPriority {
        return when (this) {
            LogLevel.VERBOSE -> LogPriority.VERBOSE
            LogLevel.DEBUG -> LogPriority.DEBUG
            LogLevel.INFO -> LogPriority.INFO
            LogLevel.WARN -> LogPriority.WARN
            LogLevel.ERROR -> LogPriority.ERROR
            LogLevel.ASSERT -> LogPriority.ASSERT
        }
    }

}