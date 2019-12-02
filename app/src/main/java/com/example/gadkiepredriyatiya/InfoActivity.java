package com.example.gadkiepredriyatiya;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void onButton2Click(View view) {
        Intent intent = new Intent(InfoActivity.this, TableViewActivity.class);
        startActivity(intent);
    }

    public void onButton3Click(View view) {
        Toast.makeText(this, "Записей не найдено", Toast.LENGTH_SHORT).show();
    }

    public void onButton4Click(View view) {
        Toast.makeText(this, "Записей не найдено", Toast.LENGTH_SHORT).show();
    }

    public void onButtonClick(View view) {
        finish();
    }
}
