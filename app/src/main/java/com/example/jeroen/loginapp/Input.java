package com.example.jeroen.loginapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class Input extends AppCompatActivity {
    private static EditText patient;
    private static EditText nrem1;
    private static EditText nrem2;
    private static EditText nrem3;
    private static EditText nrem4;
    private static EditText rem;
    private static Button send_button;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void SendButton() {
        patient = (EditText) findViewById(R.id.editText_patient);
        nrem1 = (EditText) findViewById(R.id.editText_nrem1);
        nrem2 = (EditText) findViewById(R.id.editText_nrem2);
        nrem3 = (EditText) findViewById(R.id.editText_nrem3);
        nrem4 = (EditText) findViewById(R.id.editText_nrem4);
        rem = (EditText) findViewById(R.id.editText_rem);
        send_button = (Button) findViewById(R.id.button_send);

        send_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {


                            URL url = new URL("http://104.131.178.155/api/slaap-analyse");
                            URLConnection conn = url.openConnection();
                            conn.setDoOutput(true);
                            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

                            writer.write("patient=%S&nrem1=%S&nrem2=%S&nrem3=%S&nrem4=%S&rem=%S".format(patient.getText().toString(), nrem1.getText().toString(), nrem2.getText().toString(),
                                    nrem3.getText().toString(), nrem4.getText().toString(), rem.getText().toString()));
                            writer.flush();
                            String line;
                            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                            writer.close();
                            reader.close();
                        } catch (IOException error) {

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
                "Input Page", // TODO: Define a title for the content shown.
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
                "Input Page", // TODO: Define a title for the content shown.
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
