package com.example.project5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
class PlanetAdapter(private val planetList: MutableList<String>) : RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val planetImage : ImageView
        var toast: Toast? = null

        init{
            planetImage = view.findViewById(R.id.planet_image)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.planet_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = planetList.size

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {

        Glide.with(viewholder.itemView)
            .load(planetList[position])
            .placeholder(R.drawable.placeholder)
            .centerCrop()
            .into(viewholder.planetImage)

        viewholder.planetImage.setOnClickListener{
            if(viewholder.toast != null) {viewholder.toast?.cancel()}
            viewholder.toast = Toast.makeText(viewholder.itemView.context, "Planet at position $position clicked", Toast.LENGTH_SHORT)
            viewholder.toast?.show()
        }
    }
}