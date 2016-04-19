package com.example.admin.goparty.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.goparty.models.Party;
import com.example.admin.goparty.models.UserSqliteModel;

import java.util.ArrayList;
import java.util.HashMap;

public class SqLiteDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserDatabase.db";

    public static final String USERINFO_TABLE_NAME = "users";
    public static final String USERS_COLUMN_TOKEN = "token";
    public static final String USERS_COLUMN_USERNAME = "username";

    public static final String USER_FOR_PARTY_TABLE_NAME = "userForParty";
    public static final String USER_FOR_PARTY_COLUMN_USERNAME = "username";

    public static final String PARTY_TABLE_NAME = "party";
    public static final String PARTY_COLUMN_TITLE = "partyTitle";
    public static final String PARTY_COLUMN_DURATION = "partyDuration";
    public static final String PARTY_COLUMN_LATITUDE = "partyLatitude";
    public static final String PARTY_COLUMN_LONGITUDE = "partyLongitude";

    public static final String USER_PARTY_TABLE_NAME = "userParty";
    public static final String USER_PARTY_COLUMN_USERID = "userId";
    public static final String USER_PARTY_COLUMN_PARTYID = "partyId";

    private static long userForPartyId;

    private static final String KEY_ID = "id";
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + USERINFO_TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + USERS_COLUMN_TOKEN
            + " TEXT," + USERS_COLUMN_USERNAME + " TEXT" + ")";
    private static final String CREATE_TABLE_USER_FOR_PARTY = "CREATE TABLE "
            + USER_FOR_PARTY_TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + USER_FOR_PARTY_COLUMN_USERNAME + " TEXT" + ")";
    private static final String CREATE_TABLE_PARTY = "CREATE TABLE "
            + PARTY_TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + PARTY_COLUMN_TITLE
            + " TEXT," + PARTY_COLUMN_DURATION + " INTEGER," + PARTY_COLUMN_LATITUDE + " DOUBLE,"
            + PARTY_COLUMN_LONGITUDE + " DOUBLE" + ")";
    private static final String CREATE_TABLE_PARTY_USER = "CREATE TABLE "
            + USER_PARTY_TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + USER_PARTY_COLUMN_USERID
            + " INTEGER," + USER_PARTY_COLUMN_PARTYID + " INTEGER" + ")";
    private HashMap hp;

    public SqLiteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_USER_FOR_PARTY);
        db.execSQL(CREATE_TABLE_PARTY);
        db.execSQL(CREATE_TABLE_PARTY_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + USERINFO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PARTY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_PARTY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_FOR_PARTY_TABLE_NAME);

        // create new tables
        onCreate(db);
    }

    public long insertUser(String token, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("token", token);
        contentValues.put("username", email);
        long userId = db.insert("users", null, contentValues);

        insertUserForParty(email);

        return userId;
    }

    public long insertParty(String title, Integer duration, Double latitude, Double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("partyTitle", title);
        contentValues.put("partyDuration", duration);
        contentValues.put("partyLatitude", latitude);
        contentValues.put("partyLongitude", longitude);

        long partyId = db.insert("party", null, contentValues);

        insertUserParty(userForPartyId, partyId);

        return partyId;
    }

    public boolean insertUserParty(long userId, long partyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", userId);
        contentValues.put("partyId", partyId);
        db.insert("userParty", null, contentValues);
        return true;
    }

    public void deleteContact() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USERINFO_TABLE_NAME, null, null);
    }

    public ArrayList<UserSqliteModel> getAllUsers() {
        ArrayList<UserSqliteModel> array_list = new ArrayList<UserSqliteModel>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from users", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            UserSqliteModel user = new UserSqliteModel();
            user.setId(res.getInt(res.getColumnIndex(KEY_ID)));
            user.setUsername(res.getString(res.getColumnIndex(USERS_COLUMN_USERNAME)));
            user.setToken(res.getString(res.getColumnIndex(USERS_COLUMN_TOKEN)));

            array_list.add(user);
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Party> getAllPartiesForUser() {
        ArrayList<Party> array_list = new ArrayList<Party>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from userParty up" +
                " INNER JOIN userForParty u on up.userId=u.id " +
                " INNER JOIN party p on up.partyId=p.id" +
                " WHERE up.userId=" + userForPartyId, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            Party party = new Party();
            party.setTitle(res.getString(res.getColumnIndex(PARTY_COLUMN_TITLE)));
            party.setDuration(res.getInt(res.getColumnIndex(PARTY_COLUMN_DURATION)));
            party.setLatitude(res.getDouble(res.getColumnIndex(PARTY_COLUMN_LATITUDE)));
            party.setLongitude(res.getDouble(res.getColumnIndex(PARTY_COLUMN_LONGITUDE)));
            array_list.add(party);
            res.moveToNext();
        }
        return array_list;
    }

    public long insertUserForParty(String email) {
        UserSqliteModel user = getUserByUsername(email);
        if(user.getUsername() == null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", email);
            userForPartyId = db.insert("userForParty", null, contentValues);
        }
        return userForPartyId;
    }

    public UserSqliteModel getUserByUsername(String username) {
        UserSqliteModel user = new UserSqliteModel();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "select * from userForParty where username=" + "'" + username + "'";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();

            while (cursor.isAfterLast() == false) {
                user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                user.setUsername((cursor.getString(cursor.getColumnIndex(USERS_COLUMN_USERNAME))));
                userForPartyId = cursor.getInt(cursor.getColumnIndex(KEY_ID));

                cursor.moveToNext();
            }
            cursor.close();
            //userForPartyId=user.getId();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
