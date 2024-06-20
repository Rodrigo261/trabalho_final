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
fun CarnesScreen() {
    var objetivo by rememberSaveable { mutableStateOf("") }
    var carne by rememberSaveable { mutableStateOf("") }
    var quantidade by rememberSaveable { mutableStateOf("") }
    var totalCalorias by rememberSaveable { mutableStateOf(0) }
    var showResult by rememberSaveable { mutableStateOf(false) }
    var quantidadeValida by rememberSaveable { mutableStateOf(true) }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    val carnes = listOf(
        "Bife de Porco",
        "Almôndegas",
        "Bife de Frango",
        "Picanha"
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
            Text(text = "Selecione a Carne")
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(carnes) { option ->
            val color = when (objetivo) {
                "Aumentar Peso" -> if (option == "Picanha") Color.Green else Color.Red
                "Diminuir Peso" -> if (option == "Bife de Frango") Color.Green else Color.Red
                "Manter Peso" -> when (option) {
                    "Bife de Porco", "Almôndegas" -> Color.Green
                    else -> Color.Red
                }
                else -> MaterialTheme.colorScheme.onSurface
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = carne == option,
                    onClick = { carne = option },
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
                onValueChange = {
                    quantidade = it
                    quantidadeValida = it.toIntOrNull()?.let { it > 0 } ?: false
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
                    if (carne.isEmpty() || quantidade.isEmpty()) {
                        errorMessage = "Por favor, selecione uma carne e insira a quantidade."
                        showResult = false
                    } else if (!quantidadeValida) {
                        errorMessage = "Por favor, insira uma quantidade válida e positiva."
                        showResult = false
                    } else {
                        totalCalorias = atualizarCaloriasCarne(carne, quantidade)
                        showResult = true
                        errorMessage = ""
                    }
                },
                enabled = carne.isNotEmpty() && quantidadeValida
            ) {
                Text(text = "Calcular Calorias")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            }

            if (showResult) {
                Text(text = "Total de calorias consumidas: $totalCalorias kcal")
            }
        }
    }
}

fun calcularCaloriasPorGrama(carne: String): Int {
    return when (carne) {
        "Bife de Porco" -> 20
        "Almôndegas" -> 25
        "Bife de Frango" -> 15
        "Picanha" -> 30
        else -> 0
    }
}

fun atualizarCaloriasCarne(carne: String, quantidade: String): Int {
    val quantidadeGramas = quantidade.toIntOrNull() ?: 0
    val caloriasPorGrama = calcularCaloriasPorGrama(carne)
    return (quantidadeGramas * caloriasPorGrama)
}

