package com.example.trabalho_final

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trabalho_final.ui.theme.TrabalhoFinalTheme

class ObjetivoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrabalhoFinalTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    ObjetivoScreen()
                }
            }
        }
    }
}

@Composable
fun ObjetivoScreen() {
    var nome by rememberSaveable { mutableStateOf("") }
    var sexo by rememberSaveable { mutableStateOf("") }
    var idade by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }
    var peso by rememberSaveable { mutableStateOf("") }
    var atividade by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = sexo, onValueChange = { sexo = it }, label = { Text("Sexo") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = idade, onValueChange = { idade = it }, label = { Text("Idade") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = altura, onValueChange = { altura = it }, label = { Text("Altura") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = peso, onValueChange = { peso = it }, label = { Text("Peso") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = atividade, onValueChange = { atividade = it }, label = { Text("Atividade Diária") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Calcular objetivo e exibir
        }) {
            Text(text = "Calcular Objetivo")
        }
    }
}