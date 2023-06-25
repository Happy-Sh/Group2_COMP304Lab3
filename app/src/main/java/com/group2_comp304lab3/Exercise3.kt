package com.group2_comp304lab3

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView

class Exercise3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise3)

        val btnRotate : Button = findViewById<View>(R.id.btnRotate) as Button
        btnRotate.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                performAnimation(R.anim.solarsystem)
            }
        })
    }
    private fun performAnimation(animationResourceID: Int){
        val sun : ImageView = findViewById<View>(R.id.imageSun) as ImageView
        val reusableImageView: ImageView = findViewById<View>(R.id.imageEarth) as ImageView
        sun.setImageResource(R.drawable.sun)
        val centerX = sun.x + sun.width / 2
        val centerY = sun.y + sun.height / 2

        val rotationAnimator = ObjectAnimator.ofFloat(
            reusableImageView,
            "rotation",
            0f,
            360f
        ).apply{
            duration = 4000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            interpolator = LinearInterpolator()
        }

        val pivotX = centerX - reusableImageView.x
        val pivoty = centerY - reusableImageView.y

        reusableImageView.pivotX = pivotX
        reusableImageView.pivotY = pivoty

        reusableImageView.setImageResource(R.drawable.earth)

        rotationAnimator.start()

        //val an: Animation = AnimationUtils.loadAnimation(this, animationResourceID)

        // an.setAnimationListener(MyAnimationListener())
        // an.repeatCount = ObjectAnimator.INFINITE
        //reusableImageView.startAnimation(an);
    }
}