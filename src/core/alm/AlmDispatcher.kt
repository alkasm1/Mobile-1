// File: src/core/alm/AlmDispatcher.kt
package core.alm

import core.execution.*

class AlmDispatcher(
    private val fastboot: FastbootExecutor,
    private val adb: AdbExecutor,
    private val paths: FirmwarePaths
) {

    data class FirmwarePaths(
        val bootImg: String,
        val systemImg: String,
        val vbmetaImg: String
    )

    suspend fun execute(cmd: AlmCommand): ExecResult {
        return when (cmd.action) {

            AlmAction.SYS_STATE -> {
                fastboot.getDeviceInfo()
            }

            AlmAction.SYS_INFO -> {
                adb.getSysInfo()
            }

            AlmAction.FIX_BOOT -> {
                val r1 = fastboot.flashBoot(paths.bootImg)
                if (!r1.success) return r1
                val r2 = fastboot.flashVbmeta(paths.vbmetaImg)
                if (!r2.success) return r2
                fastboot.reboot()
            }

            AlmAction.FIX_SYSTEM -> {
                val r1 = fastboot.flashSystem(paths.systemImg)
                if (!r1.success) return r1
                fastboot.reboot()
            }

            AlmAction.FIX_CACHE -> {
                val r1 = adb.wipeCache()
                if (!r1.success) return r1
                adb.wipeDalvik()
            }

            AlmAction.FIX_SYSTEMUI -> {
                adb.fixSystemUi()
            }
        }
    }
}
