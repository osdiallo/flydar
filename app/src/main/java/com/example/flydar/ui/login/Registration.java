package com.example.flydar.ui.login;

import android.content.Intent;
import android.app.Activity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;
import com.example.flydar.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Registration extends AppCompatActivity{
    ProgressBar progressBar;
    private EditText signupEmail, signupPass, signupPhone;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressBar = new ProgressBar(this);
        signupEmail = findViewById(R.id.username);
        signupPass = findViewById(R.id.password);
        signupPhone = findViewById(R.id.phone_num);
        btnRegister = findViewById(R.id.register_user);

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String emailText = signupEmail.getText().toString();
                String passText = signupPass.getText().toString();
                String phoneText = signupPhone.getText().toString();

                if(TextUtils.isEmpty(passText) || passText.length() < 5){
                    signupPass.setError("Password must be at least 5 characters");
                    return;
                } else if(TextUtils.isEmpty(emailText) || !emailText.contains("@")){
                    signupEmail.setError("Invalid email address");
                    return;
                } else if(TextUtils.isEmpty(phoneText) || phoneText.length() < 10){
                    signupPhone.setError("Invalid phone number");
                    return;
                }

                submitForm(emailText, passText, phoneText);
            }
        });
    }

    private void submitForm(String newEmail, String newPass, String newPhone){
        JSONObject newUser = new JSONObject();

        try{
            newUser.put("email", newEmail);
            newUser.put("password", newPass);
            newUser.put("phone", newPhone);

        } catch (JSONException e){
            // catch block
            e.printStackTrace();
        }
    }

}
