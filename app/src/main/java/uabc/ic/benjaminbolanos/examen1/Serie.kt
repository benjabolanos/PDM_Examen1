package uabc.ic.benjaminbolanos.examen1

import java.util.*
import kotlin.collections.ArrayList

/**
 * Clase Serie que modela una serie numerica o alfabetica.
 */
class Serie(val dificultad:Int) {
    var valores: ArrayList<String>
    var tamaño: Int = 0

    /**
     * Inicializa la serie al generar una serie aleatoria
     */
    init{
        valores = getSerieRandom()
        tamaño = valores.size
    }

    /**
     * Función para obtener un valor aleatorio de la serie.
     */
    fun getValorRandom(): String{
        val r = Random()
        return valores[r.nextInt(valores.size)]
    }

    /**
     * Función que genera una serie dependiendo de la dificultad indicada.
     * @return Retorna un String con los datos de la serie
     */
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
                ArrayList()
            }
        }
    }

    /**
     * Función para generar 15 numeros de la serie de fibonacci
     */
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

    /**
     * Función para generar el abecedario en minusculas.
     */
    private fun minusculas(): ArrayList<String> {
        val array = ArrayList<String>()
        for(i in 97 until 122){//Recorre los valores ascii de las minusculas
            array.add(i.toChar().toString())
        }
        return array
    }

    /**
     * Función para generar el abecedario en mayusculas.
     */
    private fun mayusculas(): ArrayList<String>{
        val array = ArrayList<String>()
        for(i in 65 until 90){//Recorre los valores ascii de las mayusculas
            array.add(i.toChar().toString())
        }
        return array
    }

    /**
     * Función que genera una serie numerica del 1 al 150
     */
    private fun numeros(): ArrayList<String>{
        val array = ArrayList<String>()
        for(i in 0..150){
            array.add(i.toString())
        }
        return array
    }

    /**
     * Función para generar la tabla del numero ingresado hasta el numero maximo.
     */
    private fun tablaDel(tabla:Int, numeroMaximo:Int): ArrayList<String>{
        val array = ArrayList<String>()
        for(i in 1..numeroMaximo)
            if(i%tabla == 0) array.add(i.toString())
        return array
    }

    /**
     * Función que genera los numeros primos.
     */
    private fun primos(): ArrayList<String>{
        val array = ArrayList<String>()
        for(i in 1..300){
            var esPrimo = true
            if(i == (0 or 1 or 4))
                esPrimo = false
            for(j in 2..(i/2)) {
                if (i % j == 0) {
                    esPrimo = false
                    break
                }
            }
            if(esPrimo) array.add(i.toString())
        }
        return array
    }

    /**
     * Función que toma la serie numerica y genera sus cuadrados.
     */
    private fun cuadrados(): ArrayList<String>{
        val array = ArrayList<String>()
        for(i in 1 until 32){
            array.add(i.times(i).toString())
        }
        return array
    }
}