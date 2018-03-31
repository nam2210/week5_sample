package com.hnam.localpersistence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hnam.localpersistence.LocalFiles.LocalFilesActivity;
import com.hnam.localpersistence.Realm.RealmActivity;
import com.hnam.localpersistence.SharePreferences.SharePreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_sharePreferences).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SharePreferences.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_localfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LocalFilesActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_realm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RealmActivity.class);
                startActivity(i);
            }
        });
    }
}
