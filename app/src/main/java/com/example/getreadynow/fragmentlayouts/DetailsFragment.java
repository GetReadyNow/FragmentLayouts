package com.example.getreadynow.fragmentlayouts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by AlHassaneAgne on 1/28/16.
 */
public class DetailsFragment extends Fragment {
    //Create a DetailsFragment that contains the hero data for the correct index
    public static DetailsFragment newInstance (int index) {
        DetailsFragment df = new DetailsFragment();

        //Use a bundle to save contextual info
        Bundle args = new Bundle();
        args.putInt("index", index);

        df.setArguments(args);

        return df;
    }

    public int getShownIndex() {
        //Returns the index assigned
        return getArguments().getInt("index", 0);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Use a scroll view to put your hero data in
        ScrollView scroller = new ScrollView(getActivity());

        //Create a text view to go into the scroller
        TextView textView = new TextView(getActivity());

        //A TypedValue takes padding information and device configuration
        //and dynamically calculates the final padding information
        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
                getActivity().getResources().getDisplayMetrics());

        //Set the padding on the textView
        textView.setPadding(padding, padding, padding, padding);

        //Add the textView to the scroller
        scroller.addView(textView);

        //Set the currently selected hero data to the TextView
        textView.setText(SuperHeroInfo.History[getShownIndex()]);
        return scroller;
    }
}
