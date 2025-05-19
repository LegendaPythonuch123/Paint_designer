package com.example.paint_designer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DrawingView drawingView;
    private Button rainbowButton;
    private Button randomColorButton;

    private int[] rainbowColors = {
        Color.RED,
        Color.parseColor("#FF7F00"), // ORANGE
        Color.YELLOW,
        Color.GREEN,
        Color.BLUE,
        Color.parseColor("#4B0082"), // INDIGO
        Color.parseColor("#9400D3")  // VIOLET
    };
    private int currentRainbowIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);
        rainbowButton = findViewById(R.id.rainbowButton);
        randomColorButton = findViewById(R.id.randomColorButton);


        rainbowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRainbowIndex = (currentRainbowIndex + 1) % rainbowColors.length;
                drawingView.setCurrentColor(rainbowColors[currentRainbowIndex]);
            }
        });


        randomColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int color = Color.rgb(
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
                );
                drawingView.setCurrentColor(color);
            }
        });
    }
}
