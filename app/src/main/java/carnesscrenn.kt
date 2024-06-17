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
fun CarnesScreen() {
    var carne by rememberSaveable { mutableStateOf("") }
    var quantidade by rememberSaveable { mutableStateOf("") }
    var totalCalorias by rememberSaveable { mutableStateOf(0) }
    var showResult by rememberSaveable { mutableStateOf(false) }


    val carnes = listOf(
        "Bife de Porco",
        "Almôndegas",
        "Bife de Frango",
        "Picanha"
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Seleção de carne
        Text(text = "Selecione a Carne")
        Spacer(modifier = Modifier.height(8.dp))


        carnes.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = carne == option,
                    onClick = { carne = option },
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

            val calorias = atualizarCaloriasCarne(carne, quantidade)
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


fun calcularCaloriasPorGrama(carne: String): Float {
    return when (carne) {
        "Bife de Porco" -> 2.42f
        "Almôndegas" -> 2.06f
        "Bife de Frango" -> 1.65f
        "Picanha" -> 3.30f
        else -> 0f
    }
}


fun atualizarCaloriasCarne(carne: String, quantidade: String): Int {
    val quantidadeGramas = quantidade.toIntOrNull() ?: 0
    val caloriasPorGrama = calcularCaloriasPorGrama(carne)
    return (quantidadeGramas * caloriasPorGrama).toInt()
}
