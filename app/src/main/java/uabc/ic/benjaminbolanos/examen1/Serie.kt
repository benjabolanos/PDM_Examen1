package uabc.ic.benjaminbolanos.examen1

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class Serie {
    private var valores: ArrayList<Int>
    var alfabetica: Boolean = false
    var tamaño: Int = 0

    init{
        valores = getSerieRandom()
        tamaño = valores.size
    }

    fun getValores(cantidadValores:Int, inicio:Int): ArrayList<Int> {
        val vals = valores
        Log.i("QPALV","Slice size ")
        return vals.slice(inicio until inicio+cantidadValores) as ArrayList<Int>
    }

    fun getValorRandom(): Int {
        val r = Random()
        return valores[r.nextInt(valores.size)]
    }

    private fun getSerieRandom(): ArrayList<Int>{
        return when(Random().nextInt(11)){
            0 -> fibonacci()
            1 -> minusculas()
            2 -> mayusculas()
            3 -> numeros()
            4 -> tablaDel(2, 200)
            5 -> tablaDel(3, 240)
            6 -> tablaDel(4, 320)
            7 -> tablaDel(5, 500)
            8 -> tablaDel(10, 500)
            9 -> primos()
            10-> cuadrados()
            else -> ArrayList()
        }
    }

    private fun fibonacci():ArrayList<Int>{
        var n1 = 0
        var n2 = 1
        val array = ArrayList<Int>()
        array.add(n1)
        for(i in 1..29){
            array.add(n2)
            val aux = n1+n2
            n1 = n2
            n2 = aux
        }

        return array
    }

    private fun minusculas(): ArrayList<Int> {
        val array = ArrayList<Int>()
        array.addAll(97 until 122)
        alfabetica = true
        return array
    }

    private fun mayusculas(): ArrayList<Int>{
        val array = ArrayList<Int>()
        array.addAll(65 until 90)
        alfabetica = true
        return array
    }

    private fun numeros(): ArrayList<Int>{
        val array = ArrayList<Int>()
        array.addAll(0 until 150)
        return array
    }

    private fun tablaDel(tabla:Int, numeroMaximo:Int): ArrayList<Int>{
        val array = ArrayList<Int>()
        for(i in 0..numeroMaximo)
            if(i%tabla == 0) array.add(i)
        return array
    }

    private fun primos(): ArrayList<Int>{
        val array = ArrayList<Int>()
        for(i in 1..300){
            if(i == (0 or 1 or 4))
                continue
            for(j in 2..(i/2))
                if(i%j == 0)
                    continue
            array.add(i)
        }
        return array
    }

    private fun cuadrados(): ArrayList<Int>{
        val array = ArrayList<Int>()
        for(i in numeros()){
            array.add(i.times(i))
        }
        return array
    }

}