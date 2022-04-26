package uabc.ic.benjaminbolanos.examen1

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class Serie(val dificultad:Int) {
    var valores: ArrayList<String>
    var esAlfabetica: Boolean = false
    var tamaño: Int = 0

    init{
        valores = getSerieRandom()
        tamaño = valores.size
    }

    fun getValorRandom(): String{
        val r = Random()
        return valores[r.nextInt(valores.size)]
    }

    private fun getSerieRandom(): ArrayList<String>{
        val opcion = Random().nextInt(3)
        return when(dificultad){
            0 -> {
                when(opcion){
                    0 -> minusculas()
                    1 -> tablaDel(2, 200)
                    2 -> numeros()
                    else -> getSerieRandom()
                }
            }
            1 -> {
                when(opcion){
                    0 -> mayusculas()
                    1 -> tablaDel(5, 500)
                    2 -> tablaDel(10, 200)
                    else -> getSerieRandom()
                }
            }
            2 -> {
                when(opcion){
                    0 -> tablaDel(3, 240)
                    1 -> tablaDel(4, 320)
                    2 -> tablaDel(5, 500)
                    else -> getSerieRandom()
                }
            }
            3 -> {
                when(opcion){
                    0 -> fibonacci()
                    1 -> primos()
                    2 -> cuadrados()
                    else -> getSerieRandom()
                }
            }
            else -> {
                Log.i("QPALV", "Dificultad inexistente.")
                ArrayList()
            }
        }
    }

    private fun fibonacci():ArrayList<String>{
        var n1 = 0
        var n2 = 1
        val array = ArrayList<String>()
        array.add(n1.toString())
        for(i in 1..15){
            array.add(n2.toString())
            val aux = n1+n2
            n1 = n2
            n2 = aux
        }

        return array
    }

    private fun minusculas(): ArrayList<String> {
        val array = ArrayList<String>()
        for(i in 97 until 122){
            array.add(i.toChar().toString())
        }
        esAlfabetica = true
        return array
    }

    private fun mayusculas(): ArrayList<String>{
        val array = ArrayList<String>()
        for(i in 65 until 90){
            array.add(i.toChar().toString())
        }
        esAlfabetica = true
        return array
    }

    private fun numeros(): ArrayList<String>{
        val array = ArrayList<String>()
        for(i in 0..150){
            array.add(i.toString())
        }
        return array
    }

    private fun tablaDel(tabla:Int, numeroMaximo:Int): ArrayList<String>{
        val array = ArrayList<String>()
        for(i in 0..numeroMaximo)
            if(i%tabla == 0) array.add(i.toString())
        return array
    }

    private fun primos(): ArrayList<String>{
        val array = ArrayList<String>()
        for(i in 1..300){
            if(i == (0 or 1 or 4))
                continue
            for(j in 2..(i/2))
                if(i%j == 0)
                    continue
            array.add(i.toString())
        }
        return array
    }

    private fun cuadrados(): ArrayList<String>{
        val array = ArrayList<String>()
        for(i in numeros().subList(0, numeros().size/2)){
            val n = i.toInt()
            array.add(n.times(n).toString())
        }
        return array
    }

}