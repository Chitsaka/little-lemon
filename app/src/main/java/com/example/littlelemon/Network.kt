package com.example.littlelemon

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(val menu: List<MenuItemNetwork>)

@Entity
@Serializable
data class MenuItemNetwork(
    @PrimaryKey
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("price")
    val price: String,

    @SerialName("description")
    val description: String,

    @SerialName("image")
    val image: String,

    @SerialName("category")
    val category: String
)