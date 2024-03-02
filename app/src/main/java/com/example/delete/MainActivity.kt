package com.example.delete

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonBirthday:Button=findViewById(R.id.buttonBirthday)

        buttonBirthday.setOnClickListener{view->
            datePicker(view)
          // Toast.makeText(this,"You click the Button",Toast.LENGTH_SHORT).show()
        }

    }


    @SuppressLint("SuspiciousIndentation")
    fun datePicker(view: View) {

        val calendar=Calendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH)


      val dpd=  DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                                                                 view, year, month, dayOfMonth ->
          val selectedDate="$dayOfMonth/${month+1}/$year"
            val display=findViewById(R.id.brithday) as TextView
            display.text=selectedDate

            val simpleDate=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date=simpleDate.parse(selectedDate)

            val seconds=date!!.time/1000

            val todayInSeconds=simpleDate.parse(simpleDate.format(System.currentTimeMillis()))!!.time/1000 //1970 to today
            val trueTimeSeconds=todayInSeconds-seconds

            val secondsDisplay=findViewById(R.id.inSecondsText) as TextView
            secondsDisplay.text=trueTimeSeconds.toString()

            val miniteDislpay=findViewById(R.id.inMinutesText) as TextView
            miniteDislpay.text="${trueTimeSeconds/60}"

            val hourDisplay=findViewById(R.id.inHoursText) as TextView
            hourDisplay.text="${trueTimeSeconds/3600}"

            val dayDisplay=findViewById(R.id.inDayText) as TextView
            dayDisplay.text="${trueTimeSeconds/3600*24}"


        },year,month,dayOfMonth)

        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()
    }

}