package huuphu.aprotrain.ass_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class ListUser extends AppCompatActivity {
    RecyclerView rvUser;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        appDatabase = AppDatabase.getAppDatabase(this);
        List<User> list = appDatabase.userDao().getAll_user();

        UserAdapter adapter = new UserAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rvUser = findViewById(R.id.rv_user);
        rvUser.setLayoutManager(layoutManager);
        rvUser.setAdapter(adapter);

    }
}