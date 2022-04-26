package uabc.ic.benjaminbolanos.examen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

    fun goToLevel(view: View){
        val intent = Intent(this, Juego::class.java)
        startActivity(intent)
    }
}