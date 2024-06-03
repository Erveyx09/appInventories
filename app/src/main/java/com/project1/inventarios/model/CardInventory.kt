package com.project1.inventarios.model

import java.io.Serializable


data class CardInventory(
    var id:Int?,
    val noReference:String,
    var name:String,
    val representation:Int,
    val representationName:String,
    val equivalences:Int,
    val equivalencesName:String,
    val quantity:Int,
    val deliveryBy:String
    ): Serializable