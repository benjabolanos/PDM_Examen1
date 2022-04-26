package uabc.ic.benjaminbolanos.examen1

import android.os.CountDownTimer

class Temporizador {

    lateinit var timer: CountDownTimer
    var segundosRestantes = 0
    var tiempoTerminado = true

    fun iniciarTimer(){
        if(tiempoTerminado) {
            timer = object : CountDownTimer(10000, 1000) {
                override fun onTick(milisRestantes: Long) {
                    segundosRestantes = (milisRestantes / 1000).toInt()
                    if (segundosRestantes > 0) tiempoTerminado = false
                }

                override fun onFinish() {
                    segundosRestantes = 0
                    tiempoTerminado = true
                }
            }.start()
        }
    }
}