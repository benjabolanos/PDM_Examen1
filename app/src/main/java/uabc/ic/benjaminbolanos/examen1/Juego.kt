package uabc.ic.benjaminbolanos.examen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.floor

class Juego : AppCompatActivity() {

    //JuegoModel
    private lateinit var juegoModel: JuegoModel

    //JuegoView
    private var valoresViews = ArrayList<TextView>()
    private var respuestasViews =  ArrayList<TextView>()

    //Control
    private var respuestaElegida = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nivel)
        juegoModel = JuegoModel(this)
        initView()
        setValoresOnClickListeners()
        setRespuestasOnClickListeners()
        setLevel()
    }

    /**
     * Función para tomar los valores de los niveles y mostrarlos en los diferentes Views de
     * la actividad.
     */
    fun setLevel(){
        for((i,tv) in valoresViews.withIndex()){
            tv.text = juegoModel.niveles[floor((i.toDouble()/6)).toInt()].valores[i%6]
        }

        for(i in 0 until 3) {
            for (j in juegoModel.niveles[i].posicionIncognitas) {
                valoresViews[(i*6) + j].text = "_"
            }
        }

        for((i,tv) in respuestasViews.withIndex()){
            tv.text = juegoModel.niveles[floor((i.toDouble()/3)).toInt()].posiblesRespuestas[i%3]
        }
    }

    /**
     * Función para guardar las views de los valores y respuestas en arrays para acceder a ellos
     * de manera fácil.
     */
    private fun initView(){
        val valoresIDs = arrayOf(R.id.juego_nivel0_valor0, R.id.juego_nivel0_valor1, R.id.juego_nivel0_valor2,
            R.id.juego_nivel0_valor3, R.id.juego_nivel0_valor4, R.id.juego_nivel0_valor5,
            R.id.juego_nivel1_valor0, R.id.juego_nivel1_valor1, R.id.juego_nivel1_valor2,
            R.id.juego_nivel1_valor3, R.id.juego_nivel1_valor4, R.id.juego_nivel1_valor5,
            R.id.juego_nivel2_valor0, R.id.juego_nivel2_valor1, R.id.juego_nivel2_valor2,
            R.id.juego_nivel2_valor3, R.id.juego_nivel2_valor4, R.id.juego_nivel2_valor5)
        for(id in valoresIDs){
            valoresViews.add(findViewById(id))
        }

        val respuestasIDs = arrayOf(R.id.juego_nivel0_respuesta0, R.id.juego_nivel0_respuesta1, R.id.juego_nivel0_respuesta2,
            R.id.juego_nivel1_respuesta0, R.id.juego_nivel1_respuesta1, R.id.juego_nivel1_respuesta2,
            R.id.juego_nivel2_respuesta0, R.id.juego_nivel2_respuesta1, R.id.juego_nivel2_respuesta2)
        for(id in respuestasIDs){
            respuestasViews.add(findViewById(id))
        }

        findViewById<Button>(R.id.juego_reiniciar_boton).setOnClickListener {
            setLevel()
        }
    }

    /**
     * Función que crea los ClickListeners para los textviews de las respuestas.
     */
    private fun setRespuestasOnClickListeners(){
        for((i,tv) in respuestasViews.withIndex()){
            tv.setOnClickListener {
                respuestaElegida = if(respuestaElegida == -1) {
                    tv.setBackgroundColor(resources.getColor(R.color.fondo_secundario,null))
                    i
                } else {
                    respuestasViews[respuestaElegida].setBackgroundColor(resources.getColor(R.color.fondo, null))
                    -1
                }
            }
        }
    }

    /**
     * Función que crea los ClickListeners para los textviews de los valores.
     */
    private fun setValoresOnClickListeners(){
        for((i,tv) in valoresViews.withIndex()){
            tv.setOnClickListener {
                if (respuestaElegida != -1 && tv.text == "_"){
                    tv.text = juegoModel.niveles[floor((i.toDouble()/6)).toInt()].posiblesRespuestas[respuestaElegida%3]
                    respuestasViews[respuestaElegida].setBackgroundColor(resources.getColor(R.color.fondo, null))
                    respuestaElegida = -1
                } else if(respuestaElegida != -1){
                    respuestasViews[respuestaElegida].setBackgroundColor(resources.getColor(R.color.fondo, null))
                }
            }
        }
    }

    /**
     * Función para avanzar al siguiente turno, debe haber ganador para que pueda avanzar.
     * Si llega al final del juego, muestra los resultados.
     */
    fun siguienteTurno(view: View){
        if(checarGanador()) {
            if(juegoModel.avanzarTurno()) {
                setLevel()
            } else {
                visualizarArchivo(view)
                Toast.makeText(applicationContext, "Fin del juego", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "Respuestas incorrectas", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Función para checar si los tres niveles están correctos, en ese caso se dice que si hay
     * ganador y retorna true.
     * @return True si las tres series están correctas.
     */
    fun checarGanador(): Boolean{
        val serieAVerificar = Array<ArrayList<String>>(3){ArrayList()}
        var index = 0
        for(i in 0 until 3){
            for(j in 0 until 6){
                serieAVerificar[i].add(valoresViews[index++].text.toString())
            }
        }
        return juegoModel.verificarSeries(serieAVerificar)
    }

    /**
     * Función para ir a la Actividad de resultados y mandar el archivo de juego actual para
     * mostrarlo.
     */
    fun visualizarArchivo(view: View){
        val intent = Intent(this, Resultados::class.java)
        intent.putExtra("Archivo",juegoModel.archivoJuego.archivo)
        startActivity(intent)
    }
}