package com.example.trabalho_final

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class FrutaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruta)

        val rgFruta = findViewById<RadioGroup>(R.id.rg_fruta)
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
            val calorias = when (rgFruta.checkedRadioButtonId) {
                R.id.rb_maca -> calcularCalorias(quantidade, 52)
                R.id.rb_banana -> calcularCalorias(quantidade, 89)
                R.id.rb_laranja -> calcularCalorias(quantidade, 43)
                R.id.rb_morango -> calcularCalorias(quantidade, 32)
                else -> 0
            }

            tvResultado.text = "Calorias: $calorias kcal"
        }
    }

    private fun calcularCalorias(quantidade: Int, caloriasPor100g: Int): Int {
        return (quantidade * caloriasPor100g) / 100
    }
}