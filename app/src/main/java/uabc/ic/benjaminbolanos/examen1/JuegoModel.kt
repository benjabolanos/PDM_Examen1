package uabc.ic.benjaminbolanos.examen1

import android.content.Context

/**
 * Clase JuegoModel que modela el control lógico del Juego de Adivinar Series.
 */
class JuegoModel(context: Context) {
    val niveles: ArrayList<Nivel> = ArrayList()
    private var dificultad: Int = 0
    private var turnos: Int = 1

    var archivoJuego = ArchivoJuego(context)
    var cronometro = Cronometro()

    /**
     * Al inicializar, crea los niveles del juego.
     */
    init {
        crearNiveles()
    }

    /**
     * Función para crear tres niveles dependiente de la dificultad e iniciar el cronometro
     */
    fun crearNiveles() {
        niveles.clear()
        for (i in 0 until 3) {
            niveles.add(Nivel(dificultad))
        }
        cronometro.iniciar()
    }

    /**
     * Función que recibe tres series en forma de ArrayList de String y los compara
     * con las series de los tres niveles del juego. Si en algun momento la serie es diferente,
     * retorna false.
     * @return True si las series recibidas son iguales a las generadas.
     */
    fun verificarSeries(valores: Array<ArrayList<String>>): Boolean {
        for (i in valores.indices)
            for (j in valores[i].indices)
                if (valores[i][j] != niveles[i].valores[j])
                    return false
        return true
    }

    /**
     * Función que reinicia el turno al reinicar el cronometro, aumentar la dificultad, crear nuevos
     * niveles y guardar la info en el archivo de juego.
     */
    fun avanzarTurno(): Boolean{
        cronometro.parar()
        if(dificultad == 3) return false
        archivoJuego.guardarInfoNiveles(niveles, turnos, cronometro.tiempo)
        if (turnos == 5) {
            dificultad++
            turnos = 0
        }
        crearNiveles()
        turnos++
        return true
    }
}






