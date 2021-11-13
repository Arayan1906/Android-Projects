package com.example.coffeeshop;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity =0;// global variable
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {
        EditText name =(EditText)findViewById(R.id.name);
        String namec= name.getText().toString();
        EditText email=(EditText)findViewById(R.id.email);
        String emailc=email.getText().toString();
        CheckBox whippedcream = (CheckBox)findViewById(R.id.whipped_cream);
        CheckBox choco= (CheckBox)findViewById(R.id.choco_chips);
        boolean haswp=whippedcream.isChecked();
        boolean hascc=choco.isChecked();
        int price=calculatePrice(haswp,hascc);
        createOrderSummary(price,haswp,hascc,namec,emailc);
    }
    /**
     * This method is called when the + button is clicked.
     */
    public void increament(View view)
    {
        if(quantity<100)
        {
            quantity = quantity + 1;
            display(quantity);
        }
        else
            return;
    }
    /**
     * This method is called when the - button is clicked.
     */
    public void decreament(View view)
    {
        if(quantity>0)
        {
            quantity = quantity - 1;
            display(quantity);
        }
        else
            return;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private int calculatePrice(boolean addwp , boolean addcc)
    {
        int x= quantity * 5;
        if(addwp==true)
            x=x+quantity*1;
        if(addcc==true)
            x=x+quantity*2;
        return x;
    }
    /**
     * This method displays the given string on the screen.
     */

    private void createOrderSummary(int y, boolean addwp , boolean addcc, String name, String email)
    {

        String z="Name : "+ name +"\nWhipped cream added- "+ addwp +"\nChoco Chips added- "+ addcc +"\nQuantity : " + quantity +"\nTotal: Rs "+ y +"\n\nThank You For Ordering";
        Intent intent= new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT,z);
        if (intent.resolveActivity(getPackageManager())!= null)
            startActivity(intent);
    }
}