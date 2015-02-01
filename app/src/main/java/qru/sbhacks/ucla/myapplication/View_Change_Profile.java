package qru.sbhacks.ucla.myapplication;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.Session;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class View_Change_Profile extends ActionBarActivity {

    // Implements the "Cancel" button
    public void exit(View view) {
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__change__profile);
//        Profile testProfile = new Profile("1", "2", "#", "4");
//        try {
//            testProfile.testParser();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        }

//        Profile p = new Profile("", "", "", "");
//        String rawProfile = p.readFromFile(Context);
//        p = Profile.parseString(rawProfile);

        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("profile.xml")));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view__change__profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveProfile(View view) {
        Profile p = new Profile("", "", "", "");

        TextView nameField = (TextView)findViewById(R.id.editText);
        p.name = nameField.getText().toString();

        TextView emailField = (TextView)findViewById(R.id.editText2);
        p.email = emailField.getText().toString();

        TextView numField = (TextView)findViewById(R.id.editText3);
        p.number = numField.getText().toString();

        String toFile = "";

        toFile += "<name>" + p.name + "</name>" + "<phone>" + p.number
                    + "</phone>" + "<email>" + p.email + "</email>"
                    + "<facebook>" + "</facebook>";

        System.out.println(toFile);

        p.writeToFile(toFile);

    }

}
