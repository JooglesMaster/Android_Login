package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;

    private final String username = "Admin";
    private final String password = "123456";

    boolean isValid = false;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);
        eLogin = findViewById(R.id.btnLogin);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if (inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                } else {

                    // calls the is valid function
                    isValid = validate(inputName, inputPassword);


                    // if the valid is false
                    if (!isValid) {

                        // counter goes down
                        counter--;
                        Toast.makeText(MainActivity.this, "incorrect credentials !!!", Toast.LENGTH_SHORT).show();

                        eAttemptsInfo.setText("Number of attempts remaining:" + counter);

                        if (counter == 0) {
                            eLogin.setEnabled(false);
                        }

                        // else it will go to the next activity
                    }else{
                        Toast.makeText(MainActivity.this, "Login successful!!!", Toast.LENGTH_SHORT).show();
                        // the intent is a class you input the class your on to going.
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }

                }
            }


        });


    }
    private boolean validate(String name, String password){
        // using the equal function
        if(name.equals(username) && password.equals(password)){
            return true;
        }
        return false;
    }
}