package com.project1.inventarios.model

import java.io.Serializable

data class CardInventory(
    var id:Int?,
    var name:String?,
    var quantity:Int?,
    var serialNumber:String?,
    ): Serializable