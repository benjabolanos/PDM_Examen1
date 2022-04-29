package uabc.ic.benjaminbolanos.examen1

import kotlin.random.Random

class Nivel(
    val dificultad: Int)
{
    private var cantidadIncognitas:Int = 0
    private var cantidadValores = 0
    private val serie = Serie(dificultad)
    lateinit var valores: ArrayList<String>
    lateinit var posicionIncognitas: ArrayList<Int>
    lateinit var posiblesRespuestas: ArrayList<String>

    init {
        crearNivel()
    }

    private fun crearNivel(){
        setDificultad()
        crearRespuesta()
    }

    private fun setDificultad(){
        when (dificultad){
            0 -> {
                cantidadIncognitas = 1
                cantidadValores = 6//fix a 5
            }
            1 -> {
                cantidadIncognitas = 2
                cantidadValores = 6
            }
            2 -> {
                cantidadIncognitas = 2
                cantidadValores = 6//fix a 5
            }
            else -> {
                cantidadIncognitas = 3
                cantidadValores = 6
            }
        }
    }

    private fun crearRespuesta(){
        posiblesRespuestas = ArrayList()
        val inicioSerie = Random.nextInt(0, serie.tamaño - cantidadValores - 1)

        valores = serie.valores.slice(inicioSerie..(inicioSerie + cantidadValores)) as ArrayList<String>

        posicionIncognitas = ArrayList((0 until cantidadValores).shuffled().take(cantidadIncognitas))

        for(i in 0 until cantidadIncognitas) posiblesRespuestas.add(valores[posicionIncognitas[i]])

        for(i in posiblesRespuestas.size until 3){
            posiblesRespuestas.add(serie.getValorRandom())
        }

        posiblesRespuestas.shuffle()
    }
}