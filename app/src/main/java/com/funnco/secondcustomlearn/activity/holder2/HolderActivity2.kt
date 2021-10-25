package com.funnco.secondcustomlearn.activity.holder2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.funnco.secondcustomlearn.R
import com.funnco.secondcustomlearn.databinding.ActivityHolder2Binding

class HolderActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityHolder2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHolder2Binding.inflate(layoutInflater)

        setContentView(binding.root)
    }

}