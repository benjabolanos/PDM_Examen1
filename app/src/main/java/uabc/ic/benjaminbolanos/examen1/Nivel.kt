package uabc.ic.benjaminbolanos.examen1

import kotlin.random.Random

/**
 * Clase Nivel que modela un nivel, este quiere decir que contiene la serie que se debe acompletar
 * y en que lugares va a haber incognitas.
 */
class Nivel(
    val dificultad: Int)
{
    private var cantidadIncognitas:Int = 0
    private val CANTIDAD_VALORES = 6
    private val serie = Serie(dificultad)
    lateinit var valores: ArrayList<String>
    lateinit var posicionIncognitas: ArrayList<Int>
    lateinit var posiblesRespuestas: ArrayList<String>

    /**
     * Al inicializar, se crea el nivel
     */
    init {
        crearNivel()
    }

    /**
     * Funcionón para crear la dificultad y la respuesta/datos del nivel
     */
    private fun crearNivel(){
        setDificultad()
        crearRespuesta()
    }

    /**
     * Función que determina la cantidad de incognitas a partir de la dificultad
     */
    private fun setDificultad(){
        cantidadIncognitas = when (dificultad){
            0 -> 1
            1,2 -> 2
            else -> 3
        }
    }

    /**
     * Función que determina cuales valores serán las respuestas/incognitas y guarda sus posiciones.
     * Tambien, toma otros valores de la misma serie para tener diferentes opciones de valores.
     */
    private fun crearRespuesta(){
        posiblesRespuestas = ArrayList()
        val inicioSerie = Random.nextInt(0, serie.tamaño - CANTIDAD_VALORES - 1)

        valores = serie.valores.slice(inicioSerie..(inicioSerie + CANTIDAD_VALORES)) as ArrayList<String>

        posicionIncognitas = ArrayList((0 until CANTIDAD_VALORES).shuffled().take(cantidadIncognitas))

        for(i in 0 until cantidadIncognitas) posiblesRespuestas.add(valores[posicionIncognitas[i]])

        for(i in posiblesRespuestas.size until 3){
            posiblesRespuestas.add(serie.getValorRandom())
        }

        posiblesRespuestas.shuffle()
    }
}