package com.hnam.localpersistence.LocalFiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hnam.localpersistence.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LocalFilesActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_files);


        findViewById(R.id.btn_write_internal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
                Toast.makeText(LocalFilesActivity.this, "write file internal", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_read_internal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
                Toast.makeText(LocalFilesActivity.this, "read file internal", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_write_external).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileManager.writeFile();
                Toast.makeText(LocalFilesActivity.this, "write file external", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_read_external).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileManager.readFile();
                Toast.makeText(LocalFilesActivity.this, "read file external", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void createFile(){
        // Use Activity method to create a file in the writeable directory
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("K2014", MODE_WORLD_WRITEABLE);
            // Create buffered writer
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write("Hi DTVT\n");
            writer.write("Hi DTVT 1\n");
            writer.write("Hi DTVT 2\n");
            writer.close();
            Log.e(TAG, ">>>>>>> create file successfully");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final String TAG = LocalFilesActivity.class.getSimpleName();

    private void readFile(){
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(openFileInput("K2014")));
            String line;
            StringBuilder buffer = new StringBuilder();
            while ((line = input.readLine()) != null){
                buffer.append(line + "\n");
            }
            String text = buffer.toString();
            Log.e(TAG, ">>>>>>>>>> result: " + text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
