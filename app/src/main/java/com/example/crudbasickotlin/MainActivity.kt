package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudbasickotlin.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCadAluno.setOnClickListener{
            navegarParaTelaCadastro()

        }
        binding.btnUpdate.setOnClickListener {
            navegarParaTelaAtualizar()

        }
    }
    private fun navegarParaTelaCadastro(){
        val cadAluno = Intent(this, CadAluno::class.java)
        println("MUDOU PARA TELA CADASTRO!")
        startActivity(cadAluno)

    }
    private fun navegarParaTelaAtualizar(){
        val atualizarAluno = Intent(this, AtualizarAluno::class.java)
        println("MUDOU PARA TELA ATUALIZAR!")
        startActivity(atualizarAluno)

    }

}