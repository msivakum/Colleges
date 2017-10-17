package com.example.a2018msivakum.readcsv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    private ArrayList<College> collegeList, updateList;
    private ArrayList<Integer> idList, inPriceList, outPriceList, totAdmitList, menAdmitList, womenAdmitList, enrollList, satMath25List, satRead25List, satWrit25List, satMath75List, satRead75List, satWrit75List,
        act25List, act75List, rankList;

    public static Integer admitrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerlayout);

        collegeList = new ArrayList<College>();
        updateList = new ArrayList<College>();
        idList = new ArrayList<Integer>();
        inPriceList = new ArrayList<Integer>();
        outPriceList = new ArrayList<Integer>();
        totAdmitList = new ArrayList<Integer>();
        menAdmitList = new ArrayList<Integer>();
        womenAdmitList = new ArrayList<Integer>();
        enrollList = new ArrayList<Integer>();
        satMath25List = new ArrayList<Integer>();
        satRead25List = new ArrayList<Integer>();
        satWrit25List = new ArrayList<Integer>();
        satMath75List = new ArrayList<Integer>();
        satRead75List = new ArrayList<Integer>();
        satWrit75List = new ArrayList<Integer>();
        act25List = new ArrayList<Integer>();
        act75List = new ArrayList<Integer>();
        rankList = new ArrayList<Integer>();

        readData();

        RecyclerView rvColleges = (RecyclerView) findViewById(R.id.collegerv);

        CollegeAdapter adapter = new CollegeAdapter(this, updateList);
        // Attach the adapter to the recyclerview to populate items
        rvColleges.setAdapter(adapter);
        // Set layout manager to position the items
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // Optionally customize the position you want to default scroll to
        layoutManager.scrollToPosition(0);
        // Attach layout manager to the RecyclerView
        rvColleges.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvColleges.addItemDecoration(itemDecoration);
    }

    public void readData() {
        InputStream is = getResources().openRawResource(R.raw.collegedata);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {

                String[] items = line.split(",");

                College colleges = new College();
                colleges.setId(items[0]); idList.add(Integer.parseInt(items[0]));
                colleges.setName(items[1]);
                colleges.setInPrice(items[2]); inPriceList.add(Integer.parseInt(items[2]));
                colleges.setOutPrice(items[3]); outPriceList.add(Integer.parseInt(items[3]));
                colleges.setAdmitTot(items[4]); totAdmitList.add(Integer.parseInt(items[4]));
                colleges.setAdmitMen(items[5]); menAdmitList.add(Integer.parseInt(items[5]));
                colleges.setAdmitWomen(items[6]); womenAdmitList.add(Integer.parseInt(items[6]));
                colleges.setEnrolled(items[7]); enrollList.add(Integer.parseInt(items[7]));
                colleges.setSatRead25(items[8]); satRead25List.add(Integer.parseInt(items[8]));
                colleges.setSatRead75(items[9]); satRead75List.add(Integer.parseInt(items[9]));
                colleges.setSatMath25(items[10]); satMath25List.add(Integer.parseInt(items[10]));
                colleges.setSatMath75(items[11]); satMath75List.add(Integer.parseInt(items[11]));
                colleges.setSatWrit25(items[12]); satWrit25List.add(Integer.parseInt(items[12]));
                colleges.setSatWrit75(items[13]); satWrit75List.add(Integer.parseInt(items[13]));
                colleges.setAct25(items[14]); act25List.add(Integer.parseInt(items[14]));
                colleges.setAct75(items[15]); act75List.add(Integer.parseInt(items[15]));
                colleges.setRank(items[16]); rankList.add(Integer.parseInt(items[16]));
                collegeList.add(colleges);

                Log.i("MainActivity", "Just Created " + colleges.getId());
            }
        } catch (IOException err) {
            Log.e("MainActivity", "Error" + line, err);
            err.printStackTrace();
        }

        for(int k = 0; k < totAdmitList.size(); k++) {
            if(totAdmitList.get(k) < 20)
                updateList.add(collegeList.get(k));
        }

        Log.i("MainActivity", "Number of Colleges " + collegeList.size());
    }
}
