package me.statuspages.db

data class Todo (
    val id: Int,
    val title: String,
    val completed: Boolean,
    val order: Int
)
