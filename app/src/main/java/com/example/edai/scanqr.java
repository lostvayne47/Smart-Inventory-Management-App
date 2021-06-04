package com.example.edai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class scanqr extends AppCompatActivity {
    Button scanbtn,uploadbtn,btnLogout;
    TextView productspecview;

    public static TextView scantxt;                                  //declaring scantext for input qr code
    public static String[] arrOfStr;                                //declaring array to store incoming data
    public static EditText productspecdisplay;                     //declaring multitext to display data
    DatabaseReference data_reff;
    Inventory products;



    public static void UpdateText() {
        String temp=null;

        try{
            if(arrOfStr!=null)
            {temp="<b>Product Id:</b> "+arrOfStr[0]+"<br><br><b>Name:</b> "+arrOfStr[1]+"<br><br><b>Quantity:</b> "+arrOfStr[2]+"<br><br><b>Company:</b>"+arrOfStr[3]+"<br><br><b>Color:</b> "+arrOfStr[4];}
            productspecdisplay.setText(Html.fromHtml(temp));
        }
        catch (Exception e){
            e.printStackTrace();
            productspecdisplay.setText("");
        }
    }

    public void scanqrcode(View view){
        startActivity(new Intent(getApplicationContext(),scannerView.class));
        productspecview.setVisibility(View.VISIBLE);
        productspecdisplay.setVisibility(View.VISIBLE);
        uploadbtn.setVisibility(View.VISIBLE);
        btnLogout.setVisibility(View.VISIBLE);

    }
//    public void upload_data(View view){
//        products.setProduct_Id(Integer.parseInt(arrOfStr[0]));
//        products.setProduct_name(arrOfStr[1]);
//        products.setProduct_quantity(Integer.parseInt(arrOfStr[2]));
//        products.setProduct_company(arrOfStr[3]);
//        products.setProduct_color(arrOfStr[4]);
//
//        data_reff.push().setValue(products);
//        Toast.makeText(this, arrOfStr[1],Toast.LENGTH_LONG).show();
//
//        Toast.makeText(this, "Data uploaded successfully",Toast.LENGTH_LONG).show();
//
//
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanqr);

        scantxt=(TextView)findViewById(R.id.scantext);               //declaring scantext for input qr code
        scanbtn=(Button) findViewById(R.id.scanbtn);
        uploadbtn=(Button)findViewById(R.id.uploadbtn);
        productspecview=(TextView)findViewById(R.id.productspecview);
        productspecdisplay =(EditText)findViewById(R.id.productspecdisplay);
        btnLogout = findViewById(R.id.buttonLogout);

        productspecview.setVisibility(View.INVISIBLE);
        productspecdisplay.setVisibility(View.INVISIBLE);
        uploadbtn.setVisibility(View.INVISIBLE);
        btnLogout.setVisibility(View.INVISIBLE);
        products = new Inventory();
        data_reff = FirebaseDatabase.getInstance().getReference().child("Inventory");

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products.setProduct_Id(Integer.parseInt(arrOfStr[0]));
                products.setProduct_name(arrOfStr[1]);
                products.setProduct_quantity(Integer.parseInt(arrOfStr[2]));
                products.setProduct_company(arrOfStr[3]);
                products.setProduct_color(arrOfStr[4]);

                data_reff.push().setValue(products);

                Toast.makeText(scanqr.this, "Data uploaded successfully",Toast.LENGTH_LONG).show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToSignup = new Intent(scanqr.this, login.class);
                startActivity(intToSignup);
            }
        });

    }
}