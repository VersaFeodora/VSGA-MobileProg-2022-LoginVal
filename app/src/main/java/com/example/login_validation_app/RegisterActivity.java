package com.example.login_validation_app;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;

import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    EditText editUsername, editPassword, editEmail, editName, editSchool, editAddress;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Register");
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editEmail = findViewById(R.id.editEmail);
        editName = findViewById(R.id.editNamaLengkap);
        editSchool = findViewById(R.id.editAsalSekolah);
        editAddress = findViewById(R.id.editAlamat);
        save = findViewById(R.id.btnSimpan);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidation()) {
                    saveFileData();
                } else {
                    Toast.makeText(RegisterActivity.this, "Please fill all data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    boolean isValidation() {
        if (editUsername.getText().toString().equals("") ||
                editPassword.getText().toString().equals("") || editEmail.getText().toString().equals("") || editName.getText().toString().equals("") || editSchool.getText().toString().equals("") || editAddress.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    void saveFileData() {
        String isiFile = editUsername.getText().toString() + ";" + editPassword.getText().toString() + ";" + editEmail.getText().toString() + ";" + editName.getText().toString() + ";" + editSchool.getText().toString() + ";" + editAddress.getText().toString();
        File file = new File(getFilesDir(), editUsername.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}
