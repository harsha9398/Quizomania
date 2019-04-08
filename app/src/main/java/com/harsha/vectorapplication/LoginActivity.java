package com.harsha.vectorapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    public EditText Name;
    private EditText Password;
    private TextView Info;
    public Button Login;
    private TextView Registration;
    private int counter = 5;
    private FirebaseAuth firebaseAuth;
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        Registration = (TextView)findViewById(R.id.tvRegistration);

        Info.setText("No. of attempts remaining: 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new Dialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Name.getText().toString().isEmpty() || Password.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this, "Enter All Details", Toast.LENGTH_SHORT ).show();
                }
                else
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    public void registration(View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    private void validate(String userName, String userPassword){
//
        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("No. of attempts left = "+counter);
                    if(counter == 0){
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }

}
