package com.example.codepathmail

import EmailAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var emails: List<Email>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Lookup the RecyclerView in activity layout
        val emailsRv = findViewById<RecyclerView>(R.id.Emails)
        // Fetch the list of emails
        emails = EmailFetcher.getEmails()
        // Create adapter passing in the list of emails
        val adapter = EmailAdapter(emails)
        // Attach the adapter to the RecyclerView to populate items
        emailsRv.adapter = adapter
        // Set layout manager to position the items
        emailsRv.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.loadMoreBtn).setOnClickListener {
            // Fetch next 5 emails
            val newEmails = EmailFetcher.getNext5Emails()
            // Add new emails to existing list of emails
            emails.addAll(newEmails)
            // Notify the adapter there's new emails so the RecyclerView layout is updated
            adapter.notifyDataSetChanged()
        }

    }
}

private fun List<Email>.addAll(emails: MutableList<Email>) {}



