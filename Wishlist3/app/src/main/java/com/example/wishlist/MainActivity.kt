package com.example.wishlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar



class MainActivity : AppCompatActivity() {

    private val itemList = ArrayList<Item>()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val priceInput = findViewById<EditText>(R.id.priceInput)
        val urlInput = findViewById<EditText>(R.id.urlInput)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val addButton = findViewById<Button>(R.id.addButton)

        adapter = ItemAdapter(itemList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            val price = priceInput.text.toString()
            val url = urlInput.text.toString()

            if (name.isNotEmpty() && price.isNotEmpty() && url.isNotEmpty()) {
                itemList.add(Item(name, price, url))
                adapter.notifyItemInserted(itemList.size - 1)

                nameInput.text.clear()
                priceInput.text.clear()
                urlInput.text.clear()
            }

        }

    }
}