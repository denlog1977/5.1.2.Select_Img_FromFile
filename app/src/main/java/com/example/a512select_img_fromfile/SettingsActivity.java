package com.example.a512select_img_fromfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button buttonOK = findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView editText = findViewById(R.id.editText);
                String picturesName = editText.getText().toString();
                Log.i("img_fromfile", "picturesName = " + picturesName);

                if (picturesName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "! Не заполнено наименование файла картинки для фона !", Toast.LENGTH_SHORT).show();
                } else {

                    final File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), picturesName);
                    Log.i("img_fromfile", "В каталоге " + Environment.DIRECTORY_DOWNLOADS + " найден файл с наименованием  " + picturesName);
                    Log.i("img_fromfile", "file.getAbsolutePath() =  " + file.getAbsolutePath());

                    if (file.exists()) {
                        Intent intent = new Intent();
                        intent.putExtra("fileAbsolutePath", file.getAbsolutePath());
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                       Log.i("img_fromfile", "В каталоге " + Environment.DIRECTORY_DOWNLOADS + " нет  файла с наименованием  " + picturesName);
                        Toast.makeText(SettingsActivity.this, "В каталоге " + Environment.DIRECTORY_DOWNLOADS + " нет  файла с наименованием  " + picturesName , Toast.LENGTH_SHORT).show();
                    }


//                    Intent intent = new Intent();
//                    intent.putExtra("name", picturesName);
//                    setResult(RESULT_OK, intent);
//                    finish();
                }

            }
        });

    }
}




