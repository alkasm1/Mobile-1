// File: src/core/alm/AlmParser.kt
package core.alm

object AlmParser {

    fun parse(raw: String): AlmCommand {
        val parts = raw.trim().split(":", limit = 2)
        require(parts.size == 2) { "Invalid ALM command: $raw" }

        val group = parts[0].uppercase()
        val op = parts[1].uppercase()

        return when (group) {

            "SYS" -> when (op) {
                "STATE" -> AlmCommand(raw, AlmTarget.FASTBOOT, AlmAction.SYS_STATE)
                "INFO"  -> AlmCommand(raw, AlmTarget.ADB, AlmAction.SYS_INFO)
                else    -> error("Unknown SYS op: $op")
            }

            "FIX" -> when (op) {
                "BOOT"      -> AlmCommand(raw, AlmTarget.FASTBOOT, AlmAction.FIX_BOOT)
                "SYSTEM"    -> AlmCommand(raw, AlmTarget.FASTBOOT, AlmAction.FIX_SYSTEM)
                "CACHE"     -> AlmCommand(raw, AlmTarget.ADB, AlmAction.FIX_CACHE)
                "SYSTEMUI"  -> AlmCommand(raw, AlmTarget.ADB, AlmAction.FIX_SYSTEMUI)
                else        -> error("Unknown FIX op: $op")
            }

            else -> error("Unknown ALM group: $group")
        }
    }
}
