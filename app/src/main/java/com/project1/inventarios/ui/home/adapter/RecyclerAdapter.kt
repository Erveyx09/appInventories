package com.project1.inventarios.ui.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
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

    fun setMutableInfo(inventoryRecyclerView: MutableList<CardInventory>?) {
        this.inventoryRecyclerView = inventoryRecyclerView
        this.notifyDataSetChanged()
    }

    fun setDeleteInfo(layoutPosition:Int) {
        inventoryRecyclerView?.remove(inventoryRecyclerView?.get(layoutPosition)!!)

        Log.e("insertInventory catch",layoutPosition.toString())
        notifyItemRemoved(layoutPosition)
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
        private val editInventory = view.findViewById(R.id.editInventory) as TextView


        private val bottonOk = view.findViewById(R.id.bottonOk) as TextView
        val removeId = view.findViewById(R.id.removeId) as TextView
        val addId = view.findViewById(R.id.addId) as TextView
        val deleteInventory = view.findViewById(R.id.deleteInventory) as TextView


        fun bind(cardInventory: CardInventory?) {
            serialNumberId.text = cardInventory?.serialNumber
            inventoryName.text = cardInventory?.name
            quantityId.text = cardInventory?.quantity.toString()
            numberId.text = "0"
            bottonOk.setOnClickListener(this)
            editInventory.setOnClickListener(this)
            deleteInventory.setOnClickListener(this)

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
            if (p0?.id == bottonOk.id) {
                val quantity: String = numberId.text as String
                val quantityF = quantity.toInt()
                val sum = inventoryRecyclerView?.get(layoutPosition)?.quantity!! + quantityF
                quantityId.text = sum.toString()
                inventoryListener.onClick(
                    sum,
                    inventoryRecyclerView?.get(layoutPosition)?.id,
                    0,
                    layoutPosition
                )
                numberId.text = "0"
            } else if (p0?.id == editInventory.id) {
                inventoryListener.onClick(
                    0,
                    inventoryRecyclerView?.get(layoutPosition)?.id,
                    1,
                    layoutPosition
                )
            } else if (p0?.id == deleteInventory.id) {
                inventoryListener.onClick(
                    0,
                    inventoryRecyclerView?.get(layoutPosition)?.id,
                    2,
                    layoutPosition
                )
            }

        }
    }

}