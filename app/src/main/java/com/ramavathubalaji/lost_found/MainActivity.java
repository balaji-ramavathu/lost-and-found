package com.ramavathubalaji.lost_found;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button login,signup;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        //if the objects getcurrentuser method is not null
        //means user is already logged in
        /*if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
        }*/

        email=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.loginbtn);
        signup=(Button) findViewById(R.id.signupbtn);

        email.setText("balaji@gmail.com");
        password.setText("qwert123");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);*/
                userLogin();
            }
        });

    }

    private void userLogin()
    {
        final String emailS = email.getText().toString().trim();
        final String passwordS  = password.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(emailS)){
            //Toast.makeText(MainActivity.this,"Please enter email",Toast.LENGTH_LONG).show();
            email.setError("Please enter email");
            return;
        }

        if(TextUtils.isEmpty(passwordS)){
            ///Toast.makeText(MainActivity.this,"Please enter password",Toast.LENGTH_LONG).show();
            password.setError("Please enter password");
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog
        Toast.makeText(MainActivity.this,"Logging you in...",Toast.LENGTH_LONG).show();


        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(emailS, passwordS)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), Main22Activity.class));
                        }
                        else
                        {
                            if(!(emailS.equals(null)||passwordS.equals(null)))
                            {
                                Toast.makeText(MainActivity.this,"User doesn't exist...",Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                });

    }
}
