package qru.sbhacks.ucla.myapplication;


import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.FileInputStream;
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
//
//    private static final String ns = null;
//
//    public List parse(InputStream in) throws XmlPullParserException, IOException {
//        try {
//            XmlPullParser parser = Xml.newPullParser();
//            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
//            parser.setInput(in, null);
//            parser.nextTag();
//            return readFeed(parser);
//        }
//        catch (XmlPullParserException e) {
//            System.err.println("Error: XmlPullParserException");
//            System.exit(1);
//        }
//        catch (IOException e) {
//            System.err.println("Error: IOException");
//            System.exit(1);
//        }
//        finally {
//            in.close();
//            return null;
//        }
//    }
//
//    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
//        List entries = new ArrayList();
//
//        //parser.require(XmlPullParser.START_TAG, ns, "feed");
//        while (parser.next() != XmlPullParser.END_TAG) {
//            if (parser.getEventType() != XmlPullParser.START_TAG) {
//                continue;
//            }
//            String name = parser.getName();
//            // Starts by looking for the entry tag
//            if (name.equals("profile")) {
//                entries.add(readEntry(parser));
//            }
////            else {
////                skip(parser);
////            }
//        }
//        return entries;
//    }
//
//    private Profile readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
//        parser.require(XmlPullParser.START_TAG, ns, "profile");
//        String name = null;
//        String email = null;
//        String facebook = null;
//        String number = null;
//        while (parser.next() != XmlPullParser.END_TAG) {
//            if (parser.getEventType() != XmlPullParser.START_TAG) {
//                continue;
//            }
//            String field = parser.getName();
//            if (field.equals("name")) {
//                name = readName(parser);
//            } else if (field.equals("email")) {
//                email = readEmail(parser);
//            } else if (field.equals("facebook")) {
//                facebook = readFacebook(parser);
//            } else if (field.equals("number")) {
//                number = readNumber(parser);
//            }
////            else {
////                skip(parser);
////            }
//        }
//        return new Profile(name, email, facebook, number);
//    }
//
//    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "name");
//        String title = readText(parser);
//        parser.require(XmlPullParser.END_TAG, ns, "name");
//        return title;
//    }
//
//    private String readEmail(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "email");
//        String title = readText(parser);
//        parser.require(XmlPullParser.END_TAG, ns, "email");
//        return title;
//    }
//
//    private String readFacebook(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "facebook");
//        String title = readText(parser);
//        parser.require(XmlPullParser.END_TAG, ns, "facebook");
//        return title;
//    }
//
//    private String readNumber(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "number");
//        String title = readText(parser);
//        parser.require(XmlPullParser.END_TAG, ns, "number");
//        return title;
//    }
//
//    // extracts text values?
//    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
//        String result = "";
//        if (parser.next() == XmlPullParser.TEXT) {
//            result = parser.getText();
//            parser.nextTag();
//        }
//        return result;
//    }
//
//    public void testParser() throws IOException, XmlPullParserException {
//
//        try {
//            // TODO: Give correct path to file
//            InputStream file=null;
//            List testList = parse(file);
//
//            for (int i = 0; i < testList.size(); i++) {
//                Profile p = (Profile) testList.get(i);
//                System.out.println("Name: "     + p.name);
//                System.out.println("Email: "    + p.email);
//                System.out.println("Facebook: " + p.facebook);
//                System.out.println("Number: "   + p.number);
//            }
//        }
//        catch (IOException e) {
//            System.err.println("IOException");
//            System.exit(1);
//        }
//        catch (XmlPullParserException e) {
//            System.err.println("XmlPullParserException");
//            System.exit(1);
//        }
//
//    }

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
        FileOutputStream fos = openFileOutput("profile.xml",Context.MODE_PRIVATE);
        fos.write(tosend.getBytes());
        fos.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
