// File: src/ui/dashboard/DashboardScreen.kt
package ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.viewmodels.AlmViewModel

@Composable
fun DashboardScreen(vm: AlmViewModel) {

    var almText by remember { mutableStateOf("") }
    val output by vm.output.collectAsState()

    Column(Modifier.padding(20.dp)) {

        Text("ALM Android Repair Tool", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(20.dp))

        Button(onClick = { vm.detectDevice() }) {
            Text("Detect Device via USB")
        }

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = almText,
            onValueChange = { almText = it },
            label = { Text("Enter ALM Command") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Button(onClick = { vm.executeAlm(almText) }) {
            Text("Execute")
        }

        Spacer(Modifier.height(20.dp))

        Text("Output:")
        Text(output)
    }
}
