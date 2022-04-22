package uabc.ic.benjaminbolanos.examen1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class Nivel : AppCompatActivity() {

    /**
     * COSAS POR HACER
     * 1. Valores no repetidos en posicionesIncognitas
     * 2.
     */


    //NivelModelo
    private lateinit var nivelModel: NivelModel

    //NivelView
    private var valoresViews = ArrayList<TextView>()
    private var respuestasView = ArrayList<TextView>()

    //Control
    private var respuestaElegida = -1
    private val historialNiveles = ArrayList<NivelModel>()
    private var dificultad = 0
    private var nivelesCompletados = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nivel)
        initView()
        setValoresOnClickListeners()
        setRespuestasOnClickListeners()
        setLevel()
    }

    @SuppressLint("SetTextI18n")
    private fun setLevel(){
        nivelModel = NivelModel(dificultad)
        for((i,tv) in valoresViews.withIndex()){
            tv.text = nivelModel.valores[i]
        }
        for(i in nivelModel.posicionIncognitas){
            valoresViews[i].text = "__"
        }
        for((i,tv) in respuestasView.withIndex()){
            tv.text = nivelModel.posiblesRespuestas[i].toString()
        }
    }

    private fun initView(){
        val valoresIDs = arrayOf(R.id.nivel_valores_0, R.id.nivel_valores_1, R.id.nivel_valores_2,
            R.id.nivel_valores_3, R.id.nivel_valores_4, R.id.nivel_valores_5)
        for(id in valoresIDs) valoresViews.add(findViewById(id))

        val respuestasIDs = arrayOf(R.id.nivel_respuesta_0, R.id.nivel_respuesta_1, R.id.nivel_respuesta_2)
        for(id in respuestasIDs) respuestasView.add(findViewById(id))
    }

    private fun setRespuestasOnClickListeners(){
        for((i,tv) in respuestasView.withIndex()){
            tv.setTextColor(resources.getColor(R.color.black))
            tv.setOnClickListener {
                if(respuestaElegida == -1) {
                    tv.setBackgroundColor(resources.getColor(R.color.respuestas_selected))
                    respuestaElegida = i
                } else {
                    respuestasView[respuestaElegida].setBackgroundColor(resources.getColor(R.color.white))
                    respuestaElegida = -1
                }
            }
        }
    }

    private fun setValoresOnClickListeners(){
        for(tv in valoresViews){
            tv.setOnClickListener {
                if (respuestaElegida != -1 && tv.text == "__") {
                    tv.text = nivelModel.posiblesRespuestas[respuestaElegida].toString()
                    respuestasView[respuestaElegida].setBackgroundColor(resources.getColor(R.color.white))
                    respuestaElegida = -1
                } else if(respuestaElegida != -1){
                    respuestasView[respuestaElegida].setBackgroundColor(resources.getColor(R.color.white))
                    Toast.makeText(applicationContext, "Si pero no", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun siguienteNivel(view: View){
        if(checarGanador()) {
            if(dificultad < 4) {
                historialNiveles.add(nivelModel)
                dificultad++
                setLevel()
            } else {
                Toast.makeText(applicationContext, "Fin del juego", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "Nel pa", Toast.LENGTH_SHORT).show()
        }
    }

    fun checarGanador(): Boolean{
        for(i in 0 until valoresViews.size){
            if(valoresViews[i].text == "__")
                return false
            if(valoresViews[i].text.toString() != nivelModel.valores[i])
                return false
        }
        return true
    }
}