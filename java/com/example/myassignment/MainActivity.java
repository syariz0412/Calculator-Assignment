package com.example.myassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText unitsEditText;
    private EditText rebateEditText;
    private Button calculateButton;
    private Button clearButton;
    private TextView totalChargesTextView;
    private TextView finalCostTextView;
    private AppCompatImageButton profileButton;
    private Button instructionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitsEditText = findViewById(R.id.unitsEditText);
        rebateEditText = findViewById(R.id.rebateEditText);
        calculateButton = findViewById(R.id.calculateButton);
        clearButton = findViewById(R.id.clearButton);
        totalChargesTextView = findViewById(R.id.totalChargesTextView);
        finalCostTextView = findViewById(R.id.finalCostTextView);
        profileButton = findViewById(R.id.profileButton);
        instructionButton = findViewById(R.id.instructionButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileView();
            }
        });

        instructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstructionView();
            }
        });
    }

    private void calculateBill() {
        // Retrieve the input values from the user interface
        String unitsInput = unitsEditText.getText().toString();
        String rebateInput = rebateEditText.getText().toString();

        // Check if input fields are empty
        if (unitsInput.isEmpty() || rebateInput.isEmpty()) {
            Toast.makeText(this, "Please enter input in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int unitsUsed = Integer.parseInt(unitsInput);
        float rebatePercentage = Float.parseFloat(rebateInput);

        // Calculate the electricity bill
        float totalCharges = 0.0f;

        if (unitsUsed <= 200) {
            totalCharges = unitsUsed * 0.218f;
        } else if (unitsUsed <= 300) {
            totalCharges = 200 * 0.218f + (unitsUsed - 200) * 0.334f;
        } else if (unitsUsed <= 600) {
            totalCharges = 200 * 0.218f + 100 * 0.334f + (unitsUsed - 300) * 0.516f;
        } else if (unitsUsed > 600) {
            totalCharges = 200 * 0.218f + 100 * 0.334f + 300 * 0.516f + (unitsUsed - 600) * 0.546f;
        }

        // Apply the rebate percentage
        float finalCost = totalCharges - (totalCharges * (rebatePercentage / 100.0f));

        // Display the calculated values in the output fields
        totalChargesTextView.setText(String.format("RM %.2f", totalCharges));
        finalCostTextView.setText(String.format("RM %.2f", finalCost));
    }

    private void clearFields() {
        unitsEditText.setText("");
        rebateEditText.setText("");
        totalChargesTextView.setText("RM 0.00");
        finalCostTextView.setText("RM 0.00");
    }

    private void openProfileView() {
        // Inflate profile.xml layout and set it as the content view
        View profileView = LayoutInflater.from(this).inflate(R.layout.profile, (ViewGroup) findViewById(android.R.id.content), false);
        setContentView(profileView);
    }

    private void openInstructionView() {
        // Inflate instruction.xml layout and set it as the content view
        View instructionView = LayoutInflater.from(this).inflate(R.layout.instruction, (ViewGroup) findViewById(android.R.id.content), false);
        setContentView(instructionView);
    }
}
