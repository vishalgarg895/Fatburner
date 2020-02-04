package com.app.fatburner.addmob

import android.app.Application
import android.util.Log
import com.app.fatburner.R
import com.google.android.gms.ads.MobileAds



class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // initialize the AdMob app
        Log.e("TAG", "Executed")
        MobileAds.initialize(this, resources.getString(R.string.admob_app_id))
    }

}