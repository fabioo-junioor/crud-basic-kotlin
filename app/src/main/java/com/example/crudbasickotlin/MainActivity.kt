package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button_cad_novo: Button = findViewById(R.id.btn_cad_aluno)
        val button_update_aluno: Button = findViewById(R.id.btn_update)
        button_cad_novo.setOnClickListener{
            navegarParaTelaCadastro()

        }
        button_update_aluno.setOnClickListener{
            navegarParaTelaAtualizar()

        }
    }
    private fun navegarParaTelaCadastro(){
        val cadAluno = Intent(this, CadAluno::class.java)
        println("MUDOU PARA TELA CADASTRO")
        startActivity(cadAluno)

    }
    private fun navegarParaTelaAtualizar(){
        val atualizarAluno = Intent(this, AtualizarAluno::class.java)
        println("MUDOU PARA TELA ATUALIZAR")
        startActivity(atualizarAluno)

    }
}