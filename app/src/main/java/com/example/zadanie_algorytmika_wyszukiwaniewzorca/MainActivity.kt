package com.example.zadanie_algorytmika_wyszukiwaniewzorca

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.pow
import kotlin.random.Random
import kotlin.system.measureTimeMillis

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
                val text = losujtext(iloscZnakow.text.toString().toInt())

                //czas Brute
                var czasBF = measureTimeMillis {
                    bruteForce(wzorzec.text.toString(),text)
                }
                czasBrute.text = "$czasBF ms"

                //czas KMP
                var czasKMPe = measureTimeMillis {
                    KMP(wzorzec.text.toString(),text)
                }
                czasKMP.text = "$czasKMPe ms"

                //czas BM
                var czasBMm = measureTimeMillis {
                    BM(wzorzec.text.toString(),text)
                }
                czasBM.text = "$czasBMm ms"

                //czas KR
                var czasKRr = measureTimeMillis {
                    KR(wzorzec.text.toString(),text)
                }
                czasKR.text = "$czasKRr ms"
            }
        }
    }

}
// losowanie tekstu
fun losujtext(ilosc:Int): String {
    val listaZnakow : List<Char> = ('a'..'z') + ('A'..'Z') + ('0' .. '9')
    return (1..ilosc).map { Random.nextInt(0, listaZnakow.size) }.map(listaZnakow::get).joinToString("")
}
// Brute Force
fun bruteForce(wzorzec: String, text: String): Int {
    val n = text.length
    val m = wzorzec.length
    for (i in 0..n - m) {
        var j = 0
        while (j < m && text[i+j] == wzorzec[j]) {
            j++
        }
        if (j == m) {
            return i
        }
    }
    return -1
}

//KMP

fun KMP(wzorzec: String, text: String): Int {
    val n = text.length
    val m = wzorzec.length
    val lps = computeLpsArray(wzorzec)
    var i = 0
    var j = 0
    while (i < n) {
        if (text[i] == wzorzec[j]) {
            i++
            j++
        }
        if (j == m) {
            return i - j
        } else if (i < n && text[i] != wzorzec[j]) {
            if (j != 0) {
                j = lps[j - 1]
            } else {
                i++
            }
        }
    }
    return -1
}

fun computeLpsArray(wzorzec: String): IntArray {
    val m = wzorzec.length
    val lps = IntArray(m)
    var len = 0
    var i = 1
    while (i < m) {
        if (wzorzec[i] == wzorzec[len]) {
            len++
            lps[i] = len
            i++
        } else {
            if (len != 0) {
                len = lps[len - 1]
            } else {
                lps[i] = 0
                i++
            }
        }
    }
    return lps
}

// BM

fun BM(wzorzec: String, text: String): Int {
    val n = text.length
    val m = wzorzec.length
    val last = buildLast(wzorzec)
    var i = m - 1
    var j = m - 1
    while (i < n) {
        if (text[i] == wzorzec[j]) {
            if (j == 0) {
                return i
            }
            i--
            j--
        } else {
            val k = last[text[i].toInt()]
            i += m - minOf(j, 1 + k)
            j = m - 1
        }
    }
    return -1
}

fun buildLast(wzorzec: String): IntArray {
    val last = IntArray(256) { -1 }
    for (i in wzorzec.indices) {
        last[wzorzec[i].toInt()] = i
    }
    return last
}

// KR

fun KR(wzorzec: String, text: String): Int {
    val n = text.length
    val m = wzorzec.length
    val prime = 101
    val pHash = wzorzec.hashCode()
    var tHash = text.substring(0, m).hashCode()
    for (i in 0..n - m) {
        if (tHash == pHash && text.substring(i, i + m) == wzorzec) {
            return i
        }
        if (i < n - m) {
            tHash = (((tHash - text[i].hashCode() * prime.toDouble().pow(m - 1).toInt()) % Int.MAX_VALUE) * prime + text[i + m].hashCode()) % Int.MAX_VALUE
        }
    }
    return -1
}

