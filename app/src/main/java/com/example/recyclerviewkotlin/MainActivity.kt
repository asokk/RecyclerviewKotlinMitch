package com.example.recyclerviewkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewkotlin.RecyclerViewClickAdapter.OnItemListener
import java.util.*

class MainActivity : AppCompatActivity(), OnItemListener{

    private val TAG = MainActivity::class.java.simpleName
    //vars
    private val mNames = ArrayList<String>()
    private val mImageUrls = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: started.")
        initImageBitmaps()
    }

    private fun initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.")
        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg")
        mNames.add("Havasu Falls")
        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg")
        mNames.add("Trondheim")
        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg")
        mNames.add("Portugal")
        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg")
        mNames.add("Rocky Mountain National Park")
        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg")
        mNames.add("Mahahual")
        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg")
        mNames.add("Frozen Lake")
        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg")
        mNames.add("White Sands Desert")
        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg")
        mNames.add("Austrailia")
        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg")
        mNames.add("Washington")
        initRecyclerView()
    }

    private fun initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.")
        val recyclerView: RecyclerView = findViewById(R.id.recyclerv_view)
        val adapter = RecyclerViewClickAdapter(this, mNames, mImageUrls,this)
        //val adapter = RecyclerViewAdapter(this, mNames, mImageUrls,this)
        recyclerView.setAdapter(adapter)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, GalleryActivity::class.java)
        intent.putExtra("image_url", mImageUrls[position])
        intent.putExtra("image_name", mNames[position])
        startActivity(intent)
    }
}
