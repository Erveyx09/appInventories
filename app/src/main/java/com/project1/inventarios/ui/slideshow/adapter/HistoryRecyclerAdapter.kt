package com.project1.inventarios.ui.slideshow.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project1.inventarios.R
import com.project1.inventarios.model.History

class HistoryRecyclerAdapter : RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>() {

    private var context: Context? = null
    private lateinit var historyListener: HistoryListener
    var historyRecyclerView: MutableList<History>? = ArrayList()

    fun RecyclerAdapter(
        historyRecyclerView: MutableList<History>?,
        context: Context,
        historyListener: HistoryListener
    ) {
        this.context = context
        this.historyRecyclerView = historyRecyclerView
        this.historyListener = historyListener
        this.notifyDataSetChanged()
    }

    fun setMutableInfo(historyRecyclerView: MutableList<History>?) {
        this.historyRecyclerView = historyRecyclerView
        this.notifyDataSetChanged()
    }

    fun setDeleteInfo(layoutPosition: Int) {
        historyRecyclerView?.remove(historyRecyclerView?.get(layoutPosition)!!)
        Log.e("insertInventory catch", layoutPosition.toString())
        notifyItemRemoved(layoutPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryRecyclerAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_history, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryRecyclerAdapter.ViewHolder, position: Int) {
        val item = historyRecyclerView?.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        if (historyRecyclerView == null) {
            return 0
        } else {
            return historyRecyclerView!!.size
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val dateId = view.findViewById(R.id.dateId) as TextView
        private val nameProductId = view.findViewById(R.id.nameProductId) as TextView
        private val noReferenceId = view.findViewById(R.id.noReferenceId) as TextView
        private val noteId = view.findViewById(R.id.noteId) as TextView


        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

        fun bind(history: History?) {
            dateId.setText(history!!.date.toString())
            nameProductId.setText(history!!.nameProduct)
            noReferenceId.setText(history!!.noReference)
            noteId.setText(history!!.note)

        }

    }
}