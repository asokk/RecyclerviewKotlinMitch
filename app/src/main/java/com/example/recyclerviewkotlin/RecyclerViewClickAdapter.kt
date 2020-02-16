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
import com.example.recyclerviewkotlin.RecyclerViewClickAdapter.ViewHolder
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

 class RecyclerViewClickAdapter( context: Context, imageNames: ArrayList<String>,
                                 images: ArrayList<String>, itemListener: OnItemListener)
                               : RecyclerView.Adapter<ViewHolder>() {

    private var mImageNames = ArrayList<String>()
    private var mImages = ArrayList<String>()
    private val mContext: Context
    private var mItemClickListener: OnItemListener

    init {
        mImageNames = imageNames
        mImages = images
        mContext = context
        mItemClickListener = itemListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                   .inflate(R.layout.layout_listitem, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mImageNames.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        var image: CircleImageView
        var imageName: TextView
        var parentLayout: RelativeLayout

        init {
            image = itemView.findViewById(R.id.image)
            imageName = itemView.findViewById(R.id.image_name)
            parentLayout = itemView.findViewById(R.id.parent_layout)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d(TAG, "onClick : = " + adapterPosition)
            mItemClickListener.onItemClick(adapterPosition)
        }
    }

    companion object {
        private const val TAG = "RecyclerViewClickAdapter"
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called.")
        Glide.with(mContext)
            .asBitmap()
            .load(mImages[position])
            .into(holder.image)
        holder.imageName.text = mImageNames[position]
        /*holder.parentLayout.setOnClickListener {
            Log.d(TAG, "onClick: clicked on: " + mImageNames[position])
            Toast.makeText(mContext, mImageNames[position], Toast.LENGTH_SHORT).show()
            val intent = Intent(mContext, GalleryActivity::class.java)
            intent.putExtra("image_url", mImages[position])
            intent.putExtra("image_name", mImageNames[position])
            mContext.startActivity(intent)
        }*/
    }

    interface OnItemListener {
        fun onItemClick(position: Int)
    }
}