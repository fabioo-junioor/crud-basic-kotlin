package com.example.crudbasickotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.crudbasickotlin.databinding.ActivityDeletarAlunoBinding
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

private lateinit var binding: ActivityDeletarAlunoBinding
private val gson = GsonBuilder().setLenient().create()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl("http://10.0.2.2/backendProjetoKotlin/")
    .build()
    .create(DeletarAluno.deletarAluno::class.java)
class DeletarAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeletarAlunoBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.btnDeletarAluno.setOnClickListener{
            deletarAluno()

        }
    }


    private fun deletarAluno(){
        val aluno = Aluno()
        aluno.matricula = binding.inpMatriculaDelete.text.toString()
        aluno.responsavel = binding.inpMatriculaDelete.text.toString()
        aluno.cpf = binding.inpMatriculaDelete.text.toString()
        aluno.nome = binding.inpMatriculaDelete.text.toString()
        if (TextUtils.isEmpty(aluno.matricula)) {
            Toast.makeText(this, "Preencha o campo Matricula!", Toast.LENGTH_SHORT).show()

        } else {
            retrofit.setAluno(aluno.matricula, aluno.nome, aluno.cpf, aluno.responsavel).enqueue(object :
                Callback<Aluno> {
                override fun onFailure(call: Call<Aluno>, t: Throwable) {
                    Log.d("Erro: ", t.toString())

                }

                override fun onResponse(call: Call<Aluno>, response: Response<Aluno>) {
                    if(response.isSuccessful){
                        response.body()?.let{
                            if(response.body()!!.matricula.equals("vazio")){
                                println("Matricula não encontrada!!")
                                binding.textView9.setTextColor(resources.getColor(R.color.red))
                                binding.textView9.text = "Matricula não encontrada!!"

                            } else {
                                println("Aluno excluido!!")
                                binding.textView9.setTextColor(resources.getColor(R.color.red))
                                binding.textView9.text = "Aluno excluido!!"
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
    interface deletarAluno{
        @FormUrlEncoded
        @POST("delete_aluno.php")
        fun setAluno(
            @Field("matricula") matricula: String,
            @Field("nome") nome: String,
            @Field("cpf") cpf: String,
            @Field("responsavel") responsavel: String
        ): Call<Aluno>
    }
}