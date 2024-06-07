package com.project1.inventarios.model

import java.io.Serializable


data class CardInventory(
    var id:Int?,
    val noReference:String,
    var name:String,
    var representation:Int,
    val representationName:String,
    val equivalences:Int,
    val equivalencesName:String,
    var quantity:Int,
    val deliveryBy:String
    ): Serializable