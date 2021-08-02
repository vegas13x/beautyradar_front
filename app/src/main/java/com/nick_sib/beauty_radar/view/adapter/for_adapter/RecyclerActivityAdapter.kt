package com.nick_sib.beauty_radar.view.adapter.for_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.beauty_radar.R
import geekbarains.material.ui.recycler.BaseViewHolder
import geekbarains.material.ui.recycler.Change
import geekbarains.material.ui.recycler.Data
import geekbarains.material.ui.recycler.createCombinedPayload
import kotlinx.android.synthetic.main.activity_recycler_item_earth.view.*
import kotlinx.android.synthetic.main.activity_recycler_item_mars.view.*

class RecyclerActivityAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<Pair<Data, Boolean>>,
    private val dragListener: OnStartDragListener
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_MARS ->
                MarsViewHolder(
                    inflater.inflate(R.layout.activity_recycler_item_mars, parent, false) as View
                )
            else -> HeaderViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_mars, parent, false) as View
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val combinedChange =
                createCombinedPayload(payloads as List<Change<Pair<Data, Boolean>>>)
            val oldData = combinedChange.oldData
            val newData = combinedChange.newData

            if (newData.first.someText != oldData.first.someText) {
                holder.itemView.marsTextView.text = newData.first.someText
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            else -> TYPE_MARS
        }
    }



//    private fun generateItem() = Pair(Data(1, "Mars", ""), false)

//    inner class DiffUtilCallback(
//        private var oldItems: List<Pair<Data, Boolean>>,
//        private var newItems: List<Pair<Data, Boolean>>
//    ) : DiffUtil.Callback() {
//
//        override fun getOldListSize(): Int = oldItems.size
//
//        override fun getNewListSize(): Int = newItems.size
//
//        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
//            oldItems[oldItemPosition].first.id == newItems[newItemPosition].first.id
//
//        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
//            oldItems[oldItemPosition].first.someText == newItems[newItemPosition].first.someText
//
//        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
//            val oldItem = oldItems[oldItemPosition]
//            val newItem = newItems[newItemPosition]
//
//            return Change(
//                oldItem,
//                newItem
//            )
//        }
//    }


    inner class MarsViewHolder(view: View) : BaseViewHolder(view){

        override fun bind(dataItem: Pair<Data, Boolean>) {
            itemView.marsDescriptionTextView.visibility =
                if (dataItem.second) View.VISIBLE else View.GONE
            itemView.marsTextView.text = dataItem.first.someText
            itemView.setOnClickListener { toggleText() }
        }


        private fun toggleText() {
            data[layoutPosition] = data[layoutPosition].let {
                it.first to !it.second
            }
            notifyItemChanged(layoutPosition)
        }

    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {

        override fun bind(dataItem: Pair<Data, Boolean>) {
            itemView.setOnClickListener {
                onListItemClickListener.onItemClick(dataItem.first)
//                data[1] = Pair(Data("Jupiter", ""), false)
//                notifyItemChanged(1, Pair(Data("", ""), false))
            }
        }
    }

    interface OnListItemClickListener {
        fun onItemClick(data: Data)
    }

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    companion object {
        private const val TYPE_MARS = 1
    }
}