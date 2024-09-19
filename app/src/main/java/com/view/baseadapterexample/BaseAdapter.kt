package com.view.baseadapterexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter(
    private var listViewHolder: List<ViewHolderMapper<RecyclerView.ViewHolder>> = listOf(),
    private val onBindViewHolders: (RecyclerView.ViewHolder, Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val viewHolderMapper = listViewHolder.find { it.layoutId == viewType }
        return createViewHolder(view, viewHolderMapper?.viewholderClass?.canonicalName.toString())
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

    private fun createViewHolder(view: View, viewHolderClassName: String): RecyclerView.ViewHolder {
        return try {
            val viewHolderClass = Class.forName(viewHolderClassName).asSubclass(RecyclerView.ViewHolder::class.java)
            val constructor = viewHolderClass.getConstructor(View::class.java)

            constructor.newInstance(view) as RecyclerView.ViewHolder
        } catch (e: Exception) {
            throw IllegalArgumentException("Could not create ViewHolder: $viewHolderClassName", e)
        }
    }
}

data class ViewHolderMapper<out VH: RecyclerView.ViewHolder>(
    @LayoutRes var layoutId: Int,
    var viewholderClass: Class<out @UnsafeVariance VH>
)

class ViewHolder1(var view: View): RecyclerView.ViewHolder(view) {
    fun bindFirst() {

    }
}

class ViewHolder2(var view: View): RecyclerView.ViewHolder(view) {
    fun bindSeconde() {

    }
}
