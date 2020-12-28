package com.sylko.petproject1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Неизменяемый класс модели для покупки.
 *
 * @param name - наименование
 * @param cost - цена
 * @param num - количество
 * @param uid - id
 */

@Entity
data class Sale (
        @PrimaryKey
        var uid: String,
        var name: String,
        var cost: Double,
        var num: Int
)
