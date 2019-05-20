package natto.com.roomsmple

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(entity: Item)

    @get:Query("SELECT * FROM Item")
    val all: List<Item>
}