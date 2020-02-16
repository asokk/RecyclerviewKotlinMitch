package com.example.recyclerviewkotlin

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

/**
 * Created by User on 1/1/2018.
 */
class RecyclerViewAdapter(context: Context,imageNames: ArrayList<String>,images: ArrayList<String>)
                          : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var mImageNames =  ArrayList<String>()
    private var mImages = ArrayList<String>()
    private val mContext: Context
    override fun onCreateViewHolder( parent: ViewGroup,viewType: Int ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_listitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder( holder: ViewHolder, position: Int ) {
        Log.d(TAG, "onBindViewHolder: called.")
        Glide.with(mContext)
            .asBitmap()
            .load(mImages[position])
            .into(holder.image)
        holder.imageName.text = mImageNames[position]
        holder.parentLayout.setOnClickListener {
            Log.d(TAG,"onClick: clicked on: " + mImageNames[position])
            Toast.makeText(mContext, mImageNames[position], Toast.LENGTH_SHORT).show()
            val intent = Intent(mContext, GalleryActivity::class.java)
            intent.putExtra("image_url", mImages[position])
            intent.putExtra("image_name", mImageNames[position])
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mImageNames.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var image: CircleImageView
        var imageName: TextView
        var parentLayout: RelativeLayout

        init {
            image = itemView.findViewById(R.id.image)
            imageName = itemView.findViewById(R.id.image_name)
            parentLayout = itemView.findViewById(R.id.parent_layout)
        }
    }

    companion object {
        private const val TAG = "RecyclerViewAdapter"
    }

    init {
        mImageNames = imageNames
        mImages = images
        mContext = context
    }
}