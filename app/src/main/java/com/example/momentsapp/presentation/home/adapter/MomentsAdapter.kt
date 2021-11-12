package com.example.momentsapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.momentsapp.R
import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.databinding.MomentItemBinding


class MomentsAdapter : RecyclerView.Adapter<MomentsAdapter.MomentViewHolder>() {
    inner class MomentViewHolder(itemView: MomentItemBinding) :
        RecyclerView.ViewHolder(itemView.root)


    private val differCallBack = object : DiffUtil.ItemCallback<Moment>() {
        override fun areItemsTheSame(oldItem: Moment, newItem: Moment): Boolean {
            return oldItem.photo == newItem.photo
        }

        override fun areContentsTheSame(oldItem: Moment, newItem: Moment): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentViewHolder {
        val itemBinding =
            MomentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MomentViewHolder(
            itemBinding
        )
    }

    override fun onBindViewHolder(holder: MomentViewHolder, position: Int) {
        val moment = differ.currentList[position]
        holder.itemView.apply {
           // Glide.with(this).load(moment.photo).into()
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Moment) -> Unit)? = null

    fun setOnItemClickListener(listener: (Moment) -> Unit) {
        onItemClickListener = listener
    }
}