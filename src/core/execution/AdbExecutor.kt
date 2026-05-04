// File: src/core/execution/AdbExecutor.kt
package core.execution

class AdbExecutor(
    private val executor: CommandExecutor
) {

    suspend fun getSysInfo(): ExecResult =
        executor.runCommand(listOf("adb", "shell", "getprop"))

    suspend fun wipeCache(): ExecResult =
        executor.runCommand(listOf("adb", "shell", "wipe", "cache"))

    suspend fun wipeDalvik(): ExecResult =
        executor.runCommand(listOf("adb", "shell", "wipe", "dalvik"))

    suspend fun fixSystemUi(): ExecResult =
        executor.runCommand(
            listOf(
                "adb", "shell",
                "cmd", "package", "compile",
                "-m", "speed",
                "-a"
            )
        )
}
