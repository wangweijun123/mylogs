package com.tencent.logs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-log");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = new File(Environment.getExternalStorageDirectory(), "test.log");
        FileLogger fileLogger = new FileLogger(file, 20 * 1024 * 1024);
        long start = System.currentTimeMillis();
        for (int i=0;i<1000;i++){
            fileLogger.write("name:xxx0->");
        }
        long end = System.currentTimeMillis();
        Log.e("TAG","->"+(end - start));

        SharedPreferences sp = getSharedPreferences("name",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        start = System.currentTimeMillis();
        for (int i=0;i<1000;i++){
            editor.putString("name","xxx0->");
            editor.commit();
        }
        end = System.currentTimeMillis();
        Log.e("TAG","->"+(end - start));
    }
}
