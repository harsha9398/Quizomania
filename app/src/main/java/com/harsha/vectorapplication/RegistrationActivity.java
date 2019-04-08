package com.harsha.vectorapplication;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userEmail, userPassword;
    private Button  registration;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    String name, email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        StorageReference storageReference = firebaseStorage.getReference();

        userName = (EditText)findViewById(R.id.etusername);
        userEmail = (EditText)findViewById(R.id.etuseremail);
        userPassword = (EditText)findViewById(R.id.etuserpassword);
        registration = (Button)findViewById(R.id.btnregister);
        userLogin = (TextView)findViewById(R.id.tvLogin);
    }

    public void register(View view){
        if(validate()){
            //Create Database
            String user_email = userEmail.getText().toString().trim();
            String user_password = userPassword.getText().toString().trim();
            firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    public void Login(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public Boolean validate(){
        Boolean result = false;

        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT ).show();
        }
        else{
            result = true;
        }
        return result;

    }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(name, email);
        myRef.setValue(userProfile);

    }
}

