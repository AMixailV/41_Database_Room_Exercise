package ru.mixail_akulov.a41_database_room_exercise.model.boxes.room.entities

import ru.mixail_akulov.a41_database_room_exercise.model.boxes.entities.Box

// todo #15: Define BoxDbEntity
//          - fields: id, colorName, colorValue.
//          - add toBox() method for mapping BoxDbEntity instances to Box instances.
//          - hint: use the same annotations as for AccountDbEntity (@Entity, @PrimaryKey, @ColumnInfo).
//          - DO NOT FORGET to add this entity to the @Database annotation of AppDatabase class
class BoxDbEntity {
    fun toBox(): Box = TODO()
}
