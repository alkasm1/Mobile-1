// File: src/utils/Logger.kt
package utils

object Logger {

    private val logs = mutableListOf<String>()

    fun log(msg: String) {
        logs.add("[${System.currentTimeMillis()}] $msg")
    }

    fun getLogs(): List<String> = logs
}
