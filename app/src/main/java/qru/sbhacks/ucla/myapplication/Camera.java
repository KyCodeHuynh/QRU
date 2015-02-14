package qru.sbhacks.ucla.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Camera extends ActionBarActivity {
    private IntentIntegrator ii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_camera);
        Collection<String> temp = Collections.singleton(BarcodeFormat.QR_CODE.toString());
        ii = new IntentIntegrator(Camera.this);
        ii.initiateScan(temp);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            // handle scan result
            Intent activity = new Intent(this, DisplayResult.class);
            if (scanResult.getContents()!= null) {
                activity.putExtra("xml", scanResult.getContents().toString());
                startActivity(activity);
            }
            else{
                Intent home = new Intent(this, HomeScreen.class);
                startActivity(home);
            }
        }
        // else continue with any other code you need in the method

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera, menu);
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
}
