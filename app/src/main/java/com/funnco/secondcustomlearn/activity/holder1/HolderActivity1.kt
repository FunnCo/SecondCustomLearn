package com.funnco.secondcustomlearn.activity.holder1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.funnco.secondcustomlearn.databinding.ActivityHolder1Binding

class HolderActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivityHolder1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHolder1Binding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}