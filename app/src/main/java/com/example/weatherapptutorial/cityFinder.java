package com.example.weatherapptutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class cityFinder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //створення суперкласу
        setContentView(R.layout.activity_city_finder); //пiдгрузка актiвiтi
        final EditText editText=findViewById(R.id.searchCity); //пошук мiста
        ImageView backButton=findViewById(R.id.backButton); //повернення до MainActivity

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String newCity= editText.getText().toString(); //ввiд тексту
                Intent intent=new Intent(cityFinder.this,MainActivity.class);//перехiд мiж актiвiтi
                intent.putExtra("City",newCity); //вiдправка тексту
                startActivity(intent);


                return false;
            }
        });

    }
}