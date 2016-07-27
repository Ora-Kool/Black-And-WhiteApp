package xyz.bhent.production.Connectivity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by etinge mabian edward on 7/24/16.
 */
public class PostReservation extends AsyncTask<String, Void, String> {

    private Context context;

    public PostReservation(Context mContext){
        this.context = mContext;
    }

    @Override
    protected void onPreExecute() {

        //here Progressbar can be initialized to tell the user what is going on
    }

    @Override
    protected String doInBackground(String... params) {

        //In here i will do all my connections to server, passing or retrieving
        // the data from the database

        String customer_name = params[0];
        String phone_contact = params[1];



        String link; //this should be the url of there the server is located
        String data;
        BufferedReader bufferedReader = null;
        String result = ""; //when it is success or failure
        StringBuilder builder = new StringBuilder();
        String line;


        //Building parameters
        try {

            data = "?customerName=" + URLEncoder.encode(customer_name, "UTF-8");
            data += "&phoneNumber=" + URLEncoder.encode(phone_contact, "UTF-8");


            link = "http://villagecare.netne.net/reservation.php"+data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();


            bufferedReader= new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
            //sending a post request
//            URLConnection urlConnection = url.openConnection();
//            urlConnection.setDoInput(true);
//            OutputStreamWriter streamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
//            streamWriter.write(data);
//            streamWriter.flush();
//
//            //Get the server response
//            bufferedReader= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            //Read server response
//            while((line = bufferedReader.readLine()) != null){
//                //append server response in string
//                builder.append(line +"\n");
//            }
//            result = builder.toString();
//
//        }catch(Exception e){
//            Log.d("PostError", e.getMessage());
//        }finally {
//            try{
//                bufferedReader.close();
//            }catch (Exception e){
//                Log.d("Error", e.getMessage());
//            }
//        }
        }catch (Exception e){
            return new String("Exeception: "+ e.getMessage());
        }


    }

    @Override
    protected void onPostExecute(String result) {
        //here i will get the response made by the server
        if(result != null){
            String st = result.replace("'\'", "");
            Toast.makeText(context, "result is not empty "+ result, Toast.LENGTH_SHORT).show();
            Log.d("data---->", st);
        }else{
            Toast.makeText(context, "result is empty", Toast.LENGTH_SHORT).show();
        }
    }
}
