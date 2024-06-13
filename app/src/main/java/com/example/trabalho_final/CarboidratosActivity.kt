package com.example.trabalho_final

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CarboidratosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carboidratos)

        val rgCarboidratos = findViewById<RadioGroup>(R.id.rg_carboidratos)
        val etQuantidade = findViewById<EditText>(R.id.et_quantidade)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        val tvResultado = findViewById<TextView>(R.id.tv_resultado)

        btnCalcular.setOnClickListener {
            val quantidadeStr = etQuantidade.text.toString()

            if (quantidadeStr.isEmpty()) {
                Toast.makeText(this, "Por favor, insira a quantidade", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantidade = quantidadeStr.toInt()
            val calorias = when (rgCarboidratos.checkedRadioButtonId) {
                R.id.rb_arroz -> calcularCalorias(quantidade, 130)
                R.id.rb_pao -> calcularCalorias(quantidade, 265)
                R.id.rb_massa -> calcularCalorias(quantidade, 131)
                R.id.rb_batata -> calcularCalorias(quantidade, 77)
                else -> 0
            }

            tvResultado.text = "Calorias: $calorias kcal"
        }
    }

    private fun calcularCalorias(quantidade: Int, caloriasPor100g: Int): Int {
        return (quantidade * caloriasPor100g) / 100
    }
}