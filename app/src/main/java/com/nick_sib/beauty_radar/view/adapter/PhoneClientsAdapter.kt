package com.nick_sib.beauty_radar.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.beauty_radar.databinding.ContactsListItemBinding
import com.nick_sib.beauty_radar.model.provider.phone_clients.PhoneClient
import com.nick_sib.beauty_radar.view.adapter.view_holders.BaseViewHolder

class PhoneClientsAdapter(var list: List<PhoneClient>) :
    RecyclerView.Adapter<BaseViewHolder<PhoneClient>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<PhoneClient> {
        val inflater = LayoutInflater.from(parent.context)
        val itemClientBinding = ContactsListItemBinding.inflate(inflater, parent, false)
        return PhoneClientVH(itemClientBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<PhoneClient>, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int = list.size

    private inner class PhoneClientVH(val binding: ContactsListItemBinding) :
        BaseViewHolder<PhoneClient>(binding.root) {
        override fun bind(data: PhoneClient) {
            binding.clientPhoneName.text = data.name
            binding.clientPhonePhone.text = data.phone
        }
    }

}