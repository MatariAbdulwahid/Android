package com.example.questions

import android.annotation.SuppressLint
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.support.annotation.RequiresApi
import android.view.View
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*

class Second : AppCompatActivity() {

    var list_ques:Array<String> ?=null
    var list_answer:Array<String> ?=null
    var index:Int = 0
    var TtoS :TextToSpeech?=null  //vari text to speech
    var res:Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE) //kein actionback
        setContentView(R.layout.activity_second)

        TtoS = TextToSpeech(
            this ,
            object : OnInitListener {
                override fun onInit(status: Int) {
                   res = TtoS!!.setLanguage(Locale.GERMAN)
                }

            }
        )

        list_ques= resources.getStringArray(R.array.questions)
        list_answer = resources.getStringArray(R.array.answer)

       // index = 0

        tv_question.text = "${list_ques!![index!!]}"
        tv_xx.text=(index!! +1).toString()
        tv_yy.text= "${list_ques!!.size +1}"
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun showData(v: View?){
                // index = 0
            when (v?.id){
                R.id.btnBack   -> {
                    try {
                    index -= 1

                        tv_question.text = "${list_ques!![index]}"
                        tv_xx.text = (index+ 1).toString() + "/"
                        tv_answer.text=""
                    } catch (ex: Exception){

                        index = list_ques!!.size -1
                    }
                }

                R.id.btnAnswer -> {
                    tv_answer.text = "${list_answer!![index]}"
                }

                R.id.btnNachst -> {
                    index += 1
                    try {
                        tv_question.text = "${list_ques!![index!!]}"
                        tv_xx.text = (index+ 1).toString() + "/"
                        tv_answer.text=""

                    } catch (ex :Exception) {

                        index = 0
                    }
                }

                R.id.btn_voice -> {  // function  speech
                    if(res == TextToSpeech.LANG_NOT_SUPPORTED || res == TextToSpeech.LANG_MISSING_DATA){
                        Toast.makeText(this , "Error" , Toast.LENGTH_LONG).show()
                    }else {
                        TtoS!!.speak(tv_question.text , TextToSpeech.QUEUE_FLUSH , null , null)
                    }
                }
            }
        }
    }

