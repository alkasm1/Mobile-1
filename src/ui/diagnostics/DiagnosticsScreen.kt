// File: src/ui/diagnostics/DiagnosticsScreen.kt
package ui.diagnostics

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.viewmodels.AlmViewModel

@Composable
fun DiagnosticsScreen(vm: AlmViewModel) {

    val output by vm.output.collectAsState()

    Column(Modifier.padding(20.dp)) {

        Text("System Diagnostics", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        Button(onClick = { vm.detectDevice() }) {
            Text("Detect Device")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = { vm.executeAlm("SYS:STATE") }) {
            Text("Check Device State (SYS:STATE)")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = { vm.executeAlm("SYS:INFO") }) {
            Text("Get System Info (SYS:INFO)")
        }

        Spacer(Modifier.height(20.dp))

        Text("Output:")
        Text(output)
    }
}
