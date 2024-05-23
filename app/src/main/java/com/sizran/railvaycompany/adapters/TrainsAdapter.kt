package com.sizran.railvaycompany.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.sizran.railvaycompany.R
import com.sizran.railvaycompany.db.TrainRepository
import com.sizran.railvaycompany.entity.Train
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TrainsAdapter(
    public var trainList: MutableList<Train>,
    private val trainRepository: TrainRepository
) :
    RecyclerView.Adapter<TrainsAdapter.TrainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.train_item, parent, false)
        return TrainViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        val train = trainList[position]
        holder.trainName.text = train.name
        holder.trainCars.text = train.cars.toString()

        holder.itemView.setOnClickListener {
            showDeleteDialog(holder.itemView.context, train)
        }
    }

    override fun getItemCount(): Int = trainList.size

    private fun showDeleteDialog(context: Context, train: Train) {
        AlertDialog.Builder(context)
            .setTitle("Delete Train")
            .setMessage("Are you sure you want to delete ${train.name}?")
            .setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    trainRepository.deleteTrain(train)
                }
                trainList.remove(train)
                notifyDataSetChanged()
            }
            .setNegativeButton("No", null)
            .show()
    }

    class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trainName: TextView = itemView.findViewById(R.id.train_name)
        val trainCars: TextView = itemView.findViewById(R.id.train_cars)
    }
}