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

    public static Bitmap getImageFromIntent(@NonNull Intent intent) {
        String imagePath = intent.getStringExtra("name");
        return BitmapFactory.decodeFile(Objects.requireNonNull(imagePath));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        String fileAbsolutePath = data.getStringExtra("fileAbsolutePath");
        if(!fileAbsolutePath.isEmpty()){
            Toast.makeText(this,  "Не найден Файл картинки для фона : " + fileAbsolutePath, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,  "Файл картинки для фона : " + fileAbsolutePath, Toast.LENGTH_LONG).show();

            Bitmap imageFromIntent = this.getIntent().getParcelableExtra("bmp");
            ImageView imageView2 = findViewById(R.id.imageView2);
            imageView2.getDrawable();

        }

//        if (data != null) {
//            Bitmap imageFromIntent = this.getIntent().getParcelableExtra("bmp");
//            ImageView backImage = findViewById(R.id.ImageViewBack);
//            backImage.setImageBitmap(imageFromIntent);
//        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READ_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    loadImg();
                } else {
                    // permission denied
                }
                return;
        }
    }




    private void loadImg() {

        ImageView view = findViewById(R.id.imageView2);
        if (isExternalStorageWritable()) {

//            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "1.jpg");

            File file = new File(getApplicationContext().getExternalFilesDir(null),"1.jpg");  //getExternalFilesDir  папка для доступа приложения

            Bitmap b = BitmapFactory.decodeFile(file.getAbsolutePath());
            view.setImageBitmap(b);
            Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "File Error", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this, getApplicationContext().getExternalFilesDir(null).toString(), Toast.LENGTH_LONG).show();

        File logFile = new File(getApplicationContext().getExternalFilesDir(null),"log.txt");
        try {
            FileWriter logWriter = new FileWriter(logFile);
            logWriter.append("App loaded");
            logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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




}


