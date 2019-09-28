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
import android.text.TextWatcher;
import com.example.flydar.R;
import com.example.flydar.MainActivity;

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

        signupEmail.addTextChangedListener(new viewTextWatcher());
        signupPass.addTextChangedListener(new viewTextWatcher());
        signupPhone.addTextChangedListener(new viewTextWatcher());

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String emailText = signupEmail.getText().toString();
                String passText = signupPass.getText().toString();
                String phoneText = signupPhone.getText().toString();

                if(TextUtils.isEmpty(passText) || passText.length() < 5){
                    signupPass.setError("Password must be at least 5 characters");
                    return;
                } else if(TextUtils.isEmpty(emailText) || !emailText.contains("@") ||
                            !emailText.contains(".com")){
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

    private class viewTextWatcher implements TextWatcher{
        public void beforeTextChanged(CharSequence charSequence, int i1, int i2, int i3){}
        public void onTextChanged(CharSequence charSequence, int i1, int i2, int i3){
            checkRequiredFields();
        }
        public void afterTextChanged(Editable editable){}
    }

    private void checkRequiredFields(){
        String emailField = signupEmail.getText().toString();
        String passField = signupPass.getText().toString();
        String phoneField = signupPhone.getText().toString();

        if(!TextUtils.isEmpty(emailField) && !TextUtils.isEmpty(passField)
                && !TextUtils.isEmpty(phoneField)){
            btnRegister.setEnabled(true);
        } else {
            btnRegister.setEnabled(false);
        }

    }

    private void submitForm(String newEmail, String newPass, String newPhone){
        JSONObject newUser = new JSONObject();
        JSONArray userDB = new JSONArray();

        try{
            newUser.put("email", newEmail);
            newUser.put("password", newPass);
            newUser.put("phone", newPhone);

            btnRegister.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            });

        } catch (JSONException e){
            // catch block
            e.printStackTrace();
        }
    }

}
