package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private Button btnSave;

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

        btnSave.setOnClickListener(MainActivity.this);

    }

    /*public void helloWorld(View view){
        ParseObject boxer = new ParseObject("Boxer");
        boxer.put("Punch_Speed", 200);
        boxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this, "Created Object", Toast.LENGTH_SHORT).show();

                }
            }
        });
        //we cannot use save() in main thread

    } */


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
