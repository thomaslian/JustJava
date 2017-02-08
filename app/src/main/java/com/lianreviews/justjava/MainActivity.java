package com.lianreviews.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean addWhippedCream = whippedCreamCheckBox.isChecked();
        int price = calculatePrice();
        displayMessage(createOrderSummary(price, addWhippedCream));
    }

    /**
     * Calculates the price of the order.
     * @return the price of the amount of coffees ordered
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * Calculates the price of the order.
     *
     * @param price of the order
     * @param addWhippedCream to the order or not (true or false)
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream){
        String priceMessage =
                "Name: Kaptain Kunal"
                +"\nAdd whipped cream? " + addWhippedCream
                +"\nQuantity: " + quantity
                +"\nTotal: " + price
                +"\nThank you!";
        return priceMessage;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment (View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement (View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }

    /**
     * This method displays the given text on the screen.
     */

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
