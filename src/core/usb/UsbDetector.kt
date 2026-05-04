// File: src/core/usb/UsbDetector.kt
package core.usb

data class UsbDeviceInfo(
    val vendorId: Int,
    val productId: Int,
    val deviceName: String,
    val isFastboot: Boolean,
    val isAdb: Boolean
)

class UsbDetector {

    fun detect(): UsbDeviceInfo? {
        // لاحقاً سيتم استبدال هذا بكود USB حقيقي
        return UsbDeviceInfo(
            vendorId = 0x18D1,        // Google
            productId = 0x4EE0,       // Fastboot
            deviceName = "Simulated Android Device",
            isFastboot = true,
            isAdb = false
        )
    }
}
