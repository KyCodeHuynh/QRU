package qru.sbhacks.ucla.myapplication;

import android.content.Intent;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import qru.sbhacks.ucla.myapplication.Intents;


public class DisplayResult extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        Intent data = getIntent();
        String toshow = data.getStringExtra("xml");

        Profile p = null;
        Intent create;
            p = Profile.parseString(toshow);
        if (p!= null){
           create = new Intent(Contacts.Intents.Insert.ACTION);
            create.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            create.putExtra(Contacts.Intents.Insert.EMAIL, p.email);
            create.putExtra(Contacts.Intents.Insert.NAME, p.name);
            create.putExtra(Contacts.Intents.Insert.PHONE, p.number);
            startActivity(create);
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_result, menu);
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
    public void leave(View view){
        Intent activity = new Intent(this, HomeScreen.class);
        startActivity(activity);
    }
}
