package com.example.techstore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.techstore.R;

public class LienheActivity extends AppCompatActivity {

    Toolbar tbLienhe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lienhe);
        tbLienhe = findViewById(R.id.tbLienhe);
        ActionToolbar();
    }

    private void ActionToolbar(){
        tbLienhe.setNavigationIcon(R.drawable.ic_back);
        tbLienhe.setNavigationOnClickListener(v -> finish());
    }
}