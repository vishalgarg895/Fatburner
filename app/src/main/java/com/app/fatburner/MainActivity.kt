package com.app.fatburner

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.app.fatburner.parser.loadProfiles
import com.app.fatburner.util.printLog
import com.app.fatburner.view.HeathTipCardView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder


class MainActivity : Activity() {

    private lateinit var mSwipeView: SwipePlaceHolderView
    private lateinit var mContext: Context
    private lateinit var bottomAddView: AdView
    private lateinit var topAddView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSwipeView = findViewById(R.id.swipeView)
        bottomAddView = findViewById(R.id.bottomAddView)
        topAddView = findViewById(R.id.topAddView)
        mContext = applicationContext

        mSwipeView.getBuilder<SwipePlaceHolderView,
                SwipeViewBuilder<SwipePlaceHolderView>>()
                .setDisplayViewCount(3)
                .setSwipeDecor(SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.health_tip_cardview_msg_layout)
                        .setSwipeOutMsgLayoutId(R.layout.health_tip_cardview_msg_layout))


        for (profile in loadProfiles(mContext)!!) {
            mSwipeView.addView<HeathTipCardView>(HeathTipCardView(mContext, profile, mSwipeView))
        }

        initMob(bottomAddView)
        initMob(topAddView)
//        findViewById(R.id.rejectBtn).setOnClickListener(object : View.OnClickListener() {
//            fun onClick(v: View) {
//                mSwipeView!!.doSwipe(false)
//            }
//        })
//
//        findViewById(R.id.acceptBtn).setOnClickListener(object : View.OnClickListener() {
//            fun onClick(v: View) {
//                mSwipeView!!.doSwipe(true)
//            }
//        })
    }

    public override fun onPause() {
        bottomAddView.pause()
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
        bottomAddView.resume()
    }

    public override fun onDestroy() {
        bottomAddView.destroy()
        super.onDestroy()
    }

    private fun initMob(adView: AdView) {
        // bottomAddView.adSize = AdSize.BANNER
        //bottomAddView.adUnitId = resources.getString(R.string.banner_home_footer)

        val adRequest = AdRequest.Builder()
                // Check the LogCat to get your test device ID
                .addTestDevice("93E5EB4E539E5785083AB824ACD90E9F")
                .build()

        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {}

            override fun onAdClosed() {
                printLog("Ad is closed!")
                Toast.makeText(applicationContext, "Ad is closed!", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                printLog("Ad failed to load! error code: $errorCode")
                Toast.makeText(applicationContext, "Ad failed to load! error code: $errorCode", Toast.LENGTH_SHORT).show()
            }

            override fun onAdLeftApplication() {
                printLog("Ad left application!")
                Toast.makeText(applicationContext, "Ad left application!", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                super.onAdOpened()
            }
        }

        printLog("Test Device Configuration = "+ adRequest.isTestDevice(mContext).toString())
        adView.loadAd(adRequest)
    }
}
