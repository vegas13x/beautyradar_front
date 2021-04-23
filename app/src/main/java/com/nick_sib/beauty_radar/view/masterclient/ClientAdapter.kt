package com.nick_sib.beauty_radar.view.masterclient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.beauty_radar.databinding.ItemClientBinding
import com.nick_sib.beauty_radar.model.provider.calendar.entities.CalendarProfile
import com.nick_sib.beauty_radar.view.adapter.BaseViewHolder

class ClientAdapter(var list: List<CalendarProfile>) :
    RecyclerView.Adapter<BaseViewHolder<CalendarProfile>>() {


    override fun onBindViewHolder(holder: BaseViewHolder<CalendarProfile>, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CalendarProfile> {
        val inflater = LayoutInflater.from(parent.context)
        val itemClientBinding = ItemClientBinding.inflate(inflater, parent, false)
        return RecordedClientVH(itemClientBinding)
    }

    inner private class RecordedClientVH(val binding: ItemClientBinding) :
        BaseViewHolder<CalendarProfile>(binding.root) {

        override fun bind(data: CalendarProfile) {
            binding.itemRecordedTvClientName.text = data.name
            binding.itemRecordedTvDate.text = data.dateStart
            binding.itemRecordedTvServicePriceTime.text = "${data.dateEnd}, ${data.dateStart}"
        }
    }
}