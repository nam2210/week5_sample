package com.hnam.localpersistence.LocalFiles;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileManager {

    private static final String FOLDER_NAME = "Week5";
    private static final String TAG = FileManager.class.getSimpleName();

    /**
     * if check folder is existing, if folder is not existing => create new one
     * otherwise return false
     *
     * @return
     */
    public static boolean checkIsExisting() {
        File appFolder = getRootFile();
        return (appFolder.mkdir() || appFolder.isDirectory());
    }


    private static File getRootFile() {
        File root = Environment.getExternalStorageDirectory();
        return new File(root, FOLDER_NAME);
    }

    public static void writeFile(){
        File root = Environment.getExternalStorageDirectory();
        String path = root.getAbsolutePath() + "/test1.txt";
        File file = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write("Hi guys \n");
            writer.write("I'm man \n");
            writer.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(){
        File root = Environment.getExternalStorageDirectory();
        String path = root.getAbsolutePath() + "/test1.txt";
        File file = new File(path);
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder builder = new StringBuilder();
            String line;
            while ( (line = reader.readLine()) != null){
                builder.append(line);
            }
            reader.close();
            fis.close();
            Log.e(TAG, builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}