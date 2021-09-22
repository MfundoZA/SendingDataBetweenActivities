package com.mfundoza.sendingdatabetweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.material.snackbar.Snackbar;
import com.mfundoza.sendingdatabetweenactivities.databinding.ActivityMainBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {
    private ActivityMainBinding binding;

    DatePickerFragment datePickerFragment;

    String title;
    String firstName;
    String lastName;
    Date dateOfBirth;
    String email;
    String password;
    boolean premiumService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tilDateOfBirth.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatePicker();
            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                clearForm();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        dateOfBirth = cal.getTime();

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(dateOfBirth);

        binding.tilDateOfBirth.getEditText().setText(currentDateString);

    }

    private void displayDatePicker() {
        datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void clearForm() {
        binding.spnTitle.setSelection(0);
        binding.tilFirstName.getEditText().setText("");
        binding.tilLastName.getEditText().setText("");
        binding.tilDateOfBirth.getEditText().setText("");
        binding.tilEmail.getEditText().setText("");
        binding.tilPassword.getEditText().setText("");

        if (binding.rdbYes.isChecked()) {
            binding.rdbYes.setChecked(false);
        } else {
            binding.rdbNo.setChecked(false);
        }

        Snackbar.make(binding.getRoot(), "Form Cleared!", Snackbar.LENGTH_SHORT).show();
    }

    private void submitForm() {
        // TODO create and call a form validation method

        title = binding.spnTitle.getSelectedItem().toString();
        firstName = binding.tilFirstName.getEditText().getText().toString();
        lastName = binding.tilLastName.getEditText().getText().toString();
        email = binding.tilEmail.getEditText().getText().toString();
        password = binding.tilPassword.getEditText().getText().toString();

        if (binding.rdbYes.isChecked()) {
            premiumService = true;
        } else {
            premiumService = false;
        }

        Intent openProfileActivityIntent = new Intent(this, ProfileActivity.class);
        openProfileActivityIntent.putExtra("title", title);
        openProfileActivityIntent.putExtra("firstName", firstName);
        openProfileActivityIntent.putExtra("lastName", lastName);
        openProfileActivityIntent.putExtra("dateOfBirth", dateOfBirth);
        openProfileActivityIntent.putExtra("email", email);
        openProfileActivityIntent.putExtra("premiumService", premiumService);
        startActivity(openProfileActivityIntent);

        Snackbar.make(binding.getRoot(), "Form Submitted!", Snackbar.LENGTH_SHORT).show();
    }
}