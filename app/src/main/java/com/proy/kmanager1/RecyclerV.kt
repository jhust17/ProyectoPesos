package com.proy.kmanager1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proy.kmanager1.io.ApiService
import com.proy.kmanager1.ui.MainActivity
import com.proy.kmanager1.util.PreferenceHelper
import com.proy.kmanager1.util.PreferenceHelper.get
import com.proy.kmanager1.util.PreferenceHelper.set
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecyclerV : AppCompatActivity() {
    private lateinit var adapter: ListElementAdapter
    private lateinit var etFilter: EditText
    var id: Int =0
    private val apiService: ApiService by lazy {
        ApiService.create()
    }
    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_v)
        initRecyclerView()
        etFilter = findViewById(R.id.etFilter)
        etFilter.addTextChangedListener { userfilter ->
            val userlistFilter = ListElementProvider.userelelist.filter { listuser ->
                listuser.name.lowercase().contains(userfilter.toString().lowercase())
            }
            adapter.updateListUsers(userlistFilter)
        }
        userGet()

    }



    private fun getRetrofit(): Retrofit {
        val Base_url ="https://private-47aaaa-jhust17.apiary-mock.com/"
        return Retrofit.Builder()
            .baseUrl(Base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun userGet() {
        val call: Call<List<ListElement>> = getRetrofit().create(APIService::class.java).getUsers()
        call.enqueue(object : Callback<List<ListElement>> {
            override fun onResponse(
                call: Call<List<ListElement>>,
                response: Response<List<ListElement>>
            ) {
                if (response.isSuccessful){
                    val user:List<ListElement>? = response.body()
                    val users: List<ListElement> = user?: emptyList()
                    ListElementProvider.userelelist.clear()
                    ListElementProvider.userelelist.addAll(users)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<ListElement>>, t: Throwable) {
                showError()
            }
        })

    }
    private fun showError() {
        Toast.makeText(this, "Error no carga", Toast.LENGTH_SHORT).show()
    }



    private fun initRecyclerView() {
        adapter = ListElementAdapter(ListElementProvider.userelelist) { listElement ->
            onItemSelected(listElement)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.listRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    fun onItemSelected(listElement: ListElement) {
        val intent = Intent(this@RecyclerV, DetalleU::class.java)
        intent.putExtra("Nombre", listElement.name)
        intent.putExtra("Edad", listElement.age)
        intent.putExtra("Altura", listElement.height)
        intent.putExtra("Peso", listElement.weight)
        intent.putExtra("Genero", listElement.gender)
        intent.putExtra("Fuma", listElement.smoke)
        startActivity(intent)

    }


    fun Ausers(view: View) {
        val i = Intent(this, RegProfileActivity::class.java)
        startActivity(i)
    }

    fun Logout(view: View) {
        performLogout()
    }
    private fun gotoLogin(){
        val i = Intent(this, MainActivity::class.java)

        startActivity(i)
        finish()
    }

    private fun performLogout(){
        val body = preferences["body", ""]
        val call = apiService.postLogout("Bearer $body")
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                clearSessionPreference()
                gotoLogin()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, "Error en el servidor", Toast.LENGTH_SHORT).show()
            }

        })

    }
    private fun clearSessionPreference(){
        preferences["body"] = ""
    }
}
/*
 private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://private-47aaaa-jhust17.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun userGet() {
        val call: Call<List<ListElement>> = getRetrofit().create(APIService::class.java).getUsers()
        call.enqueue(object : Callback<List<ListElement>> {
            override fun onResponse(
                call: Call<List<ListElement>>,
                response: Response<List<ListElement>>
            ) {
                if (response.isSuccessful){
                    val user:List<ListElement>? = response.body()
                    val users: List<ListElement> = user?: emptyList()
                    ListElementProvider.userelelist.addAll(users)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<ListElement>>, t: Throwable) {
                showError()
            }
        })

    }
    private fun showError() {
        Toast.makeText(this, "Error no carga", Toast.LENGTH_SHORT).show()
    }*/
/*val retrofit = Retrofit.Builder()
    .baseUrl("http://127.0.0.1:3305/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()



private fun userGet() {
    val service = retrofit.create(APIService::class.java)
    val call = service.getUsers()

    call.enqueue(object : Callback<List<ListElement>> {
        override fun onResponse(call: Call<List<ListElement>>, response: Response<List<ListElement>>) {
            if (response.isSuccessful) {
                val list:List<ListElement>? = response.body()
                val users:List<ListElement> = list?: emptyList()
                ListElementProvider.userelelist.addAll(users)
                adapter.notifyDataSetChanged()

            } else {
                showError()
            }
        }
        override fun onFailure(call: Call<List<ListElement>>, t: Throwable) {
            showError()
        }
    })

}
private fun showError() {
    Toast.makeText(this, "Error no carga", Toast.LENGTH_SHORT).show()
}*/
/*
private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://127.0.0.1:3305/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
private fun userGet() {
    CoroutineScope(Dispatchers.IO).launch {
        val call: Response<List<ListElement>> = getRetrofit().create(APIService::class.java).getUsers()
        val p: List<ListElement>? = call.body()
        runOnUiThread {
            if (call.isSuccessful) {

                val users: List<ListElement> = p?: emptyList()
                ListElementProvider.userelelist.clear()
                ListElementProvider.userelelist.addAll(users)
                adapter.notifyDataSetChanged()
            } else {
                showError()
            }
        }
    }

}
private fun showError() {
    Toast.makeText(this, "Error no carga", Toast.LENGTH_SHORT).show()
}*/
