package com.joseneves.pdf

import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView


class LineAdapter(private val mDataList: MutableList<String>) : RecyclerView.Adapter<LineAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_line_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemText.text = mDataList[position]
        holder.itemText2.text = mDataList[position]
        holder.itemText3.text = mDataList[position] + mDataList[position] + mDataList[position] + mDataList[position] + mDataList[position] + mDataList[position] + mDataList[position] + mDataList[position] + mDataList[position] + mDataList[position] + mDataList[position]
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var itemText: TextView
        internal var itemText2: TextView
        internal var itemText3: TextView

        init {
            itemText = itemView.findViewById<View>(R.id.item_text) as TextView
            itemText2 = itemView.findViewById<View>(R.id.item_text2) as TextView
            itemText3 = itemView.findViewById<View>(R.id.item_text3) as TextView
        }
    }
}
