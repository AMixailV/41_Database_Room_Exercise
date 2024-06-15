package ru.mixail_akulov.a41_database_room_exercise.model.boxes.room

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import ru.mixail_akulov.a41_database_room_exercise.model.accounts.AccountsRepository
import ru.mixail_akulov.a41_database_room_exercise.model.boxes.BoxesRepository
import ru.mixail_akulov.a41_database_room_exercise.model.boxes.entities.Box
import ru.mixail_akulov.a41_database_room_exercise.model.boxes.entities.BoxAndSettings
import ru.mixail_akulov.a41_database_room_exercise.model.room.wrapSQLiteException

class RoomBoxesRepository(
    private val accountsRepository: AccountsRepository,
    private val boxesDao: BoxesDao,
    private val ioDispatcher: CoroutineDispatcher
) : BoxesRepository {

    override suspend fun getBoxesAndSettings(onlyActive: Boolean): Flow<List<BoxAndSettings>> {
        return accountsRepository.getAccount()
            .flatMapLatest { account ->
                if (account == null) return@flatMapLatest flowOf(emptyList())
                queryBoxesAndSettings(account.id)
            }
            .mapLatest { boxAndSettings ->
                if (onlyActive) {
                    boxAndSettings.filter { it.isActive }
                } else {
                    boxAndSettings
                }
            }
    }

    override suspend fun activateBox(box: Box) = wrapSQLiteException(ioDispatcher) {
        setActiveFlagForBox(box, true)
    }

    override suspend fun deactivateBox(box: Box) = wrapSQLiteException(ioDispatcher) {
        setActiveFlagForBox(box, false)
    }

    private fun queryBoxesAndSettings(accountId: Long): Flow<List<BoxAndSettings>> {
        TODO("#19: fetch boxes and settings from BoxesDao and map them to the " +
                "list of BoxAbdSettings instances")
    }

    private suspend fun setActiveFlagForBox(box: Box, isActive: Boolean) {
        // todo #20: get the current account (throw AuthException if there is no logged-in user)
        //           and then use BoxesDao to activate/deactivate the box for the current account
    }
}