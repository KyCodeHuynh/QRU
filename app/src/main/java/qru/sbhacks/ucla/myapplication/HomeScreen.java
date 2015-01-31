package qru.sbhacks.ucla.myapplication;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeScreen extends ActionBarActivity {

    // get text via textView library


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
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

    public void view_change_profile(View view)
    {
        Intent profileChange = new Intent(this, View_Change_Profile.class);
        startActivity(profileChange);
    }

    public void generate_code(View view)
    {
        Intent generateCode = new Intent(this, GenerateCode.class);
        startActivity(generateCode);
    }

    public void exit(View view) {
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public String readFromFile(Context ctx) {

        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    ctx.openFileInput("profile.xml")));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }
            return inputString;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Otherwise, failure
        return null;
    }
}


/*
IO IMPLEMENTATIONS

    public void writeToFile() {
        try {
            FileOutputStream fos = openFileOutput("prof.txt",Context.MODE_PRIVATE);
            String tosend = "bananas";
            fos.write(tosend.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


 */
