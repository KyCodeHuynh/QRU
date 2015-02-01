package qru.sbhacks.ucla.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Generated extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_generated, menu);
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
    public void runtest(View view){
        Profile jong;
        System.out.println("Inside of runtest()");
        jong=Profile.parseString("<name>Kelly</name><phone>555-123-5465</phone>" +
                "<email>kelly@ucla.edu</email><facebook>Kelly Hosokawa</facebook>");

        TextView theone=(TextView) findViewById(R.id.textview20);
        theone.setText(jong.name + "\n" + jong.number + "\n" + jong.email + "\n" + jong.facebook);
        System.out.println(jong.name);

    }

}
