package com.mfundoza.sendingdatabetweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mfundoza.sendingdatabetweenactivities.databinding.ActivityProfileBinding;

import java.util.Date;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String title = extras.getString("title");
            String firstName = extras.getString("firstName");
            String lastName = extras.getString("lastName");
            Date dateOfBirth = (Date) extras.get("dateOfBirth");
            String email = extras.getString("email");

            String premiumService;
            if (extras.getBoolean("premiumService")) {
                premiumService = "Yes";
            } else {
                premiumService = "No";
            }

            binding.txtFirstName.setText(firstName);
            binding.txtLastName.setText(lastName);
            binding.txtDateOfBirth.setText(dateOfBirth.toString());
            binding.txtEmail.setText(email);
            binding.txtPremiumService.setText(premiumService);
        }
    }
}