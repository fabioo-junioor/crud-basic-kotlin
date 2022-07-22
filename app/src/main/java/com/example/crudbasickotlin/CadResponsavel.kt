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
import com.example.crudbasickotlin.databinding.ActivityCadAlunoBinding
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
    .create(CadResponsavel.enviarForm::class.java)

class CadResponsavel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadResponsavelBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSalvarCadResponsavel.setOnClickListener{
            salvarNovoResponsavel()

        }
    }
    private fun salvarNovoResponsavel(){
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

        }else {
            retrofit.setForm(responsavel.nome, responsavel.email, responsavel.cpf,).enqueue(object : Callback<Responsavel>{
                override fun onFailure(call: Call<Responsavel>, t: Throwable) {
                    Log.d("Erro: ", t.toString())

                }
                override fun onResponse(call: Call<Responsavel>, response: Response<Responsavel>) {
                    if(response.isSuccessful){
                        response.body()?.let{
                            if(response.body()!!.cpf.equals("achou")) {
                                println("Cpf já cadastrado!!")
                                binding.textView19.setTextColor(resources.getColor(R.color.red))
                                binding.textView19.text = "Cpf já cadastrado!!"
                                imprimeInputs(responsavel)

                            } else {
                                println("Cadastro Efetuado!!")
                                binding.textView19.setTextColor(resources.getColor(R.color.red))
                                binding.textView19.text = "Cadastro Efetuado!!"
                                imprimeInputs(responsavel)
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
        println("MUDOU PARA TELA BUSCAR!")
        startActivity(selectResponsavel)

    }
    private fun imprimeInputs(responsavel: Responsavel){
        println("Dados Informados pelo Usuario!!")
        println("Nome: " + responsavel.nome)
        println("Email: " + responsavel.email)
        println("Cpf: " + responsavel.cpf)

    }
    interface enviarForm{
        @FormUrlEncoded
        @POST("insert_responsavel.php")
        fun setForm(
            @Field("nome") nome: String,
            @Field("email") email: String,
            @Field("cpf") cpf: String,
        ): Call<Responsavel>
    }
}