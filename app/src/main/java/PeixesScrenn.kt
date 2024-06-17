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
fun PeixesScreen() {
    var peixe by rememberSaveable { mutableStateOf("") }
    var quantidade by rememberSaveable { mutableStateOf("") }
    var totalCalorias by rememberSaveable { mutableStateOf(0) }
    var showResult by rememberSaveable { mutableStateOf(false) }


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
        // Seleção de peixe
        Text(text = "Selecione o Peixe")
        Spacer(modifier = Modifier.height(8.dp))


        peixes.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = peixe == option,
                    onClick = { peixe = option },
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

            val calorias = atualizarCaloriasPeixe(peixe, quantidade)
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


fun calcularCaloriasPorGramaPeixe(peixe: String): Float {
    return when (peixe) {
        "Salmão" -> 2.04f
        "Atum" -> 1.88f
        "Sardinha" -> 0.96f
        "Bacalhau" -> 0.74f
        else -> 0f
    }
}


fun atualizarCaloriasPeixe(peixe: String, quantidade: String): Int {
    val quantidadeGramas = quantidade.toIntOrNull() ?: 0
    val caloriasPorGrama = calcularCaloriasPorGramaPeixe(peixe)
    return (quantidadeGramas * caloriasPorGrama).toInt()
}

