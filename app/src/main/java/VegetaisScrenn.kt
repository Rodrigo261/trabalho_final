package com.example.trabalho_final

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun VegetaisScreen() {
    var objetivo by rememberSaveable { mutableStateOf("") }
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
        // Seleção do objetivo
        Text(text = "Selecione o Objetivo")
        Spacer(modifier = Modifier.height(8.dp))

        val objetivos = listOf(
            "Manter Peso",
            "Diminuir Peso",
            "Aumentar Peso"
        )

        objetivos.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = objetivo == option,
                    onClick = { objetivo = option },
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

        // Seleção de vegetal
        Text(text = "Selecione o Vegetal")
        Spacer(modifier = Modifier.height(8.dp))

        vegetais.forEach { option ->
            val color = when (objetivo) {
                "Aumentar Peso" -> if (option == "Cenoura") Color.Green else Color.Red
                "Diminuir Peso" -> if (option == "Alface") Color.Green else Color.Red
                "Manter Peso" -> when (option) {
                    "Espinafre", "Couve" -> Color.Green
                    else -> Color.Red
                }
                else -> MaterialTheme.colorScheme.onSurface
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = vegetal == option,
                    onClick = { vegetal = option },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = color,
                        unselectedColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(text = option, color = color)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (objetivo.isNotEmpty()) {
            val recomendacao = when (objetivo) {
                "Aumentar Peso" -> "Para seu objetivo, recomendamos a quantidade entre 300 e 500 g."
                "Diminuir Peso" -> "Para seu objetivo, recomendamos a quantidade entre 100 e 200 g."
                "Manter Peso" -> "Para seu objetivo, recomendamos a quantidade entre 200 e 300 g."
                else -> ""
            }
            Text(text = recomendacao)
            Spacer(modifier = Modifier.height(8.dp))
        }

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
            Text(text = "Total de calorias consumidas: $totalCalorias kcal")
        }
    }
}

fun calcularCaloriasPorGramaVegetal(vegetal: String): Int {
    return when (vegetal) {
        "Alface" -> 1
        "Espinafre" -> 2
        "Cenoura" -> 4
        "Couve" -> 3
        else -> 0
    }
}

fun atualizarCaloriasVegetal(vegetal: String, quantidade: String): Int {
    val quantidadeGramas = quantidade.toIntOrNull() ?: 0
    val caloriasPorGrama = calcularCaloriasPorGramaVegetal(vegetal)
    return (quantidadeGramas * caloriasPorGrama)
}
