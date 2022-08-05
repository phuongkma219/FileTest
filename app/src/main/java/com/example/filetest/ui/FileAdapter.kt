package com.example.filetest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filetest.R
import com.example.filetest.data.model.FileModel
import com.example.filetest.data.model.FileModelDataType
import com.example.filetest.data.model.PickFileModel
import com.example.filetest.databinding.ItemFileBinding
import com.example.filetest.databinding.ItemFolderBinding


class FileAdapter(
    private val context: Context,
    private val listener: ListenerClickItem
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
            binding.constRoot.setOnClickListener {
                listener.onClickFileItem(position,pickFileModel!!.fileModelDataType,pickFileModel!!.data[position])
            }

        }
    }
    inner  class FileViewHolder(itemView: ItemFileBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        internal val binding = itemView
        fun bind(position: Int) {
            binding.model = pickFileModel!!.data[position]

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == FileModelDataType.LoadFolder.ordinal){
            FolderViewHolder(ItemFolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
        }
        else{
            FileViewHolder(ItemFileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FolderViewHolder ->{
                holder.bind(position)
            }
            is FileViewHolder ->{
                holder.bind(position)
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (pickFileModel != null) {
            pickFileModel!!.fileModelDataType.ordinal
        } else {
            FileModelDataType.LoadFolder.ordinal
        }
    }

    override fun getItemCount(): Int = if (pickFileModel!= null){
        pickFileModel!!.data.size
    } else 0

    interface ListenerClickItem {
        fun onClickFileItem(
            position: Int,
            fileModelDataType: FileModelDataType,
            fileModel: FileModel
        )
    }


}


