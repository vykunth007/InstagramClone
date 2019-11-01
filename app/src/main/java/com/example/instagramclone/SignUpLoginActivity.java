package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUserNameSignUp, edtPasswordSignUp, edtUsername, edtPassword;
    private Button btnSignUp, btnSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_activity);

        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, appUser.getUsername() + " Succeffully Added",
                                    FancyToast.SUCCESS, FancyToast.LENGTH_LONG, true).show();

                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(),
                                    FancyToast.ERROR,FancyToast.LENGTH_LONG,true).show();
                        }
                    }
                });

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logInInBackground(edtUsername.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, user.getUsername() + "  Successfully Logged iN",
                                    FancyToast.SUCCESS, FancyToast.LENGTH_LONG, true).show();

                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(),
                                    FancyToast.ERROR, FancyToast.LENGTH_LONG, true).show();


                        }
                    }
                });

            }
        });

    }
}
