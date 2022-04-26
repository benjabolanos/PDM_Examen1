package uabc.ic.benjaminbolanos.examen1

import android.content.Context
import android.os.CountDownTimer

class JuegoModel {
    val niveles: ArrayList<Nivel> = ArrayList()
    private var dificultad: Int = 0
    private var turnos: Int = 0

    private var archivoJuego = ArchivoJuego()

    private val temporizador = Temporizador()

    init {
        crearNiveles()
    }

    private fun crearNiveles() {
        for (i in 0 until 3) {
            niveles[i] = Nivel(dificultad)
        }
    }

    fun verificarSerie(valores: Array<ArrayList<String>>): Boolean {
        for (i in valores.indices) {
            for (j in valores[i].indices) {
                if (valores[i][j] != niveles[i].valores[j])
                    return false
            }
        }
        return true
    }

    fun avanzarTurno(context: Context){
        archivoJuego.guardarInfoNiveles(context, niveles, turnos)
        crearNiveles()
        turnos++
        if (turnos == 5) {
            dificultad++
            turnos = 0
        }
    }


}






