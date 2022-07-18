package com.example.s60361255;
//Student Number : 60361255
//Student Name : Ricardo Duarte
/* Purpose:
 My city is planning a 42 killometer marathone race,
 Participants will be ranked in 3 groups being Top,Middle or Bottom depending on their completion times
 The application should show the user what group they fall into depending on their race times.

 Please note the following
 problem with 10 hour control -
 Question states that race cannot be longer than 10 hours however for someone to get a bronze they would need
 to run for longer than 15 minutes per km
 15*42.2 = 633 / 60 minutes
 = 10.55 hours, for someonme to get bronze they would need to run longer than 10.55 hours
 the acceptance criteria is incorrect here as it doesnt cater for the what its asking for
 as a result I have removed the 10 hour cap to make the max someone can run the race to 10:59 mins so you can
 see the bronze award thats part of the switch statement



 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
//below I am adding the variables that will be used in the code
    int Hours = 0;
    int Minutes = 0; // here I will store the number of minutes user ran for.

    int TotalHours;
    int TotalMinutes;
    float Average;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Below I am setting the code for the action bar and the Icon so that I have a custom Icon
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_launcher_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //end


        //Getting users input and storing it into variables
        EditText Hours = (EditText)findViewById(R.id.InputHours);
        EditText Minutes = (EditText)findViewById(R.id.InputMinutes);

        //if statement to stop empty resutlts

        Button submit =  (Button)findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                // If statement prevents blank fields
            if(Hours.getText().toString().isEmpty() || Minutes.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "Fields Cannot be blank", Toast.LENGTH_LONG).show();
            }else {
                // converting the input  integers so tht we can apply math unctions to them
                final int TotalHours = (int) Integer.parseInt(Hours.getText().toString());

                final int TotalMinutes = (int) Integer.parseInt(Minutes.getText().toString());
                // Error handling, If the user enters a value tats larget thant 10 hours or larger than 59 minutes
                if( TotalHours >10 || TotalMinutes > 59 ){
                    if(TotalHours > 10){
                        //error handling - if user enters more than 10 hours to finish race, Toast will show and field will clear
                        Toast.makeText(MainActivity.this, "The maximum race time is 10 hours", Toast.LENGTH_SHORT).show();
                        Hours.setText("");
                    }
                    if(TotalMinutes > 59) {
                        Toast.makeText(MainActivity.this, "Maximum entry of 59 minutes in an hour ", Toast.LENGTH_SHORT).show();
                        Minutes.setText("");
                    }
                }else{
                    Persisting_data(TotalHours,TotalMinutes);
                }
            }
                } // end of onclick method


// ALL METHODS THAT ARE NEEDED ARE STORED BELOW

            private void Persisting_data( int TotalHours, int TotalMinutes){
                // this method stores the values of Total hours and TotalMinutes in the shared prefence folder
                SharedPreferences sharedPreferences =  getSharedPreferences("MySharedPref" , MODE_PRIVATE);
                final SharedPreferences.Editor editor = sharedPreferences.edit();
                // storing values in keys to be used later in app
                editor.putInt("Key1", TotalHours);

                editor.putInt("Key2", TotalMinutes);
                editor.commit();
                Toast.makeText(MainActivity.this, "Saved Data", Toast.LENGTH_LONG).show();
                // This part of the code will take the user to the next page that will display the results as well as an image
                startActivity(new Intent(MainActivity.this , AverageResults_Activity.class));




            }// end of Persisting data Method

        });
    }
}