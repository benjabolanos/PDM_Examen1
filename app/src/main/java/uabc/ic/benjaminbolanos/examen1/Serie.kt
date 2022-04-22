package uabc.ic.benjaminbolanos.examen1

import java.util.*
import kotlin.collections.ArrayList

class Serie {
    private var valores: ArrayList<String>
    var esAlfabetica: Boolean = false
    var tamaño: Int = 0

    init{
        valores = getSerieRandom()
        tamaño = valores.size
    }

    fun getValores(cantidadValores:Int, inicio:Int): ArrayList<String> {
        val vals = valores
        return vals.slice(inicio until inicio+cantidadValores) as ArrayList<String>
    }

    fun getValorRandom(): String{
        val r = Random()
        return valores[r.nextInt(valores.size)]
    }

    private fun getSerieRandom(): ArrayList<String>{
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

    private fun fibonacci():ArrayList<String>{
        var n1 = 0
        var n2 = 1
        val array = ArrayList<String>()
        array.add(n1.toString())
        for(i in 1..29){
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
        for(i in numeros()){
            val n = i.toInt()
            array.add(n.times(n).toString())
        }
        return array
    }

}