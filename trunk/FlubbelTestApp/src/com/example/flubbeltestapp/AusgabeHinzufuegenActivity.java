package com.example.flubbeltestapp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.flubbeltestapp.model.Category;
import com.example.flubbeltestapp.sqlite.MySQLiteHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class AusgabeHinzufuegenActivity extends Activity{
	List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;
    
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ausgabe_activity);

        final SaveOnClickListener saveListener = new SaveOnClickListener(this);
        
        Button backToMainActivity = (Button) this.findViewById(R.id.backToMainButton);
        backToMainActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(v.getContext(), MainActivity.class);
				startActivity(intent);
			}
		});
        
        //Kategorienauflistung hinzufügen
//        ExpandableListView exp = (ExpandableListView) this.findViewById(R.id.expandableListView1);

        createGroupList();
        
        createCollection();
 
        expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, laptopCollection);
        expListView.setAdapter(expListAdapter);
 
        //setGroupIndicatorToRight();
//        int groupPositionIndex;
//        int childPositionIndex;
//        expListAdapter.getChild(groupPositionIndex, childPositionIndex);
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();
                saveListener.setCategoryName(selected);
                return true;
            }
        });
        
        
        //Eingaben Abspeichern
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(saveListener);
	}
	
	private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Kategorien");

    }
 
    private void createCollection() {
        // preparing laptops collection(child)
        
        MySQLiteHelper db = MySQLiteHelper.getInstance(this);
        List<Category> allCategories = db.getAllCategories();
        
        laptopCollection = new LinkedHashMap<String, List<String>>();
 
        for (String laptop : groupList) {
            if (laptop.equals("Kategorien")) {
                this.loadChild(allCategories);
            }
 
            laptopCollection.put(laptop, childList);
        }
    }
 
    private void loadChild(List<Category> allCategories) {
        childList = new ArrayList<String>();
        for (Category model : allCategories)
            childList.add(model.getCategoryname());
    }
 
//    private void setGroupIndicatorToRight() {
//        /* Get the screen width */
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
// 
//        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
//                - getDipsFromPixel(5));
//    }
 
    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
 

	
}

