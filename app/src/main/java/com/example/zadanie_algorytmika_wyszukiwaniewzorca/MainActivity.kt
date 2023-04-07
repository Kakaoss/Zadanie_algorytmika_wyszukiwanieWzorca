package com.example.zadanie_algorytmika_wyszukiwaniewzorca

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnWyk = findViewById<Button>(R.id.btnWykonaj)
        val czasBrute = findViewById<TextView>(R.id.textczasBF)
        val czasKMP = findViewById<TextView>(R.id.textczasKMP)
        val czasBM = findViewById<TextView>(R.id.textczasBM)
        val czasKR = findViewById<TextView>(R.id.textczasKR)
        val iloscZnakow = findViewById<EditText>(R.id.iloscZnakow)
        val wzorzec = findViewById<EditText>(R.id.wzorzec)

        btnWyk.setOnClickListener {

            if (iloscZnakow.text.toString() == "" || wzorzec.text.toString() == ""){
                Toast.makeText(this, "Uzupelnij wszystkie pola", Toast.LENGTH_SHORT).show()
            }
            else{
                val text = losujtext(iloscZnakow.toString().toInt())


            }
        }
    }

}
// losowanie tekstu
fun losujtext(ilosc:Int): String {
    val listaZnakow : List<Char> = ('a'..'z') + ('A'..'Z') + ('0' .. '9')
    return (1..ilosc).map { Random.nextInt(0, listaZnakow.size) }.map(listaZnakow::get).joinToString("")
}