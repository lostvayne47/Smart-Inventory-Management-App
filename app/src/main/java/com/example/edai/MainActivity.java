package com.example.edai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button scanbtn;
    Button uploadbtn;
    public static TextView scantxt;
    public static String[] arrOfStr;
    public static EditText multiText;


    public static void UpdateText() {
        String temp=null;

        try{
            if(arrOfStr!=null)
            {temp="Product Id: "+arrOfStr[0]+"\nName: "+arrOfStr[1]+"\nQuantity: "+arrOfStr[2]+"\nCompany: "+arrOfStr[3]+"\nColor: "+arrOfStr[4];}
            multiText.setText(temp);
        }
        catch (Exception e){
            e.printStackTrace();
            multiText.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scantxt=(TextView)findViewById(R.id.scantext);
        scanbtn=(Button) findViewById(R.id.scanbtn);

        multiText=(EditText)findViewById(R.id.scantxt2);

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),scannerView.class));

            }
        });
    }
}