package com.harshita.sit.backup;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Zomato extends AppCompatActivity {
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zomato);

        b = findViewById(R.id.zoo);

    }

    public void goToSo4(View view) {
        goToUrl4("https://www.zomato.com/order?utm_source=Google&utm_medium=CPC&utm_term=%2Bzomato&utm_campaign=Gsearch_P-MWeb_O-NA_C-Brand_A-NewUser_SC-Generic_L-Rest_All&gclid=Cj0KCQjwkIzlBRDzARIsABgXqV9VsO5GUONk4sXZ9iGROlZ-tzJhk8ZmD9FI-kIqLZ4VIFuaCgWI8BwaAqpEEALw_wcB");

    }

    private void goToUrl4(String url4) {
        Uri uriUrl4 = Uri.parse(url4);
        Intent launch4 = new Intent(Intent.ACTION_VIEW, uriUrl4);
        startActivity(launch4);
    }
}
