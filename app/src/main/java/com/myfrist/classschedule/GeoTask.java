package com.myfrist.classschedule;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by SRIVASTAVA on 1/9/2016.
 */
/*The instance of this class is called by "MainActivty",to get the time taken reach the destination from Google Distance Matrix API in background.
  This class contains interface "Geo" to call the function setDouble(String) defined in "MainActivity.class" to display the result.*/

public class GeoTask extends AsyncTask<String, Void, String> {



    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    ProgressDialog pd;
    Context mContext;
    Double duration;
    //Geo geo1;
    String time_appointments,time_notice;
    String hr,mins;
    MainActivity mainActivity;
    Alarm objAlarm;
    //constructor is used to get the context.
    private ListView listAlarm;
    public static ArrayList<String> listValue;
    public GeoTask(Context mContext) {
        this.mContext = mContext;
        Log.d("mContext", String.valueOf(mContext));
        this.time_appointments=time_appointments;
        this.time_notice = time_notice;

      //  geo1= (Geo) mContext;
       // Log.d("mContext_geo1", String.valueOf(geo1));
    }
    //This function is executed before before "doInBackground(String...params)" is executed to dispaly the progress dialog
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        pd=new ProgressDialog(mContext);
//        pd.setMessage("Loading");
//        pd.setCancelable(false);
//        pd.show();
    }
    //This function is executed after the execution of "doInBackground(String...params)" to dismiss the dispalyed progress dialog and call "setDouble(Double)" defined in "MainActivity.java"
    @Override
    protected void onPostExecute(String aDouble) {
        super.onPostExecute(aDouble);
        if(aDouble!=null) {
//            geo1.setDouble(aDouble);
//            pd.dismiss();
            String res[] = aDouble.split(",");
            Double min = Double.parseDouble(res[0]) / 60;
            Log.i("min_GeoTask", String.valueOf(min));
            delegate.processFinish(min.toString());
        }
//  Log.d("Duration", Duration);
//            String Duration=  + (int) (min / 60) + ":" + (int) (min % 60) +":"+"00";
//               int x = (int) (min/60);
//            if(x < 10)
//            {
//                hr =  "0" + x;
//            }
////
//            int y = (int)(min%60);
//            if(y<10){
//                 mins = "0"+y;
//            }
//            String Duration = hr+":"+mins+":"+"00";
//
//            Log.d("Duration", Duration);
//        int dist=Integer.parseInt(res[1])/1000;
//            String A="2016-04-23 13:43:00";
//            String N="5";
//            Double T = 5.0;
//            try {
//                run(A, N, T);
//
//                Log.d("Benznest", String.valueOf(mContext));
//
////                Intent intent = new Intent(mContext.getApplicationContext(),Alarm.class);
////                mContext.startActivity(intent);
////                ((Activity)mContext).finish();
////                run(time_appointments,time_notice, min);
//
//
//
//
//
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            Log.d("oooooooooooooooop", "opppppppp End Loop");
//        }
        else
            Log.d("Error", "Error can not connect GPS");
           // Toast.makeText(mContext, "Error4!Please Try Again wiht proper values", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statuscode=con.getResponseCode();
            if(statuscode==HttpURLConnection.HTTP_OK)
            {
                BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb=new StringBuilder();
                String line=br.readLine();
                while(line!=null)
                {
                    sb.append(line);
                    line=br.readLine();
                }
                String json=sb.toString();
                Log.d("JSON",json);
                JSONObject root=new JSONObject(json);
                JSONArray array_rows=root.getJSONArray("rows");
                Log.d("JSON","array_rows:"+array_rows);
                JSONObject object_rows=array_rows.getJSONObject(0);
                Log.d("JSON","object_rows:"+object_rows);
                JSONArray array_elements=object_rows.getJSONArray("elements");
                Log.d("JSON","array_elements:"+array_elements);
                JSONObject  object_elements=array_elements.getJSONObject(0);
                Log.d("JSON","object_elements:"+object_elements);
                JSONObject object_duration=object_elements.getJSONObject("duration");
                JSONObject object_distance=object_elements.getJSONObject("distance");

                Log.d("JSON","object_duration:"+object_duration);
                return object_duration.getString("value")+","+object_distance.getString("value");

            }
        } catch (MalformedURLException e) {
            Log.d("error", "error1");
        } catch (IOException e) {
            Log.d("error", "error2");
        } catch (JSONException e) {
            Log.d("error","error3");
        }


        return null;
    }

