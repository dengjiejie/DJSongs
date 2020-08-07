package com.dj.songs.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dj.songs.R

/**
 *  author : dengjiejie
 *  date : 2020/8/3 6:09 PM
 *  description :
 */
class ViewPager2Adapter(private var mList: List<String>? = null, private var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.view_pager_list_item, parent, false)
        return ViewPager2Holder(view);
    }

    override fun getItemCount(): Int {
        return 6;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewPager2Holder
        holder.imageView?.setImageResource(R.drawable.page0)
        holder.text?.text = "" + position
    }

    inner class ViewPager2Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var imageView: ImageView? = null
        var text: TextView? = null

        init {
            imageView =
                itemView.findViewById<View>(R.id.image) as ImageView
            text = itemView.findViewById<View>(R.id.text) as TextView
        }

    }

}