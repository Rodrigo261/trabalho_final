package com.example.trabalho_final

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ObjetivoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objetivo)

        val etNome = findViewById<EditText>(R.id.et_nome)
        val etIdade = findViewById<EditText>(R.id.et_idade)
        val etAltura = findViewById<EditText>(R.id.et_altura)
        val etPeso = findViewById<EditText>(R.id.et_peso)
        val spSexo = findViewById<Spinner>(R.id.sp_sexo)
        val spAtividade = findViewById<Spinner>(R.id.sp_atividade)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        val tvResultado = findViewById<TextView>(R.id.tv_resultado)

        // Configure spinners
        ArrayAdapter.createFromResource(
            this,
            R.array.sexos,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spSexo.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.niveis_atividade,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spAtividade.adapter = adapter
        }

        btnCalcular.setOnClickListener {
            val nome = etNome.text.toString()
            val idade = etIdade.text.toString().toIntOrNull() ?: 0
            val altura = etAltura.text.toString().toDoubleOrNull() ?: 0.0
            val peso = etPeso.text.toString().toDoubleOrNull() ?: 0.0
            val sexo = spSexo.selectedItem.toString()
            val atividade = spAtividade.selectedItem.toString()

            val bmr = calculateBMR(sexo, idade, altura, peso)
            val caloriasDiarias = calculateDailyCalorieNeeds(bmr, atividade)

            tvResultado.text = "Olá, $nome. Sua necessidade calórica diária é de aproximadamente ${caloriasDiarias.toInt()} calorias."
        }
    }

    private fun calculateBMR(sexo: String, idade: Int, altura: Double, peso: Double): Double {
        return if (sexo == "Masculino") {
            10 * peso + 6.25 * altura - 5 * idade + 5
        } else {
            10 * peso + 6.25 * altura - 5 * idade - 161
        }
    }

    private fun calculateDailyCalorieNeeds(bmr: Double, atividade: String): Double {
        return when (atividade) {
            "Sedentário" -> bmr * 1.2
            "Levemente ativo" -> bmr * 1.375
            "Moderadamente ativo" -> bmr * 1.55
            "Muito ativo" -> bmr * 1.725
            "Super ativo" -> bmr * 1.9
            else -> bmr
        }
    }
}