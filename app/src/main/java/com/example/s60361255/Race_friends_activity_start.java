package com.example.s60361255;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Race_friends_activity_start extends AppCompatActivity {
    double weightBefore;// store the weight before race
    double weightAfter;// store weight after race
    double Result; // store the result of the calc to loose weight
    double convertRate = 2.2;

    // below are the different string variables we will use
    String KG = " Killograms";
    String lbs = "Pounds ";
    String Gain = "Gained";
    String Lost = "Lost";
    String Gain_or_loss;
    String Unit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_friends_start);

        //Below I am setting the code for the action bar and the Icon so that I have a custom Icon
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_launcher_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //end

        //get the values of the weight before and after marathon as well as radio button
        // we will use the buttons to determine what message to print, should we print killograms or pounds in the result
        // the radip buttons will answer that for us
        EditText preRaceWeight = (EditText) findViewById(R.id.WeightBefore);
        EditText posRaceWeight = (EditText) findViewById(R.id.WeightAfter);
        TextView WeightChange = (TextView) findViewById(R.id.weightChange);

        Button button = (Button)findViewById(R.id.btnCalcWeight);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weightBefore = Double.parseDouble(preRaceWeight.getText().toString());
                weightAfter = Double.parseDouble(posRaceWeight.getText().toString());
                Result = weightAfter - weightBefore;
                Log.d("weight",String.valueOf(Result));
                Log.d("weight",(msgOUTWeight()));
                checked();
                WeightChange.setText("You " + Gain_or_loss + " " + String.valueOf(Result) + Unit);





            }// end of onclick
        });



    }//end of on on create

    public String msgOUTWeight(){ // method will hold string value for gain or loss
        if (Result > 0.01){
            Gain_or_loss = Gain;
        }else {
            Gain_or_loss = Lost;
        }
        return Gain_or_loss;
    };

    public String checked(){ // this will cause a message for the unit of measurement  as well as convert form kg to lbs and visa versa
        final RadioButton killo = (RadioButton)findViewById(R.id.radioKG);
        final RadioButton pounds = (RadioButton)findViewById(R.id.radiolbs);

        if (killo.isChecked()){
            Unit = KG;


        }else if(pounds.isChecked()) {

            Unit = lbs;
        }else{
            Toast.makeText(this, "Please select unit of measurement", Toast.LENGTH_LONG).show();
        }
        return Unit;
    };

}//end of Activity