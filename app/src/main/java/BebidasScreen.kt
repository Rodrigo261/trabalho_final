package com.example.trabalho_final

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun BebidasScreen() {
    var bebida by rememberSaveable { mutableStateOf("") }
    var quantidade by rememberSaveable { mutableStateOf("") }


    val bebidas = listOf(
        "Coca-Cola",
        "Sumo de Laranja",
        "Água",
        "Leite"
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Selecione a Bebida")
        Spacer(modifier = Modifier.height(8.dp))


        bebidas.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = bebida == option,
                    onClick = { bebida = option },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(text = option)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade (ml)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = {

            val quantidadeMl = quantidade.toIntOrNull() ?: 0
            val caloriasPorMl = calcularCaloriasPorMl(bebida)
            val totalCalorias = quantidadeMl * caloriasPorMl

            val resultado = "Total de calorias consumidas: $totalCalorias calorias"
            Toast.makeText(context, resultado, Toast.LENGTH_LONG).show()
        }) {
            Text(text = "Calcular Calorias")
        }
    }
}


fun calcularCaloriasPorMl(bebida: String): Int {
    return when (bebida) {
        "Coca-Cola" -> 37
        "Sumo de Laranja" -> 45
        "Água" -> 0
        "Leite" -> 64
        else -> 0
    }
}