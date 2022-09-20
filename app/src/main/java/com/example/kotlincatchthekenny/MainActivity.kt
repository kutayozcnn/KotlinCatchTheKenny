package com.example.kotlincatchthekenny

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var score = 0
    var ImageArray = ArrayList<ImageView >()
    var handler = Handler()
    var runnable = Runnable {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImageArray.add(imageView1)
        ImageArray.add(imageView2)
        ImageArray.add(imageView3)
        ImageArray.add(imageView4)
        ImageArray.add(imageView5)
        ImageArray.add(imageView6)
        ImageArray.add(imageView7)
        ImageArray.add(imageView8)
        ImageArray.add(imageView9)
        ImageArray.add(imageView10)
        ImageArray.add(imageView11)
        ImageArray.add(imageView12)

        hideImage()
        //CountTimeTimer
        object : CountDownTimer(15500,1000){
            override fun onTick(p0: Long) { textTime.text = "Time ="+ p0/1000 }
            override fun onFinish() {
                handler.removeCallbacks(runnable)
                for (image in ImageArray){ image.visibility = View.INVISIBLE }
                //alert
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over!")
                alert.setMessage("Restart The Game ?")
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                val intent = intent
                    finish()
                    startActivity(intent)
                })
                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(this@MainActivity,"GAME OVER!",Toast.LENGTH_LONG).show()
                })
                alert.show()
            }
        }.start()
    }
    fun hideImage(){
        runnable = object :Runnable{
            override fun run() {
                for(image in ImageArray){ image.visibility = View.INVISIBLE }
                val random = Random
                val randomIndex = random.nextInt(13)
                ImageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
            handler.post(runnable)
    }
    fun increaseScore(view : View){
        score++;
        textScore.text = "Score = $score"
    }
}