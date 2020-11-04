package com.example.anteeoneapp

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PlaceAdapter(private var list:ArrayList<Place>):RecyclerView.Adapter<PlaceHolder>() {

    private var placeList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
        return PlaceHolder.create(parent) {
            PlacesRepository.placesList.removeAt(it)
            notifyDataSetChanged()
        }
    }



    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onBindViewHolder(holder: PlaceHolder, position: Int, payloads: MutableList<Any>) {
        if(payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else{
            (payloads[0] as? Bundle)?.also {
                holder.updateFromBundle(it)
            } ?: run {super.onBindViewHolder(holder, position, payloads)}
        }
    }

    fun updateData(newList: ArrayList<Place>) {
        val callback : PlacesListDiffCallback = PlacesListDiffCallback(list,newList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)

    }

}