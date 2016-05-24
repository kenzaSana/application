package com.example.kenzack.applicationtest.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kenzack.applicationtest.R;

public class wop extends AppCompatActivity {
    Button cnx,insp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wop);
        cnx=(Button)findViewById(R.id.button);
        insp=(Button)findViewById(R.id.button3);
        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(wop.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        insp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(wop.this,formulaire.class);
                startActivity(intent);

            }
        });
    }
}
