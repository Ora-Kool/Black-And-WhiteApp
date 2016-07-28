package xyz.bhent.production.Connectivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import xyz.bhent.production.Activities.Availables;
import xyz.bhent.production.Model.ItemModel;

/**
 * Created by root on 7/26/16.
 */
public class InfosRetrieving {
    private static Context context;
    private static String str_request = "string_req";
    private static ProgressDialog progressDialog;
    private static String RESERVATION_URL = "http://villagecare.netne.net/reservation.php";//posting reservation data
    private static String CONFIRMATION_RESPONSE = "";

    private static String DATARETRIVAL_URL = "";// link to retrieve information from the database

    public InfosRetrieving(Context mContext){
        this.context = mContext;
    }
    public static void POST_DATA(final ItemModel itemModel){
        progressDialog = new ProgressDialog(context, android.R.style.Theme_Translucent);
        progressDialog.setMessage("Making Reservations... Please wait!");
        progressDialog.setCancelable(false);
        progressDialog.show();
                StringRequest stringRequest = new StringRequest(Method.POST, RESERVATION_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("SUCCESS")){
                            Toast.makeText(context, "RESERVATION CONFIRMED!!!", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(context, "ERROR!! PLEASE TRY AGAIN", Toast.LENGTH_LONG).show();
                        }
                        Log.d("response", response.toString());
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "message "+error.toString(), Toast.LENGTH_LONG).show();
                        VolleyLog.d("InfoRet", "Error: " + error.getMessage());
                        progressDialog.dismiss();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("customer", itemModel.getTitle());
                params.put("phoneNo", itemModel.getPrice());
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        //Toast.makeText(context, "This POST_DATA("+itemModel.getTitle()+" / "+itemModel.getPrice()+")", Toast.LENGTH_SHORT).show();
    }
}
