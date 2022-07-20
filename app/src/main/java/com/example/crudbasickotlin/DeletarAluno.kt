package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudbasickotlin.databinding.ActivityDeletarAlunoBinding

private lateinit var binding: ActivityDeletarAlunoBinding
class DeletarAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeletarAlunoBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.btnDeletarAluno.setOnClickListener{
            navegarParaTelaMain()

        }
    }

    private fun navegarParaTelaMain(){
        val listaAluno = Intent(this, MainActivity::class.java)
        println("MUDOU PARA TELA MAIN")
        startActivity(listaAluno)

    }
}