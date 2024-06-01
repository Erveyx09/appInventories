package com.project1.inventarios.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project1.inventarios.R
import com.project1.inventarios.model.CardInventory

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var context: Context? = null
    private lateinit var inventoryListener: InventoryListener
    var inventoryRecyclerView: MutableList<CardInventory>? = ArrayList()

    fun RecyclerAdapter(
        inventoryRecyclerView: MutableList<CardInventory>?,
        context: Context,
        inventoryListener: InventoryListener
    ) {
        this.context = context
        this.inventoryRecyclerView = inventoryRecyclerView
        this.inventoryListener = inventoryListener
        this.notifyDataSetChanged()
    }

    fun setMutableInfo(inventoryRecyclerView: MutableList<CardInventory>?){
        this.inventoryRecyclerView = inventoryRecyclerView
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_inventory, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item = inventoryRecyclerView?.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        if (inventoryRecyclerView == null) {
            return 0
        } else {
            return inventoryRecyclerView!!.size
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val serialNumberId = view.findViewById(R.id.serialNumberId) as TextView
        private val inventoryName = view.findViewById(R.id.InventoryName) as TextView
        private val quantityId = view.findViewById(R.id.quantityId) as TextView
        private val numberId = view.findViewById(R.id.numberId) as TextView

        private val bottonOk = view.findViewById(R.id.bottonOk) as TextView
        val removeId = view.findViewById(R.id.removeId) as TextView
        val addId = view.findViewById(R.id.addId) as TextView


        fun bind(cardInventory: CardInventory?) {
            serialNumberId.text = cardInventory?.serialNumber
            inventoryName.text = cardInventory?.name
            quantityId.text = cardInventory?.quantity.toString()
            numberId.text = "0"
            bottonOk.setOnClickListener(this)

            removeId.setOnClickListener {
                val quantity: String = numberId.text as String
                val quantityF = quantity.toInt() - 1
                numberId.text = quantityF.toString()
            }

            addId.setOnClickListener {
                val quantity: String = numberId.text as String
                val quantityF = quantity.toInt() + 1
                numberId.text = quantityF.toString()
            }

        }

        override fun onClick(p0: View?) {
            val quantity: String = numberId.text as String
            val quantityF = quantity.toInt()
            val sum = inventoryRecyclerView?.get(layoutPosition)?.quantity!!+quantityF
            quantityId.text = sum.toString()

            inventoryListener.onClick(
                sum,
                inventoryRecyclerView?.get(layoutPosition)?.id
            )
            numberId.text = "0"
        }
    }

}