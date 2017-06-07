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

public class FourthFragment extends Fragment {

    private RelativeLayout rlClick ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fourth_fragment, container, false);

        final String string= getArguments().getString("msg");

        rlClick = (RelativeLayout) v.findViewById(R.id.rlFrag4);

        rlClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), ""+string , Toast.LENGTH_SHORT).show();
            }
        });


        TextView tv = (TextView) v.findViewById(R.id.tvFragFourth);
        tv.setText(getArguments().getString("msg"));

        return v;
    }

    public static SecondFragment newInstance(String text) {

        SecondFragment f = new SecondFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}