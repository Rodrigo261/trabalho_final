package com.example.trabalho_final

import android.widget.Toast
import androidx.compose.foundation.layout.*
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


    val atividades = listOf(
        "Sedentário",
        "Levemente Ativo",
        "Moderadamente Ativo",
        "Muito Ativo",
        "Extremamente Ativo"
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
        Spacer(modifier = Modifier.height(8.dp))


        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = sexo == "Masculino",
                onClick = { sexo = "Masculino" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(text = "Masculino")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = sexo == "Feminino",
                onClick = { sexo = "Feminino" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.onSurface
                )
            )
            Text(text = "Feminino")
        }
        Spacer(modifier = Modifier.height(8.dp))


        TextField(value = idade, onValueChange = { idade = it }, label = { Text("Idade") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = altura, onValueChange = { altura = it }, label = { Text("Altura") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = peso, onValueChange = { peso = it }, label = { Text("Peso") })
        Spacer(modifier = Modifier.height(8.dp))


        Column {
            Text(text = "Atividade Diária")
            Spacer(modifier = Modifier.height(8.dp))
            atividades.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = atividade == option,
                        onClick = { atividade = option },
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
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = {

            if (nome.isNotBlank() && sexo.isNotBlank() && idade.isNotBlank() && altura.isNotBlank() && peso.isNotBlank() && atividade.isNotBlank()) {
                val idadeInt = idade.toIntOrNull() ?: 0
                val alturaFloat = altura.toFloatOrNull() ?: 0f
                val pesoFloat = peso.toFloatOrNull() ?: 0f


                val gastoEnergetico = calcularGastoEnergetico(sexo, idadeInt, alturaFloat, pesoFloat, atividade)

                val resultado = "$nome, o seu gasto calórico é $gastoEnergetico calorias"
                Toast.makeText(context, resultado, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Calcular Gasto Energético Diário")
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

    val gastoEnergetico = (bmr * atividadeMultiplier).toInt()
    return gastoEnergetico
}


