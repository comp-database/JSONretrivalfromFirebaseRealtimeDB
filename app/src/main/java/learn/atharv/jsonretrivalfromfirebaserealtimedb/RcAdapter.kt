package learn.atharv.jsonretrivalfromfirebaserealtimedb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RcAdapter(private val dataset : ArrayList<studentDetail>)
    : RecyclerView.Adapter<RcAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val leadboardTV : TextView = view.findViewById(R.id.leadboard)
        val nameTV : TextView = view.findViewById(R.id.name)
        val pointsTV : TextView = view.findViewById(R.id.points)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item,parent,false)//inflate actual item view
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item : studentDetail = dataset[position]
        holder.nameTV.text = item.name
        holder.leadboardTV.text = item.leadboards
        holder.pointsTV.text = item.points
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}
