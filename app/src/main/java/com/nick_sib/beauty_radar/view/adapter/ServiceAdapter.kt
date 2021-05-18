package com.nick_sib.beauty_radar.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.beauty_radar.R

class ServiceAdapter(private val list: List<*>) :
    RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_service, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран используя ViewHolder
        viewHolder.textView.text = list[i].toString()
//        viewHolder.imageView.setImageResource(R.drawable.aaa)
    }

    // Вернуть размер данных, вызывается менеджером
    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById<View>(R.id.textView) as TextView
//        private val imageView: ImageView = itemView.findViewById<View>(R.id.imageView) as ImageView

    }
}