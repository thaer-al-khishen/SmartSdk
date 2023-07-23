package com.example.smartsdk

//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewbinding.ViewBinding
//
//class GenericAdapter<T, VB : ViewBinding>(
//    private val layoutInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
//    private val areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
//    private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean,
//    private val bind: VB.(item: T) -> Unit
//) : ListAdapter<T, GenericAdapter.GenericViewHolder<T, VB>>(
//    object : DiffUtil.ItemCallback<T>() {
//        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
//            return areItemsTheSame(oldItem, newItem)
//        }
//
//        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
//            return areContentsTheSame(oldItem, newItem)
//        }
//    }
//) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T, VB> {
//        return GenericViewHolder(
//            layoutInflater.invoke(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            ), bind
//        )
//    }
//
//    override fun onBindViewHolder(holder: GenericViewHolder<T, VB>, position: Int) {
//        holder.bind(getItem(position))
//    }
//
//    class GenericViewHolder<T, VB : ViewBinding>(
//        private val binding: VB,
//        private val bind: VB.(item: T) -> Unit
//    ) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: T) {
//            binding.bind(item)
//        }
//    }
//
//}
