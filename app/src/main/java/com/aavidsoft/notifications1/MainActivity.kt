package com.aavidsoft.notifications1

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.aavidsoft.notifications1.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var notificationManager: NotificationManagerCompat
    val notificationChannelEntertainmentId = "bitcode_entertainment_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //notificationManager  = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager = NotificationManagerCompat.from(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        snackBarDemo()
        createNotificationChannels()
        simpleNotification()
        bigPictureNotification()
        inboxStyleNotification()
        actionTextStyle()
    }

    fun actionTextStyle() {
        binding.btnActionTextStyleNotification.setOnClickListener {
            binding.btnInboxStyleNotification.setOnClickListener {
            }

            var builder = NotificationCompat.Builder(this, notificationChannelEntertainmentId)

            builder.setContentTitle("Hackathon Registrations open..")
            builder.setContentText("Visit BitCode for registrations..")
            builder.setSmallIcon(R.mipmap.ic_launcher)

            var actionIntent = Intent(this, HomeActivity::class.java)
            var actionPendingIntent = PendingIntent.getActivity(
                this,
                1,
                actionIntent,
                0
            )

            builder.setAutoCancel(true)

            var actionRegister = NotificationCompat.Action(R.mipmap.ic_launcher, "Register", actionPendingIntent)
            //var actionRegBuilder = NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Register", actionPendingIntent)

            //builder.addAction(R.mipmap.ic_launcher, "Register", actionPendingIntent)
            builder.addAction(actionRegister)
            builder.addAction(R.mipmap.ic_launcher, "Share", actionPendingIntent)
            builder.addAction(R.mipmap.ic_launcher, "Not Useful", actionPendingIntent)

            notificationManager.notify(104, builder.build())

        }
    }

    fun inboxStyleNotification() {
        binding.btnInboxStyleNotification.setOnClickListener {
            var builder = NotificationCompat.Builder(this, notificationChannelEntertainmentId)
            builder.setContentTitle("Hackathon Registrations open..")
            builder.setContentText("Visit BitCode for registrations..")
            builder.setSmallIcon(R.mipmap.ic_launcher)

            var inboxStyle = NotificationCompat.InboxStyle()
            inboxStyle.addLine("Meet Vishal for Android.")
            inboxStyle.addLine("Meet Sonal for Web.")
            inboxStyle.addLine("Meet Aishwarya for iOS.")
            inboxStyle.addLine("Meet Akanksha for Java.")

            builder.setStyle(inboxStyle)

            notificationManager.notify(103, builder.build())
        }
    }

    fun bigPictureNotification() {
        binding.btnBigPictureNotification.setOnClickListener {
            var builder = NotificationCompat.Builder(this, notificationChannelEntertainmentId)
            builder.setContentTitle("Hackathon Registrations open..")
            builder.setContentText("Visit BitCode for registrations..")
            builder.setSmallIcon(R.mipmap.ic_launcher)

            var bigPictureStyle = NotificationCompat.BigPictureStyle()
            bigPictureStyle.setBigContentTitle("Hackathon Registrations open..")
            bigPictureStyle.setSummaryText("Visit BitCode for registrations..")

            var bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog)
            bigPictureStyle.bigPicture(bitmap)

            builder.setStyle(bigPictureStyle)

            notificationManager.notify(102, builder.build())
        }
    }

    fun simpleNotification() {
        binding.btnSimpleNotification.setOnClickListener {


            var notificationBuilder =
                NotificationCompat.Builder(this, notificationChannelEntertainmentId)

            notificationBuilder.setContentTitle("BitCode Technologies")
            notificationBuilder.setContentText("You have won lottery worth 1,00,000 GBP.. Please call 9881125904")

            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)

            var largeIcon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            notificationBuilder.setLargeIcon(largeIcon)

            notificationBuilder.setNumber(3)
            notificationBuilder.setColor(Color.RED)

            //notificationBuilder.setSound(Uri.parse("file:///storage/sdcard/myaudio.mp3"))
            notificationBuilder.setVibrate(
                LongArray(10, { index -> 400 })
            )
            notificationBuilder.setLights(Color.RED, 500, 400)
            notificationBuilder.setAutoCancel(true)
            notificationBuilder.setOngoing(true)
            notificationBuilder.setVisibility(NotificationCompat.VISIBILITY_SECRET)


            var actionIntent = Intent(this, HomeActivity::class.java)
            var actionPendingIntent = PendingIntent.getActivity(
                this,
                1,
                actionIntent,
                0
            )
            notificationBuilder.setContentIntent(actionPendingIntent)


            var notification = notificationBuilder.build()

            notificationManager.notify(101, notification)
        }
    }

    fun snackBarDemo() {
        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Download completed!",
                Snackbar.LENGTH_LONG
            )   //.setBackgroundTint(Color.RED)
                .setTextColor(Color.WHITE)
                .setAction(
                    "View",
                    {
                        Log.e("tag", "View action is taken...")
                    }
                )
                .show()
        }
    }

    fun createNotificationChannels() {
        var channelEntertainmentBuilder = NotificationChannelCompat.Builder(
            "bitcode_entertainment_channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channelEntertainmentBuilder.setName("Entertainment")
        channelEntertainmentBuilder.setDescription("Audio/Video related to BitCode")
        channelEntertainmentBuilder.setShowBadge(true)

        notificationManager.createNotificationChannel(channelEntertainmentBuilder.build())


    }
}