package qru.sbhacks.ucla.myapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Session;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;


public class View_Change_Profile extends FragmentActivity {
    private MainFragment mainFragment;
    private UiLifecycleHelper uiHelper;
    private static final String TAG = "MainFragment";

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");
        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };
    // Implements the "Cancel" button
    public void exit(View view) {
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("qru.sbhacks.ucla.myapplication", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }

      uiHelper = new UiLifecycleHelper(this, callback);
      uiHelper.onCreate(savedInstanceState);


        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, mainFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }
          StringBuffer stringBuffer = null;
        setContentView(R.layout.activity_view__change__profile);

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

        if (stringBuffer != null) {

            Profile p = new Profile("", "", "", "");
            p = Profile.parseString(stringBuffer.toString());
            EditText temp;
            temp = (EditText) findViewById(R.id.editText);
            if(p.name != null){
                temp.setText(p.name);
            }
            else{
                Log.d("debug", "problem with parsing name from file");
            }
            temp = (EditText) findViewById(R.id.editText2);
            temp.setText(p.email);
            temp = (EditText) findViewById(R.id.editText3);
            temp.setText(p.number);
        }

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
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

    public void saveProfile(View view) throws IOException {
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



        Log.d("Kappa", toFile +"written to global string");

        try {
            FileOutputStream fos = openFileOutput("Data", Context.MODE_PRIVATE);
            fos.write(toFile.getBytes());
            fos.close();
        } catch(Exception e){
            e.printStackTrace();
        }

        System.exit(0);


    }
    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed()) ) {
            onSessionStateChange(session, session.getState(), null);
        }

        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

}
