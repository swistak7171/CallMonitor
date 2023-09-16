package pl.kamilszustak.callmonitor.logger

interface Logger {
    fun log(level: LogLevel, tag: String, message: () -> String)
    fun verbose(tag: String, message: () -> String)
    fun debug(tag: String, message: () -> String)
    fun info(tag: String, message: () -> String)
    fun warn(tag: String, message: () -> String)
    fun error(tag: String, message: () -> String)
    fun assert(tag: String, message: () -> String)
}