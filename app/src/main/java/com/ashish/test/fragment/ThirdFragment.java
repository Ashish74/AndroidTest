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

public class ThirdFragment extends Fragment {

    public RelativeLayout rlClick ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.third_fragment, container, false);

        rlClick = (RelativeLayout) v.findViewById(R.id.rlFrag3);

        rlClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String string = getArguments().getString("msg");

                Toast.makeText(getActivity(), ""+string , Toast.LENGTH_SHORT).show();
            }
        });


        TextView tv = (TextView) v.findViewById(R.id.tvFragThird);
        tv.setText(getArguments().getString("msg"));

        return v;
    }

    public static ThirdFragment newInstance(String text) {

        ThirdFragment f = new ThirdFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
