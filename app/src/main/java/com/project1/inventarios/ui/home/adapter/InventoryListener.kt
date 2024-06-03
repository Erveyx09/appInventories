package com.project1.inventarios.ui.home.adapter

interface InventoryListener {
    fun onClick(id:Int?,representation:Int, quantity:Int,type:Int,position:Int)
}