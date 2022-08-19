package com.example.login_validation_app;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {
    public static final String FILENAME = "login";
    EditText editUsername, editPassword;
    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        login = findViewById(R.id.action_login);
        register = findViewById(R.id.action_register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editUsername.getText().toString().equals("") || editPassword.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Fill the username/password first!", Toast.LENGTH_SHORT).show();
                } else{
                    login();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    void saveFileLogin() {
        String isiFile = editUsername.getText().toString() + ";" + editPassword.getText().toString();
        File file = new File(getFilesDir(), FILENAME);

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
        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
    void login() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, editUsername.getText().toString());
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br =
                        new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");

            if (dataUser[1].equals(editPassword.getText().toString())) {
                saveFileLogin();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Password is not matched", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "User is not found", Toast.LENGTH_SHORT).show();
        }
    }
}
