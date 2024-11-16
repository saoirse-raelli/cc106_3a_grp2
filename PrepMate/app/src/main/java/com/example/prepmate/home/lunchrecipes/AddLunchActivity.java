package com.example.prepmate.home.lunchrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.prepmate.DatabaseHelper;
import com.example.prepmate.R;


public class AddLunchActivity extends AppCompatActivity {

    EditText title_input, ingredients_input, procedures_input;
    Spinner hours_input, minutes_input;
    Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_lunch);
        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // This will show the back icon
        getSupportActionBar().setDisplayShowHomeEnabled(true); // This ensures the icon is shown

        title_input = findViewById(R.id.title_input);
        hours_input = findViewById(R.id.spinner_hours);
        minutes_input = findViewById(R.id.spinner_minutes);
        ingredients_input = findViewById(R.id.ingredients_input);
        procedures_input = findViewById(R.id.procedures_input);
        save_button = findViewById(R.id.save_button);

        // Set up Spinners with hours and minutes options
        ArrayAdapter<CharSequence> hoursAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.hours_array, // Define this array in strings.xml
                android.R.layout.simple_spinner_item
        );
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hours_input.setAdapter(hoursAdapter);

        ArrayAdapter<CharSequence> minutesAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.minutes_array, // Define this array in strings.xml
                android.R.layout.simple_spinner_item
        );
        minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minutes_input.setAdapter(minutesAdapter);

        //save button logic
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(AddLunchActivity.this);

                String hours = hours_input.getSelectedItem().toString();
                String minutes = minutes_input.getSelectedItem().toString();

                //Database method for adding lunch in the lunch_table
                databaseHelper.addLunchRecipe(

                        title_input.getText().toString().trim(),
                        hours,
                        minutes,
                        ingredients_input.getText().toString().trim(),
                        procedures_input.getText().toString().trim()
                );

                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish(); // Close AddActivity and go back to CustomRecipesActivity

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back navigation
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Or use finish() to close the activity
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}