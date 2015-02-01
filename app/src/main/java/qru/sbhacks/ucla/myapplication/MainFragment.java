package qru.sbhacks.ucla.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.widget.LoginButton;

public class MainFragment extends android.support.v4.app.Fragment {

    private LoginButton loginButton;
    private TextView skipLoginButton;
    private SkipLoginCallback skipLoginCallback;

    public interface SkipLoginCallback {
        void onSkipLoginPressed();
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_testlogin,
                container, false);
        return view;
    }
    public void setSkipLoginCallback(SkipLoginCallback callback) {
        skipLoginCallback = callback;
    }
}

