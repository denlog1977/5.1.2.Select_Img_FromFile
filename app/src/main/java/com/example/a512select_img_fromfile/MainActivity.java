package com.example.a512select_img_fromfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 10;
    public static final int REQUEST_CODE_PERMISSION_WRITE_STORAGE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            Toast.makeText(MainActivity.this, R.string.settings, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(intent, 1);

            return true;

        }
//        else if (id == R.id.action_settings) {
//            Toast.makeText(MainActivity.this, "Открыть НАСТРОЙКИ ПРОГРАММЫ", Toast.LENGTH_LONG).show();
//        }
//        else if (id == R.id.action_hello_word) {
//            Toast.makeText(MainActivity.this, "1.1.1 Открыть самую первую программу Привет Мир!", Toast.LENGTH_LONG).show();
//            Intent intentNotes = new Intent(MainActivity.this, FirstActivity.class);
//            startActivity(intentNotes);
//        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        loadImg(data.getStringExtra("fileAbsolutePath"));
    }

    private void loadImg(String fileAbsolutePath) {
        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);
        if (isExternalStorageWritable()) {
//            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "1.jpg");
//            File file = new File(getApplicationContext().getExternalFilesDir(null),"1.jpg");  //getExternalFilesDir  папка для доступа приложения
            fileAbsolutePath = "/sdcard/Download/1.jpg";
            Bitmap b = BitmapFactory.decodeFile(fileAbsolutePath);
            imageView.setImageBitmap(b);
            //textView.setText(fileAbsolutePath.toString() + " \n  " + fileAbsolutePath);

            Toast.makeText(this, " все ОК Bitmap b = " + fileAbsolutePath, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "File Error isExternalStorageWritable", Toast.LENGTH_LONG).show();
        }



    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READ_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    Toast.makeText(this, " ДОступ к файлам открыт. Повторите ваше действие", Toast.LENGTH_LONG).show();

//                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
//                    startActivityForResult(intent, 1);
                } else {
                    // permission denied
                }
                return;
        }
    }












}


