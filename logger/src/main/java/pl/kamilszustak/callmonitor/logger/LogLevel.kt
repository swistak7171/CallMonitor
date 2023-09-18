package pl.kamilszustak.callmonitor.logger

/**
 * An enum class representing a log level.
 */
enum class LogLevel {

    /**
     * Verbose log level. Used for messages that contain a lot of information. It is the lowest
     * log level.
     */
    VERBOSE,

    /**
     * Debug log level. Used for messages that contain information that is useful for debugging.
     */
    DEBUG,

    /**
     * Info log level. Used for messages that contain information about the application's state.
     */
    INFO,

    /**
     * Warn log level. Used for messages that contain information about a possible error.
     */
    WARN,

    /**
     * Error log level. Used for messages that contain information about an error.
     */
    ERROR,

    /**
     * Assert log level. Used for messages that contain information about a critical error. It is
     * the highest log level.
     */
    ASSERT;
}