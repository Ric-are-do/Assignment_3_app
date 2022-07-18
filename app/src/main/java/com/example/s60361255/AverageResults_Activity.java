package com.example.s60361255;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class AverageResults_Activity extends AppCompatActivity {
    double averageResult;
    int TotalHours;
    int TotalMinutes;
    double Distance = 42.2 ;//this is the distance of the race and th is needed for average calculation
    double TotalTime; // this will be used to store the total amount of mimutes
    int result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_results);

        //Below I am setting the code for the action bar and the Icon so that I have a custom Icon
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_launcher_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //end

        // when displaying the the average distance in minutes there needs to be a set format, this is accomplishaed below
        DecimalFormat time  =  new DecimalFormat( "##.##");
        //end

        GetData(); // here I will call the Get Data method that gets the data entered in MainActivity.java and calculate the average time in tat method
        TextView myResult = (TextView)findViewById(R.id.txtOutput);
        // calculate average race time
        int convert = TotalHours * 60;// convers hours into minutes
        int totalTime =  convert + TotalMinutes;// adds all the minutes together
        double averageResult = (double) (totalTime / Distance);// stores the average time
        myResult.setText(String.valueOf("Your average time per KM was: " + time.format(averageResult) + " minutes"));

        //below is the if statement which will be used to determine the output of the users results
        TextView MedalType = (TextView)findViewById(R.id.txtStatus);
        medal(averageResult);// takes into account the details for the switch statement
        ImageView medalImage = (ImageView)findViewById(R.id.ImgView);


        // Switch statement to display Image depending on the result of the if statement medal method I created earlier
        switch (result) {
            case 1: MedalType.setText("Gold");
            medalImage.setImageResource(R.drawable.gold);
            break;
            case 2: MedalType.setText("Silver");
            medalImage.setImageResource(R.drawable.silver);
            break;
            case 3: MedalType.setText("Bronze");
            medalImage.setImageResource(R.drawable.bronze);
            break;

        };//end switch

        // User needs to select a button which will take them back to the main activity or take them to race friends activity
        Button back = (Button)findViewById(R.id.btnBack);
        Button race = (Button) findViewById(R.id.btnRace);

        //event listener for going back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoBack();
            }
        });
        // event listener for going to race activity
        race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoRace();
            }
        });



    }// end of Main onCreate


    public void GetData(){
        // This code will get the values that are stored in SharedPreferences ( MySharedPref )10
        setContentView(R.layout.activity_average_results);
        SharedPreferences sharedData = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        // SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        TotalHours = sharedData.getInt("Key1",0);
        //Log.d("info", String.valueOf(TotalHours));// used to debug - my goal with this is to see if the data pulled through
        TotalMinutes = sharedData.getInt("Key2",0);
        //Log.d("info", String.valueOf(TotalMinutes));// used to debug - my goal with this is to see if the data pulled through
    };

        public int medal( double averageResult) {
            /* Switch statements cannot use double values as a result, I will use an If statement
            to generte a number that will be stored into the Results variable, which the switch statement
            will use to determine the type of award and which image to display.
             */
            if (averageResult < 11.0) {
                result = 1;
            } else if (averageResult > 11.0 && averageResult < 15.0) {
                result = 2;
            } else {
                result = 3;
            }
            Log.d("info" , "Result string value " + String.valueOf(averageResult)); // used to get check logcat
            Log.d("info" , "Result string value " + String.valueOf(result));// used to get check logcat
            return result;


        }
        // navigation method for going back to main page
        public void GoBack(){
            startActivity(new Intent(AverageResults_Activity.this, Stats.class));
        }// end

        public void GoRace(){
            startActivity(new Intent(AverageResults_Activity.this, Race_friends_activity_start.class));
        }


      //  }




}// end of public class