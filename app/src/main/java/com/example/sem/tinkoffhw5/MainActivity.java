package com.example.sem.tinkoffhw5;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;

public class MainActivity extends AppCompatActivity {

    public static int color = 0xFF33B5E5;
    private final String[] colors = {"green", "red", "blue", "black"};

    private ArrayList<Float> xPoints = new ArrayList<>();
    private ArrayList<Float> yPoints = new ArrayList<>();

    private EditText xInput;
    private EditText yInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addPoint = (Button) findViewById(R.id.add_point);
        Button showGraph = (Button) findViewById(R.id.show_graph);
        xInput = (EditText) findViewById(R.id.input_x);
        yInput = (EditText) findViewById(R.id.input_y);
        Spinner colorSpinner = (Spinner) findViewById(R.id.chart_colors_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), simple_spinner_item, colors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener colorItemSelectedListener = initColorItemSelectedListener();
        colorSpinner.setOnItemSelectedListener(colorItemSelectedListener);

        addPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newX = xInput.getText().toString();
                String newY = yInput.getText().toString();
                if (!(newX.isEmpty() || newY.isEmpty())) {
                    xPoints.add(Float.valueOf(newX));
                    yPoints.add(Float.valueOf(newY));
                    xInput.setText("");
                    yInput.setText("");
                }
            }
        });

        showGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(xPoints.isEmpty() && yPoints.isEmpty()))
                    ActivityForGraphic.start(MainActivity.this, xPoints.toArray(new Float[xPoints.size()]), yPoints.toArray(new Float[yPoints.size()]));
            }
        });
    }

    private AdapterView.OnItemSelectedListener initColorItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = (String) parent.getItemAtPosition(position);
                switch (item) {
                    case "blue":
                        color = Color.BLUE;
                        break;
                    case "black":
                        color = Color.BLACK;
                        break;
                    case "red":
                        color = Color.RED;
                        break;
                    case "green":
                        color = Color.GREEN;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };
    }
}
