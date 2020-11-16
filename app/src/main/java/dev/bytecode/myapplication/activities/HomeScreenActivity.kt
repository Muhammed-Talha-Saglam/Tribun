package dev.bytecode.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.bytecode.myapplication.R

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
    }
}