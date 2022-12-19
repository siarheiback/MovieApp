package happigin.inc.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import happigin.inc.data.database.dao.MovieDao
import happigin.inc.data.database.entity.MovieEntity

@Database(
    entities = [MovieEntity::class], version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getMovieDao() : MovieDao

    companion object {
        @Volatile
        private var database: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "database"
                ).build()
                database as AppDatabase
            } else database as AppDatabase
        }
    }
}