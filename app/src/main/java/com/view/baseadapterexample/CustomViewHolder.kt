package com.view.baseadapterexample

import androidx.recyclerview.widget.RecyclerView
import com.view.baseadapterexample.databinding.ItemViewHolder1Binding
import com.view.baseadapterexample.databinding.ItemViewHolder2Binding

class ViewHolder1(var item: ItemViewHolder1Binding): RecyclerView.ViewHolder(item.root) {
    fun bindFirst() {
    }
}

class ViewHolder1Factory : ViewHolderFactory<ItemViewHolder1Binding> {
    override fun create(binding: ItemViewHolder1Binding): RecyclerView.ViewHolder {
        return ViewHolder1(binding)
    }
}

class ViewHolder2(var item: ItemViewHolder2Binding): RecyclerView.ViewHolder(item.root) {
    fun bindSecond() {
    }
}


class ViewHolder2Factory : ViewHolderFactory<ItemViewHolder2Binding> {
    override fun create(binding: ItemViewHolder2Binding): RecyclerView.ViewHolder {
        return ViewHolder2(binding)
    }
}