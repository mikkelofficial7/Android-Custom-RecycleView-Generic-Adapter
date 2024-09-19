package com.view.baseadapterexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.view.baseadapterexample.databinding.ActivityMainBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecycleView()
    }

    private fun prepareRecycleView() {
        val viewHolderFactories: Map<Int, ViewHolderFactory<out ViewDataBinding>> = mapOf(
            R.layout.item_view_holder_1 to ViewHolder1Factory(),
            R.layout.item_view_holder_2 to ViewHolder2Factory(),
        )

        val viewHolderList = ArrayList<ViewHolderMapper<out RecyclerView.ViewHolder>>()
        viewHolderList.add(ViewHolderMapper(R.layout.item_view_holder_1, ViewHolder1::class.java))
        viewHolderList.add(ViewHolderMapper(R.layout.item_view_holder_2, ViewHolder2::class.java))


        val adapter = BaseAdapter(viewHolderList, viewHolderFactories, onBindViewHolders = { viewHolder, i ->
            when (viewHolder) {
                is ViewHolder1 -> viewHolder.bindFirst()
                is ViewHolder2 -> viewHolder.bindSecond()
            }
        })

        binding.rvSample.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvSample.adapter = adapter
    }
}