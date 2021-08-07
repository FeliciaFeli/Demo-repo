package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.view.View

//imports....
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.mobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener





class MainActivity : AppCompatActivity() {
    private var mLoadAdButton:Button?=null
    private var mInterstitialAd:InterstitialAd?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create the InterstitialAd and setthe adUnitId(defined in values /strings.xml)
        mInterstitialAd=newInterstitialAd()
        loadInterstitial()
        //Create the load ad button ,tries to show an interstitial when clicked
        mLoadAdButton= findViewById<Button>(R.id.button) as Button
        mLoadAdButton!!.isEnabled=false
        mLoadAdButton!!.setOnClickListener {
            showInterstitial()
        }
            //operations to be performed when user tap on the button



}
       private fun newInterstitialAd():InterstitialAd{
           val interstitialAd=InterstitialAd(this)
           interstitialAd.adUnitId=getString(R.string.interstitial_ad_unit_id)
           interstitialAd.adListener=object:AdListener(){
               override fun onAdLoaded=true
               Toast.makeText( applicationContext," Ad Loaded,Toast.LENGTH_SHORT").show()
           }

           override fun onAdFailedToLoad(errorCode:int){
               mLoadAdButton!!.isEnabled=true
               Toast.makeText( applicationContext," Ad Failed To Load,Toast.LENGTH_SHORT").show()

           }
           override fun onAdClosed(){
               //proceed to the next level
               //go to the next level
               Toast.makeText( applicationContext," Ad Closed,Toast.LENGTH_SHORT").show()
               tryToLoadAdOnceAgain()
           }
           return interstitialAd
       }
       private fun loadInterstitial(){
           //Disable the load ad button and load the ad
           mLoadAdButton!!!.isEnabled=false
           val adRequest = AdRequest.Builder().build()
           mInterstitialAd!!.loadAd(adRequest)
       }

     private fun showInterstitial(){
         //show the ad if it is ready.otherwise toast and relaod the ad
         if (mInterstitialAd!=null&& mInterstitialAd!!.isLoaded){
             mInterstitialAd!!.show()
         }
         else {
             Toast.makeText(this,"Ad did not load" ,Toast.LENGTH_SHORT).show()
             tryToLoadAdOnceAgain
         }
     }
     private fun tryToLoadAdOnceAgain{
         mInterstitialAd=newInterstitialAd()
         loadInterstitial()
     }
}






