package com.sizran.railvaycompany.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sizran.railvaycompany.R
import com.sizran.railvaycompany.entity.Train
import com.sizran.railvaycompany.adapters.TrainsAdapter
import com.sizran.railvaycompany.db.TrainRepository
import kotlinx.coroutines.launch

class FragmentTrains : Fragment() {

    private lateinit var adapter: TrainsAdapter
    private lateinit var trainRepository: TrainRepository
    public lateinit var trainList: MutableList<Train>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        trainList = mutableListOf()
        trainRepository = TrainRepository(requireContext())
        loadTrains()
        val view = inflater.inflate(R.layout.fragment_trains, container, false)


        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_trains)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TrainsAdapter(trainList, trainRepository)
        recyclerView.adapter = adapter

        val fab: FloatingActionButton = view.findViewById(R.id.fab_add_train)
        fab.setOnClickListener {
            showAddTrainDialog()
        }


        adapter.notifyDataSetChanged()
        return view
    }

    private fun loadTrains() {
        Thread {
            trainList.clear()
            var y = trainRepository.getAllTrains()
            for (i in y) {
                trainList.add(i)
            }
        }.start()
    }

    private fun showAddTrainDialog() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_train, null)
        val trainNameInput = dialogView.findViewById<EditText>(R.id.input_train_name)
        val trainCarsInput = dialogView.findViewById<EditText>(R.id.input_train_cars)
        val trainDescriptionInput = dialogView.findViewById<EditText>(R.id.input_train_description)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Train")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                var trainCars = 1
                if (trainCarsInput.text.toString() != ""){
                    trainCars = trainCarsInput.text.toString().toInt()
                }
                val trainName = trainNameInput.text.toString()

                val trainDescription = trainDescriptionInput.text.toString()
                val newTrain =
                    Train(name = trainName, cars = trainCars, description = trainDescription)

                Thread {
                    trainRepository.insertTrain(newTrain)

                }.start()
                adapter.trainList.add(newTrain)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}