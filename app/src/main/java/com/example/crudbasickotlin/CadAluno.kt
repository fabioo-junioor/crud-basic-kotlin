package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cad_aluno.*
import java.util.*
import kotlin.concurrent.schedule

class CadAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cad_aluno)

        val button_salvar_aluno: Button = findViewById(R.id.btn_salvar_cad_aluno)
        button_salvar_aluno.setOnClickListener{
            salvarNovoAluno()

        }
    }
    private fun salvarNovoAluno(){
        val nome = inp_nome_cad.text.toString()
        val matricula = inp_matricula_cad.text.toString()
        val responsavel = inp_responsavel_cad.text.toString()
        if (TextUtils.isEmpty(nome)) {
            Toast.makeText(this, "Preencha o campo Nome!", Toast.LENGTH_SHORT).show()
            println("Nome:" + nome)

        }else if (TextUtils.isEmpty(matricula)) {
            Toast.makeText(this, "Preencha o campo Matricula!", Toast.LENGTH_SHORT).show()
            println("Matricula:" + matricula)

        }else if (TextUtils.isEmpty(responsavel)) {
            Toast.makeText(this, "Preencha o campo Responsavel!", Toast.LENGTH_SHORT).show()
            println("Responsavel:" + responsavel)

        }else {
            msg_cadastrado.setTextColor(resources.getColor(R.color.red))
            msg_cadastrado.text = "Cadastrado com Sucesso!!"
            Timer().schedule(2000){
                navegarParaTelaMain()

            }
        }
    }
    private fun navegarParaTelaMain(){
        val listaAluno = Intent(this, MainActivity::class.java)
        println("MUDOU PARA TELA MAIN")
        startActivity(listaAluno)


    }
}