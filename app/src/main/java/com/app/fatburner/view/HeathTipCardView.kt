package com.app.fatburner.view

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState
import com.mindorks.placeholderview.annotations.swipe.SwipeInState
import com.mindorks.placeholderview.annotations.swipe.SwipeIn
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState
import com.mindorks.placeholderview.annotations.swipe.SwipeOut
import com.bumptech.glide.Glide
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.SwipePlaceHolderView
import android.widget.TextView
import com.app.fatburner.R
import com.app.fatburner.model.HealthTip
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.View

@Layout(R.layout.health_tip_cardview)
class HeathTipCardView(private val mContext: Context,
                       private val healthTipModel: HealthTip,
                       private val mSwipeView: SwipePlaceHolderView) {

    @View(R.id.healthTipPicture)
    private val profileImageView: ImageView? = null

    @View(R.id.tipTitleText)
    private val nameAgeTxt: TextView? = null

    @View(R.id.tipDescriptionText)
    private val locationNameTxt: TextView? = null

    @Resolve
    private fun onResolved() {
        Log.e("TAG",healthTipModel.image)
        Glide.with(mContext).load(healthTipModel.image).into(profileImageView)
        nameAgeTxt!!.setText(healthTipModel.title)
        locationNameTxt!!.setText(healthTipModel.description)
    }

    @SwipeOut
    private fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")
        mSwipeView.addView(this)
    }

    @SwipeCancelState
    private fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")
    }

    @SwipeIn
    private fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")
        mSwipeView.addView(this)
    }

    @SwipeInState
    private fun onSwipeInState() {
        Log.d("EVENT", "onSwipeInState")
    }

    @SwipeOutState
    private fun onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState")
    }
}