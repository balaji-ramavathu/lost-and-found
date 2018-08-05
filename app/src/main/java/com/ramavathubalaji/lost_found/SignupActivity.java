package com.ramavathubalaji.lost_found;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText email,password,conpassword;
    Button submit;
    private FirebaseAuth firebaseAuth;
    String emailS,passwordS,conpasswordS;
    private void showError(EditText e,String error) {
        e.setError(error);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();

        email=(EditText) findViewById(R.id.signupEmail);
        password=(EditText) findViewById(R.id.signupPassword);
        conpassword=(EditText) findViewById(R.id.signupConPassword);
        submit=(Button) findViewById(R.id.signupSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                passwordS  = password.getText().toString();
                conpasswordS=conpassword.getText().toString();
                emailS = email.getText().toString();
                if(!passwordS.equals(conpasswordS))
                {
                    conpassword.setError("Should match with previous field!");
                }
                else
                {
                    registerUser();
                }

            }
        });



    }


    private void registerUser()
    {

        //getting email and password from edit texts


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(emailS)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(passwordS)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog
        Toast.makeText(this,"Registering Please Wait...",Toast.LENGTH_LONG).show();
        //progressDialog.setMessage("Registering Please Wait...");
        //progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(emailS, passwordS)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(SignupActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            finish();
                        }else{
                            //display some message here
                            Toast.makeText(SignupActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        //progressDialog.dismiss();
                    }
                });

    }

   /* @Override
    public void onClick(View view)
    {
        //calling register method on click
        registerUser();
    }*/
}

