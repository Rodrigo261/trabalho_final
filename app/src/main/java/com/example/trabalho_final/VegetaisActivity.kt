package com.example.trabalho_final

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class VegetaisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vegetais)

        val rgVegetais = findViewById<RadioGroup>(R.id.rg_vegetais)
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
            val calorias = when (rgVegetais.checkedRadioButtonId) {
                R.id.rb_brocolis -> calcularCalorias(quantidade, 34)
                R.id.rb_cenoura -> calcularCalorias(quantidade, 41)
                R.id.rb_alface -> calcularCalorias(quantidade, 15)
                R.id.rb_tomate -> calcularCalorias(quantidade, 18)
                else -> 0
            }

            tvResultado.text = "Calorias: $calorias kcal"
        }
    }

    private fun calcularCalorias(quantidade: Int, caloriasPor100g: Int): Int {
        return (quantidade * caloriasPor100g) / 100
    }
}