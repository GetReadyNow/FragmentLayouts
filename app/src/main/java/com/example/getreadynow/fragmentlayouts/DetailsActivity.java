package com.example.getreadynow.fragmentlayouts;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by AlHassaneAgne on 1/28/16.
 */
public class DetailsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if the device is in landscape mode

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            //If the screen is in landscape mode, close activity and return
            finish();
            return;
        }

        //Check if we have any hero data saved
        if (savedInstanceState == null) {

            //If not then create the detailsFragment
            DetailsFragment details = new DetailsFragment ();

            //Get the bundle of key value pairs and assign them to the DetailsFragment
            details.setArguments(getIntent().getExtras());

            //Add the details fragment
            getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }

    }
}
