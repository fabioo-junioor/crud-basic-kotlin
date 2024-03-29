package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.crudbasickotlin.databinding.ActivityDeletarAlunoBinding
import com.example.crudbasickotlin.databinding.ActivityDeletarResponsavelBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*
import kotlin.concurrent.schedule

private lateinit var binding: ActivityDeletarResponsavelBinding
private val gson = GsonBuilder().setLenient().create()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl("http://10.0.2.2/backendProjetoKotlin/")
    .build()
    .create(DeletarResponsavel.deletarResponsavel::class.java)
class DeletarResponsavel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeletarResponsavelBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.btnDeletarResponsavel.setOnClickListener{
            deletarResponsavel()

        }
    }
    private fun deletarResponsavel(){
        val responsavel = Responsavel()
        responsavel.nome = ""
        responsavel.email = ""
        responsavel.cpf = binding.inpCpfDelete.text.toString()
        if (TextUtils.isEmpty(responsavel.cpf)) {
            Toast.makeText(this, "Preencha o campo Cpf!", Toast.LENGTH_SHORT).show()

        } else {
            retrofit.setAluno(responsavel.nome, responsavel.email, responsavel.cpf).enqueue(object :
                Callback<Responsavel> {
                override fun onFailure(call: Call<Responsavel>, t: Throwable) {
                    Log.d("Erro: ", t.toString())

                }
                override fun onResponse(call: Call<Responsavel>, response: Response<Responsavel>) {
                    if(response.isSuccessful){
                        response.body()?.let{
                            if(response.body()!!.cpf.equals("vazio")) {
                                println("Cpf não encontrado!!")
                                binding.textView22.setTextColor(resources.getColor(R.color.red))
                                binding.textView22.text = "Cpf não encontrada!!"

                            }else if (response.body()!!.cpf.equals("achouID")){
                                println("Responsavel não pode ser excluido!!")
                                println("Existe um aluno atrelado a esse cadastro!!")
                                binding.textView22.setTextColor(resources.getColor(R.color.red))
                                binding.textView22.text = "Responsavel não pode ser excluido!!" +
                                                            "\nExiste um aluno atrelado a esse cadastro!!" +
                                                            "\nNome: " + response.body()!!.nome

                            } else {
                                println("Responsavel excluido!!")
                                binding.textView22.setTextColor(resources.getColor(R.color.red))
                                binding.textView22.text = "Responsavel excluido!!"
                                Timer().schedule(2000){
                                    navegarParaTelaBuscarResponsavel()

                                }
                            }
                        }
                    }
                }
            })
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.insertAluno -> {
                val insertAluno = Intent(this, CadAluno::class.java)
                println("MUDOU PARA TELA CADASTRO!")
                startActivity(insertAluno)
                true
            }
            R.id.updateAluno -> {
                val updateAluno = Intent(this, AtualizarAluno::class.java)
                println("MUDOU PARA TELA ATUALIZAR!")
                startActivity(updateAluno)
                true
            }
            R.id.deleteAluno -> {
                val deletaAluno = Intent(this, DeletarAluno::class.java)
                println("MUDOU PARA TELA DELETAR!")
                startActivity(deletaAluno)
                true
            }
            R.id.selectAluno -> {
                val selectAluno = Intent(this, BuscaAluno::class.java)
                println("MUDOU PARA TELA BUSCAR!")
                startActivity(selectAluno)
                true
            }
            R.id.insertResponsavel -> {
                val insertResponsavel = Intent(this, CadResponsavel::class.java)
                println("MUDOU PARA TELA CADASTRO!")
                startActivity(insertResponsavel)
                true
            }
            R.id.updateResponsavel -> {
                val updateResponsavel = Intent(this, AtualizarResponsavel::class.java)
                println("MUDOU PARA TELA ATUALIZAR!")
                startActivity(updateResponsavel)
                true
            }
            R.id.deleteResponsavel -> {
                val deletaResponsavel = Intent(this, DeletarResponsavel::class.java)
                println("MUDOU PARA TELA DELETAR!")
                startActivity(deletaResponsavel)
                true
            }
            R.id.selectResponsavel -> {
                val selectResponsavel = Intent(this, BuscarResponsavel::class.java)
                println("MUDOU PARA TELA BUSCAR!")
                startActivity(selectResponsavel)
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
    private fun navegarParaTelaBuscarResponsavel(){
        val selectResponsavel = Intent(this, BuscarResponsavel::class.java)
        println("MUDOU PARA TELA BUSCAR")
        startActivity(selectResponsavel)

    }
    interface deletarResponsavel{
        @FormUrlEncoded
        @POST("delete_responsavel.php")
        fun setAluno(
            @Field("nome") nome: String,
            @Field("email") email: String,
            @Field("cpf") cpf: String,
        ): Call<Responsavel>
    }
}