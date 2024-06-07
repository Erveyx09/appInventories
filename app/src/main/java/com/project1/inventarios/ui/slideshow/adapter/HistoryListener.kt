package com.project1.inventarios.ui.slideshow.adapter

interface HistoryListener {
    fun onClick(id:Int?,representation:Int, quantity:Int,type:Int,position:Int)
}