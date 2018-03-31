package com.hnam.localpersistence.SharePreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.hnam.localpersistence.R;

public class SharePreferences extends AppCompatActivity {
    private static final String TAG = SharePreferences.class.getSimpleName();

    SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        write(100);
        Toast.makeText(this,String.valueOf(read()), Toast.LENGTH_SHORT).show();

    }

    public static final String KEY_INT = "KEY_INT";

    //SharePreferences - read
    private int read() {

        return sharedPreferences.getInt(KEY_INT, -1);
    }

    //SharePreferences - write / update
    private void write(int data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_INT, data);
        editor.apply();
    }

    //SharePreferences - delete
    private void delete(String key) {
        sharedPreferences.edit()
                .remove(key)
                .apply();
    }

    private void deleteAll() {
        sharedPreferences.edit()
                .clear()
                .apply();
    }


}
