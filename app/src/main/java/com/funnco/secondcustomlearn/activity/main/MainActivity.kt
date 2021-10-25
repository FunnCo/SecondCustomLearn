package com.funnco.secondcustomlearn.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.funnco.secondcustomlearn.activity.holder1.HolderActivity1
import com.funnco.secondcustomlearn.activity.holder2.HolderActivity2
import com.funnco.secondcustomlearn.databinding.ActivityMainBinding

import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.arcProgress1.setSectorsCount(1000)
        binding.arcProgress1.setUpdateTime(250)
        binding.arcProgress1.runUpdateLoop()

        binding.arcProgress2.setMaxValue(1200)
        binding.arcProgress2.setValue(1200)

        binding.buttonRandomize1.setOnClickListener {
            binding.arcProgress2.setValue(Random.nextInt(1200))
        }

        binding.buttonRandomize2.setOnClickListener {
            binding.barProgress.setValue(Random.nextInt(100))
        }

        binding.coffeeProgress1.setMaxSteps(500)

        binding.buttonRandomize3.setOnClickListener {
            binding.coffeeProgress1.setCurrentStep(Random.nextInt(500))
        }

        binding.buttonGotoholder1.setOnClickListener {
            startActivity(Intent(this, HolderActivity1::class.java))
        }
    }
}