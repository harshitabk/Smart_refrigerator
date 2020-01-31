package com.harshita.sit.backup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class Main_recipe extends AppCompatActivity {

    Button p, r;
    Button t;
    TextView t1,t2,t3;
    MqttAndroidClient client;
    public int x;
    public int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recipe);

        p = findViewById(R.id.pic);
        r = findViewById(R.id.recipe);
        t = findViewById(R.id.text);
        t1=findViewById(R.id.veg1);
        t2=findViewById(R.id.veg2);
        t3=findViewById(R.id.veg3);
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(getApplicationContext(), "tcp://iot.eclipse.org:1883",
                clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
// We are connected
                    Log.d("MQTT", "onSuccess");
                    Toast.makeText(Main_recipe.this, "Successfull", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
// Something went wrong e.g. connection timeout or firewall problems
                    Log.d("MQTT", "onFailure");

                    Toast.makeText(Main_recipe.this, "Not Successfull", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic = "foo/bar6";
                String payload = "1";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }
            }
        });


        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //setContentView(R.layout.recipe_1);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(" ");
                myRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue().toString();
                        String value1 = value.substring(0, 19);
                        String value2 = value.substring(21, 28);
                        String value4 = value.substring(34, 40);
                        if (value2.equals("present")) {
                            x = 1;
                        }
                        if (value4.equals("enough")) {
                            y = 1;
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                DatabaseReference myRef1 = database1.getReference("colors");
                myRef1.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String value = dataSnapshot.getValue().toString();
                        String val1 = value.substring(0, 6);
                        String val2 = value.substring(6, 14);
                        String val3 = value.substring(14, 21);

                        if (val1.equals("Tomato") && val2.equals("Capsicum") && val3.equals("xxxxxxx")) {
                            setContentView(R.layout.recipe_1);
                        } else if (val1.equals("Tomato") && val2.equals("xxxxxxxx") && val3.equals("xxxxxxx")) {
                            setContentView(R.layout.tomato_soup);
                        } else if (val1.equals("xxxxxx") && val2.equals("xxxxxxxx") && val3.equals("Brinjal")) {
                            setContentView(R.layout.tomato_eggs);
                        } else {
                            Intent intent4=new Intent(Main_recipe.this,Zomato.class);
                            startActivity(intent4);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                DatabaseReference myRef2 = database2.getReference("colors");
                myRef2.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String valueveg = dataSnapshot.getValue().toString();
                        String v1 = valueveg.substring(0, 6);
                        String v2 = valueveg.substring(6, 14);
                        String v3 = valueveg.substring(14, 21);
                        if(v1.equals("Tomato") ){
                            t1.setText(v1);
                        }
                        else{
                            t1.setText(" ");
                        }
                        if(v2.equals("Capsicum") ){
                            t2.setText(v2);
                        }
                        else{
                            t2.setText(" ");
                        }
                        if(v3.equals("Brinjal") ){
                            t3.setText(v3);
                        }
                        else{
                            t3.setText(" ");
                        }
                        /*
                        t1.setText(v1);
                        t2.setText(v2);
                        t3.setText(v3);
*/
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            ;


        });
    }
}
