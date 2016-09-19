package com.example.andyphung.memorygames;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveToRules();
        moveToPlay();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_play);
    }

    public void moveToPlay()
    {
        Button moveToRules = (Button) findViewById(R.id.play);
        moveToRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, play.class);
                startActivity(intent);

            }
        });
    }

    public void moveToRules()
    {
        Button moveToRules = (Button) findViewById(R.id.rule);
        moveToRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, rules.class);
                startActivity(intent);
            }
        });
    }
}
