package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.crudbasickotlin.databinding.ActivityAtualizarAlunoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.util.*
import kotlin.concurrent.schedule

private lateinit var binding: ActivityAtualizarAlunoBinding
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("http://10.0.2.2/backendProjetoKotlin/")
    .build()
    .create(AtualizarAluno.updateForm::class.java)

class AtualizarAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarAlunoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAtualizar.setOnClickListener{
            atualizaDadosAluno()

        }
    }
    private fun atualizaDadosAluno(){
        val aluno = Aluno()
        aluno.nome = binding.inpNomeAtualiza.text.toString()
        aluno.matricula = binding.inpMatriculaAtualiza.text.toString()
        aluno.cpf = binding.inpCpfAtualiza.text.toString()
        aluno.responsavel = binding.inpResponsavelAtualiza.text.toString()
        if (TextUtils.isEmpty(aluno.nome)) {
            Toast.makeText(this, "Preencha o campo Nome!", Toast.LENGTH_SHORT).show()

        }else if (TextUtils.isEmpty(aluno.matricula)) {
            Toast.makeText(this, "Preencha o campo Matricula!", Toast.LENGTH_SHORT).show()

        }else if(TextUtils.isEmpty((aluno.cpf))){
            Toast.makeText(this, "Preencha o campo Cpf!", Toast.LENGTH_SHORT).show()

        }else if (TextUtils.isEmpty(aluno.responsavel)) {
            Toast.makeText(this, "Preencha o campo Responsavel!", Toast.LENGTH_SHORT).show()

        } else {
            retrofit.setForm(aluno.matricula, aluno.nome, aluno.cpf, aluno.responsavel).enqueue(object :
                Callback<Aluno> {
                override fun onFailure(call: Call<Aluno>, t: Throwable) {
                    Log.d("Erro: ", t.toString())

                }

                override fun onResponse(call: Call<Aluno>, response: Response<Aluno>) {
                    if(response.isSuccessful){
                        response.body()?.let{
                            if(response.body()!!.matricula.equals("vazio")) {
                                println("Aluno não cadastrado!!")
                                binding.textView15.setTextColor(resources.getColor(R.color.red))
                                binding.textView15.text = "Aluno não cadastrado!!"
                                imprimeInputs(aluno)

                            }else if(response.body()!!.responsavel.equals("vazio")){
                                println("Responsavel não cadastrado!!")
                                binding.textView15.setTextColor(resources.getColor(R.color.red))
                                binding.textView15.text = "Responsavel não cadastrado!!"
                                imprimeInputs(aluno)

                            } else {
                                println("Atualização Efetuada!!")
                                binding.textView15.setTextColor(resources.getColor(R.color.red))
                                binding.textView15.text = "Atualização Efetuada!!"
                                imprimeInputs(aluno)
                                Timer().schedule(2000){
                                    navegarParaTelaMain()

                                }
                            }
                        }
                    }
                }
            })
        }
    }
    private fun navegarParaTelaMain(){
        val listaAluno = Intent(this, MainActivity::class.java)
        println("MUDOU PARA TELA MAIN")
        startActivity(listaAluno)

    }
    private fun imprimeInputs(aluno: Aluno){
        println("Dados Informados pelo Usuario!!")
        println("Nome: " + aluno.nome)
        println("Matricula: " + aluno.matricula)
        println("Cpf: " + aluno.cpf)
        println("Responsavel: " + aluno.responsavel)

    }
    interface updateForm{
        @FormUrlEncoded
        @POST("update_aluno.php")
        fun setForm(
            @Field("matricula") matricula: String,
            @Field("nome") nome: String,
            @Field("cpf") cpf: String,
            @Field("responsavel") responsavel: String
        ): Call<Aluno>
    }
}