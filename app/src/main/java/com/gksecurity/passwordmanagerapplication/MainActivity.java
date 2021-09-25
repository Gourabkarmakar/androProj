package com.gksecurity.passwordmanagerapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText domainname, username, password;
    Button save, update, delete, view;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        domainname = findViewById(R.id.domainname);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        save = findViewById(R.id.save);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        view = findViewById(R.id.view);

        DB = new DBHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String domiannameTxt = domainname.getText().toString();
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();

                Boolean checkinsertData = DB.insertdata(domiannameTxt, usernameTxt, passwordTxt);
                if (checkinsertData == true){
                    Toast.makeText(MainActivity.this, "New UserName Password Save", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "New Entry Not Added", Toast.LENGTH_SHORT).show();
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String domiannameTxt = domainname.getText().toString();
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();

                Boolean checkupdateData = DB.updatedata(domiannameTxt, usernameTxt, passwordTxt);
                if (checkupdateData == true){
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "New Entry Not Update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String domiannameTxt = domainname.getText().toString();


                Boolean checkdeleteData = DB.deletedata(domiannameTxt);
                if (checkdeleteData == true){
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "New Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.viewdata();
                if (res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Domain Name : "+res.getString(0)+"\n");
                    buffer.append("Username : "+res.getString(1)+"\n");
                    buffer.append("Password : "+res.getString(2)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Username & Password");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }
}