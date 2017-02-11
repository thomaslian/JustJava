package com.lianreviews.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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

        //Get the users name
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String addName = nameEditText.getText().toString();

        //Figure out if the user wants whipped cream
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean addWhippedCream = whippedCreamCheckBox.isChecked();

        //Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean addChocolateTopping = chocolateCheckBox.isChecked();
        int price = calculatePrice(addWhippedCream, addChocolateTopping);

        //Commented out because the order button will now create an email the user can send
        //displayMessage(createOrderSummary(price, addWhippedCream, addChocolateTopping, addName));

        composeEmail(getString(R.string.order_summary_email_subject, addName),
                createOrderSummary(price, addWhippedCream, addChocolateTopping, addName));
    }

    /**
     * Calculates the price of the order.
     * @param addWhippedCream if the user wants whipped cream or not
     * @param addChocolateTopping if the user wants chocolate topping or not
     * @return the price of the amount of coffees ordered
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolateTopping) {
        //basePrice is set to the price of one cup of coffee
        int basePrice = 5;

        //Add $1 if the user wants whipped cream
        if (addWhippedCream){
            basePrice = basePrice + 1;
        }

        //Add $2 if the user wants chocolate topping
        if (addChocolateTopping) {
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;
    }

    /**
     * Make a intent and send it to any email app.
     * It is a order summary that the user can send
     *
     * @param subject of the email.
     * @param textMessage of the email, order summary.
     */
    public void composeEmail(String subject, String textMessage) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, textMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {

            //Show a toast when the app can't find any email app
            Toast.makeText(this,
                    "We didn't find any email app on your phone",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @param addName of the user of the app
     * @param price of the order
     * @param addWhippedCream to the order or not (true or false)
     * @param addChocolate to the order or not (true or false)
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate,
                                      String addName){
        String priceMessage =
                getString(R.string.order_summary_name, addName)
                +"\n" + getString(R.string.order_summary_whipped_cream) + " " + addWhippedCream
                +"\n" + getString(R.string.order_summary_chocolate) + " " + addChocolate
                +"\n" + getString(R.string.order_summary_quantity) + " " + quantity
                +"\n" + getString(R.string.order_summary_price) + price
                +"\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment (View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else {

            //Show a toast when the user tries to go over 100 cups of coffee
            Toast.makeText(this,
                    "You can't have more than 100 cups of coffee",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement (View view) {
        if (quantity == 1) {
            //Show a toast when the user tries to go below 1 cups of coffee
            Toast.makeText(this,
                    "You can't have less than 1 cup of coffee",
                    Toast.LENGTH_SHORT).show();

            //Return will make the whole decrement method stop
            return;
        }
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
