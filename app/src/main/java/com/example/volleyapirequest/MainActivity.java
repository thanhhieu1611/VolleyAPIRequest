package com.example.volleyapirequest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtRocketName1;
    private EditText txtRocketName2;
    private EditText txtRocketName3;
    private EditText txtRocketName4;
    private EditText txtRocketName5;

    private EditText txtRocket1LaunchDescription;
    private EditText txtRocket2LaunchDescription;
    private EditText txtRocket3LaunchDescription;
    private EditText txtRocket4LaunchDescription;
    private EditText txtRocket5LaunchDescription;

    private JSONObject jsonObject;
    private EditText[] txtRocketNames;
    private EditText[] txtRocketLaunchDescriptions;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find objects
        txtRocketName1 = findViewById(R.id.txtName1stRocket);
        txtRocketName2 = findViewById(R.id.txtName2ndRocket);
        txtRocketName3 = findViewById(R.id.txtName3rdRocket);
        txtRocketName4 = findViewById(R.id.txtName4thRocket);
        txtRocketName5 = findViewById(R.id.txtName5thRocket);

        txtRocket1LaunchDescription = findViewById(R.id.txtLaunch1stRocket);
        txtRocket2LaunchDescription = findViewById(R.id.txtLaunch2ndRocket);
        txtRocket3LaunchDescription = findViewById(R.id.txtLaunch3rdRocket);
        txtRocket4LaunchDescription = findViewById(R.id.txtLaunch4thRocket);
        txtRocket5LaunchDescription = findViewById(R.id.txtLaunch5thRocket);

        // Initialization
        txtRocketNames = new EditText[5];
        txtRocketLaunchDescriptions = new EditText[5];

        txtRocketNames[0] = txtRocketName1;
        txtRocketNames[1] = txtRocketName2;
        txtRocketNames[2] = txtRocketName3;
        txtRocketNames[3] = txtRocketName4;
        txtRocketNames[4] = txtRocketName5;

        txtRocketLaunchDescriptions[0] = txtRocket1LaunchDescription;
        txtRocketLaunchDescriptions[1] = txtRocket2LaunchDescription;
        txtRocketLaunchDescriptions[2] = txtRocket3LaunchDescription;
        txtRocketLaunchDescriptions[3] = txtRocket4LaunchDescription;
        txtRocketLaunchDescriptions[4] = txtRocket5LaunchDescription;

        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://fdo.rocketlaunch.live/json/launches/next/5";

        // Request a string response from the provided url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the JSON data
                //textView.setText(response);
                try {
                    // Read from returned string
                    jsonObject = new JSONObject(response);

                    // Getting JSON "result" Array Node
                    JSONArray result = jsonObject.getJSONArray("result");

                    // Looping through all result
                    for(int i = 0; i < result.length(); i++){
                        JSONObject res = result.getJSONObject(i);
                        txtRocketNames[i].setText(res.getString("name"));
                        txtRocketLaunchDescriptions[i].setText(res.getString("launch_description"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "That did not work!", Toast.LENGTH_SHORT).show();
                //textView.setText("That did not work!");
            }
        });

        // Add the request to the requestqueue
        queue.add(stringRequest);
    }
}