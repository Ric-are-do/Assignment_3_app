package com.example.s60361255;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Stats extends AppCompatActivity {

    ArrayList<Double> Races;// instantiate the array

    int RaceNum; // here we will ask the user how many other marathons they have run
    double averagePerKillometer; // this value will hold the average per killometer as per user input

    ListView listView; // instantiate list view
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        //Below I am setting the code for the action bar and the Icon so that I have a custom Icon
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.icon_launcher_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //end


        //todo get the user to enter how many other races they have been part of max 10 , this should be stored in variable  NumRaces
        EditText raceNum = (EditText) findViewById(R.id.numbeOfRacesRan);
        TextView ArrayLeadMessage = (TextView)findViewById(R.id.txtOutMessage);

        // hidden fields that need to display when user clicks button


        Button button = (Button) findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RaceNum = Integer.parseInt(raceNum.getText().toString());
                if( raceNum.getText().toString().isEmpty()){
                    Toast.makeText(Stats.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                }
                    Log.d("popopo", String.valueOf(RaceNum));
                button.setEnabled(false); // prevents user from entering a new value once selected
                // output array message
                ArrayLeadMessage.setText("  Great you have completed " +RaceNum + " marathons, Lets enter that data into a list.");
                MakeVisible(); // run make visible method so we can se the next fields

            }
        });// end of onclick listener 1

        // the variables for the array list are below
        EditText inputMarahonAvergae = (EditText)findViewById(R.id.inputMarathon);
        Button btnMarathon = (Button)findViewById(R.id.SubmitMarathon);
        Button finSubmit = (Button)findViewById(R.id.btnfin);
        Races=new ArrayList<>();
        // below is the counter for when user enters number of races


        btnMarathon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // adding distances to an array
                averagePerKillometer = Double.parseDouble(inputMarahonAvergae.getText().toString());
                Races.add(averagePerKillometer);
                inputMarahonAvergae.setText("");




                Log.d("arList", String.valueOf(Races));
                // Preventing user form entering more values once they have entered the same values as the marathons attended
                if ((Races.size()) == RaceNum){
                    inputMarahonAvergae.setEnabled(false);
                    btnMarathon.setEnabled(false);
                    finSubmit.setVisibility(View.VISIBLE);
                        finSubmit.setOnClickListener(new View.OnClickListener() { // when this button is clicked it will do the following
                            @Override
                            public void onClick(View view) {
                                MakeGone(); //Hides all elements on
                                // 0utput array
                                // getting the list view showing tge results of all the marathons
                                listView = (ListView)findViewById(R.id.ListView);
                                ArrayAdapter arrayAdapter = new ArrayAdapter(Stats.this, android.R.layout.simple_list_item_1,Races);
                                listView.setAdapter(arrayAdapter);
                                ArrayLeadMessage.setText("Below is a list of marathon times you have achieved ");



                            }
                        });


                }


            }
        });

    }// end on create

    public void MakeVisible(){
        EditText inputMarahonAvergae = (EditText)findViewById(R.id.inputMarathon);
        Button btnMarathon = (Button)findViewById(R.id.SubmitMarathon);
        inputMarahonAvergae.setVisibility(View.VISIBLE);
        btnMarathon.setVisibility(View.VISIBLE);
    }// end of makeVisible

    public void MakeGone(){
        EditText inputMarahonAvergae = (EditText)findViewById(R.id.inputMarathon);
        Button btnMarathon = (Button)findViewById(R.id.SubmitMarathon);
        Button finSubmit = (Button)findViewById(R.id.btnfin);
        TextView ArrayLeadMessage = (TextView)findViewById(R.id.txtOutMessage);

        inputMarahonAvergae.setVisibility(View.GONE);
        btnMarathon.setVisibility(View.GONE);
        finSubmit.setVisibility(View.GONE);

    }


}// end class