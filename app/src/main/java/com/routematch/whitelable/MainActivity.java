package com.routematch.whitelable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.routematch.mymodule.ModuleFragment;

public class MainActivity extends AppCompatActivity implements ModuleFragment.OnFragmentInteractionListener {

    String BLANK_FRAGMENT = "fragment_blank";
    String mCurrentFragmentKey = BLANK_FRAGMENT;
    private Fragment mFragment;
    public static boolean ADD_FRAGMENT_TO_BACKSTACK = true;
    public static boolean NOT_ADD_FRAGMENT_TO_BACKSTACK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.text_main_type);

        TextView hello = (TextView) findViewById(R.id.text_main_info);

        Button greeting = (Button) findViewById(R.id.button_main_action);
        greeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GreetingActivity.class);
                startActivity(intent);
            }
        });

        if (BuildConfig.PAID_VERSION) {// this is the flag configured in build.gradle
            textView.setText(getString(R.string.msg_paid));
        } else {
            textView.setText(getString(R.string.msg_free));
        }

        setCurrentFragmentKey(BLANK_FRAGMENT);
        loadBlankFragment(getIntent().getExtras(), NOT_ADD_FRAGMENT_TO_BACKSTACK);
    }

    public void loadBlankFragment(Bundle args, boolean addToBackStack) {
        getFragment(args, addToBackStack, new ModuleFragment(), BLANK_FRAGMENT).commit();
    }

    private FragmentTransaction getFragment(Bundle args, boolean addToBackStack, Fragment fragment,
            String fragmentKey) {
        setCurrentFragmentKey(fragmentKey);
        mFragment = fragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        if (args != null) fragment.setArguments(args);

        ft.replace(R.id.frame_main_container, fragment, fragmentKey);
        if (addToBackStack) ft.addToBackStack("");
        return ft;
    }

    public void setCurrentFragmentKey(String fragment) {
        this.mCurrentFragmentKey = fragment;
    }

    private void reloadFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

        ft.detach(mFragment);
        ft.attach(mFragment);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
