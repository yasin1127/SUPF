package com.example.simpleuserprofilefetcher

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private val viewModel: UserProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewName: TextView = findViewById(R.id.textViewName)
        val textViewUsername: TextView = findViewById(R.id.textViewUsername)
        val textViewEmail: TextView = findViewById(R.id.textViewEmail)
        val textViewPhone: TextView = findViewById(R.id.textViewPhone)
        val textViewAddress: TextView = findViewById(R.id.textViewAddress)
        val textViewError: TextView = findViewById(R.id.textViewError)

        viewModel.userProfile.observe(this, Observer { userProfile ->
            if (userProfile != null) {
                textViewName.text = userProfile.name
                textViewUsername.text = userProfile.username
                textViewEmail.text = userProfile.email
                textViewPhone.text = userProfile.phone
                textViewAddress.text = "${userProfile.address.street}, ${userProfile.address.city}"
                textViewError.visibility = View.GONE
            } else {
                textViewError.visibility = View.VISIBLE
                textViewError.text = "Failed to load user profile"
            }
        })

        viewModel.loadUserProfile()
    }
}
