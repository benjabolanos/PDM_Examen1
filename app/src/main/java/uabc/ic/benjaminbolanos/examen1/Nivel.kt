package uabc.ic.benjaminbolanos.examen1

import kotlin.random.Random

class Nivel(
    val dificultad: Int)
{
    var cantidadIncognitas:Int = 0
    var cantidadValores = 0
    val serie = Serie(dificultad)
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
        valores = serie.valores.slice(0..cantidadValores) as ArrayList<String>

        posicionIncognitas = ArrayList((0 until cantidadValores).shuffled().take(cantidadIncognitas))

        for(i in 0 until cantidadIncognitas) posiblesRespuestas.add(valores[posicionIncognitas[i]])

        for(i in posiblesRespuestas.size until 3){
            posiblesRespuestas.add(serie.getValorRandom())
        }

        posiblesRespuestas.shuffle()
    }
}