package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText addCityField;
    Button addCityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // UI update; add cities
        cityList = findViewById(R.id.city_list);
        addCityField = findViewById(R.id.add_city_field);
        addCityButton = findViewById(R.id.add_city_button);

        String [] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi", "Lahore", "Rome", "Athens", "Mykonos"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<> (this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        //add cities
        addCityButton.setOnClickListener(view -> {
            String cityName = addCityField.getText().toString();
            if (!cityName.isEmpty()) {
                dataList.add(cityName); //add to ArrayList
                cityAdapter.notifyDataSetChanged(); // refresh UI so change is reflected
                addCityField.setText(""); //clear field input
            }
        });

        //delete cities; long click
        cityList.setOnItemLongClickListener((adapterView, view, i, l) -> {
            dataList.remove(i); // Remove item at the clicked index
            cityAdapter.notifyDataSetChanged(); // refresh UI so change is reflected
            return true;
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}