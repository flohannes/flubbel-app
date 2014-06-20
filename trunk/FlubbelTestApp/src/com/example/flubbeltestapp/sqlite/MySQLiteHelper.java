package com.example.flubbeltestapp.sqlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.example.flubbeltestapp.model.Accounting;
import com.example.flubbeltestapp.model.Category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "AccountingDB";
	
	private static final String TABLE_ACCOUNTING = "accounting";
	private static final String TABLE_CATEGORY = "category";
	
	private static final String ACCOUNTING_ID = "id";
	private static final String ACCOUNTING_VALUE = "value";
	private static final String ACCOUNTING_CATEGORY_ID = "category_id";
	private static final String ACCOUNTING_DATE_CREATED = "date_created";
	private static final String[] ACCOUNTING_COLUMNS = {ACCOUNTING_ID, ACCOUNTING_VALUE, ACCOUNTING_CATEGORY_ID, ACCOUNTING_DATE_CREATED};
	
	private static final String CATEGORY_ID = "id";
	private static final String CATEGORY_CATEGORYNAME = "categoryname";
	private static final String[] CATEGORY_COLUMNS = {CATEGORY_ID, CATEGORY_CATEGORYNAME};

	private static MySQLiteHelper instance = null;
	protected MySQLiteHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
   public static MySQLiteHelper getInstance(Context context) {
      if(instance == null) {
         instance = new MySQLiteHelper(context);
      }
      return instance;
   }
	   
	   
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_ACCOUNTING_TABLE = "CREATE TABLE accounting ( id INTEGER PRIMARY KEY AUTOINCREMENT, value REAL, category_id INTEGER, date_created TEXT )";
		String CREATE_CATEGORY_TABLE = "CREATE TABLE category ( id INTEGER PRIMARY KEY AUTOINCREMENT, categoryname TEXT )";
		
		db.execSQL(CREATE_ACCOUNTING_TABLE);
		db.execSQL(CREATE_CATEGORY_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS category");
		db.execSQL("DROP TABLE IF EXISTS accounting");
		
		this.onCreate(db);
	}

	public void addAccounting(Accounting a){
		Log.d("addAccounting", a.toString());
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(this.ACCOUNTING_VALUE, a.getValue());
		values.put(this.ACCOUNTING_CATEGORY_ID, a.getCategory_id());
		values.put(this.ACCOUNTING_DATE_CREATED, a.getDate().getTime());
		
		db.insert(this.TABLE_ACCOUNTING, null, values);
		db.close();
	}
	
	public void addCategory(Category c){
		Log.d("addCategory", c.toString());
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(this.CATEGORY_CATEGORYNAME, c.getCategoryname());
		
		db.insert(this.TABLE_CATEGORY, null, values);
		db.close();
	}
	
	public List<Accounting> getAllAccountings(){
		List<Accounting> accountingListe = new LinkedList<Accounting>();
		
		String query = "SELECT * FROM " + this.TABLE_ACCOUNTING;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		Accounting a = null;
		if(cursor.moveToFirst()){
			do {
				a = new Accounting();
				a.setId(Integer.parseInt(cursor.getString(0)));
				a.setValue(Double.parseDouble(cursor.getString(1)));
				a.setCategory_id(Integer.parseInt(cursor.getString(2)));
				a.setDate(new Date(Long.parseLong(cursor.getString(3))));
//				try {
//					a.setDate(new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(cursor.getString(3)));
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				accountingListe.add(a);
			} while (cursor.moveToNext());
		}
		
		Log.d("getAllAccounting()", accountingListe.toString());
		return accountingListe;
	}
	
	public List<Category> getAllCategories(){
		List<Category> categoryListe = new LinkedList<Category>();
		
		String query = "SELECT * FROM " + this.TABLE_CATEGORY;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		Category c = null;
		if(cursor.moveToFirst()){
			do {
				c = new Category();
				c.setId(Integer.parseInt(cursor.getString(0)));
				c.setCategoryname(cursor.getString(1));
				
				categoryListe.add(c);
			} while (cursor.moveToNext());
		}
		
		Log.d("getAllCategories()", categoryListe.toString());
		return categoryListe;
	}
	
	public List<Category> getCategoryIdByName(String name){
		List<Category> categoryListe = new LinkedList<Category>();
		
		String query = "SELECT * FROM " + this.TABLE_CATEGORY + " WHERE categoryname='" + name+"'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		Category c = null;
		if(cursor.moveToFirst()){
			do {
				c = new Category();
				c.setId(Integer.parseInt(cursor.getString(0)));
				c.setCategoryname(cursor.getString(1));
				
				categoryListe.add(c);
			} while (cursor.moveToNext());
		}
		
		Log.d("getAllCategories()", categoryListe.toString());
		return categoryListe;
	}
	
	public void deleteAccounting(Accounting a){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(this.TABLE_ACCOUNTING, this.ACCOUNTING_ID+" = ?", new String[]{String.valueOf(a.getId())});
		db.close();
		Log.d("deleteAccounting()", a.toString());
	}
	
}
