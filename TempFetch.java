package com.example.roma9465.projekt1;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import android.content.pm.PackageInstaller;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import ch.ethz.ssh2.SCPClient;


public class TempFetch {

    public TempFetch() {

    }

    public String getTemp() {
        String text = run("tdtool -l").toString();
        String str = "135";
        return text.startsWith(str).split[]
    }

    public StringBuilder run (String command) {
        String hostname = "130.237.177.204";
        String username = "pi";
        String password = "raspberry";

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        StringBuilder text = new StringBuilder();
        try
        {


            Connection conn = new Connection(hostname); //init connection
            conn.connect(); //start connection to the hostname
            boolean isAuthenticated =
                    conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false)
                throw new IOException("Authentication failed.");

            Session sess = conn.openSession();
            sess.execCommand(command);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(stdout)); //reads text


            while (true)
            {
                String line = br.readLine(); // read line
                text.append(line + "\n");
                if (line == null)
                    break;
                System.out.println(line);
                //skriver ut värdena från sensorerna i run
            }
            /* Show exit status, if available (otherwise "null") */
            System.out.println("ExitCode: " + sess.getExitStatus());
            sess.close(); // Close this session
            conn.close();
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
            System.exit(2);
        }
        return text;
    }

}


