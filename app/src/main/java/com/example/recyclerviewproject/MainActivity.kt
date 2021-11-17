package com.example.recyclerviewproject

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewproject.adapters.RecyclerAdapter
import com.example.recyclerviewproject.databinding.ActivityMainBinding
import com.example.recyclerviewproject.network.DataApi
import com.example.recyclerviewproject.network.RemoteDataSource
import com.example.recyclerviewproject.network.Resource
import com.example.recyclerviewproject.repository.DataRepository
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    //    private val repository:DataRepository
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: DataViewModel
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var sharedPref = getSharedPreferences(R.string.data_path.toString(), MODE_PRIVATE)

        val gson = Gson()
        val Json = sharedPref.getString("Saved Data",null)

        if (Json != null) {
            Log.d("Shared Pref", Json)
        }else{
            Log.d("Shared Pref", "JSON EMPTY")
        }

        Log.d("LifeCycle","Inside OnCreate")

        recyclerAdapter = RecyclerAdapter(this)
        binding.recyclerView.adapter=recyclerAdapter

        if(Json!=null && recyclerAdapter.itemCount==0){
            recyclerAdapter.setDataUsingSharedPref(Json)
        }
        val factory = ViewModelFactory(DataRepository(RemoteDataSource.buildApi(DataApi::class.java)))
        viewModel = ViewModelProvider(this, factory).get(DataViewModel::class.java)

        viewModel.incomingData.observe(this, Observer {
            Log.d("Internet Connectivity","${isOnline()}")
            when(it){
                is Resource.Success -> {
                    recyclerAdapter.setData(it.value.data)
                }
            }
        })

        binding.buttonFetch.setOnClickListener {
            Log.i("Button Hit","Button Hit")
            viewModel.getDataFromApi()
        }
        binding.clearButton.setOnClickListener{
            recyclerAdapter.clearData()
        }
    }

    fun isOnline(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}