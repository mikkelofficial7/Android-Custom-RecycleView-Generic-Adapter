package com.view.baseadapterexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.view.baseadapterexample.databinding.ItemViewHolder1Binding

class BaseAdapter(
    private var listViewHolder: ArrayList<ViewHolderMapper<out RecyclerView.ViewHolder>>,
    private val viewHolderFactories: Map<Int, ViewHolderFactory<out ViewDataBinding>>,
    private val onBindViewHolders: (RecyclerView.ViewHolder, Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        val viewHolderItem = listViewHolder.find { it.layoutId == viewType }!!
        return createViewHolder(binding, viewHolderItem.layoutId)
    }

    override fun getItemCount(): Int {
        return listViewHolder.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHolders(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return listViewHolder[position].layoutId
    }

    @Suppress("UNCHECKED_CAST")
    private fun createViewHolder(binding: ViewDataBinding, layoutId: Int): RecyclerView.ViewHolder {
        val factory = viewHolderFactories[layoutId]
        return (factory as ViewHolderFactory<ViewDataBinding>).create(binding)
    }
}

interface ViewHolderFactory<VB : ViewDataBinding> {
    fun create(binding: VB): RecyclerView.ViewHolder
}


data class ViewHolderMapper<VH : RecyclerView.ViewHolder>(
    @LayoutRes var layoutId: Int,
    val viewholderClass: Class<VH>
)