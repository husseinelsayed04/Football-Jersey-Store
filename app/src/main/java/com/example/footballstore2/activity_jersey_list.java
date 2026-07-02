package com.example.footballstore2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_jersey_list extends AppCompatActivity {

    ListView listView;
    ArrayList<Jersey> jerseys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jersey_list);

        listView = findViewById(R.id.listViewJerseys);

        jerseys = new ArrayList<>();

        jerseys.add(new Jersey(
                "Barcelona 26/27",
                "$79.99",
                R.drawable.t1,
                "Official FC Barcelona 2026/2027 Home Jersey. Premium breathable fabric, modern fit, and authentic club badge. Perfect for match days or casual wear."
        ));

        jerseys.add(new Jersey(
                "Barcelona Black",
                "$79.99",
                R.drawable.t2,
                "Limited Edition Barcelona Black Jersey. Stylish and bold design with high-quality material for a unique modern look."
        ));

        jerseys.add(new Jersey(
                "Messi 20/21",
                "$79",
                R.drawable.t4,
                "Lionel Messi Final Season Jersey. A legendary shirt representing his last year at Barcelona with lightweight fabric and classic design."
        ));

        jerseys.add(new Jersey(
                "Messi 2009",
                "$100",
                R.drawable.messi2009,
                "Historic 2009 Treble-Winning Jersey. A must-have for collectors with premium quality and iconic design."
        ));

        jerseys.add(new Jersey(
                "Neymar 2015",
                "$100",
                R.drawable.ney,
                "Neymar Jr 2015 Jersey from the treble season. High-performance fabric and comfortable fit."
        ));

        jerseys.add(new Jersey(
                "Rapha 2025",
                "$70",
                R.drawable.rap,
                "Modern Barcelona Jersey 2025 with breathable material, ideal for training and daily wear."
        ));

        jerseys.add(new Jersey(
                "Yamal 2025",
                "$70",
                R.drawable.yamal55,
                "Lamine Yamal Rising Star Jersey. Lightweight, stylish, and perfect for young fans."
        ));

        jerseys.add(new Jersey(
                "Messi 2011",
                "$150",
                R.drawable.messi2011,
                "Legendary Messi 2011 Jersey from one of the greatest seasons in football history."
        ));

        jerseys.add(new Jersey(
                "Xavi 2015",
                "$100",
                R.drawable.xav,
                "Xavi Farewell Jersey 2015. A tribute to a midfield legend with elegant design."
        ));

        jerseys.add(new Jersey(
                "Yamal 2025 Special",
                "$80",
                R.drawable.yamal2025,
                "Special Edition Yamal Jersey with premium materials and unique design."
        ));

        jerseys.add(new Jersey(
                "Messi 2017",
                "$150",
                R.drawable.bg_welcome,
                "Classic Messi 2017 Jersey. Timeless design and high-quality fabric."
        ));

        JerseyAdapter adapter = new JerseyAdapter(this, jerseys);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            Jersey selected = jerseys.get(position);

            Intent intent = new Intent(activity_jersey_list.this, ActivityDeteails.class);

            intent.putExtra("productName", selected.getName());
            intent.putExtra("price", selected.getPrice());
            intent.putExtra("image", selected.getImage());
            intent.putExtra("description", selected.getDescription());

            startActivity(intent);
        });
    }
}
