package com.nalvarte.maryurit.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nalvarte.maryurit.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    private val teachers: List<Teacher>,
    private val onItemClick: (Teacher) -> Unit,
    private val onLongItemClick: (Teacher) -> Unit
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(val binding: ItemTeacherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(teacher: Teacher) {
            binding.tvName.text = "${teacher.name} ${teacher.lastName}"
            binding.tvEmail.text = teacher.email

            Glide.with(itemView.context)
                .load(teacher.imageUrl)
                .placeholder(R.drawable.ic_person_placeholder) // Drawable alternativo
                .error(R.drawable.ic_broken_image) // Para errores de carga
                .circleCrop() // Opcional: imagen circular
                .into(binding.ivTeacher)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.bind(teacher)

        holder.itemView.setOnClickListener { onItemClick(teacher) }
        holder.itemView.setOnLongClickListener {
            onLongItemClick(teacher)
            true
        }
    }

    override fun getItemCount() = teachers.size
}