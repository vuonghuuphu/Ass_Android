package huuphu.aprotrain.ass_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditUser extends AppCompatActivity {
EditText EditName,EditGender,EditDescription;
Button btnEdit,btnDelete;
    AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Intent intent = getIntent();
        int id =  intent.getIntExtra("id_select",0);

        database = AppDatabase.getAppDatabase(this);
        EditName = findViewById(R.id.ed_editname);
        EditGender = findViewById(R.id.ed_editgender);
        EditDescription = findViewById(R.id.ed_editDescription);
        btnEdit = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        User user = database.userDao().find_user(id);
        EditName.setText(user.username);
        EditGender.setText(user.gender);
        EditDescription.setText(user.des);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User Useredit = new User();
                Useredit.id = user.id;
                Useredit.username = EditName.getText().toString();
                Useredit.gender = EditGender.getText().toString();
                Useredit.des = EditDescription.getText().toString();
                int id = database.userDao().update_user(Useredit);
                Intent intent = new Intent(view.getContext(),ListUser.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = database.userDao().delete_user(user);
                Intent intent = new Intent(view.getContext(),ListUser.class);
                startActivity(intent);
            }
        });
    }
}