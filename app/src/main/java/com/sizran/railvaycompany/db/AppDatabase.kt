package com.sizran.railvaycompany.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sizran.railvaycompany.entity.Train

@Database(entities = [Train::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trainDao(): TrainDao
}
