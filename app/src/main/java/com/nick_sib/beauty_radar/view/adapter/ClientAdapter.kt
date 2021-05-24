package com.nick_sib.beauty_radar.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.beauty_radar.databinding.ItemClient2Binding
import com.nick_sib.beauty_radar.databinding.ItemClientBinding
import com.nick_sib.beauty_radar.model.provider.calendar.CalendarProfile
import com.nick_sib.beauty_radar.view.adapter.view_holders.BaseViewHolder

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
        val itemClientBinding = ItemClient2Binding.inflate(inflater, parent, false)
        return RecordedClientVH(itemClientBinding)
    }

    inner private class RecordedClientVH(val binding: ItemClient2Binding) :
        BaseViewHolder<CalendarProfile>(binding.root) {

        override fun bind(data: CalendarProfile) {
            var dataItem = false
            binding.itemRecordedTvClientName.text = data.name
            binding.itemRecordedTvClientName.setOnClickListener {
                dataItem = !dataItem

            }
            binding.itemRecordedTvProcedureTime.text = data.dateStart
            binding.itemRecordedTvServicePriceTime.text = data.service
            binding.itemRecordedTvServicePriceTime2.visibility =
                if (dataItem) View.VISIBLE else View.GONE
        }
    }
}