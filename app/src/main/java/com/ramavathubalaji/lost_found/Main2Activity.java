package com.ramavathubalaji.lost_found;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    Button lost,found;
    public String lf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lost=(Button) findViewById(R.id.lostbtn);
        found=(Button) findViewById(R.id.foundbtn);

        lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(getApplicationContext(), Main22Activity.class);
                lf="lost";

                Global a =Global.getInstance();

                i.putExtra("lf",lf);
                startActivity(i);


            }
        });
        found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(getApplicationContext(), Main22Activity.class);
                lf="found";

                Global a =Global.getInstance();

                a.setlf(lf);
                i.putExtra("lf",lf);
                startActivity(i);


            }
        });



    }
}
