package com.example.trabalho_final

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun VegetaisScreen() {
    var vegetal by rememberSaveable { mutableStateOf("") }
    var quantidade by rememberSaveable { mutableStateOf("") }
    var totalCalorias by rememberSaveable { mutableStateOf(0) }
    var showResult by rememberSaveable { mutableStateOf(false) }


    val vegetais = listOf(
        "Alface",
        "Espinafre",
        "Cenoura",
        "Couve"
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Selecione o Vegetal")
        Spacer(modifier = Modifier.height(8.dp))


        vegetais.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = vegetal == option,
                    onClick = { vegetal = option },
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
            label = { Text("Quantidade (g)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            val calorias = atualizarCaloriasVegetal(vegetal, quantidade)
            totalCalorias = calorias
            showResult = true
        }) {
            Text(text = "Calcular Calorias")
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (showResult) {
            Text(text = "Total de calorias consumidas: $totalCalorias cal")
        }
    }
}

fun calcularCaloriasPorGramaVegetal(vegetal: String): Float {
    return when (vegetal) {
        "Alface" -> 0.17f
        "Espinafre" -> 0.23f
        "Cenoura" -> 0.41f
        "Couve" -> 0.35f
        else -> 0f
    }
}

fun atualizarCaloriasVegetal(vegetal: String, quantidade: String): Int {
    val quantidadeGramas = quantidade.toIntOrNull() ?: 0
    val caloriasPorGrama = calcularCaloriasPorGramaVegetal(vegetal)
    return (quantidadeGramas * caloriasPorGrama).toInt()
}
