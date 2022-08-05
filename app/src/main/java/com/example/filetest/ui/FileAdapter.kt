package com.example.filetest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filetest.data.model.PickFileModel
import com.example.filetest.databinding.ItemFolderBinding


class FileAdapter(
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var pickFileModel: PickFileModel? = null

    fun update(pickPhotoModel: PickFileModel) {
        this.pickFileModel = pickPhotoModel
        notifyDataSetChanged()

    }
    inner  class FolderViewHolder(itemView: ItemFolderBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        internal val binding = itemView
        fun bind(position: Int) {
            binding.model = pickFileModel!!.data[position]
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

       val itemBinding= ItemFolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FolderViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FolderViewHolder ->{
                holder.bind(position)
            }
        }
    }

    override fun getItemCount(): Int = if (pickFileModel!= null){
        pickFileModel!!.data.size
    } else 0




}


