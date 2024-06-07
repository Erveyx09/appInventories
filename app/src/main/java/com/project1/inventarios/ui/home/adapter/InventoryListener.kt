package com.project1.inventarios.ui.home.adapter

import com.project1.inventarios.model.CardInventory

interface InventoryListener {
    fun onClick(cardInventory: CardInventory,note:String,type:Int,position:Int)
}