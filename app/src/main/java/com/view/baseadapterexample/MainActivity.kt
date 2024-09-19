package com.view.baseadapterexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.view.baseadapterexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecycleView()
    }

    fun prepareRecycleView() {
        val viewHolderList = ArrayList<ViewHolderMapper<RecyclerView.ViewHolder>>()
        viewHolderList.add(ViewHolderMapper(R.layout.item_view_holder_1, ViewHolder1::class.java))
        viewHolderList.add(ViewHolderMapper(R.layout.item_view_holder_2, ViewHolder2::class.java))
        viewHolderList.add(ViewHolderMapper(R.layout.item_view_holder_1, ViewHolder1::class.java))
        viewHolderList.add(ViewHolderMapper(R.layout.item_view_holder_2, ViewHolder2::class.java))

        val adapter = BaseAdapter(viewHolderList, onBindViewHolders = { viewHolder, i ->
            when (viewHolder) {
                is ViewHolder1 -> viewHolder.bindFirst()
                is ViewHolder2 -> viewHolder.bindSeconde()
            }
        })

        binding.rvSample.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvSample.adapter = adapter
    }
}