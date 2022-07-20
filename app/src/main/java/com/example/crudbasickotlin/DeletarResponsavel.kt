package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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
        setContentView(R.layout.activity_deletar_responsavel)


    }
    private fun deletarResponsavel(){
        val responsavel = Responsavel()
        responsavel.nome = binding.inpCpfDelete.text.toString()
        responsavel.cpf = binding.inpCpfDelete.text.toString()
        responsavel.email = binding.inpCpfDelete.text.toString()
        if (TextUtils.isEmpty(responsavel.cpf)) {
            Toast.makeText(this, "Preencha o campo Cpf!", Toast.LENGTH_SHORT).show()

        } else {
            retrofit.setResponsavel(responsavel.nome, responsavel.email, responsavel.cpf).enqueue(object :
                Callback<Responsavel> {
                override fun onFailure(call: Call<Responsavel>, t: Throwable) {
                    Log.d("Erro: ", t.toString())

                }

                override fun onResponse(call: Call<Responsavel>, response: Response<Responsavel>) {
                    if(response.isSuccessful){
                        response.body()?.let{
                            if(response.body()!!.cpf.equals("vazio")){
                                println("Cpf não encontrada!!")
                                binding.textView22.setTextColor(resources.getColor(R.color.red))
                                binding.textView22.text = "Cpf não encontrada!!"

                            } else {
                                println("Responsavel excluido!!")
                                binding.textView22.setTextColor(resources.getColor(R.color.red))
                                binding.textView22.text = "Responsavel excluido!!"/*
                                Timer().schedule(2000){
                                    navegarParaTelaResponsavel()

                                }*/
                            }
                        }
                    }
                }
            })
        }
    }
    private fun navegarParaTelaResponsavel(){
        val listaAluno = Intent(this, MainActivity::class.java)
        println("MUDOU PARA TELA RESPONSAVEL")
        startActivity(listaAluno)

    }
    interface deletarResponsavel{
        @FormUrlEncoded
        @POST("delete_responsavel.php")
        fun setResponsavel(
            @Field("nome") nome: String,
            @Field("email") email: String,
            @Field("cpf") cpf: String
        ): Call<Responsavel>
    }
}