package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudbasickotlin.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.schedule

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Timer().schedule(3000){
            navegarParaTelaBuscaAluno()

        }
    }
    private fun navegarParaTelaBuscaAluno(){
        val buscaAluno = Intent(this, BuscaAluno::class.java)
        println("MUDOU PARA TELA BUSCAR ALUNO!")
        startActivity(buscaAluno)

    }
}