package ru.otus.hw.database

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.otus.hw.database.tables.HabitObject

    fun transactionMock(): List<String> {
        val connect = SQLLiteConnector.db
        val connection = connect.connector.invoke()

        transaction {

            SchemaUtils.create(HabitObject)

            HabitObject.insert {
                it[habit_name] = "create and habit list"
                it[habit_value] =
                    "{\"name\": \"first habit\", \"name2\": \"Не забывать про состояние базы данных\", \"name3\": \"third habit\"}"
            }

            HabitObject.insert {
                it[habit_name] = "delete habit"
                it[habit_value] =
                    "{\"name2\": \"Не забывать про состояние базы данных\", \"name3\": \"third habit\"}"
            }
        }

        val transactionMockCreateAndList = transaction {
            HabitObject.slice(HabitObject.habit_value)
                .select { HabitObject.habit_name eq "create and habit list" }
                .map { it[HabitObject.habit_value] }.toString().replace("[", "")
                .replace("]", "")
        }

        val transactionMockDelete = transaction {
            HabitObject.slice(HabitObject.habit_value)
                .select { HabitObject.habit_name eq "delete habit" }
                .map { it[HabitObject.habit_value] }.toString().replace("[", "")
                .replace("]", "")
        }

        connection.close()

        return listOf(transactionMockCreateAndList, transactionMockDelete)
}