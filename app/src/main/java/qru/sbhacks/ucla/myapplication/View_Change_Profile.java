package qru.sbhacks.ucla.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;


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
        File f;
        f = getDir("res/xml", MODE_PRIVATE);
        try{
            f.createNewFile();
        }catch (IOException e){

        }
        FileOutputStream fileos = null;
        try{
            fileos = new FileOutputStream(f);
        } catch(IOException e){

        }
        XmlSerializer xms = Xml.newSerializer();
        try{
            xms.setOutput(fileos, "UTF-8");
            xms.startDocument(null,true);

            //name
            xms.startTag(null, "name");
            //if text view does not equal "", we write something, else we will have <name></name>
            if (!(((TextView)findViewById(R.id.editText)).getText().equals(""))) {
                xms.text((String) ((TextView) findViewById(R.id.editText)).getText());
                Toast.makeText(getApplicationContext(), "name written...", Toast.LENGTH_SHORT);
            }
            xms.endTag(null, "name");


            //phone number
            xms.startTag(null, "phone");
            xms.text((String)((TextView)findViewById(R.id.editText2)).getText());
            xms.endTag(null, "phone");

            //email
            xms.startTag(null, "email");
            xms.text((String)((TextView)findViewById(R.id.editText3)).getText());
            xms.endTag(null, "email");



            xms.flush();
            fileos.close();

        }catch (Exception e){

        }
    }
}
