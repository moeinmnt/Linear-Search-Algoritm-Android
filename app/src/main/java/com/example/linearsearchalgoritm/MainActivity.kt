package com.example.linearsearchalgoritm

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.Instant
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var processTime:Duration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayCount = findViewById<EditText>(R.id.arrayNum)
        val searchTxt = findViewById<EditText>(R.id.searchTxt)

        val fillBtn = findViewById<Button>(R.id.fillBtn)
        val searchBtn = findViewById<Button>(R.id.searchBtn)

        val listView = findViewById<ListView>(R.id.arrayLv)
        val resultTv = findViewById<TextView>(R.id.resultTv)
        val timeTv = findViewById<TextView>(R.id.timeTv)

        lateinit var array:  Array<Int>

        fillBtn.setOnClickListener(){

            if(arrayCount.text != null) {
                var tempNum = arrayCount.text.toString().toInt()
                array = Array(tempNum) { 0 }
                for (i in 0 until tempNum){
                    array[i] = Random.nextInt()
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, array)
                listView.adapter = adapter
            }else {
                Toast.makeText(this, "Enter array element number please!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        searchBtn.setOnClickListener(){
            val tempTarget = searchTxt.text.toString().toInt()
            if(searchTxt != null){
                val tempIndex = LinearSearch(array, tempTarget)
                if(tempIndex != -1) {
                    resultTv.text =
                        "The number ($tempTarget) has been found at index $tempIndex."
                } else{
                    resultTv.text = "Sorry! Not Found!!!"
                }
            }else{
                resultTv.text = "Fill Search Box And Then Try Again!"
            }
            timeTv.text = "The SEARCHING execution time was ${processTime.toMillis()} milliseconds."
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun LinearSearch(array:Array<Int>, target:Int):Int{
        var start = Instant.now()

        var index = -1
        for(i in 0 until array.size){
            if(array[i] == target) index = i
        }

        var end = Instant.now()

        processTime = Duration.between(start, end)

        return index
    }
}