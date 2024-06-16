package com.example.trabalho_final

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ObjetivoScreen() {
    var nome by rememberSaveable { mutableStateOf("") }
    var sexo by rememberSaveable { mutableStateOf("") }
    var idade by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }
    var peso by rememberSaveable { mutableStateOf("") }
    var atividade by rememberSaveable { mutableStateOf("") }
    var objetivo by rememberSaveable { mutableStateOf("") }
    var caloriasDiarias by rememberSaveable { mutableStateOf(0) }
    var showResult by rememberSaveable { mutableStateOf(false) }

    val atividades = listOf(
        "Sedentário",
        "Levemente Ativo",
        "Moderadamente Ativo",
        "Muito Ativo",
        "Extremamente Ativo"
    )

    val objetivos = listOf(
        "Diminuir Peso",
        "Manter Peso",
        "Aumentar Peso"
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input fields
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Radio buttons for Sexo
        Text(text = "Sexo")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            RadioButton(
                selected = sexo == "Masculino",
                onClick = { sexo = "Masculino" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "Masculino")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = sexo == "Feminino",
                onClick = { sexo = "Feminino" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "Feminino")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Input fields for Idade, Altura, Peso
        OutlinedTextField(
            value = idade,
            onValueChange = { idade = it },
            label = { Text("Idade") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Altura (cm)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Radio buttons for Atividade Diária
        Text(text = "Atividade Diária")
        atividades.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = atividade == option,
                    onClick = { atividade = option },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = option)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Radio buttons for Objetivo
        Text(text = "Objetivo")
        objetivos.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = objetivo == option,
                    onClick = { objetivo = option },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = option)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para calcular objetivo
        Button(onClick = {
            if (nome.isNotBlank() && sexo.isNotBlank() && idade.isNotBlank() && altura.isNotBlank() && peso.isNotBlank() && atividade.isNotBlank() && objetivo.isNotBlank()) {
                val idadeInt = idade.toIntOrNull() ?: 0
                val alturaFloat = altura.toFloatOrNull() ?: 0f
                val pesoFloat = peso.toFloatOrNull() ?: 0f

                val gastoEnergetico = calcularGastoEnergetico(sexo, idadeInt, alturaFloat, pesoFloat, atividade)

                caloriasDiarias = calcularCaloriasDiarias(gastoEnergetico, objetivo)
                showResult = true
            } else {
                Toast.makeText(context, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Calcular Calorias Diárias")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibir calorias calculadas somente quando showResult é true
        if (showResult) {
            Text(text = "$nome, para alcançar o objetivo de $objetivo, você deve consumir $caloriasDiarias calorias por dia.")
        }
    }
}

fun calcularGastoEnergetico(sexo: String, idade: Int, altura: Float, peso: Float, atividade: String): Int {
    val atividadeMultiplier = when (atividade) {
        "Sedentário" -> 1.2f
        "Levemente Ativo" -> 1.375f
        "Moderadamente Ativo" -> 1.55f
        "Muito Ativo" -> 1.725f
        "Extremamente Ativo" -> 1.9f
        else -> 1.2f
    }

    val bmr = if (sexo.equals("Masculino", ignoreCase = true)) {
        88.362f + (13.397f * peso) + (4.799f * altura) - (5.677f * idade)
    } else {
        447.593f + (9.247f * peso) + (3.098f * altura) - (4.330f * idade)
    }

    return (bmr * atividadeMultiplier).toInt()
}

fun calcularCaloriasDiarias(gastoEnergetico: Int, objetivo: String): Int {
    return when (objetivo) {
        "Diminuir Peso" -> gastoEnergetico - 500
        "Aumentar Peso" -> gastoEnergetico + 500
        "Manter Peso" -> gastoEnergetico
        else -> gastoEnergetico
    }
}
