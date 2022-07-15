package huuphu.aprotrain.ass_android;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    long insert_user(User user);
    @Update
    int update_user(User user);
    @Delete
    int delete_user(User user);
    @Query("SELECT * FROM user")
    List<User> getAll_user();
    @Query("SELECT * FROM user WHERE id = :id")
    User find_user(int id);

}
