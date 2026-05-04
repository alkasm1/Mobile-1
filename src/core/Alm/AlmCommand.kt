// File: src/core/alm/AlmCommand.kt
package core.alm

enum class AlmTarget {
    FASTBOOT,
    ADB
}

enum class AlmAction {
    SYS_STATE,
    SYS_INFO,
    FIX_BOOT,
    FIX_SYSTEM,
    FIX_CACHE,
    FIX_SYSTEMUI
}

data class AlmCommand(
    val raw: String,
    val target: AlmTarget,
    val action: AlmAction,
    val args: Map<String, String> = emptyMap()
)
