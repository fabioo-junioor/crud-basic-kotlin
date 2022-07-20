package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.crudbasickotlin.databinding.ActivityCadResponsavelBinding
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

private lateinit var binding: ActivityCadResponsavelBinding
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("http://10.0.2.2/backendProjetoKotlin/")
    .build()
    .create(CadResponsavel.enviaForm::class.java)
class CadResponsavel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadResponsavelBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.btnSalvarCadResponsavel.setOnClickListener{
            salvaNovoResponsavel()

        }
    }
    private fun salvaNovoResponsavel(){
        val responsavel = Responsavel()
        responsavel.nome = binding.inpNomeCad.text.toString()
        responsavel.email = binding.inpEmailCad.text.toString()
        responsavel.cpf = binding.inpCpfCad.text.toString()
        if (TextUtils.isEmpty(responsavel.nome)) {
            Toast.makeText(this, "Preencha o campo Nome!", Toast.LENGTH_SHORT).show()

        }else if (TextUtils.isEmpty(responsavel.email)) {
            Toast.makeText(this, "Preencha o campo Email!", Toast.LENGTH_SHORT).show()

        }else if(TextUtils.isEmpty((responsavel.cpf))){
            Toast.makeText(this, "Preencha o campo Cpf!", Toast.LENGTH_SHORT).show()

        } else {
            retrofit.setForm(responsavel.nome, responsavel.email, responsavel.cpf).enqueue(object :
                Callback<Responsavel> {
                override fun onFailure(call: Call<Responsavel>, t: Throwable) {
                    Log.d("Erro: ", t.toString())

                }

                override fun onResponse(call: Call<Responsavel>, response: Response<Responsavel>) {
                    if(response.isSuccessful){
                        response.body()?.let{
                            if(response.body()!!.cpf.equals("achou")) {
                                println("Responsavel já existe!!")
                                binding.textView19.setTextColor(resources.getColor(R.color.red))
                                binding.textView19.text = "Responsavel já existe!!"
                                imprimeInputs(responsavel)

                            } else {
                                println("Cadastro realizado!!")
                                binding.textView19.setTextColor(resources.getColor(R.color.red))
                                binding.textView19.text = "Cadastro realizado!!"
                                imprimeInputs(responsavel)
                                Timer().schedule(2000){
                                    navegarParaTelaResponsavel()

                                }
                            }
                        }
                    }
                }
            })
        }
    }
    private fun navegarParaTelaResponsavel(){
        val listaAluno = Intent(this, MainActivity::class.java)
        println("MUDOU PARA TELA MAIN")
        startActivity(listaAluno)

    }
    private fun imprimeInputs(responsavel: Responsavel){
        println("Dados Informados pelo Responsavel!!")
        println("Nome: " + responsavel.nome)
        println("Email: " + responsavel.email)
        println("Cpf: " + responsavel.cpf)

    }
    interface enviaForm{
        @FormUrlEncoded
        @POST("insert_responsavel.php")
        fun setForm(
            @Field("nome") nome: String,
            @Field("email") email: String,
            @Field("cpf") cpf: String
        ): Call<Responsavel>
    }
}