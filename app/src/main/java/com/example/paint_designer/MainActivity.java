package com.example.paint_designer;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DrawingView drawingView;
    private Button rainbowButton;
    private Button colorPickerButton;

    private int[] rainbowColors = {
        Color.RED,
        Color.parseColor("#FF7F00"), // ORANGE
        Color.YELLOW,
        Color.GREEN,
        Color.BLUE,
        Color.parseColor("#4B0082"), // INDIGO
        Color.parseColor("#9400D3")  // VIOLET
    };

    private int[] paletteColors = {
        Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
        Color.CYAN, Color.MAGENTA, Color.GRAY, Color.parseColor("#FFA500"),
        Color.parseColor("#4B0082"), Color.parseColor("#9400D3"),
        Color.parseColor("#FFD700"), Color.parseColor("#00FF00"),
        Color.parseColor("#8B0000"), Color.parseColor("#00008B")
    };

    private int currentRainbowIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);
        rainbowButton = findViewById(R.id.rainbowButton);
        colorPickerButton = findViewById(R.id.randomColorButton);

        rainbowButton.setOnClickListener(v -> {
            currentRainbowIndex = (currentRainbowIndex + 1) % rainbowColors.length;
            drawingView.setCurrentColor(rainbowColors[currentRainbowIndex]);
        });

        colorPickerButton.setOnClickListener(v -> showColorPickerDialog());
    }

    private void showColorPickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выберите цвет");

        GridView gridView = new GridView(this);
        gridView.setNumColumns(5);
        gridView.setAdapter(new ColorAdapter());

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            drawingView.setCurrentColor(paletteColors[position]);
            ((AlertDialog) builder.create()).dismiss();
        });

        builder.setView(gridView);
        builder.setNegativeButton("Отмена", null);
        builder.show();
    }

    private class ColorAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return paletteColors.length;
        }

        @Override
        public Object getItem(int position) {
            return paletteColors[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = new View(MainActivity.this);
                view.setLayoutParams(new GridView.LayoutParams(100, 100));
                view.setPadding(8, 8, 8, 8);
                view.setBackgroundResource(R.drawable.color_selector);
            }
            view.setBackgroundColor(paletteColors[position]);
            return view;
        }
    }
}
