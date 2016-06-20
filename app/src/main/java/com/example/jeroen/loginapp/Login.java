package com.example.jeroen.loginapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.IOException;

public class Login{

    private static EditText username;
    private static EditText password;
    private static Button login_button;
    private static boolean OnlinePassTest = true;
    private static boolean loginSuccess = false;
    private static boolean isStaff = false;
    private static String token;
    private static final String POST_URL = "http://104.131.178.155/api/slaap-analyse";
    private static String PostParams;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void LoginButton() {
        username = (EditText) findViewById(R.id.editText_user);
        password = (EditText) findViewById(R.id.editText_password);
        login_button = (Button) findViewById(R.id.button_login);

        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (OnlinePassTest) {
                            try {
                                System.out.print(sendPOST());
                                loginSuccess = sendPOST();
                            }
                            catch (IOException error){
                                error.printStackTrace();
                            }
                            if (loginSuccess) {
                                Toast.makeText(Login.this, "Username and password is correct",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent("com.example.jeroen.loginapp.Input");
                                startActivity(intent);

                            } else {
                                Toast.makeText(Login.this, "Username and password is NOT correct",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }


                         else {
                            if (username.getText().toString().equals("user") &&
                                    password.getText().toString().equals("pass")) {
                                Toast.makeText(Login.this, "Username and password is correct",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent("com.example.jeroen.loginapp.Input");
                                startActivity(intent);

                            } else {
                                Toast.makeText(Login.this, "Username and password is NOT correct",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
}

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jeroen.loginapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jeroen.loginapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}