package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button =findViewById<Button>(R.id.button)

        var bannerView=findViewById<AdView>(R.id.adView)
        MobileAds.initialise(this)
        val adRequest =AdRequest.Builder().build()
        bannerView=loadAd(adRequest)

    }
}




