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
fun PeixesScreen() {
    var objetivo by rememberSaveable { mutableStateOf("") }
    var peixe by rememberSaveable { mutableStateOf("") }
    var quantidade by rememberSaveable { mutableStateOf("") }
    var totalCalorias by rememberSaveable { mutableStateOf(0) }
    var showResult by rememberSaveable { mutableStateOf(false) }
    var quantidadeValida by rememberSaveable { mutableStateOf(true) }

    val peixes = listOf(
        "Salmão",
        "Atum",
        "Sardinha",
        "Bacalhau"
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

        // Seleção do peixe
        Text(text = "Selecione o Peixe")
        Spacer(modifier = Modifier.height(8.dp))

        peixes.forEach { option ->
            val color = when (objetivo) {
                "Aumentar Peso" -> if (option == "Salmão") Color.Green else Color.Red
                "Diminuir Peso" -> if (option == "Bacalhau") Color.Green else Color.Red
                "Manter Peso" -> when (option) {
                    "Atum", "Sardinha" -> Color.Green
                    else -> Color.Red
                }
                else -> MaterialTheme.colorScheme.onSurface
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = peixe == option,
                    onClick = { peixe = option },
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
                totalCalorias = atualizarCaloriasPeixe(peixe, quantidade)
                showResult = true
            },
            enabled = peixe.isNotEmpty() && quantidadeValida
        ) {
            Text(text = "Calcular Calorias")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showResult) {
            Text(text = "Total de calorias consumidas: $totalCalorias kcal")
        }
    }
}

fun calcularCaloriasPorGramaPeixe(peixe: String): Int {
    return when (peixe) {
        "Salmão" -> 4
        "Atum" -> 3
        "Sardinha" -> 5
        "Bacalhau" -> 2
        else -> 0
    }
}

fun atualizarCaloriasPeixe(peixe: String, quantidade: String): Int {
    val quantidadeGramas = quantidade.toIntOrNull() ?: 0
    val caloriasPorGrama = calcularCaloriasPorGramaPeixe(peixe)
    return (quantidadeGramas * caloriasPorGrama)
}


