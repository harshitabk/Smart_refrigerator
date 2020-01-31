package com.harshita.sit.backup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt;
    EditText etUsn,etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsn=findViewById(R.id.editTextUserName);
        etPwd=findViewById(R.id.editTextPassword);

        bt=findViewById(R.id.button1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usn=etUsn.getText().toString();
                String pwd=etPwd.getText().toString();
                if (usn.equals(pwd)){
                    Toast.makeText(getApplicationContext(),"Login Succesfull",Toast.LENGTH_LONG).show();
                    Intent intent1=new Intent(MainActivity.this,MainActivity_grid.class);
                    startActivity(intent1);
                }else{
                    Toast.makeText(getApplicationContext(), "Password incorrect",Toast.LENGTH_LONG).show();

                }
                etUsn.setText(null);
                etPwd.setText(null);
            }
        });


    }
}
