package com.example.erp_crm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.erp_crm.model.ClienteCrmDto
import com.example.erp_crm.ui.CrmViewModel
import com.example.erp_crm.ui.theme.ERP_CRMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ERP_CRMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaCrm(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PantallaCrm(modifier: Modifier = Modifier, viewModel: CrmViewModel = viewModel()) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { viewModel.sincronizarCrm() },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("Sincronizar CRM Corporativo")
        }

        if (viewModel.estaCargando.value) {
            CircularProgressIndicator()
        }

        if (viewModel.mensajeError.value.isNotEmpty()) {
            Text(text = viewModel.mensajeError.value, color = MaterialTheme.colorScheme.error)
        }

        LazyColumn {
            items(viewModel.clientes) { cliente ->
                TarjetaCliente(cliente)
            }
        }
    }
}

@Composable
fun TarjetaCliente(cliente: ClienteCrmDto) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = cliente.nombreCompleto, style = MaterialTheme.typography.titleLarge)
            Text(text = "Empresa: ${cliente.empresa.nombreEmpresa}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Email: ${cliente.correo}", style = MaterialTheme.typography.bodySmall)
        }
    }
}