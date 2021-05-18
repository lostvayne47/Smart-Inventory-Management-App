package com.example.edai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class scanqr extends AppCompatActivity {
    Button scanbtn,uploadbtn;
    TextView productspecview;

    public static TextView scantxt;                                  //declaring scantext for input qr code
    public static String[] arrOfStr;                                //declaring array to store incoming data
    public static EditText productspecdisplay;                     //declaring multitext to display data



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
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanqr);

        scantxt=(TextView)findViewById(R.id.scantext);               //declaring scantext for input qr code
        scanbtn=(Button) findViewById(R.id.scanbtn);
        uploadbtn=(Button)findViewById(R.id.uploadbtn);
        productspecview=(TextView)findViewById(R.id.productspecview);
        productspecdisplay =(EditText)findViewById(R.id.productspecdisplay);

        productspecview.setVisibility(View.INVISIBLE);
        productspecdisplay.setVisibility(View.INVISIBLE);
        uploadbtn.setVisibility(View.INVISIBLE);

    }
}