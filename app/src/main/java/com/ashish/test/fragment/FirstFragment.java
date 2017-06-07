package com.ashish.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashish.test.R;

/**
 * Created by ashis on 05-06-2017.
 */

public class FirstFragment  extends Fragment {

    public RelativeLayout rlClick ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_fragment, container, false);

        rlClick = (RelativeLayout) v.findViewById(R.id.rlFrag1);

        rlClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String string = getArguments().getString("msg");

                Toast.makeText(getActivity(), ""+string , Toast.LENGTH_SHORT).show();
            }
        });

        TextView tv = (TextView) v.findViewById(R.id.tvFragFirst);
        tv.setText(getArguments().getString("msg"));

        return v;
    }

    public static FirstFragment newInstance(String text) {

        FirstFragment f = new FirstFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
