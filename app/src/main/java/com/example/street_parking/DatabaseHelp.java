package com.example.street_parking;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class DatabaseHelp extends SQLiteOpenHelper {


    //tables
    private static String TABLE_LOGIN="login";
    private static String TABLE_STAFF="staff";
    private static String TABLE_ASSIGNMENT="assignment";
    private static String TABLE_STREETS="streets";
    private static String TABLE_CARS="cars";
    private static String TABLE_PARKING="parking";
    //table columns
    //staff table columns
    private static String ID="id";
    private static String STAFF_NAME="name";
    private static String STAFF_TEL="tel";
    //street columns
       private static String STREET_NAME="name";
    //cars columns
    private static String CARS_NAME="name";
    private static String CARS_STATUS="status";
    //ASSIGNMENT COLUMNS
    private static String ASS_street="street_id";
    private static String ASS_staff="staff_id";
    private static String ASS_start="start";
    private static String ASS_end="endd";
    private static String ASS_cars="cars";
    //parking columns
    private static String PARK_STAFF="staff_id";
    private static String PARK_STREET="street_id";
    private static String PARK_CAR="car_id";
    private static String PARK_TIME="time";
    private static String PARK_STATUS="status";
    //LOGIN
    private static String LOGIN_NAME="user_name";
    private static String LOGIN_PASS="password";
    private static String LOGIN_STAFF="staff_id";
    private static String PAID="Paid";
    private static String NOT_PAID="Not paid";
    //create table strings
    private static String CREATE_TABLE_LOGIN="create table "+TABLE_LOGIN+"("
            +LOGIN_NAME+" TEXT, "
            + LOGIN_PASS+" text, "
            + LOGIN_STAFF+" INTEGER)";
    private static String CREATE_TABLE_STAFF="create table "+TABLE_STAFF+"("
            +ID+" integer primary key autoincrement, "
            + STAFF_NAME+" text, "
            + STAFF_TEL+" text)";
    private static String CREATE_TABLE_CARS="create table "+TABLE_CARS+"("
            +ID+" integer primary key autoincrement, "
            + CARS_NAME+" text, "
            +CARS_STATUS+" text)";
    private static String CREATE_TABLE_STREETS="create table "+TABLE_STREETS+"("
            +ID+" integer primary key autoincrement, "
            + STREET_NAME+" text)";
    private static String CREATE_TABLE_ASSIGNMENT="create table "+TABLE_ASSIGNMENT+"("
            +ID+" integer primary key autoincrement, "
            + ASS_staff+" INTEGER, "
            + ASS_street+" INTEGER, "
            + ASS_cars+" INTEGER, "
            + ASS_start +" DATETIME, "
            + ASS_end +" DATETIME)";
    private static String CREATE_TABLE_PARKING="create table "+TABLE_PARKING+"("
            +ID+" integer primary key autoincrement, "
            + PARK_STAFF+" INTEGER, "
            + PARK_STREET+" INTEGER, "
            + PARK_CAR+ " INTEGER, "
            + PARK_STATUS+ " TEXT, "
            + PARK_TIME+" DATETIME)";
    DatabaseHelp(Context context) {
        super(context, "Street_parking", null, 1);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LOGIN);
        db.execSQL(CREATE_TABLE_STAFF);
        db.execSQL(CREATE_TABLE_STREETS);
        db.execSQL(CREATE_TABLE_CARS);
        db.execSQL(CREATE_TABLE_ASSIGNMENT);
        db.execSQL(CREATE_TABLE_PARKING);
        Log.d("Database", "Created");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_LOGIN);
        db.execSQL("drop table if exists "+TABLE_STAFF);
        db.execSQL("drop table if exists "+TABLE_CARS);
        db.execSQL("drop table if exists "+TABLE_ASSIGNMENT);
        db.execSQL("drop table if exists "+TABLE_PARKING);
        onCreate(db);
    }

    private String getDateTime(){
        SimpleDateFormat dateFormat= null;
            dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
            Date date = new Date();
        return dateFormat.format(date);
    }

    public long login(String name, String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(LOGIN_NAME,name);
        contentValues.put(LOGIN_PASS,pass);
        return db.insert(TABLE_LOGIN,null,contentValues);
    }
    public long staff(String name, String tel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        //contentValues.put(ID,1);
        contentValues.put(STAFF_NAME,name);
        contentValues.put(STAFF_TEL,tel);
        return db.insert(TABLE_STAFF,null,contentValues);
    }
    public long cars(Cars cars){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CARS_NAME,cars.getNmb_plate());
        contentValues.put(CARS_STATUS,cars.getStatus());
        return db.insert(TABLE_CARS,null,contentValues);
    }
    public long assignment(Staff_Assignment ass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ASS_staff,ass.getStaff_id());
        contentValues.put(ASS_street,ass.getStreet_id());
        contentValues.put(ASS_cars,ass.getNmb_cars());
        contentValues.put(ASS_start,getDateTime());
        contentValues.put(ASS_end,"");
        return db.insert(TABLE_ASSIGNMENT,null,contentValues);
    }
    public long parking(Parking parking){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(PARK_CAR,parking.getCar_id());
        contentValues.put(PARK_STAFF,parking.getStaff_id());
        contentValues.put(PARK_STREET,parking.getStreet_id());
        contentValues.put(PARK_STATUS,NOT_PAID);
        contentValues.put(PARK_TIME,getDateTime());
        return db.insert(TABLE_PARKING,null,contentValues);
    }
    public long street(Street street){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(STREET_NAME,street.getName());
        return db.insert(TABLE_STREETS,null,contentValues);
    }
    public int count(String table,String column, String value){
        SQLiteDatabase db=this.getReadableDatabase();
        int count=0;
        String query="SELECT * FROM "+table+" WHERE "+column+" = '"+value+"'";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor!=null){
            count =cursor.getCount();
            cursor.close();
        }
       return count;
    }

    public Boolean username(String usernam){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_LOGIN+" where "+LOGIN_NAME+"=?",new String[]{usernam});
        boolean c=cursor.getCount() <= 0;
        cursor.close();
        return c;
    }


    public String foreignk_resolver(String table, int value){
        SQLiteDatabase db=this.getReadableDatabase();
        String solved="";
        String query="SELECT * FROM "+table+" WHERE "+ID+" = "+value;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor!=null){
            solved=cursor.getString(1);
            cursor.close();
        }
        return solved;
    }

    public ArrayList<Staff> allStaff(){
        ArrayList<Staff> staffList=new ArrayList<>();
        String query="Select * from "+TABLE_STAFF;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {

                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                String tel=cursor.getString(2);
                Staff staff=new Staff(id,name,tel);
                staffList.add(staff);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return staffList;
    }
    public Boolean logins(String name,String pass){
        String query="Select * from "+TABLE_LOGIN +
                " where "+LOGIN_NAME+" = '"+name+"' and " +LOGIN_PASS+ "= '"+pass+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToNext()){
            cursor.close();
            return true;
         }
        cursor.close();
        return false;
    }
    public ArrayList<Cars> allcars(){
        ArrayList<Cars> carlist=new ArrayList<>();
        String query="Select * from "+TABLE_CARS;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                Cars cars=new Cars();
                cars.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                cars.setNmb_plate(cursor.getString(cursor.getColumnIndex(CARS_NAME)));
                cars.setStatus(checkstatus());
                carlist.add(cars);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return carlist;
    }
    private String checkstatus(){
        int x=count(TABLE_PARKING,PARK_STATUS,NOT_PAID);
        if(x>0) return NOT_PAID;
        else return PAID;
    }
    public int updateparking(Parking parking){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(PARK_STATUS,parking.getStatus());
        return db.update(TABLE_PARKING, values, PARK_CAR+"=?",new String [] {String.valueOf(parking.getCar_id())});
    }
    public void closeDB(){
        SQLiteDatabase db=this.getReadableDatabase();
        if(db!=null && db.isOpen()){
            db.close();
        }
    }
}
