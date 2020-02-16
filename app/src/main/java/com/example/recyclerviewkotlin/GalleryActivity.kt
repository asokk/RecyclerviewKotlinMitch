package com.example.recyclerviewkotlin

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

/**
 * Created by User on 1/2/2018.
 */
class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        Log.d(TAG, "onCreate: started.")
        incomingIntent
    }

    private val incomingIntent: Unit
        private get() {
            Log.d(
                TAG,
                "getIncomingIntent: checking for incoming intents."
            )
            if (intent.hasExtra("image_url") && intent.hasExtra("image_name")) {
                Log.d(
                    TAG,
                    "getIncomingIntent: found intent extras."
                )
                val imageUrl = intent.getStringExtra("image_url")
                val imageName = intent.getStringExtra("image_name")
                setImage(imageUrl, imageName)
            }
        }

    private fun setImage(imageUrl: String, imageName: String) {
        Log.d(
            TAG,
            "setImage: setting te image and name to widgets."
        )
        val name = findViewById<TextView>(R.id.image_description)
        name.text = imageName
        val image = findViewById<ImageView>(R.id.image)
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(image)
    }

    companion object {
        private const val TAG = "GalleryActivity"
    }
}