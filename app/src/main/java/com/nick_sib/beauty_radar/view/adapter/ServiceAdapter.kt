package com.nick_sib.beauty_radar.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.beauty_radar.databinding.ItemServiceBinding
import com.nick_sib.beauty_radar.model.provider.service.ServiceProfile
import com.nick_sib.beauty_radar.view.adapter.view_holders.BaseViewHolder

class ServiceAdapter(var list: List<ServiceProfile>) :
    RecyclerView.Adapter<BaseViewHolder<ServiceProfile>>() {


    override fun onBindViewHolder(holder: BaseViewHolder<ServiceProfile>, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ServiceProfile> {
        val inflater = LayoutInflater.from(parent.context)
        val itemServiceBinding = ItemServiceBinding.inflate(inflater, parent, false)
        return RecordedClientVH(itemServiceBinding)
    }

    private inner class RecordedClientVH(val binding: ItemServiceBinding) :
        BaseViewHolder<ServiceProfile>(binding.root) {

        override fun bind(data: ServiceProfile) {
            binding.itemText.text = data.itemServiceName
        }
    }
}