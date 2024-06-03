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

        private val notaText = view.findViewById(R.id.notaText) as TextView

        private val noReference = view.findViewById(R.id.noReference) as TextView
        private val inventoryName = view.findViewById(R.id.InventoryName) as TextView

        private val numberId = view.findViewById(R.id.numberId) as TextView
        private val numberId2 = view.findViewById(R.id.numberId2) as TextView

        private val ref = view.findViewById(R.id.quantity) as TextView
        private val equ = view.findViewById(R.id.quantity2) as TextView
        private var equivalence = 0;

        private val editInventory = view.findViewById(R.id.editInventory) as TextView

        private val namePresentationId = view.findViewById(R.id.namePresentationId) as TextView
        private val nameEquivalenceId = view.findViewById(R.id.nameEquivalenceId) as TextView



        private val bottonOk = view.findViewById(R.id.bottonOk) as TextView
        val removeId = view.findViewById(R.id.removeId) as TextView
        val addId = view.findViewById(R.id.addId) as TextView

        val removeId2 = view.findViewById(R.id.removeId2) as TextView
        val addId2 = view.findViewById(R.id.addId2) as TextView

        val deleteInventory = view.findViewById(R.id.deleteInventory) as TextView

        var representationName = ""
        var equivalencesName = ""

        fun bind(cardInventory: CardInventory?) {
            representationName = cardInventory?.representationName!!
            equivalencesName = cardInventory.equivalencesName

            namePresentationId.setText(equivalencesName)
            nameEquivalenceId.setText(representationName)

            noReference.text = cardInventory?.noReference
            inventoryName.text = cardInventory?.name
            equivalence = cardInventory?.equivalences!!

            numberId.text = "0"
            numberId2.text = "0"

            ref.setText(equivalencesName+":"+cardInventory?.representation.toString())
            equ.setText(representationName+":"+cardInventory?.quantity.toString())

            bottonOk.setOnClickListener(this)
            editInventory.setOnClickListener(this)
            deleteInventory.setOnClickListener(this)

            removeId.setOnClickListener {
                val quantity: String = numberId.text as String
                val quantityF = quantity.toInt() - 1
                numberId.text = quantityF.toString()
            }

            removeId2.setOnClickListener {
                val quantity: String = numberId2.text as String
                val quantityF = quantity.toInt() - 1
                numberId2.text = quantityF.toString()
            }

            addId.setOnClickListener {
                val quantity: String = numberId.text as String
                val quantityF = quantity.toInt() + 1
                numberId.text = quantityF.toString()
            }

            addId2.setOnClickListener {
                val quantity: String = numberId2.text as String
                val quantityF = quantity.toInt() + 1
                numberId2.text = quantityF.toString()
            }

        }

        override fun onClick(p0: View?) {
            if (p0?.id == bottonOk.id) {
                val refs: String = numberId.text as String
                val quantity: String = numberId2.text as String

                val quantityF = quantity.toInt()
                val refFinal = refs.toInt()
                val sumQuantity = refFinal*equivalence

                val sum = inventoryRecyclerView?.get(layoutPosition)?.quantity!! + quantityF+sumQuantity
                val sumReference = Math.ceil(sum.toDouble()/equivalence).toInt()

                ref.setText(equivalencesName+":"+sumReference.toString())
                equ.setText(representationName+":"+sum.toString())

                inventoryListener.onClick(
                    inventoryRecyclerView?.get(layoutPosition)?.id,
                    sumReference,
                    sum,
                    0,
                    layoutPosition
                )
                numberId.text = "0"
            } else if (p0?.id == editInventory.id) {
                inventoryListener.onClick(
                    inventoryRecyclerView?.get(layoutPosition)?.id,
                    0,
                    0,
                    1,
                    layoutPosition
                )
            } else if (p0?.id == deleteInventory.id) {
                inventoryListener.onClick(
                    inventoryRecyclerView?.get(layoutPosition)?.id,
                    0,
                    0,
                    2,
                    layoutPosition
                )
            }

        }
    }

}