package com.example.trabalho_final

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trabalho_final.ui.theme.TrabalhoFinalTheme

class BebidasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrabalhoFinalTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    BebidasScreen()
                }
            }
        }
    }
}

@Composable
fun BebidasScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Bebidas Activity", style = MaterialTheme.typography.headlineMedium)

    }
}

@Preview(showBackground = true)
@Composable
fun BebidasScreenPreview() {
    TrabalhoFinalTheme {
        BebidasScreen()
    }
}