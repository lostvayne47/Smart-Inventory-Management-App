package com.example.edai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class scanqr extends AppCompatActivity {
    Button scanbtn,uploadbtn,btnLogout;
    TextView productspecview;

    boolean update_flag,filter_flag=true;
    public static TextView scantxt;                                 //declaring scantext for input qr code
    public static String[] arrOfStr;                                //declaring array to store incoming data
    public static EditText productspecdisplay;                     //declaring multitext to display data
    DatabaseReference data_reff,update_data;                                   //declaring database objects
    Inventory products;                                            //declaring products object



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

    public void upload_database(View view) {

        //call update data function to check if the product is already present
        //if already present then only update quantity in the same branch/node
        //need to get reference of the same branch
        filter_flag=true;
        update_data();

    }

    public void update_data(){
//        data_reff.child(String.valueOf(arrOfStr[0])).child("product_quantity").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.i("firebase", "No Duplicate Found", task.getException());
//                }
//                else {
//                    Log.i("firebase", String.valueOf(task.getResult().getValue()));
//                }
//            }
//        });
        data_reff.child(arrOfStr[0]).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Inventory inventory=snapshot.getValue(Inventory.class);
                if(inventory==null){
                    Log.i("Info","Directory does not exist");

                    products = new Inventory(Integer.parseInt(arrOfStr[0]), arrOfStr[1], arrOfStr[3], arrOfStr[4], Integer.parseInt(arrOfStr[2]));
                    data_reff.child(String.valueOf(products.getProduct_Id())).setValue(products);
                    filter_flag=false;
                    Log.i("Info","Data Added");
                    Toast.makeText(scanqr.this, "Data uploaded successfully", Toast.LENGTH_LONG).show();
                }else
                {
                    if(filter_flag) {
                        inventory.product_display();
                        Toast.makeText(scanqr.this, "Data is already present", Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        btnLogout = findViewById(R.id.buttonLogout);

        productspecview.setVisibility(View.INVISIBLE);
        productspecdisplay.setVisibility(View.INVISIBLE);
        uploadbtn.setVisibility(View.INVISIBLE);
        btnLogout.setVisibility(View.INVISIBLE);

        data_reff = FirebaseDatabase.getInstance().getReference().child("Inventory");


        //update_data();
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