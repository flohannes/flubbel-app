package com.example.flubbeltestapp;

import java.util.Date;
import java.util.List;

import com.example.flubbeltestapp.model.Accounting;
import com.example.flubbeltestapp.model.Category;
import com.example.flubbeltestapp.sqlite.MySQLiteHelper;

import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class SaveOnClickListener implements View.OnClickListener {

	AusgabeHinzufuegenActivity view;
	private String name;
	
	public SaveOnClickListener(AusgabeHinzufuegenActivity view){
		this.view = view;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		MySQLiteHelper db = MySQLiteHelper.getInstance(this.view);
		EditText textField = (EditText) this.view.findViewById(R.id.editText1);
		double value = 0;
		if(textField.getText().toString()!=""){
			value = Double.parseDouble(textField.getText().toString());
			List<Category> currentCategoryList = db.getCategoryIdByName(name);
			Category currentCategory = currentCategoryList.get(0);
			Accounting a = new Accounting(value, currentCategory.getId(), new Date());
			db.addAccounting(a);
			Toast.makeText(v.getContext(),
					"saved",
					Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public void setCategoryName(String name){
		this.name = name;
	}

}
