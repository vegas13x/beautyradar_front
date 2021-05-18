package com.nick_sib.beauty_radar.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.ItemServiceBinding
import com.nick_sib.beauty_radar.view.adapter.view_holders.BaseViewHolder
import com.nick_sib.beauty_radar.view.utils.ServiceItem

class ServiceAdapter(var list: List<ServiceItem>) :
    RecyclerView.Adapter<BaseViewHolder<ServiceItem>>() {


    override fun onBindViewHolder(holder: BaseViewHolder<ServiceItem>, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ServiceItem> {
        val inflater = LayoutInflater.from(parent.context)
        val itemServiceBinding = ItemServiceBinding.inflate(inflater, parent, false)
        return RecordedClientVH(itemServiceBinding)
    }

    private inner class RecordedClientVH(val binding: ItemServiceBinding) :
        BaseViewHolder<ServiceItem>(binding.root) {

        override fun bind(data: ServiceItem) {
            binding.itemText.text = data.itemServiceName
        }
    }
}