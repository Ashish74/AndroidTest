package com.ashish.test.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashish.test.fragment.FirstFragment;
import com.ashish.test.fragment.FourthFragment;
import com.ashish.test.R;
import com.ashish.test.fragment.SecondFragment;
import com.ashish.test.fragment.ThirdFragment;

public class Test1Activity extends AppCompatActivity {

    private ViewPager vpFragment;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;

    private TextView tvLayout4 ;

    private LinearLayout llColor ;

    private Button btnRed;
    private Button btnBlue;
    private Button btnGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Test 1");

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        tvLayout4 = (TextView) findViewById(R.id.tvLayout4);
        llColor = (LinearLayout) findViewById(R.id.llTest1Color);

        btnRed = (Button) findViewById(R.id.btnRed) ;
                btnBlue = (Button) findViewById(R.id.btnBlue) ;
        btnGreen = (Button) findViewById(R.id.btnGreen) ;

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llColor.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llColor.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llColor.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvLayout4.setText(btn1.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvLayout4.setText(btn2.getText().toString());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvLayout4.setText(btn3.getText().toString());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvLayout4.setText(btn4.getText().toString());
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvLayout4.setText(btn5.getText().toString());
            }
        });


        vpFragment = (ViewPager) findViewById(R.id.vpFragment);
        vpFragment.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {

                case 0:
                    return FirstFragment.newInstance("FirstFragment, Instance 1");
                case 1:
                    return SecondFragment.newInstance("SecondFragment, Instance 2");
                case 2:
                    return ThirdFragment.newInstance("ThirdFragment, Instance 3");
                case 3:
                    return FourthFragment.newInstance("FourthFragment, Instance 4");
                default:
                    return FirstFragment.newInstance("FirstFragment, Default");
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
