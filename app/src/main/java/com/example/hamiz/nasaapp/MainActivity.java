//Hamiz Khan Insy 4306
package com.example.hamiz.nasaapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String url="https://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY";
    private Button button;
    private ListView listView;
    Context context;
    private RequestQueue requestQueue;
    JSONArray jsonArray;
    String msg;
    public  String [] prgmNameList=new String[10];
    public  int [] prgmImages={R.drawable.images,R.drawable.images1,R.drawable.images2,R.drawable.images3,R.drawable.images4,
            R.drawable.images5,R.drawable.images6,R.drawable.images7,R.drawable.images8,R.drawable.images10};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        button=(Button)findViewById(R.id.buttonID);
        listView=(ListView)findViewById(R.id.listViewID);
        requestQueue=Volley.newRequestQueue(this);
        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject jsonObject = response.getJSONObject("near_earth_objects");
                            jsonArray = jsonObject.getJSONArray("2015-09-08");



                            for (int i = 0; i < 10; i++) {
                                JSONObject asteroids = jsonArray.getJSONObject(i);
                                msg="";

                                String name = asteroids.getString("name");

                                String link=asteroids.getString("nasa_jpl_url");

                                String hazardous=asteroids.getString("is_potentially_hazardous_asteroid");

                                msg=name+"\n"+"Hazardous "+hazardous+"\n"+link;

                                prgmNameList[i]=msg;


                            }

                            listView.setAdapter(new CustomAdapter(MainActivity.this, prgmNameList,prgmImages));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Volley","Error");

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3726710")));
                        break;

                    case 1:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3359445")));
                        break;

                    case 2:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3553060")));
                        break;

                    case 3:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3731587")));
                        break;

                    case 4:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3727181")));
                        break;

                    case 5:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3747356")));
                        break;

                    case 6:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3758838")));
                        break;

                    case 7:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3730577")));
                        break;

                    case 8:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3727639")));
                        break;

                    case 9:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3799865")));
                        break;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(jsonObjectRequest);


            }
        });


    }


}
