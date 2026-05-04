// File: src/core/execution/DummyExecutor.kt
package core.execution

class DummyExecutor : CommandExecutor {
    override suspend fun runCommand(command: List<String>): ExecResult {
        val cmd = command.joinToString(" ")
        return ExecResult(
            success = true,
            output = "Simulated execution: $cmd"
        )
    }
}
