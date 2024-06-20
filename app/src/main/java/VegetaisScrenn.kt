package com.example.trabalho_final

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    var quantidadeValida by rememberSaveable { mutableStateOf(true) }

    val vegetais = listOf(
        "Alface",
        "Espinafre",
        "Cenoura",
        "Couve"
    )

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Text(text = "Selecione o Objetivo")
            Spacer(modifier = Modifier.height(8.dp))
        }

        val objetivos = listOf(
            "Manter Peso",
            "Diminuir Peso",
            "Aumentar Peso"
        )

        items(objetivos) { option ->
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

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Selecione o Vegetal")
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(vegetais) { option ->
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

        item {
            Spacer(modifier = Modifier.height(8.dp))

            if (objetivo.isNotEmpty()) {
                val recomendacao = when (objetivo) {
                    "Aumentar Peso" -> "Para seu objetivo, recomendamos a quantidade entre 300 e 450 g."
                    "Diminuir Peso" -> "Para seu objetivo, recomendamos a quantidade entre 100 e 200 g."
                    "Manter Peso" -> "Para seu objetivo, recomendamos a quantidade entre 200 e 300 g."
                    else -> ""
                }
                Text(text = recomendacao)
                Spacer(modifier = Modifier.height(8.dp))
            }

            OutlinedTextField(
                value = quantidade,
                onValueChange = {
                    quantidade = it
                    quantidadeValida = quantidade.toIntOrNull()?.let { it > 0 } ?: false
                },
                label = { Text("Quantidade (g)") },
                modifier = Modifier.fillMaxWidth(),
                isError = !quantidadeValida
            )
            if (!quantidadeValida) {
                Text(
                    text = "Por favor, insira um valor positivo.",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    totalCalorias = atualizarCaloriasVegetal(vegetal, quantidade)
                    showResult = true
                },
                enabled = vegetal.isNotEmpty() && quantidadeValida
            ) {
                Text(text = "Calcular Calorias")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (showResult) {
                Text(text = "Total de calorias consumidas: $totalCalorias kcal")
            }
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