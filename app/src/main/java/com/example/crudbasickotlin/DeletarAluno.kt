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
        aluno.responsavel = ""
        aluno.cpf = ""
        aluno.nome = ""
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
                                    navegarParaTelaBuscarAluno()

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
    private fun navegarParaTelaBuscarAluno(){
        val selectAluno = Intent(this, BuscaAluno::class.java)
        println("MUDOU PARA TELA BUSCAR")
        startActivity(selectAluno)

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