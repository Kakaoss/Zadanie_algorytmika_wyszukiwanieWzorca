package com.example.zadanie_algorytmika_wyszukiwaniewzorca

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

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

            }
        }
    }
}
// losowanie tekstu
fun losujtext(ilosc:Int):String{

    return -1
}