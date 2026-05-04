// File: src/ui/repair/RepairScreen.kt
package ui.repair

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.viewmodels.AlmViewModel

@Composable
fun RepairScreen(vm: AlmViewModel) {

    val output by vm.output.collectAsState()

    Column(Modifier.padding(20.dp)) {

        Text("Quick Repair", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        Button(onClick = { vm.executeAlm("FIX:BOOT") }) {
            Text("Fix Boot (FIX:BOOT)")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = { vm.executeAlm("FIX:SYSTEM") }) {
            Text("Fix System (FIX:SYSTEM)")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = { vm.executeAlm("FIX:CACHE") }) {
            Text("Fix Cache (FIX:CACHE)")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = { vm.executeAlm("FIX:SYSTEMUI") }) {
            Text("Fix SystemUI (FIX:SYSTEMUI)")
        }

        Spacer(Modifier.height(20.dp))

        Text("Output:")
        Text(output)
    }
}
