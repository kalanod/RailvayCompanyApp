package com.sizran.railvaycompany.db

import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.sizran.railvaycompany.entity.Train
import kotlinx.coroutines.launch

class TrainRepository(context: Context) {
    private val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "train-database"
    ).build()

    private val trainDao = db.trainDao()

    fun getAllTrains() = trainDao.getAllTrains()

    fun insertTrain(train: Train) {
        Thread { trainDao.insertTrain(train) }.start()
    }

    fun deleteTrain(train: Train) {
        Thread { trainDao.deleteTrain(train) }.start()
    }
}
