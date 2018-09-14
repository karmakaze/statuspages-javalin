package me.statuspages.db

data class TodoRecord (
    val id: Int,
    val title: String?,
    val completed: Boolean?,
    val item_order: Int?
)