//    public String getDateTime() {
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat(
//                    "yyyy-MM-dd", Locale.getDefault());
//            Date date = new Date();
//
////        Log.i("Day", String.valueOf(date));
//
//            return dateFormat.format(date);
//    }
//    private String getDateTime2() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        Date date = new Date();
//
//        Log.i("Day", String.valueOf(date));
//        return dateFormat.format(date);
//    }


    interface Geo{
        public void setDouble(String min);
    }

    public void run(String time_appointments,String time_notice,Double time_travel) throws ParseException {

//        //  String time_appointments2=time_appointments;
//        // int time_appointments2 = Integer.parseInt(time_appointments);
////        String currentDate = getDateTime2();
////       // Log.i("currentDateppppppppppppppppppppppp", currentDate);
////        String currentDateTimeNotice = currentDate+" "+time_notice;
////        Log.i("currentDatecurrentDateTimeNotice", currentDateTimeNotice);
////        String currentDateTimeTravel = currentDate+" "+time_travel;
//       // Log.i("currentDatecurrentDateTimeTravel", currentDateTimeTravel);
//        int time_notice1 = Integer.parseInt(time_notice);
//        int time_notice2 = time_notice1*60*1000;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date time_appointments2 = dateFormat.parse(time_appointments);
//        Log.i("WoW_time_appointments2 ", String.valueOf(time_appointments2));
//        Double time_travel2 = time_travel*60*1000;
//        Log.i("WoW_time_travel2 ", String.valueOf(time_travel2));
////        Date time_notice2 = dateFormat.parse(currentDateTimeNotice);
//        Log.i("WoW_time_notice2 ", String.valueOf(time_notice2));
////        Date time_travel2 = dateFormat.parse(currentDateTimeTravel);
//        Date time_cal = new Date((long) ((time_appointments2.getTime() - time_travel2))-time_notice2);
//
//        Log.i("WoW_time_cal", String.valueOf(time_cal));
////        int time_notice2 = Integer.parseInt(time_notice);
//        Log.i("Test_parseInt_tceime_noti2", "5555");
//        //double time_travel2 = time_travel;
//        //int notic = time_notice2*60*1000;
//       // Log.i("Test_parseInt_tceime_noti2", String.valueOf(notic));
//        //String s = Float.toString(25.0f);
//                while (true) {
//            try {
//                Thread.sleep(10000);
//
//                String currentDate = getDateTime2();
//                Date currentDate2 = dateFormat.parse(currentDate);
//                if (time_cal.getTime() <= currentDate2.getTime()) {
//
////                    Intent intent = new Intent(mContext,Alarm.class);
////                    mContext.startActivity(intent);
////                    ((Activity)mContext).finish();
//
////                    objAlarm.getAlarm();
////                    Calendar nnnn = Calendar.getInstance();
////
////                    nnnn.getTime();
////                    Alarm alarm = new Alarm();
////                    Log.i("Alarmmmmmmmmmmmmmmmmmm", String.valueOf(alarm));
////                    alarm.setAlarm(nnnn);
//                    System.out.println("countdown:Now,I'm very happy ");
////                    mainActivity.x.interrupt();;
//                    break;
//
//                }else {
//                    System.out.println("countdown:Nottttttttttttttttttttttttttttt,I'm very happy ");
//                }
////                  if (time_cal.getTime() !=0 ){
////                System.out.println("countdown:Now,I'm very happy " );
////                 } else
////                System.out.println("countdown:done");
//
//            } catch (InterruptedException e) {
//            }
//        }
//
    }


}

