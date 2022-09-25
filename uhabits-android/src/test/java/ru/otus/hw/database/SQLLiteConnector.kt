package ru.otus.hw.database

import org.jetbrains.exposed.sql.Database

object SQLLiteConnector {
    val db by lazy {
        Database.connect("jdbc:sqlite:file:habit-database?mode=memory&cache=shared", "org.sqlite.JDBC")
    }
}