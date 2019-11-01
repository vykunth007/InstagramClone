package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private Button btnSave, btnGetAllData;
    private TextView txtGetData;
    private  String allKickBoxers;
    private Button btnTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        btnSave = findViewById(R.id.btnSave);
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        btnTransition = findViewById(R.id.btnNextActivity);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("pHoTc0CZBd", new GetCallback<ParseObject>() { //to get one object use getinbgn()
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if (object != null && e == null) {
                            txtGetData.setText(object.get("Name") + " - " + "PunchPower" + object.get("Punch_Power"));
                        }

                    }
                });
            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");

                //queryAll.whereGreaterThan("Punch_Power", 100000);
                queryAll.whereGreaterThanOrEqualTo("Punch_Power", 1000);
                queryAll.setLimit(1);

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject kickBoxer: objects) {
                                    allKickBoxers = allKickBoxers + kickBoxer.get("Name") + "\n";
                                }
                                FancyToast.makeText(MainActivity.this, allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();


                            }
                        } else {
                            FancyToast.makeText(MainActivity.this, e.getMessage() + "", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }


                    }
                });



            }
        });

        btnSave.setOnClickListener(MainActivity.this);

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SignUpLoginActivity.class);
                startActivity(intent);

            }
        });

    }



    @Override
    public void onClick(View view) {

        try {


            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("Name", edtName.getText().toString());
            kickBoxer.put("Punch_Speed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickBoxer.put("Punch_Power", Integer.parseInt(edtPunchPower.getText().toString()));
            kickBoxer.put("Kick_speed", Integer.parseInt(edtKickSpeed.getText().toString())); //the first type will be saved as the default type in parseSERVER
            kickBoxer.put("Kick_Power", Integer.parseInt(edtKickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null)

                        FancyToast.makeText(MainActivity.this, kickBoxer.get("Name") + " is saved to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    else
                        FancyToast.makeText(MainActivity.this, e.getMessage() + "", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                }
            });
        } catch (Exception m) {
            FancyToast.makeText(MainActivity.this, m.getMessage() + "", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }

    }
}
