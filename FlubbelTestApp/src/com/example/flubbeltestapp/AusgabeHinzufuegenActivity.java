package com.example.flubbeltestapp;

import java.util.List;

import com.example.flubbeltestapp.model.Category;
import com.example.flubbeltestapp.sqlite.MySQLiteHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AusgabeHinzufuegenActivity extends Activity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ausgabe_activity);

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
	}
}
