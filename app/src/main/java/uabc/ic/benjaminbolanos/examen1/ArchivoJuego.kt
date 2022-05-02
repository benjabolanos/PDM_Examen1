package uabc.ic.benjaminbolanos.examen1

import android.content.Context
import java.io.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ArchivoJuego(val contexto: Context) {

    lateinit var archivo: File

    init {
        crearNombreArchivo()
    }

    private fun crearNombreArchivo(){
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm", Locale.getDefault())
        val nombreArchivo = "niveles_examen1_${formatter.format(date)}.txt"

        val path = contexto.filesDir
        val examenDir = File(path, "Examen1")
        examenDir.mkdir()
        archivo = File(examenDir, nombreArchivo)
    }

    fun guardarInfoNiveles(context: Context, niveles: ArrayList<Nivel>, turno:Int, tiempoRestante: Double){
        try{
            val fileWriter = FileWriter(archivo, true)
            val bufferedWriter = BufferedWriter(fileWriter)
            bufferedWriter.write(formatInfoNiveles(niveles, turno, tiempoRestante))
            bufferedWriter.close()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun formatInfoNiveles(niveles: ArrayList<Nivel>, turno: Int, segundosRestantes: Double): String{
        var info = "TURNO $turno DIFICULTAD ${niveles[0].dificultad} TIEMPO: ${String.format("%.2f",segundosRestantes)}s\n"

        for((i,niv) in niveles.withIndex()){
            info += "Nivel $i - Valores: ${niv.valores}\n"
        }
        info += "\n------------------------\n\n"
        return info
    }


}