package com.xococ.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        Button btnVsAI = findViewById(R.id.btnVsAI);
        Button btnVsPlayer = findViewById(R.id.btnVsPlayer);
        
        btnVsAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(true);
            }
        });
        
        btnVsPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(false);
            }
        });
    }
    
    private void startGame(boolean vsAI) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("VS_AI", vsAI);
        startActivity(intent);
    }
}
