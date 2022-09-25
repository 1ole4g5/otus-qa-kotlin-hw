package ru.otus.hw.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object HabitObject : IntIdTable("habit_object") {
    val habit_name = varchar("habit_name", 30)
    val habit_value = varchar("habit_value", 100)
}