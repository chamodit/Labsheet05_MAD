package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    public static final String BROADCAST_ACTION = "The Broadcast Message";
    Receiver localListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txtBroadcast);

        button = findViewById(R.id.btnStart);
    }

    @Override
    protected void onStart() {
        super.onStart();

        localListner = new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListner, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListner);
    }

    public void onClick(View view){
        if(view.getId() == R.id.btnStart){
            BackgroundService.startAction(this.getApplicationContext());
        }
    }

    public class Receiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText = textView.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived " + message;
            textView.setText(currentText);
        }
    }
}

