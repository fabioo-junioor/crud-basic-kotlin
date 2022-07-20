package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudbasickotlin.databinding.ActivityCadResponsavelBinding

private lateinit var binding: ActivityCadResponsavelBinding
class CadResponsavel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadResponsavelBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.btnSalvarCadResponsavel.setOnClickListener{
            navegarParaTelaCadAluno()

        }
    }
    private fun navegarParaTelaCadAluno(){
        val cadAluno = Intent(this, CadAluno::class.java)
        println("MUDOU PARA TELA CADASTRO ALUNO!")
        startActivity(cadAluno)

    }
}