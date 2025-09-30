package com.argumento.appcafeteriarev2.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.argumento.appcafeteriarev2.viewmodel.CarrinhoViewModel

@Composable
fun EditarMesaDialog(
    carrinhoViewModel: CarrinhoViewModel,
    onDismissRequest: () -> Unit
) {
    var mesaInput by remember { mutableStateOf(carrinhoViewModel.numMesa.intValue.toString()) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Alterar Número da Mesa") },
        text = {
            Column {
                Text("Digite o novo número da mesa:")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = mesaInput,
                    onValueChange = { novoValor ->
                        // Permite apenas números
                        if (novoValor.all { it.isDigit() }) {
                            mesaInput = novoValor
                        }
                    },
                    label = { Text("Número da Mesa") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val novoNumeroMesa = mesaInput.toIntOrNull()
                    if (novoNumeroMesa != null) {
                        carrinhoViewModel.numMesa.intValue = novoNumeroMesa
                    }
                    onDismissRequest() // Fecha o diálogo
                }
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest) {
                Text("Cancelar")
            }
        },
        modifier = Modifier.padding(16.dp)
    )
}
