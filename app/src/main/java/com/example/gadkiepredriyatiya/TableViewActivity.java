package com.example.gadkiepredriyatiya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TableViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);
    }

    public void onButtonClick(View view) {
        finish();
    }
}
