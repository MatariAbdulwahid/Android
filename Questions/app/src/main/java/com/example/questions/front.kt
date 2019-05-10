package com.example.questions

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import kotlinx.android.synthetic.main.activity_front.*

class front : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_front)

       btnQues.setOnClickListener {
           var i = Intent(this , Second::class.java)
           startActivity(i)
       }

        btnRateApp.setOnClickListener {
           try {
               var uri = Uri.parse("market://details?id $packageName")
               var goToMarktet = Intent(Intent.ACTION_VIEW)
               goToMarktet.data=uri
               startActivity(goToMarktet)
           } catch (ex:Exception){
               var uri = Uri.parse("http//play.google.com/store/apps/details?$packageName")
               var goToMarktet = Intent(Intent.ACTION_VIEW)
               goToMarktet.data=uri
               startActivity(goToMarktet)
           }
        }
    }
}
