package geekbarains.material.ui.recycler

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.beauty_radar.R
import kotlinx.android.synthetic.main.activity_recycler_item_earth.view.*
import kotlinx.android.synthetic.main.activity_recycler_item_mars.view.*


class NewAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<Pair<Data, Boolean>>,
    private val dragListener: OnStartDragListener
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_EARTH -> EarthViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_earth, parent, false) as View
            )
            TYPE_MARS ->
                MarsViewHolder(
                    inflater.inflate(R.layout.activity_recycler_item_mars, parent, false) as View
                )
            else -> HeaderViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_header, parent, false) as View
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
            position == 0 -> TYPE_HEADER
            data[position].first.someDescription.isNullOrBlank() -> TYPE_MARS
            else -> TYPE_EARTH
        }
    }


    fun setItems(newItems: List<Pair<Data, Boolean>>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(data, newItems))
        result.dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newItems)
    }

    fun appendItem() {
        data.add(generateItem())
        notifyItemInserted(itemCount - 1)
    }

    private fun generateItem() = Pair(Data(1, "Mars", ""), false)

    inner class DiffUtilCallback(
        private var oldItems: List<Pair<Data, Boolean>>,
        private var newItems: List<Pair<Data, Boolean>>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].first.id == newItems[newItemPosition].first.id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].first.someText == newItems[newItemPosition].first.someText

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]

            return Change(
                oldItem,
                newItem
            )
        }
    }

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {

        override fun bind(dataItem: Pair<Data, Boolean>) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.descriptionTextView.text = dataItem.first.someDescription
                itemView.wikiImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(
                        dataItem.first
                    )
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view){

        override fun bind(dataItem: Pair<Data, Boolean>) {
            itemView.marsImageView.setOnClickListener { onListItemClickListener.onItemClick(dataItem.first) }
            itemView.marsDescriptionTextView.visibility =
                if (dataItem.second) View.VISIBLE else View.GONE
            itemView.marsTextView.setOnClickListener { toggleText() }
            itemView.dragHandleImageView.setOnTouchListener { _, event ->
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(this)
                }
                false
            }
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
        private const val TYPE_EARTH = 0
        private const val TYPE_MARS = 1
        private const val TYPE_HEADER = 2
    }
}