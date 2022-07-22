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
import com.example.crudbasickotlin.databinding.ActivityBuscaAlunoBinding
import com.example.crudbasickotlin.databinding.ActivityBuscarResponsavelBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

private lateinit var binding: ActivityBuscarResponsavelBinding
private val gson = GsonBuilder().setLenient().create()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl("http://10.0.2.2/backendProjetoKotlin/")
    .build()
    .create(BuscarResponsavel.buscarResponsavel::class.java)
class BuscarResponsavel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscarResponsavelBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnBuscaResponsavel.setOnClickListener{
            mostraLista()

        }
    }
    private fun mostraLista(){
        val responsavel = Responsavel()
        responsavel.nome = ""
        responsavel.email = ""
        responsavel.cpf = binding.inpBuscaCpf.text.toString()
        if (TextUtils.isEmpty(responsavel.cpf)) {
            Toast.makeText(this, "Preencha o campo Cpf!", Toast.LENGTH_SHORT).show()

        }else {
            retrofit.getResponsavel(responsavel.nome, responsavel.email, responsavel.cpf).enqueue(object :
                Callback<Responsavel> {
                override fun onFailure(call: Call<Responsavel>, t: Throwable) {
                    Log.d("ErrO: ", t.toString())

                }
                override fun onResponse(call: Call<Responsavel>, response: Response<Responsavel>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            if (response.body()!!.cpf.equals("vazio")) {
                                println("Responsavel não encontrado!!")
                                binding.textView28.setTextColor(resources.getColor(R.color.red))
                                binding.textView28.text = "Responsavel não encontrado!!"

                            } else {
                                println("Responsavel encontrado!!")
                                binding.textView28.setTextColor(resources.getColor(R.color.red))
                                binding.textView28.text = "Nome: " + response.body()!!.nome +
                                        "\nEmail: " + response.body()!!.email +
                                        "\nCpf: " + response.body()!!.cpf

                            }
                        }
                    }
                }
            })
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
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
    interface buscarResponsavel{
        @FormUrlEncoded
        @POST("select_responsavel.php")
        fun getResponsavel(
            @Field("nome") nome: String,
            @Field("email") email: String,
            @Field("cpf") cpf: String
        ): Call<Responsavel>
    }
}