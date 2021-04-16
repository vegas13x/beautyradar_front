package com.nick_sib.beauty_radar.ui.masterclient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.beauty_radar.databinding.FragmentInitalProfileSetupBinding
import com.nick_sib.beauty_radar.databinding.ItemClientBinding
import com.nick_sib.beauty_radar.provider.profile.entities.CalendareProfile
import com.nick_sib.beauty_radar.ui.adapter.BaseViewHolder

class ClientAdapter(val list: List<CalendareProfile>) :
    RecyclerView.Adapter<BaseViewHolder<CalendareProfile>>() {


    override fun onBindViewHolder(holder: BaseViewHolder<CalendareProfile>, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CalendareProfile> {
        val inflater = LayoutInflater.from(parent.context)
        val itemClientBinding = ItemClientBinding.inflate(inflater, parent, false)
        return RecordedClientVH(itemClientBinding)
    }

    inner private class RecordedClientVH(val binding: ItemClientBinding) :
        BaseViewHolder<CalendareProfile>(binding.root) {

        override fun bind(data: CalendareProfile) {
            binding.itemRecordedTvClientName.text = data.name
            binding.itemRecordedTvDate.text = data.dateStart
            binding.itemRecordedTvServicePriceTime.text = "${data.dateEnd}, ${data.dateStart}"
        }
    }
}