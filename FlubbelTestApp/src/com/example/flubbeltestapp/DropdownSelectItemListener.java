package com.example.flubbeltestapp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.flubbeltestapp.model.Accounting;
import com.example.flubbeltestapp.sqlite.MySQLiteHelper;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class DropdownSelectItemListener implements OnItemSelectedListener{

	private MainActivity mainView;
	
	public DropdownSelectItemListener(MainActivity view){
		this.mainView = view;
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		TextView ausgabenTextView = (TextView) this.mainView.findViewById(R.id.showAusgabenTextView);
		MySQLiteHelper db = MySQLiteHelper.getInstance(this.mainView);
    	List<Accounting> accountingListe = db.getAllAccountings();
		switch (position) {
			case 0:
			// What ever you want to happen when item 1 selected
		    	if(accountingListe == null || accountingListe.isEmpty() || accountingListe.size()==0){
		            ausgabenTextView.setText("0 Euro");
		    	} else {
		    		double gesamtAusgabe = 0;
		    		for(Accounting a : accountingListe){
		    			Calendar cal = Calendar.getInstance();
		    			cal.add(Calendar.DATE, -7);
		    			Date last7days = cal.getTime();
		    			if(a.getDate().after(last7days)){
		    				gesamtAusgabe = gesamtAusgabe + a.getValue();
		    			}
		    		}
		    		ausgabenTextView.setText(gesamtAusgabe + " Euro");
		    	}
			break;
			case 1:
			// What ever you want to happen when item 2 selected
				if(accountingListe == null || accountingListe.isEmpty() || accountingListe.size()==0){
		            ausgabenTextView.setText("0 Euro");
		    	} else {
		    		double gesamtAusgabe = 0;
		    		for(Accounting a : accountingListe){
		    			Calendar cal = Calendar.getInstance();
		    			cal.add(Calendar.DATE, -30);
		    			Date last7days = cal.getTime();
		    			if(a.getDate().after(last7days)){
		    				gesamtAusgabe = gesamtAusgabe + a.getValue();
		    			}
		    		}
		    		ausgabenTextView.setText(gesamtAusgabe + " Euro");
		    	}
			break;
			case 2:
			// What ever you want to happen when item 3 selected
				if(accountingListe == null || accountingListe.isEmpty() || accountingListe.size()==0){
		            ausgabenTextView.setText("0 Euro");
		    	} else {
		    		double gesamtAusgabe = 0;
		    		for(Accounting a : accountingListe){
		    			gesamtAusgabe = gesamtAusgabe + a.getValue();
		    		}
		    		ausgabenTextView.setText(gesamtAusgabe + " Euro");
		    	}
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
