package huuphu.aprotrain.ass_android;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;
    public abstract UserDao userDao();

    public static AppDatabase getAppDatabase(Context context){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context,AppDatabase.class,"user.db").allowMainThreadQueries().build();
        }
        return appDatabase;
    }


}
