package com.ashish.test.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ashish.test.R;
import com.ashish.test.adapter.LocationSelectAdapter;
import com.ashish.test.constants.IErrors;
import com.ashish.test.constants.IJSON;
import com.ashish.test.helper.AppController;
import com.ashish.test.helper.ConnectionDetector;
import com.ashish.test.helper.RecyclerItemClickListener;
import com.ashish.test.model.TestModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Test2Activity extends AppCompatActivity {

    private String urlJsonArry = "https://express-it.optusnet.com.au/sample.json";
    private static String TAG = Test2Activity.class.getSimpleName();

    ArrayList<TestModel> testModelArrayList;

    private RelativeLayout rlChooseLocation;
    private TextView tvChooseLocation;
    private TextView tvTransportMode;
    private Button btNavigate;
    private TextView tvTransportModeTrain;
    private TextView tvTransportModeCar;

    private Dialog dialogSelectLocation;
    private RecyclerView recyclerView;
    private RelativeLayout progress, noInternet;
    private LocationSelectAdapter recyclerViewAdapter;
    LinearLayoutManager linearLayoutManager;

    String stringLocationName = null;
    String stringCar = null;
    String stringTrain = null;
    String stringLatitude = null;
    String stringLongitude = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Test 2");

        rlChooseLocation = (RelativeLayout) findViewById(R.id.rlChooseLocation);
        tvChooseLocation = (TextView) findViewById(R.id.tvChooseLocation);
//        tvTransportMode = (TextView) findViewById(R.id.tvTransportMode);
        btNavigate = (Button) findViewById(R.id.btNavigate);

        tvTransportModeTrain = (TextView) findViewById(R.id.tvTransportModeTrain);
        tvTransportModeCar = (TextView) findViewById(R.id.tvTransportModeCar);

        dialogSelectLocation = new Dialog(Test2Activity.this);
//                dialogSelectLocation.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSelectLocation.setTitle("Select Location");
        dialogSelectLocation.setContentView(R.layout.dialog_select_location);
        dialogSelectLocation.setCancelable(true);
        dialogSelectLocation.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        //dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);

        recyclerView = (RecyclerView) dialogSelectLocation.findViewById(R.id.rvSelectLocation);
        progress = (RelativeLayout) dialogSelectLocation.findViewById(R.id.progress);
        noInternet = (RelativeLayout) dialogSelectLocation.findViewById(R.id.noInternet);

        linearLayoutManager = new LinearLayoutManager(Test2Activity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        rlChooseLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogSelectLocation.show();

                testModelArrayList = new ArrayList<>();

                recyclerViewAdapter = new LocationSelectAdapter(Test2Activity.this, testModelArrayList);

                // making json array request
                makeJsonArrayRequest();

                recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {

                                stringLocationName = testModelArrayList.get(position).getName();
                                stringCar = testModelArrayList.get(position).getCar();
                                stringTrain = testModelArrayList.get(position).getTrain();
                                stringLatitude = testModelArrayList.get(position).getLatitude();
                                stringLongitude = testModelArrayList.get(position).getLongitude();

                                tvChooseLocation.setText(stringLocationName);

                                if (stringCar == null) {
                                    stringCar = "";
                                } else {

                                    tvTransportModeCar.setText(stringCar);
                                }

                                if (stringTrain == null) {
                                    stringTrain = "";
                                } else {
                                    tvTransportModeTrain.setText(stringTrain);
                                }

                                System.out.println(" stringLocationName " + stringLocationName + " stringCar " + stringCar +
                                        " stringTrain " + stringTrain + " stringLatitude " + stringLatitude + " stringLongitude " + stringLongitude);

                                dialogSelectLocation.dismiss();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }
                        }));
            }
        });

        btNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (stringLocationName != null) {
                    Intent intent = new Intent(Test2Activity.this, MapsActivity.class);
                    intent.putExtra("stringLatitude", stringLatitude);
                    intent.putExtra("stringLongitude", stringLongitude);
                    intent.putExtra("stringLocationName", stringLocationName);
                    startActivity(intent);
                } else {
                    Toast.makeText(Test2Activity.this, IErrors.LOCATION_EMPTY, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    /**
     * Method to make json array request where response starts with [
     */
    private void makeJsonArrayRequest() {

        ConnectionDetector cd = ConnectionDetector.getInstance(Test2Activity.this);
        if (cd.isConnectionAvailable()) {

            progress.setVisibility(View.VISIBLE);
            noInternet.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);

            JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());

                            try {

                                testModelArrayList.clear();

                                for (int i = 0; i < response.length(); i++) {

                                    testModelArrayList.add(parseData(response.getJSONObject(i)));
                                }

                                for (int i = 0; i < testModelArrayList.size(); i++) {
                                    System.out.println("\n " + testModelArrayList.size() + " " +
                                            testModelArrayList.get(i).getTrain());
                                }


                                if (testModelArrayList.size() == 0) {
                                    progress.setVisibility(View.GONE);
                                    noInternet.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                } else {

                                    System.out.println("testModelArrayList size : " + testModelArrayList.size());

                                    recyclerView.setAdapter(recyclerViewAdapter);
                                    recyclerViewAdapter.notifyDataSetChanged();

                                    progress.setVisibility(View.GONE);
                                    noInternet.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                progress.setVisibility(View.GONE);
                                noInternet.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    progress.setVisibility(View.GONE);
                    noInternet.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            });

            AppController.getInstance().addToRequestQueue(req);


        } else {
            Toast.makeText(Test2Activity.this, IErrors.INTERNET_CHECK, Toast.LENGTH_LONG).show();
            progress.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }


    }

    private TestModel parseData(JSONObject jsonObject) {

        TestModel testModelObj = new TestModel();

        try {

            testModelObj.setId(jsonObject.getString(IJSON.ID));
            testModelObj.setName(jsonObject.getString(IJSON.NAME));

            JSONObject fromcentralJSONObj = jsonObject.getJSONObject(IJSON.FROM_CENTRAL);

            if (fromcentralJSONObj.has(IJSON.CAR)) {
                testModelObj.setCar(fromcentralJSONObj.getString(IJSON.CAR));
            }
            if (fromcentralJSONObj.has(IJSON.TRAIN)) {
                testModelObj.setTrain(fromcentralJSONObj.getString(IJSON.TRAIN));
            }

            JSONObject locationJSONObj = jsonObject.getJSONObject(IJSON.LOCATION);
            testModelObj.setLatitude(locationJSONObj.getString(IJSON.LATITUDE));
            testModelObj.setLongitude(locationJSONObj.getString(IJSON.LONGITUDE));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return testModelObj;
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
