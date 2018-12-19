package com.example.roma9465.projekt1;

import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.os.AsyncTask;
import android.os.StrictMode;

import java.util.ArrayList;


public class Tab2Graf25 extends Fragment {
    ArrayList inTemps = new ArrayList<>();
    String fetched;

    private static final String TAG = "Tab2Graf25";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


                View view = inflater.inflate(R.layout.tab2, container, false);




        String[] hours = {"00.00", "01.00", "02.00", "03.00", "04.00", "05.00", "06.00", "07.00",
                "08.00", "09.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00",
                "17.00", "18.00", "19.00", "20.00", "21.00", "22.00", "23.00", "24.00"};

        // double [] outtemps = {};

        double x;
        double y;

        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();

        graph.addSeries(series);
        graph.getViewport().setMinX(0.0);
        graph.getViewport().setMaxX(24.0);
        graph.getViewport().setMinY(0.0);
        graph.getViewport().setMaxY(30.0);


        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);

        for (int i = 0; i < 23; i++) {
            new AsyncTask<Integer, Void, String>() {
                @Override
                protected String doInBackground(Integer... params) {
                    //här hämtas koden via tempFetch
                    fetched =new TempFetch().getTemp();
                    return fetched;
                }

                protected void onPostExecute(String result) {
                    //skriv här vad som ska göras i bakgunden
                    inTemps.add(Double.parseDouble(fetched));
                }
            }.execute(1);
        }


        for (int i = 0; i < inTemps.size(); i++) {
            x = Double.parseDouble(hours[i]);
            //y = intemps25[i];
            if ( inTemps.get(i) instanceof Double ) {
                y = (Double) inTemps.get(i);
            } else {
                y = 0.0;
            }
            series.appendData(new DataPoint(x, y), true, 23);

        }
        return view;

    }


}



