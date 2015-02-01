package qru.sbhacks.ucla.myapplication;


import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;




public class Profile {

    public String name;
    public String email;
    public String facebook;
    public String number;

    public Profile(String name, String email, String facebook, String number) {
        this.name = name;
        this.email = email;
        this.facebook = facebook;
        this.number = number;
    }

    static public Profile parseString(String str) {
        System.out.println(str);
            // Initialize empty user
            Profile user = new Profile("a", "b", "c", "d");
             String val1 = "";
             String val2 = "";
             String val3 = "";
             String val4 = "";
            String curTag = "";
            String curVal = "";

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                // Opening tag
                if (c == '<') {
                    // Identify tag; move past opening brace
                    i++;

                    // Go until next closing brance
                    while (str.charAt(i) != '>') {
                        curTag += str.charAt(i);
                        i++;
                    }

                    // Move past closing brace
                    i++;

                    // Go until next opening brace
                    while (str.charAt(i) != '<') {
                        curVal += str.charAt(i);
                        System.out.println(curVal);
                        i++;
                    }
                    System.out.println(curTag);

                    // We now have a a full value and a tag
                    switch (curTag) {
                        case "name":
                            user.name = curVal;
                            val1 = curVal;
                            System.out.println("Name written");
                            break;
                        case "phone":
                            user.number = curVal;
                            val2 = curVal;
                            System.out.println("Name written");
                            break;
                        case "email":
                            user.email = curVal;
                            val3 = curVal;
                            System.out.println("email written");
                            break;
                        case "facebook":
                            user.facebook = curVal;
                            val4 = curVal;
                            System.out.println("facebook written");
                            break;

                        // Nothing to store
                        default:
                            break;
                    }

                    curTag = "";
                    curVal = "";
                }
            }

        return new Profile(val1,val2 ,val3, val4);
}
public String readFromFile(Context ctx) {

    try {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                ctx.openFileInput("profile.xml")));
        String inputString;
        StringBuffer stringBuffer = new StringBuffer();
        while ((inputString = inputReader.readLine()) != null) {
            stringBuffer.append(inputString + "\n");
        }
        return inputString;
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Otherwise, failure
    return null;
}

public boolean writeToFile(String tosend) {
    try {
        FileOutputStream fos = openFileOutput("profile.xml", Context.MODE_PRIVATE);
        fos.write(tosend.getBytes());
        fos.close();
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
}

}
