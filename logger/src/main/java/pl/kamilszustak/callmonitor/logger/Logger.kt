package pl.kamilszustak.callmonitor.logger

/**
 * An interface representing a logger that can be used to log messages. It provides methods for
 * logging messages with different log levels. Each method takes a tag and a lambda function
 * returning a message as parameters to avoid unnecessary [String] concatenation.
 */
interface Logger {

    /**
     * Logs the [message] with the specified [level] and [tag].
     */
    fun log(level: LogLevel, tag: String, message: () -> String)

    /**
     * Logs the [message] with the [LogLevel.VERBOSE] level and the specified [tag].
     */
    fun verbose(tag: String, message: () -> String)

    /**
     * Logs the [message] with the [LogLevel.DEBUG] level and the specified [tag].
     */
    fun debug(tag: String, message: () -> String)

    /**
     * Logs the [message] with the [LogLevel.INFO] level and the specified [tag].
     */
    fun info(tag: String, message: () -> String)

    /**
     * Logs the [message] with the [LogLevel.WARN] level and the specified [tag].
     */
    fun warn(tag: String, message: () -> String)

    /**
     * Logs the [message] with the [LogLevel.ERROR] level and the specified [tag].
     */
    fun error(tag: String, message: () -> String)

    /**
     * Logs the [message] with the [LogLevel.ASSERT] level and the specified [tag].
     */
    fun assert(tag: String, message: () -> String)
}