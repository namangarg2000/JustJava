package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();

        int price = calculatePrice(hasWhippedCream,hasChocolate);

        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        composeEmail(message);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void decrement(View view) {

        if(quantity==1){
            Toast.makeText(this,"Quantity cannot be less than 1", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        display(quantity);
    }

    public void increment(View view) {

        if(quantity==100){
            Toast.makeText(this,"Max Quantity Reached", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity += 1;
        display(quantity);
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5;

        if(hasWhippedCream){
            basePrice += 1;
        }

        if(hasChocolate){
            basePrice += 2;
        }

        return basePrice * quantity;
    }

    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String message = "Name: " + name + "\n";
        message = message + "Add whipped cream? " + hasWhippedCream + "\n";
        message += "Add chocolate? " + hasChocolate + "\n";
        message = message + "Quantity: " + quantity + "\n";
        message = message + "Total Price: $" + price + "\n";
        message = message + "Thank You!";
        return message;
    }

    private void composeEmail(String message){
        //The ACTION_SENDTO along with Uri.parse("mailto:") allows only the email apps to handle the intent and not text message and other social media apps.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));

        intent.putExtra(Intent.EXTRA_SUBJECT,"Order Summary");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}