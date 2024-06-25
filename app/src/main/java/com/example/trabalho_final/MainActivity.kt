package com.example.trabalho_final

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trabalho_final.ui.theme.TrabalhoFinalTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TrabalhoFinalTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                ) {
                    MainScreen()
                }
            }
        }
        Log.d(TAG, "onCreate called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current

    val buttons = listOf(
        "Bebidas" to BebidasActivity::class.java,
        "Peixes" to PeixesActivity::class.java,
        "Carnes" to CarnesActivity::class.java,
        "Carboidratos" to CarboidratosActivity::class.java,
        "Frutas" to FrutaActivity::class.java,
        "Vegetais" to VegetaisActivity::class.java,
        "Calcular Objetivo" to ObjetivoActivity::class.java
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Main Menu") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(buttons) { (text, activityClass) ->
                    Button(
                        onClick = { navigateToActivity(context, activityClass) },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
                    ) {
                        Text(text = text)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )
}

fun navigateToActivity(context: android.content.Context, activityClass: Class<*>) {
    context.startActivity(Intent(context, activityClass))
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TrabalhoFinalTheme {
        MainScreen()
    }
}