package uabc.ic.benjaminbolanos.examen1

import android.util.Log
import kotlin.random.Random

class NivelModel(
    val dificultad: Int)
{
    var cantidadIncognitas:Int = 0
    var cantidadValores = 0
    val serie = Serie()
    var inicioSerie = 0
    lateinit var valores: ArrayList<Int>
    lateinit var posicionIncognitas: ArrayList<Int>
    lateinit var posiblesRespuestas: ArrayList<Int>

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
                inicioSerie = Random.nextInt(4)
            }
            1 -> {
                cantidadIncognitas = 2
                cantidadValores = 6
                inicioSerie = Random.nextInt(serie.tamaño / 4)
            }
            2 -> {
                cantidadIncognitas = 2
                cantidadValores = 6//fix a 5
                inicioSerie = Random.nextInt(serie.tamaño / 2)
            }
            else -> {
                cantidadIncognitas = 3
                cantidadValores = 6
                inicioSerie = Random.nextInt(serie.tamaño - cantidadValores - 1)
            }
        }
    }

    private fun crearRespuesta(){
        posicionIncognitas = ArrayList(cantidadIncognitas)
        posiblesRespuestas = ArrayList(cantidadIncognitas)
        valores = serie.getValores(cantidadValores, inicioSerie)
        for(i in 0 until cantidadIncognitas){
            Log.i("QPALV","valores.size = ${valores.size}")
            posicionIncognitas.add(Random.nextInt(cantidadValores))
            posiblesRespuestas.add(valores[posicionIncognitas[i]])
        }
        for(i in posiblesRespuestas.size until 3){
            posiblesRespuestas.add(serie.getValorRandom())
        }
        posiblesRespuestas.shuffle()
    }
}