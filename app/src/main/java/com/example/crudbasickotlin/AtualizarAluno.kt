package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudbasickotlin.databinding.ActivityAtualizarAlunoBinding
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

private lateinit var binding: ActivityAtualizarAlunoBinding

class AtualizarAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarAlunoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAtualizar.setOnClickListener{
            navegarParaTelaMain()

        }
    }
    private fun navegarParaTelaMain(){
        val listaAluno = Intent(this, MainActivity::class.java)
        println("MUDOU PARA TELA MAIN")
        startActivity(listaAluno)

    }
}