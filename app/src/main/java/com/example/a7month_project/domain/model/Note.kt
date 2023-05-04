package com.example.a7month_project.domain.model

class Note (
    val id: Int = DEFAULT_ID,
    val title: String,
    val desc: String,
) {
    companion object{
        const val DEFAULT_ID = 0
    }
}