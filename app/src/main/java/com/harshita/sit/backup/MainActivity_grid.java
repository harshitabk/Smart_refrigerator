package com.harshita.sit.backup;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity_grid extends AppCompatActivity {

    TextView txt1,txt2,txt3,txt4;
    Button btn1,btn2,btn3,btn4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);

        txt1=findViewById(R.id.text1);
        txt2=findViewById(R.id.text2);
        txt3=findViewById(R.id.text3);
        txt4=findViewById(R.id.text4);

        btn3=findViewById(R.id.bt3);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(" ");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                String value1=value.substring(0,19);
                String value2=value.substring(21,29);
                String value4=value.substring(31,42);

                txt1.setText(value1);
                txt2.setText(value2);
                txt4.setText(value4);
                // txt4.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity_grid.this,Main_recipe.class);
                startActivity(intent1);

            }
        });

    }
    public void  goToSo(View view){
        goToUrl("https://www.bigbasket.com/pd/242671/nandini-goodlife-toned-milk-500-ml-pouch/");

    }
    public void goToSo2(View view){
        goToUrl1("https://www.bigbasket.com/pd/281204/fresho-eggs-regular-6-pcs/");

    }
    public void goToSo3(View view){
        goToUrl2("https://www.bigbasket.com/pd/1204353/fresho-bread-brown-chemical-free-400g-eggs-regular-6pcs-combo/");

    }

    private void goToUrl1(String url1) {
        Uri uriUrl1=Uri.parse(url1);
        Intent launch1=new Intent(Intent.ACTION_VIEW,uriUrl1);
        startActivity(launch1);

    }

    private void goToUrl(String url) {
        Uri uriUrl=Uri.parse(url);
        Intent launch=new Intent(Intent.ACTION_VIEW,uriUrl);
        startActivity(launch);
    }
    private void goToUrl2(String url2) {
        Uri uriUrl2=Uri.parse(url2);
        Intent launch2=new Intent(Intent.ACTION_VIEW,uriUrl2);
        startActivity(launch2);

    }


}
