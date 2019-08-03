package com.example.lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout textInputName;
    private TextInputLayout textInputRoll;
    private Button mainButton;

    private Spinner s;

    //Data for populating in Spinner
    String [] dept_array={"CSE","ECE","IT","Mech","Civil"};

    String name,reg,dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s= (Spinner) findViewById(R.id.spinner);

        //Creating Adapter for Spinner for adapting the data from array to Spinner
        ArrayAdapter adapter= new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,dept_array);
        s.setAdapter(adapter);

        textInputName = findViewById(R.id.main_name);
        textInputRoll = findViewById(R.id.main_roll);
        mainButton = findViewById(R.id.main_button);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateName() | !validateRoll() ) {
                    return;
                }

                String input = "Email: " + textInputName.getEditText().getText().toString();
                input += "\n";
                input += "Username: " + textInputRoll.getEditText().getText().toString();

                //Getting the Values from Views(Edittext & Spinner)
                name=textInputName.getEditText().getText().toString();
                reg=textInputRoll.getEditText().getText().toString();
                dept=s.getSelectedItem().toString();

                //Intent For Navigating to Second Activity
                Intent i = new Intent(MainActivity.this,SecondActivity.class);

                //For Passing the Values to Second Activity
                i.putExtra("name_key", name);
                i.putExtra("reg_key",reg);
                i.putExtra("dept_key", dept);

                startActivity(i);

            }

        });
    }

    private boolean validateName() {
        String nameInput = textInputName.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()) {
            textInputName.setError("Field can't be empty");
            return false;
        }else if (nameInput.length() > 20) {
            textInputName.setError("Username too long");
            return false;
        } else {
            textInputName.setError(null);
            return true;
        }
    }

    private boolean validateRoll() {
        String rollInput = textInputRoll.getEditText().getText().toString().trim();

        if (rollInput.isEmpty()) {
            textInputRoll.setError("Field can't be empty");
            return false;
        }else if (rollInput.length() > 7) {
            textInputRoll.setError("Roll No. too long");
            return false;
        } else {
            textInputRoll.setError(null);
            return true;
        }
    }
}
