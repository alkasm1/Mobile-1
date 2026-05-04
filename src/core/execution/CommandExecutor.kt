// File: src/core/execution/CommandExecutor.kt
package core.execution

interface CommandExecutor {
    suspend fun runCommand(command: List<String>): ExecResult
}

data class ExecResult(
    val success: Boolean,
    val output: String,
    val error: String? = null
)
