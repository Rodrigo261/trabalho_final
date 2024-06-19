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
fun FrutaScreen() {
    var objetivo by rememberSaveable { mutableStateOf("") }
    var fruta by rememberSaveable { mutableStateOf("") }
    var quantidade by rememberSaveable { mutableStateOf("") }
    var totalCalorias by rememberSaveable { mutableStateOf(0) }
    var showResult by rememberSaveable { mutableStateOf(false) }

    val frutas = listOf(
        "Maçã",
        "Banana",
        "Laranja",
        "Morango"
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

        // Seleção de fruta
        Text(text = "Selecione a Fruta")
        Spacer(modifier = Modifier.height(8.dp))

        frutas.forEach { option ->
            val color = when (objetivo) {
                "Aumentar Peso" -> if (option == "Banana") Color.Green else Color.Red
                "Diminuir Peso" -> if (option == "Maçã") Color.Green else Color.Red
                "Manter Peso" -> when (option) {
                    "Laranja", "Morango" -> Color.Green
                    else -> Color.Red
                }
                else -> MaterialTheme.colorScheme.onSurface
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = fruta == option,
                    onClick = { fruta = option },
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
            val calorias = atualizarCaloriasFruta(fruta, quantidade)
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

fun calcularCaloriasPorGramaFruta(fruta: String): Int {
    return when (fruta) {
        "Maçã" -> 2
        "Banana" -> 8
        "Laranja" -> 4
        "Morango" -> 5
        else -> 0
    }
}

fun atualizarCaloriasFruta(fruta: String, quantidade: String): Int {
    val quantidadeGramas = quantidade.toIntOrNull() ?: 0
    val caloriasPorGrama = calcularCaloriasPorGramaFruta(fruta)
    return (quantidadeGramas * caloriasPorGrama).toInt()
}



