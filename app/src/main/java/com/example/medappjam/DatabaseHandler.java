package com.example.medappjam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "patientProviderInfo";

    //Patient table: id, username, & password
    private static final String TABLE_PATIENT = "patient";
    private static final String PATIENT_ID = "id";
    private static final String PATIENT_USERNAME = "username";
    private static final String PATIENT_PASSWORD = "password";

    //Provider table: id, name, & phoneNumber
    private static final String TABLE_PROVIDER = "provider";
    private static final String PROVIDER_ID = "id";
    private static final String PROVIDER_NAME = "name";
    private static final String PROVIDER_PHONE = "phoneNumber";

    //Patient to provider relationship table: patientId & providerId
    private static final String TABLE_PATIENT_PROVIDER = "patientToProvider";
    private static final String PAT_ID = "patientId";
    private static final String PROV_ID = "providerId";

    //Profiles table: id & profile
    private static final String TABLE_PROFILE = "profile";
    private static final String PROFILE_ID = "id";
    private static final String PROFILE = "profile";

    //Patient to profiles relationship table: patientId(PAT_ID) & profileId
    private static final String TABLE_PATIENT_PROFILE = "patientToProfile";
    private static final String PROF_ID = "profileId";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("tag", "onCreate database");

        String CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_PATIENT + "("
                + PATIENT_ID + " INTEGER,"
                + PATIENT_USERNAME + " TEXT,"
                + PATIENT_PASSWORD + " TEXT,"
                + "PRIMARY KEY(" + PATIENT_ID + ", " + PATIENT_USERNAME +")"
                + ")";

        String CREATE_PROVIDER_TABLE = "CREATE TABLE " + TABLE_PROVIDER + "("
                + PROVIDER_ID + " INTEGER,"
                + PROVIDER_NAME + " TEXT,"
                + PROVIDER_PHONE + " TEXT"
                + "PRIMARY KEY(" + PROVIDER_ID + ")"
                + ")";

        String CREATE_PATIENT_PROVIDER_TABLE = "CREATE TABLE " + TABLE_PATIENT_PROVIDER + "("
                + PAT_ID + " INTEGER,"
                + PROV_ID + " INTEGER,"
                + "PRIMARY KEY(" + PAT_ID + ", " + PROV_ID + "),"
                + "FOREIGN KEY(" + PAT_ID + ") REFERENCES " + TABLE_PATIENT + "(" + PATIENT_ID + "),"
                + "FOREIGN KEY(" + PROV_ID + ") REFERENCES " + TABLE_PROVIDER + "(" + PROVIDER_ID + ")"
                + ")";

        String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE + "("
                + PROFILE_ID + "INTEGER,"
                + PROFILE + " TEXT,"
                + "PRIMARY KEY(" + PROFILE_ID + ") REFERENCES " + TABLE_PATIENT_PROFILE + "("
                + ")";

        String CREATE_PATIENT_PROFILE_TABLE = "CREATE TABLE " + TABLE_PATIENT_PROFILE + "("
                + PAT_ID + "INTEGER,"
                + PROF_ID + "INTEGER,"
                + "PRIMARY KEY(" + PAT_ID + ", " + PROF_ID + "),"
                + "FOREIGN KEY(" + PAT_ID + ") REFERENCES " + TABLE_PATIENT + "(" + PATIENT_ID + "),"
                + "FOREIGN KEY(" + PROF_ID + ") REFERENCES " + TABLE_PROFILE + "(" + PROFILE_ID + ")"
                + ")";

        db.execSQL(CREATE_PATIENT_TABLE);
        db.execSQL(CREATE_PROVIDER_TABLE);
        db.execSQL(CREATE_PATIENT_PROVIDER_TABLE);
        db.execSQL(CREATE_PROFILE_TABLE);
        db.execSQL(CREATE_PATIENT_PROFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROVIDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT_PROVIDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    public void addPatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PATIENT_USERNAME, patient.getUsername());
        values.put(PATIENT_PASSWORD, patient.getPassword());

        db.insert(TABLE_PATIENT, null, values);
        db.close();
    }


    public Patient getPatient(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                PATIENT_ID,
                PATIENT_USERNAME,
                PATIENT_PASSWORD
        };

        //where username = username
        String selection = PATIENT_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_PATIENT, projection, selection, selectionArgs, null, null, null);

        //move cursor to first row if found
        if(cursor != null) {
            cursor.moveToFirst();
        }

        Patient patient = new Patient(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return patient;
    }

    /**Don't need to get all patients yet
    public ArrayList<Patient> getAllPatients() {
        //get all patients
    }
     */

    public void updatePatient(int patientId) {
        //update patient info
    }

    public void deletePatient(String username) {
        //drop database
    }

    public void addProvider(Provider provider) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PROVIDER_NAME, provider.getName());
        values.put(PROVIDER_PHONE, provider.getPhoneNumber());

        db.insert(TABLE_PROVIDER, null, values);
        db.close();
    }

    //Get all providers of the patient
    public ArrayList<Provider> getAllProviders(int patientId) {
        ArrayList<Provider> providers = new ArrayList<>();

        String selectQuery = "SELECT " + PROV_ID + ", " + PROVIDER_NAME + ", " + PROVIDER_PHONE
                + " FROM " + TABLE_PATIENT_PROVIDER + ", " + TABLE_PROVIDER
                + " WHERE " + PAT_ID + " = " + patientId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do{
                Provider provider = new Provider(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                providers.add(provider);
            } while(cursor.moveToNext());
        }

        return providers;
    }

    //Delete the provider related to the patient
    public void deleteProvider(Provider provider, String patientUsername) {
        //delete provider
    }
}
