package com.example.trabalho_final

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
    var totalCalorias by rememberSaveable { mutableStateOf(0) }
    var showResult by rememberSaveable { mutableStateOf(false) }

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
        // Seleção de bebida
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

            totalCalorias = atualizarCalorias(bebida, quantidade)
            showResult = true
        }) {
            Text(text = "Calcular Calorias")
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (showResult) {
            Text(text = "Total de calorias consumidas: $totalCalorias calorias")
        }
    }
}


fun calcularCaloriasPorMl(bebida: String): Int {
    return when (bebida) {
        "Coca-Cola" -> 37  // Calorias por ml
        "Sumo de Laranja" -> 45
        "Água" -> 0
        "Leite" -> 64
        else -> 0
    }
}


fun atualizarCalorias(bebida: String, quantidade: String): Int {
    val quantidadeMl = quantidade.toIntOrNull() ?: 0
    val caloriasPorMl = calcularCaloriasPorMl(bebida)
    return quantidadeMl * caloriasPorMl
}