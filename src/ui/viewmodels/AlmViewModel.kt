// File: src/ui/viewmodels/AlmViewModel.kt
package ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.alm.*
import core.execution.*
import core.usb.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import utils.Logger
import utils.PathResolver

class AlmViewModel : ViewModel() {

    private val usbDetector = UsbDetector()
    private val usbPermission = UsbPermissionManager()
    private val usbBridge = UsbBridge()

    private val fastboot = FastbootExecutor(usbBridge)
    private val adb = AdbExecutor(usbBridge)

    private val dispatcher = AlmDispatcher(
        fastboot,
        adb,
        AlmDispatcher.FirmwarePaths(
            bootImg = PathResolver.bootImg(),
            systemImg = PathResolver.systemImg(),
            vbmetaImg = PathResolver.vbmetaImg()
        )
    )

    private val _output = MutableStateFlow("Ready")
    val output: StateFlow<String> = _output

    fun detectDevice() {
        val info = usbDetector.detect()
        if (info == null) {
            _output.value = "No device detected"
            return
        }

        _output.value = """
            Device: ${info.deviceName}
            Vendor: ${info.vendorId}
            Product: ${info.productId}
            Fastboot: ${info.isFastboot}
            ADB: ${info.isAdb}
        """.trimIndent()
    }

    fun executeAlm(raw: String) {
        viewModelScope.launch {
            try {
                val cmd = AlmParser.parse(raw)
                Logger.log("Executing ALM: $raw")

                val result = dispatcher.execute(cmd)

                _output.value = result.output
                Logger.log("Result: ${result.output}")

            } catch (e: Exception) {
                _output.value = "Error: ${e.message}"
                Logger.log("Error: ${e.message}")
            }
        }
    }
}
