package uabc.ic.benjaminbolanos.examen1

import android.content.Context

class JuegoModel(context: Context) {
    val niveles: ArrayList<Nivel> = ArrayList()
    private var dificultad: Int = 0
    private var turnos: Int = 1

    var archivoJuego = ArchivoJuego(context)
    var cronometro = Cronometro()

    init {
        crearNiveles()
    }

    fun crearNiveles() {
        niveles.clear()
        for (i in 0 until 3) {
            niveles.add(Nivel(dificultad))
        }
        cronometro.iniciar()
    }

    fun verificarSeries(valores: Array<ArrayList<String>>): Boolean {
        for (i in valores.indices)
            for (j in valores[i].indices)
                if (valores[i][j] != niveles[i].valores[j])
                    return false
        return true
    }

    fun avanzarTurno(context: Context): Boolean{
        cronometro.parar()
        if(dificultad == 3) return false
        archivoJuego.guardarInfoNiveles(context, niveles, turnos, cronometro.tiempo)
        if (turnos == 5) {
            dificultad++
            turnos = 0
        }
        crearNiveles()
        turnos++
        return true
    }
}






