package qru.sbhacks.ucla.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


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
    public void QRcode(View view){
        String given = "<TEXT>Bruin</TEXT><EMAIL_TYPE>JoeBruin@ucla.edu</EMAIL_TYPE><PHONE_TYPE>9999999</PHONE_TYPE>";
        Intent activity = new Intent(this, DisplayQR.class);
        activity.putExtra("xml", given);
        startActivity(activity);
    }
}
