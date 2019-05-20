package natto.com.roomsmple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val items by lazy { ArrayList<Item>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.titleView.text = items[position].title
    }

    fun add(item: Item) {
        items.add(item)
    }

    fun clear() {
        items.clear()
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView by lazy { view.findViewById(R.id.text_title) as TextView }
    }
}