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
fun CarboidratosScreen() {
    var objetivo by rememberSaveable { mutableStateOf("") }
    var alimento by rememberSaveable { mutableStateOf("") }
    var quantidade by rememberSaveable { mutableStateOf("") }
    var totalCarboidratos by rememberSaveable { mutableStateOf(0) }
    var showResult by rememberSaveable { mutableStateOf(false) }
    var quantidadeValida by rememberSaveable { mutableStateOf(true) }

    val alimentos = listOf(
        "Massa",
        "Batata",
        "Pão",
        "Arroz"
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
            Text(text = "Selecione o Alimento")
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(alimentos) { option ->
            val color = when (objetivo) {
                "Aumentar Peso" -> if (option == "Pão") Color.Green else Color.Red
                "Diminuir Peso" -> if (option == "Batata") Color.Green else Color.Red
                "Manter Peso" -> when (option) {
                    "Massa", "Arroz" -> Color.Green
                    else -> Color.Red
                }
                else -> MaterialTheme.colorScheme.onSurface
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = alimento == option,
                    onClick = { alimento = option },
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
                    totalCarboidratos = atualizarCarboidratos(alimento, quantidade)
                    showResult = true
                },
                enabled = alimento.isNotEmpty() && quantidadeValida
            ) {
                Text(text = "Calcular Carboidratos")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (showResult) {
                Text(text = "Total de carboidratos consumidos: $totalCarboidratos g")
            }
        }
    }
}

fun calcularCarboidratosPorGrama(alimento: String): Int {
    return when (alimento) {
        "Massa" -> 25
        "Batata" -> 17
        "Pão" -> 50
        "Arroz" -> 28
        else -> 0
    }
}

fun atualizarCarboidratos(alimento: String, quantidade: String): Int {
    val quantidadeGramas = quantidade.toIntOrNull() ?: 0
    val carboidratosPorGrama = calcularCarboidratosPorGrama(alimento)
    return (quantidadeGramas * carboidratosPorGrama)
}



