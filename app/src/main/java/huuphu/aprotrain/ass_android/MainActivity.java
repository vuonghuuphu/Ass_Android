package huuphu.aprotrain.ass_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText edName,edDes;
    Spinner spinner;
    CheckBox checkBox;
    Button btnRegister;
    String gender = "Male";
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = AppDatabase.getAppDatabase(this);

        edName = findViewById(R.id.ed_username);
        spinner = findViewById(R.id.spinner);
        checkBox = findViewById(R.id.ck);
        btnRegister = findViewById(R.id.btn_register);
        edDes = findViewById(R.id.ed_description);

        String[] listgenser = {"Male","Female","Unknow"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listgenser
                );
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister();
            }
        });
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "onItemSelected :" + listgenser[i]);
                gender = listgenser[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void onRegister(){
        Toast.makeText(this,"Register",Toast.LENGTH_SHORT).show();
if (!validate()){
    return;
}
        User user = new User();
        user.username = edName.getText().toString();
        user.des = edDes.getText().toString();
        user.gender = gender;
        long id = database.userDao().insert_user(user);
        if (id>0){
            Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
        }
        goList();
    }

    private void goList() {
        Intent intent = new Intent(this,ListUser.class);
        startActivity(intent);
    }

    private boolean validate() {
        String mes = null;
        if (edName.getText().toString().trim().isEmpty()){
            mes = "Chưa nhập username";
        }else if (edDes.getText().toString().trim().isEmpty()){
            mes = "Chưa nhập giới thiệu";
        }else if (!checkBox.isChecked()){
            mes = "Bạn phải đồng ý điều khoản sử dụng";
        }
        if (mes != null){
            Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}