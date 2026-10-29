package com.example.erp_crm.model

import com.google.gson.annotations.SerializedName

data class ClienteCrmDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val nombreCompleto: String,
    @SerializedName("email") val correo: String,
    @SerializedName("phone") val telefono: String,
    @SerializedName("company") val empresa: EmpresaDto
)

data class EmpresaDto(
    @SerializedName("name") val nombreEmpresa: String
)