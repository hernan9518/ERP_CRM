package com.example.erp_crm.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.erp_crm.model.ClienteCrmDto
import com.example.erp_crm.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CrmViewModel : ViewModel() {
    val clientes = mutableStateListOf<ClienteCrmDto>()
    val estaCargando = mutableStateOf(false)
    val mensajeError = mutableStateOf("")

    fun sincronizarCrm() {
        viewModelScope.launch(Dispatchers.IO) {
            estaCargando.value = true
            mensajeError.value = ""
            try {
                val respuesta = ApiClient.crmService.obtenerClientes()
                clientes.clear()
                clientes.addAll(respuesta)
            } catch (e: Exception) {
                mensajeError.value = "Error de conexión: ${e.localizedMessage}"
            } finally {
                estaCargando.value = false
            }
        }
    }
}