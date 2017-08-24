package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    // initialize variables
    int quantity = 1;
    int price_multiplier = 5;
    int price = 5;
    String orderSum = "";
    boolean whipped = false;
    boolean chocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_box);
        // boolean hasWhippedCream = whippedCream.isChecked();
        // Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);

        EditText name = (EditText) findViewById(R.id.name_box);
        orderSum = "Name: " + name.getText() + "\nAdd whipped cream: " + whipped +
                "\nAdd chocolate: " + chocolate + "\nQuantity: " + quantity +
                "\nTotal: $" + price + "\nThank you!";

        calculatePrice();

        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Your Order from Just Java Coffee Shop");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, orderSum);

        startActivity(emailIntent.createChooser(emailIntent, "Which app would you like to send your " +
                "message in?"));
    }

    /* Toppings Functions */
    public void whippedCheck(View view) {
        whipped = !whipped;
        calculatePrice();
        // displayPrice(price);
    }

    public void chocolateCheck(View view) {
        chocolate = !chocolate;
        calculatePrice();
        // displayPrice(price);
    }


    /* Calculate the price.
     * @return the total price for the order
     * */
    public void calculatePrice() {
        price = quantity * price_multiplier;

        if (whipped) { price = price + (quantity*1); }
        if (chocolate) { price = price + (quantity*2); }
    }




    /* Increment and Decrement Functions */

    /* This method increments the quantity value, then displays it. */
    public void increment(View view) {
        if (quantity == 99) {
            TextView error = (TextView) findViewById(R.id.error_message);
            error.setText("ERROR: You cannot purchase more than 100 cups of coffee.");
        }

        else {
            quantity++;
            display(quantity);
            calculatePrice();
            // displayPrice(price);

            TextView error = (TextView) findViewById(R.id.error_message);
            error.setText(" ");
        }
    }

    /* This method decrements the quantity value, then displays it. */
    public void decrement(View view) {

        if (quantity <= 1) {
            TextView error = (TextView) findViewById(R.id.error_message);
            error.setText("ERROR: You cannot purchase less than 1 cup of coffee.");
        }
        else {
            quantity--;
            display(quantity);
            //displayPrice(quantity * price_multiplier);
            calculatePrice();
            // displayPrice(price);

            TextView error = (TextView) findViewById(R.id.error_message);
            error.setText(" ");
        }
    }



    /* Display Functions */

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     It is commented out because displayPrice is not necessary once the email intent is implemented.
     */
    /*
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    } */

    /**
     * This method displays the given text on the screen. This method is commented out for the same reason.
     */
    /*
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }*/

}