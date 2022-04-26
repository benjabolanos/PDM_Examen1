package uabc.ic.benjaminbolanos.examen1

import android.content.Context
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ArchivoJuego {

    private lateinit var nombreArchivo: String

    init {
        crearNombreArchivo()
    }

    private fun crearNombreArchivo(){
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH:mm", Locale.getDefault())
        nombreArchivo = "niveles_examen1_${formatter.format(date)}.txt"
    }

    fun guardarInfoNiveles(context: Context, niveles: ArrayList<Nivel>, turno:Int){
        try{
            val fileOutPutStream: FileOutputStream = context.openFileOutput(nombreArchivo,
                Context.MODE_APPEND or Context.MODE_PRIVATE)
            val outputWriter = OutputStreamWriter(fileOutPutStream)
            outputWriter.append(formatInfoNiveles(niveles, turno))
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun formatInfoNiveles(niveles: ArrayList<Nivel>, turno: Int, segundosRestantes: Int): String{
        var info = String()

        info += "TURNO $turno DIFICULTAD ${niveles[0].dificultad}"

        for((i,niv) in niveles.withIndex()){
            info += "Nivel $i - Valores: ${niv.valores} - Tiempo restante: ${segundosRestantes}s\n"
        }
        info += "\n------------------------"
        return info
    }


}