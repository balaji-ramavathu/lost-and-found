package com.ramavathubalaji.lost_found;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {

    EditText oldpassword,newpassword,connewpassword;
    Button change;
    FirebaseAuth auth;
    FirebaseUser user;
    String oldpasswordS,newpasswordS,connewpasswordS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldpassword=(EditText) findViewById(R.id.etoldpassword);
        newpassword=(EditText) findViewById(R.id.etnewpassword);
        connewpassword=(EditText) findViewById(R.id.etconnewpassword);
        change=(Button) findViewById(R.id.btnchange);
        auth= FirebaseAuth.getInstance();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                oldpasswordS=oldpassword.getText().toString();
                newpasswordS=newpassword.getText().toString();
                connewpasswordS=connewpassword.getText().toString();
                 if(newpasswordS.equals(connewpasswordS))
                 {
                     user = auth.getInstance().getCurrentUser();

                     AuthCredential credential = EmailAuthProvider
                             .getCredential(user.getEmail(),oldpassword.getText().toString());

                     user.reauthenticate(credential)
                             .addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if (task.isSuccessful()) {
                                         user.updatePassword(newpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                             @Override
                                             public void onComplete(@NonNull Task<Void> task) {
                                                 if (task.isSuccessful())
                                                 {
                                                     Toast.makeText(getApplicationContext(), "Your password has been changed successfully", Toast.LENGTH_SHORT).show();
                                                     auth.signOut();
                                                     finish();
                                                     Intent i=new Intent(ChangePassword.this,MainActivity.class);
                                                     startActivity(i);

                                                 } else {
                                                     Toast.makeText(getApplicationContext(), "change failed", Toast.LENGTH_SHORT).show();
                                                 }
                                             }
                                         });
                                     } else {
                                         Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             });
                 }
                else
                 {
                     connewpassword.setError("Should match with previous field!");
                 }


            }
        });

    }
    /*public void change(View v,String news)
    {
        user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            user.updatePassword(news).addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(Task<Void> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(), "Your password has been changed successfully", Toast.LENGTH_SHORT).show();
                        auth.signOut();
                        finish();
                        Intent i=new Intent(ChangePassword.this,MainActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Your password could not be changed.", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }*/

}
