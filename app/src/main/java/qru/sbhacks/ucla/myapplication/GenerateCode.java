package qru.sbhacks.ucla.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;

import com.google.zxing.BarcodeFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class GenerateCode extends ActionBarActivity {

    public void exit(View view) {
       System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_generate_code);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_generate_code, menu);
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
    public void checkall(View view){
        CheckBox temp = (CheckBox)findViewById(R.id.checkBox);
        temp.setChecked(true);
        temp = (CheckBox)findViewById(R.id.checkBox2);
        temp.setChecked(true);
        temp = (CheckBox)findViewById(R.id.checkBox3);
        temp.setChecked(true);
        temp = (CheckBox)findViewById(R.id.checkBox4);
        temp.setChecked(true);
    }
    public void uncheck (View view){
        CheckBox temp = (CheckBox)findViewById(R.id.checkBox);
        temp.setChecked(false);
        temp = (CheckBox)findViewById(R.id.checkBox2);
        temp.setChecked(false);
        temp = (CheckBox)findViewById(R.id.checkBox3);
        temp.setChecked(false);
        temp = (CheckBox)findViewById(R.id.checkBox4);
        temp.setChecked(false);
    }

    public void generation(View view)
    {
        //Intent generation = new Intent(this, Generated.class);
        Intent generation = new Intent(this, EncodeActivity.class);
       // generation.putExtra("checkedBoxes", checkedBoxes(view));
       // generation.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE);
        StringBuffer stringBuffer = null;
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("Data")));
            String inputString;
            stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        generation.putExtra(Intents.Encode.FORMAT, "QR_CODE");
        generation.setAction(Intents.Encode.ACTION);
        generation.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
        Log.d("Kappa", "data written is: ");
        generation.putExtra(Intents.Encode.DATA, stringBuffer.toString());
        startActivity(generation);
    }

    // Return a boolean array indicating whether checkbox checked
    public boolean[] checkedBoxes(View view)
    {
        // If true: checked, false otherwise
        // Item indices
        // 0. Name
        // 1. Phone number
        // 2. Email
        // 3. Facebook
        // The rest are initialized to false
        boolean [] checkboxes = new boolean[10];
        for (int i = 0; i < checkboxes.length; i++) {
            checkboxes[i] = false;
        }

        if (((CheckBox)findViewById(R.id.checkBox)).isChecked()) {
            checkboxes[0] = true;
        }
        else if (((CheckBox)findViewById(R.id.checkBox2)).isChecked()) {
            checkboxes[1] = true;
        }
        else if (((CheckBox)findViewById(R.id.checkBox3)).isChecked()) {
            checkboxes[2] = true;
        }
        else if (((CheckBox)findViewById(R.id.checkBox2)).isChecked()) {
            checkboxes[3] = true;
        }

        return checkboxes;

    }
}
