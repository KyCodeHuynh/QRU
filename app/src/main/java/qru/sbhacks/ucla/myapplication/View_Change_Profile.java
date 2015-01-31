package qru.sbhacks.ucla.myapplication;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/*public static class Data{
    public final String name;
    public final String email;
    public final String phonenumber;
    private Data(String Name, String Email, String Phonenumber){
        name = Name;
        email= Email;
        phonenumber = Phonenumber;
    }
}*/
public class View_Change_Profile extends ActionBarActivity {

    public void exit(View view) {
        System.exit(0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__change__profile);


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
    public void updateProfile(View view){
        FileOutputStream fileos = null;
        try {
            fileos = openFileOutput("profile.xml", Context.MODE_PRIVATE);
            Log.d("DebugUpdate", "Filestream Generated");
        }catch (FileNotFoundException e) {
        }
        XmlSerializer xms = Xml.newSerializer();

        try{
            xms.setOutput(fileos, "UTF-8");
            xms.startDocument(null,true);

            Log.d("DebugUpdate", "Filestream bound");
            //name
            xms.startTag(null, "name");
            //if text view does not equal "", we write something, else we will have <name></name>
            Log.d("DebugUpdate", "tag written");
            String temp = ((TextView)findViewById(R.id.editText)).getText().toString();
                xms.text(temp);
                //Toast.makeText(getApplicationContext(), "name written...", Toast.LENGTH_SHORT).show();

            xms.endTag(null, "name");
            Log.d("DebugUpdate", "Name written");

            //phone number
            xms.startTag(null, "phone");
            xms.text(((TextView)findViewById(R.id.editText2)).getText().toString());
            xms.endTag(null, "phone");
            Log.d("DebugUpdate", "phone written");

            //email
            xms.startTag(null, "email");
            xms.text(((TextView)findViewById(R.id.editText3)).getText().toString());
            xms.endTag(null, "email");

            Log.d("DebugUpdate", "email written");


            xms.flush();
            fileos.close();

        }catch (Exception e){
            Log.d("DebugUpdate", "failure");
        }
        Toast.makeText(getApplicationContext(), "Profile Updated!", Toast.LENGTH_SHORT).show();

    }
}
