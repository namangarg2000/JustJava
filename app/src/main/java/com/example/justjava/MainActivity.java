package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        int price = calculatePrice();

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();


        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);

        displayMessage(createOrderSummary(price,whippedCream.isChecked(),chocolate.isChecked(),name));
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void decrement(View view) {
        quantity -= 1;
        display(quantity);
    }

    public void increment(View view) {
        quantity += 1;
        display(quantity);
    }

    private int calculatePrice() {
        return quantity * 5;
    }

    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String message = "Name: "+ name + "\n";
        message = message + "Add whipped cream? " + hasWhippedCream + "\n";
        message += "Add chocolate? " + hasChocolate + "\n";
        message = message + "Quantity: " + quantity + "\n";
        message = message + "Total Price: $" + price + "\n";
        message = message + "Thank You!";
        return message;
    }

}