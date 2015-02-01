package qru.sbhacks.ucla.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.Encoder;


public class DisplayQR extends ActionBarActivity {
    private String xmldata;
    private QRCodeWriter qcw;
    private int dimensions;
    private boolean pass;
    private Context temp;
    private QRCodeEncoder qrCodeEncoder;
    private Bitmap bm;
    private ImageView IV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_qr);

        //creates new intent
        Intent intent = new Intent();
        intent.setAction(Intents.Encode.ACTION);
        //tells we weant to encode
        intent.putExtra(Intents.Encode.DATA, getIntent().getStringExtra("xml"));
        //appends a string for the format
        pass = true;
        temp = null;
        try {
            temp = createPackageContext("qru.sbhacks.ucla.myapplication", CONTEXT_INCLUDE_CODE);
        }catch (PackageManager.NameNotFoundException e){

        }
        dimensions = 128;
        qrCodeEncoder = null;
        bm = null;
        try {
           qrCodeEncoder= new QRCodeEncoder(temp, intent, dimensions, pass);
        }catch(WriterException e){

        }
        try {
            bm = qrCodeEncoder.encodeAsBitmap();
        }catch (WriterException e){

        }

       IV = (ImageView)findViewById(R.id.imageView);
        IV.setImageBitmap(bm);






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_qr, menu);
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
