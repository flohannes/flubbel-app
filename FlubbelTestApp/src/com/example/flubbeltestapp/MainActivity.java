package com.example.flubbeltestapp;

import java.util.List;

import com.example.flubbeltestapp.model.Accounting;
import com.example.flubbeltestapp.model.Category;
import com.example.flubbeltestapp.sqlite.MySQLiteHelper;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	boolean mboolean = false;

    	SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
    	mboolean = settings.getBoolean("FIRST_RUN", false);
    	if (!mboolean) {
    	 // do the thing for the first time 
    	  settings = getSharedPreferences("PREFS_NAME", 0);
    	                    SharedPreferences.Editor editor = settings.edit();
    	                    editor.putBoolean("FIRST_RUN", true);
    	                    editor.commit(); 
    	                    
    	  //Erstelle DatenBank
	        MySQLiteHelper db = MySQLiteHelper.getInstance(this);
	        String[] categoryList = {"Lebensmittel","Haushalt","Kosmetik","Geschenke","Kleidung","Schuhe","Technik","Bücher","Büromaterial","Freizeit","Imbiss","Accessoires", "Apotheke","Baumarkt/IKEA","Drogerie","Bahn","Auto"};
	        for(String c : categoryList){
	        	Category cNew = new Category(c);
	        	db.addCategory(cNew);
	        }
	        
//	        List<Category> list = db.getAllCategories();
//	        for(Category c : list){
//	        	
//	        	System.out.println("CName: "+c.getCategoryname());
//	        }
    	}    

		TextView ausgabenTextView = (TextView) this.findViewById(R.id.showAusgabenTextView);

		
    	MySQLiteHelper db = MySQLiteHelper.getInstance(this);
    	List<Accounting> accountingListe = db.getAllAccountings();
    	if(accountingListe == null || accountingListe.isEmpty() || accountingListe.size()==0){
            ausgabenTextView.setText("0 Euro");
    	} else {
    		double gesamtAusgabe = 0;
    		for(Accounting a : accountingListe){
    			gesamtAusgabe = gesamtAusgabe + a.getValue();
    		}
    		ausgabenTextView.setText(gesamtAusgabe + " Euro");
    	}
    	
    	
        Button addAusgabeButton = (Button) this.findViewById(R.id.AddAusgabeButton);
        addAusgabeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(v.getContext(), AusgabeHinzufuegenActivity.class);
				startActivity(intent);
			}
		});

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
