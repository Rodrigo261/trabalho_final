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
fun CarboidratosScreen() {
    var alimento by rememberSaveable { mutableStateOf("") }
    var quantidade by rememberSaveable { mutableStateOf("") }
    var totalCarboidratos by rememberSaveable { mutableStateOf(0) }
    var showResult by rememberSaveable { mutableStateOf(false) }


    val alimentos = listOf(
        "Massa",
        "Batata",
        "Pão",
        "Arroz"
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Selecione o Alimento")
        Spacer(modifier = Modifier.height(8.dp))


        alimentos.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = alimento == option,
                    onClick = { alimento = option },
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

            totalCarboidratos = atualizarCarboidratos(alimento, quantidade)
            showResult = true
        }) {
            Text(text = "Calcular Carboidratos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showResult) {
            Text(text = "Total de carboidratos consumidos: $totalCarboidratos g")
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
    return (quantidadeGramas * carboidratosPorGrama) / 100
}

