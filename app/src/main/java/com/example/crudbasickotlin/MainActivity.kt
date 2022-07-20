package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.crudbasickotlin.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

private lateinit var binding: ActivityMainBinding
private val gson = GsonBuilder().setLenient().create()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl("http://10.0.2.2/backendProjetoKotlin/")
    .build()
    .create(MainActivity.mostrarAluno::class.java)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mostraLista()

        binding.btnCadAluno.setOnClickListener{
            navegarParaTelaCadastro()

        }
        binding.btnUpdateAluno.setOnClickListener {
            navegarParaTelaAtualizar()

        }
        binding.btnDeletarAluno.setOnClickListener{
            navegarParaTelaDeletar()

        }
    }

    private fun mostraLista(){
        val aluno = Aluno()
        aluno.matricula = ""
        aluno.nome = ""
        aluno.cpf = ""
        aluno.responsavel = ""
        retrofit.getAluno(aluno.matricula, aluno.nome, aluno.cpf, aluno.responsavel).enqueue(object : Callback<Aluno> {
            override fun onFailure(call: Call<Aluno>, t: Throwable) {
                Log.d("ErrO: ", t.toString())

            }

            override fun onResponse(call: Call<Aluno>, response: Response<Aluno>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (response.body()!!.cpf.equals("vazio")) {
                            println("Aluno não encontrado!!")
                            binding.textViewLista.text = response.body()!!.nome.toString()

                        } else {
                            println("Aluno encontrado!!")
                            binding.textViewLista.text = "teste"

                        }
                    }
                }
            }
        })
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
    private fun navegarParaTelaDeletar(){
        val deletarAluno = Intent(this, DeletarAluno::class.java)
        println("MUDOU PARA TELA DELETAR!")
        startActivity(deletarAluno)

    }
    interface mostrarAluno{
        @FormUrlEncoded
        @POST("select_aluno.php")
        fun getAluno(
            @Field("matricula") matricula: String,
            @Field("nome") nome: String,
            @Field("cpf") cpf: String,
            @Field("responsavel") responsavel: String
        ): Call<Aluno>
    }
}