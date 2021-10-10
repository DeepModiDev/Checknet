package com.deepmodi.checknet.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.deepmodi.checknet.MainActivity
import com.deepmodi.checknet.R
import com.deepmodi.checknet.databinding.ActivitySplashScreenBinding
import com.deepmodi.checknet.utils.ConnectivityStatus

class SplashScreen : AppCompatActivity() {

     val binding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        checkConnectivity()

    }

    private fun checkConnectivity(){
        val connectivity = ConnectivityStatus(this)
        connectivity.observe(this, Observer {
            isConnected ->
            if(isConnected){
                binding.message = "Internet is connected!!!"
                binding.internetStatusTV.setBackgroundColor(ContextCompat.getColor(this,R.color.internet_conn_green))
                Handler(Looper.myLooper()!!).postDelayed({
                    binding.networkStatus = true
                    startNextActivity()
                    finish()
                },2000)
            }else{
                binding.networkStatus = false
                binding.message = "No internet connection!!!"
                binding.internetStatusTV.setBackgroundColor(ContextCompat.getColor(this,R.color.internet_conn_red))
            }
        })
    }

    private fun startNextActivity(){
        Intent(this,MainActivity::class.java).apply {
            startActivity(this)
        }
    }

}