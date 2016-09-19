package com.pfariasmunoz.myemacscard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ExpandableAdapter listAdapter;
    AnimatedExpandableListView expListView;
    ArrayList<String> headerList;
    HashMap<String, ArrayList<String>> childList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expListView = (AnimatedExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableAdapter(this, headerList, childList);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (expListView.isGroupExpanded(groupPosition)) {
                    expListView.collapseGroupWithAnimation(groupPosition);
                    Log.d("MainActivity", "Collapsing");
                } else {
                    expListView.expandGroupWithAnimation(groupPosition);
                    Log.d("MainActivity", "Expanding");
                }
                return true;
            }

        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {


            @Override
            public void onGroupExpand(int groupPosition) {
                    /*Toast.makeText(getApplicationContext(),
                            headerList.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();*/
            }

        });

        //Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                    /*Toast.makeText(getApplicationContext(),
                            headerList.get(groupPosition) + " Collapsed",
                            Toast.LENGTH_SHORT).show();*/

            }
        });

        //Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        headerList.get(groupPosition)
                                + " : "
                                + childList.get(
                                headerList.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    /*
     * Preparing our beloved data
     */
    private void prepareListData() {
        headerList = new ArrayList<String>();
        childList = new HashMap<String, ArrayList<String>>();
        ArrayList<String> obj = new ArrayList<String>();

        //Header For loop

        for (int i = 0; i < 50; i++) {
            headerList.add("Weather " + i);
            childList.put(headerList.get(i), obj);
        }


        //child within header for loop
        for (int j = 0; j < 5; j++) {

            if ((j & 1) == 0) {
                obj.add("Sunny " + j);
            } else {
                obj.add("Cloudy " + j);
            }
        }

    }
}