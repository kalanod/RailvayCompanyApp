package com.sizran.railvaycompany.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.sizran.railvaycompany.R

data class Station(val name: String, val city: String, val description: String)

class StationsAdapter(private val context: Context, private var stationList: List<Station>) :
    RecyclerView.Adapter<StationsAdapter.StationViewHolder>() {

    private var filteredStationList: List<Station> = stationList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.station_item, parent, false)
        return StationViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val station = filteredStationList[position]
        holder.stationName.text = station.name
        holder.stationCity.text = station.city

        holder.itemView.setOnClickListener {
            showDescriptionDialog(station)
        }
    }

    override fun getItemCount(): Int = filteredStationList.size

    fun filter(query: String) {
        filteredStationList = if (query.isEmpty()) {
            stationList
        } else {
            stationList.filter {
                it.city.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    private fun showDescriptionDialog(station: Station) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(station.name)
        builder.setMessage(station.description)
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

    class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stationName: TextView = itemView.findViewById(R.id.station_name)
        val stationCity: TextView = itemView.findViewById(R.id.station_city)
    }
}
