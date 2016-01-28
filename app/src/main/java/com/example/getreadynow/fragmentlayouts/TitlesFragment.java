package com.example.getreadynow.fragmentlayouts;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.PriorityQueue;

/**
 * Created by AlHassaneAgne on 1/27/16.
 */

    /* Displays the details fragment when we are in portrait mode
       When a listview is selected, an activity is created an the
       details pasted on it in portrait mode.
       In landscape mode, the FrameLayout is displayed an the details
       fragment is put onto it.
    */

public class TitlesFragment extends ListFragment {

    //Set to TRUE if we are in landscape mode
    boolean mDualPane;

    //Last item selected by the user in the list
    int mCurCheckedPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //An array adapter connects the array to the listview
        ArrayAdapter <String> connectArrayToListView  =
                new ArrayAdapter<String>(
                        getActivity(),                                     //Returns a context
                        android.R.layout.simple_list_item_activated_1,     //Template for the list
                        SuperHeroInfo.Names);                              //String Array input

        //Connect the list view to our array
        setListAdapter(connectArrayToListView);


        //Get a handle to the details FrameLayout
        View detailsFrame = getActivity().findViewById(R.id.details);

        //Check if we are in landscape mode
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        //Read the last saved position
        if (savedInstanceState != null) {
            mCurCheckedPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            //Let showDetails know which hero was selected
            showDetails(mCurCheckedPosition);
        }

    }

    /* This is called every time android kills an activity
       to give us a chance to save some data from the context
    */

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("curChoice", mCurCheckedPosition);
    }

    //Action to take when a list item has been selected

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        showDetails(position);
    }


    void showDetails (int index) {
        //The most recently selected list index is passed as an input
        mCurCheckedPosition = index;

        //Based on our current display mode
        //Take appropriate action

        if(mDualPane) {

            //highlight the current selection
            getListView().setItemChecked(mCurCheckedPosition, true);

            //Create a handle to the FrameLayout where we will put the details
            DetailsFragment detailsFragment = (DetailsFragment)
                    getFragmentManager().findFragmentById(R.id.details);

            //Check if the DetailsFragment is valid. If it is, it should have
            //the current index stored whenever it is updated

            if (detailsFragment == null || (detailsFragment.getShownIndex () != index)) {
                //Create the new instance and pass the current index

                detailsFragment = DetailsFragment.newInstance(index);

                //start the Fragment transactions
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                //Put the right fragment into the Fragment Transaction
                ft.replace(R.id.details, detailsFragment);

                //Set the transition for the fragment to FADE
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            //Launch activity to show our details fragment
            Intent intentToShowActivity = new Intent();

            //Define the class for the activity
            intentToShowActivity.setClass(getActivity(), DetailsActivity.class);

            //Save the index into the context
            intentToShowActivity.putExtra("index", index);

            //Now open the activity
            startActivity(intentToShowActivity);
        }
    }
};





