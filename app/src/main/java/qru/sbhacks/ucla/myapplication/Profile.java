package qru.sbhacks.ucla.myapplication;


import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kellyhosokawa on 1/31/15.
 */

// need to implement skip tag?


public class Profile {

    public final String name;
    public final String email;
    public final String facebook;
    public final String number;

    public Profile(String name, String email, String facebook, String number) {
        this.name = name;
        this.email = email;
        this.facebook = facebook;
        this.number = number;
    }

    private static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        }
        catch (XmlPullParserException e) {
            System.err.println("Error: XmlPullParserException");
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Error: IOException");
            System.exit(1);
        }
        finally {
            in.close();
            return null;
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        //parser.require(XmlPullParser.START_TAG, ns, "feed");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("profile")) {
                entries.add(readEntry(parser));
            }
//            else {
//                skip(parser);
//            }
        }
        return entries;
    }

    private Profile readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "profile");
        String name = null;
        String email = null;
        String facebook = null;
        String number = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String field = parser.getName();
            if (field.equals("name")) {
                name = readName(parser);
            } else if (field.equals("email")) {
                email = readEmail(parser);
            } else if (field.equals("facebook")) {
                facebook = readFacebook(parser);
            } else if (field.equals("number")) {
                number = readNumber(parser);
            }
//            else {
//                skip(parser);
//            }
        }
        return new Profile(name, email, facebook, number);
    }

    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return title;
    }

    private String readEmail(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "email");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "email");
        return title;
    }

    private String readFacebook(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "facebook");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "facebook");
        return title;
    }

    private String readNumber(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "number");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "number");
        return title;
    }

    // extracts text values?
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    public void testParser() throws IOException, XmlPullParserException {

        try {
            // TODO: Give correct path to file
            InputStream file = FileInputStream(R.menu.strings.xml);
            List testList = parse(file);

            for (int i = 0; i < testList.size(); i++) {
                Profile p = (Profile) testList.get(i);
                System.out.println("Name: " + p.name);
                System.out.println("Email: " + p.email);
                System.out.println("Facebook: " + p.facebook);
                System.out.println("Number: " + p.number);
            }
        }
        catch (IOException e) {
            System.err.println("IOException");
            System.exit(1);
        }
        catch (XmlPullParserException e) {
            System.err.println("XmlPullParserException");
            System.exit(1);
        }

    }
}
