package com.nalvarte.maryurit.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalvarte.maryurit.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import kotlinx.coroutines.launch

class Ejercicio01 : AppCompatActivity() {
    private lateinit var binding: ActivityEjercicio01Binding
    private lateinit var adapter: TeacherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadTeachers()
    }

    // En Ejercicio01.kt
    private fun setupRecyclerView() {
        binding.rvTeachers.layoutManager = LinearLayoutManager(this)
        adapter = TeacherAdapter(
            emptyList(),
            onItemClick = { teacher ->
                // Llamar al profesor
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${teacher.phoneNumber}")
                }
                startActivity(intent)
            },
            onLongItemClick = { teacher ->
                // Enviar email
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${teacher.email}")
                }
                startActivity(intent)
            }
        )
        binding.rvTeachers.adapter = adapter
    }

    private fun loadTeachers() {
        lifecycleScope.launch {
            try {
                val teachers = RetrofitClient.instance.getTeachers()
                adapter = TeacherAdapter(
                    teachers,
                    onItemClick = { teacher -> callTeacher(teacher.phoneNumber) },
                    onLongItemClick = { teacher -> sendEmail(teacher.email) }
                )
                binding.rvTeachers.adapter = adapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun callTeacher(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }

    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
        }
        startActivity(intent)
    }
}