package com.mfundoza.sendingdatabetweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mfundoza.sendingdatabetweenactivities.databinding.ActivityBmiBinding;

public class BMIActivity extends AppCompatActivity {
    Intent intent;
    ActivityBmiBinding binding;
    double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_bmi);
        binding = ActivityBmiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();

       binding.btnReturnBMI.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               calculateBMI();
               returnBMI();
           }
       });

    }

    private void calculateBMI() {
        double height = Double.parseDouble(binding.tilHeight.getEditText().getText().toString());
        double weight = Double.parseDouble(binding.tilWeight.getEditText().getText().toString());

        double heightSquared = Math.pow(height, 2);

        bmi = weight / heightSquared;
    }

    private void returnBMI() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("bmi", bmi);

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}