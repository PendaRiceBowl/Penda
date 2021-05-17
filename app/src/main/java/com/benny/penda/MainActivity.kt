package com.benny.penda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.benny.annotation.Builder
import com.benny.annotation.Optional
import com.benny.annotation.Required
import com.benny.penda.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

@Builder
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Required
    lateinit var name: String

    @Required
    lateinit var owner: String

    @Optional
    lateinit var url: String

    @Optional
    var time: Long = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun clickButton() {
        button1.setOnClickListener {

        }
        button2.setOnClickListener {

        }
    }
}