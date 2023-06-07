package com.tomato.fitvibe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Detailpage extends AppCompatActivity {
    RequestQueue queue;
//chrger g
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpage);
        TextView txtData;
        TextView lblWarm=findViewById(R.id.lblWarm);
        TextView lblCool=findViewById(R.id.lblCool);
        TextView lblExer=findViewById(R.id.lblExer);
        String Time= getIntent().getStringExtra("Time");
        String Muscle= getIntent().getStringExtra("Muscle");
        String Location= getIntent().getStringExtra("Location");
        String Equipment= getIntent().getStringExtra("Equipment");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://workout-planner1.p.rapidapi.com/?time=30&muscle=biceps&location=gym&equipment=dumbbells";
        StringRequest getRequest = new StringRequest(Request.Method.GET, "https://workout-planner1.p.rapidapi.com/?time=30&muscle=biceps&location=gym&equipment=dumbbells",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(getApplicationContext(),response.substring(0,500),Toast.LENGTH_SHORT).show();
                        System.out.println("my data"+response);
                        //JSONParser parser = new JSONParser();
                        try {
                            JSONObject obj = new JSONObject(response);
                            //String id=(String) obj.get("Warm Up");
                            //txtData.setText(obj.getString("Warm Up"));
                            JSONArray warmdata=obj.getJSONArray("Warm Up");
                            JSONObject warm = warmdata.getJSONObject(0);
                            lblWarm.setText("WarmUp\n");
                            for (int i=0;i<warmdata.length();i++) {
                                warm = warmdata.getJSONObject(i);
                                String exer = warm.getString("Exercise");
                                String time= warm.getString("Time");
                                lblWarm.append("Exercise: "+exer+"\n");
                                lblWarm.append("Time: "+time+"\n");
                                Log.e("exercise data :   " , exer+"\n");
                                Log.e("time data " , time+"\n");
                            }
                            lblExer.setText("Exercise");
                            JSONArray exedata=obj.getJSONArray("Exercises");
                            for (int i=0;i<exedata.length();i++) {
                                warm = exedata.getJSONObject(i);
                                String exer = warm.getString("Exercise");
                                String sets= warm.getString("Sets");
                                String rep= warm.getString("Reps");
                                lblExer.append("Exercise: "+exer+"\n");
                                lblExer.append("Sets: "+sets+"\n");
                                lblExer.append("rep: "+rep+"\n");
                                Log.e("exercise data 11111   " , exer);
                                Log.e("time data " , sets);
                                System.out.println("rep data " + rep);
                            }
                            lblCool.setText("CoolDown");
                            JSONArray cddata=obj.getJSONArray("Cool Down");
                            for (int i=0;i<cddata.length();i++) {
                                warm = cddata.getJSONObject(i);
                                String exer = warm.getString("Exercise");
                                String time= warm.getString("Time");
                                lblCool.append("Exercise: "+exer+"\n");
                                lblCool.append("Time: "+time+"\n");
                                Log.e("exercise data 11111   " , exer);
                                Log.e("time data " , time);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //txtData.setText(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", "16612e2573mshd76cdaad9f331a4p1db15ejsn339800c25b44");
                params.put("X-RapidAPI-Host", "workout-planner1.p.rapidapi.com");
                return params;
            }
        };
        queue.add(getRequest);
    }
}