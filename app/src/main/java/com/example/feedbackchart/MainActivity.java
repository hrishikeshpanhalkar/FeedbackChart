package com.example.feedbackchart;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PieChart pieChart;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = findViewById(R.id.pie_chart);

        pieChart.setDrawHoleEnabled(true);

        pieChart.setMaxAngle(300);
        pieChart.setRotationAngle(120);
        pieChart.setCenterTextOffset(0, -20);
        pieChart.setHoleRadius(64f);//18f
        pieChart.setCenterTextSize(10);//30

        pieChart.setExtraOffsets(5f,0f,10f,-100f);

        setData();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(MainActivity.this, "highlight: " + h.getX(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onValueSelected: " + "highlight: " + h.getX());
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void setData(){
        ArrayList<PieEntry> values = new ArrayList<>();
        for(int i = 0; i< 11; i++){
            values.add(new PieEntry(1, String.valueOf(i)));
        }
        Log.d("TAG", "setData: " + values);
        PieDataSet dataSet = new PieDataSet(values, "");
        dataSet.setSelectionShift(10f);
        dataSet.setSliceSpace(3f);
        dataSet.setDrawValues(false);
        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#EC4D50"));
        colors.add(Color.parseColor("#EC4D50"));
        colors.add(Color.parseColor("#EC4D50"));
        colors.add(Color.parseColor("#EC4D50"));
        colors.add(Color.parseColor("#EC4D50"));
        colors.add(Color.parseColor("#EC4D50"));
        colors.add(Color.parseColor("#EC4D50"));
        colors.add(Color.parseColor("#FFBB01"));
        colors.add(Color.parseColor("#FFBB01"));
        colors.add(Color.parseColor("#50B37C"));
        colors.add(Color.parseColor("#50B37C"));

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new DefaultValueFormatter(0));
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.getLegend().setEnabled(false);
    }

}