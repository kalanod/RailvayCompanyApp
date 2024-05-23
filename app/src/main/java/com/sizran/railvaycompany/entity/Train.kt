package com.sizran.railvaycompany.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trains")
data class Train(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val cars: Int,
    val description: String
)
