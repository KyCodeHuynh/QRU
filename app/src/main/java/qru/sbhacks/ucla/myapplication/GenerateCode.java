package qru.sbhacks.ucla.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.google.zxing.BarcodeFormat;


public class GenerateCode extends ActionBarActivity {

    public void exit(View view) {
       System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void generation(View view)
    {
        //Intent generation = new Intent(this, Generated.class);
        Intent generation = new Intent(this, EncodeActivity.class);
        generation.putExtra("checkedBoxes", checkedBoxes(view));
       // generation.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE);
        generation.putExtra(Intents.Encode.FORMAT, "QR_CODE");
        generation.setAction(Intents.Encode.ACTION);
        generation.putExtra(Intents.Encode.TYPE,Contents.Type.TEXT);
        generation.putExtra(Intents.Encode.DATA, HomeScreen.globalStr);
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
