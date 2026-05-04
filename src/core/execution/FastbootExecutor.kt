// File: src/core/execution/FastbootExecutor.kt
package core.execution

class FastbootExecutor(
    private val executor: CommandExecutor
) {

    suspend fun getDeviceInfo(): ExecResult =
        executor.runCommand(listOf("fastboot", "getvar", "all"))

    suspend fun flashBoot(path: String): ExecResult =
        executor.runCommand(listOf("fastboot", "flash", "boot", path))

    suspend fun flashSystem(path: String): ExecResult =
        executor.runCommand(listOf("fastboot", "flash", "system", path))

    suspend fun flashVbmeta(path: String): ExecResult =
        executor.runCommand(listOf("fastboot", "flash", "vbmeta", path))

    suspend fun reboot(): ExecResult =
        executor.runCommand(listOf("fastboot", "reboot"))
}
