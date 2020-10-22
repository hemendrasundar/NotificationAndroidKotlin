package com.hemendra.notificationandvibrationservice

import android.app.*
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

          var count =0;
           btn_notify.setOnClickListener {

            var nManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if(android.os.Build.VERSION.SDK_INT<android.os.Build.VERSION_CODES.O)
            {
                var nCompact = NotificationCompat.Builder(this@MainActivity)
                nCompact.setSmallIcon(R.drawable.ic_baseline_brightness_4_24)
                nCompact.setTicker("sample notification")
                nCompact.setContentTitle("Test App Notification")
                nCompact.setContentText("demo text of the notification")

                var i = Intent(this@MainActivity,MainActivity2::class.java)
                var PIntent = PendingIntent.getActivity(this@MainActivity,100,i,0)

                nCompact.setContentIntent(PIntent)
                nCompact.setAutoCancel(true)
                nCompact.addAction(R.drawable.ic_baseline_brightness_4_24,"cancel",PIntent)
                nCompact.addAction(R.drawable.ic_baseline_brightness_4_24,"Ok",PIntent)
                nManager.notify(++count,nCompact.build())
            }
            else{

                var nChannel:NotificationChannel = NotificationChannel("1","testchannel",NotificationManager.IMPORTANCE_HIGH)
                nChannel.enableLights(true)
                nChannel.enableVibration(true)
                nManager.createNotificationChannel(nChannel)

                var nCompact = NotificationCompat.Builder(this@MainActivity,"1")
                nCompact.setSmallIcon(R.drawable.ic_baseline_brightness_4_24)
                nCompact.setTicker("sample notification")
                nCompact.setContentTitle("Test App Notification")
                nCompact.setContentText("demo text of the notification")

                var i = Intent(this@MainActivity,MainActivity::class.java)
                var PIntent = PendingIntent.getActivity(this@MainActivity,100,i,0)

                nCompact.setContentIntent(PIntent)
                nCompact.addAction(R.drawable.ic_baseline_brightness_4_24,"cancel",PIntent)
                nCompact.addAction(R.drawable.ic_baseline_brightness_4_24,"Action 1",PIntent)
                nManager.notify(1,nCompact.build())


            }


        }

        btn_vibrate.setOnClickListener {

            var vib:Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            vib.vibrate(VibrationEffect.createOneShot(10000,VibrationEffect.EFFECT_HEAVY_CLICK))
        }
    }
}