package ru.mixail_akulov.a41_database_room_exercise

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.mixail_akulov.a41_database_room_exercise.model.accounts.AccountsRepository
import ru.mixail_akulov.a41_database_room_exercise.model.accounts.room.RoomAccountsRepository
import ru.mixail_akulov.a41_database_room_exercise.model.boxes.BoxesRepository
import ru.mixail_akulov.a41_database_room_exercise.model.boxes.room.RoomBoxesRepository
import ru.mixail_akulov.a41_database_room_exercise.model.room.AppDatabase
import ru.mixail_akulov.a41_database_room_exercise.model.settings.AppSettings
import ru.mixail_akulov.a41_database_room_exercise.model.settings.SharedPreferencesAppSettings

object Repositories {

    private lateinit var applicationContext: Context

    // -- stuffs

    private val database: AppDatabase by lazy<AppDatabase> {
        TODO("#21: Create an AppDatabase instance by using Room.databaseBuilder static method. " +
                "Use createFromAssets method to initialize a new database from the pre-packaged SQLite file from assets")
    }

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    private val appSettings: AppSettings by lazy {
        SharedPreferencesAppSettings(applicationContext)
    }

    // --- repositories

    val accountsRepository: AccountsRepository by lazy {
        RoomAccountsRepository(TODO("#22: Use AccountsDao here from AppDatabase"), appSettings, ioDispatcher)
    }

    val boxesRepository: BoxesRepository by lazy {
        RoomBoxesRepository(accountsRepository, TODO("#23: Use BoxesDao here from AppDatabase"), ioDispatcher)
    }

    /**
     * Call this method in all application components that may be created at app startup/restoring
     * (e.g. in onCreate of activities and services)
     */
    fun init(context: Context) {
        applicationContext = context
    }
}