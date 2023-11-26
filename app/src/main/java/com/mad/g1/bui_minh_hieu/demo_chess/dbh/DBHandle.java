package com.mad.g1.bui_minh_hieu.demo_chess.dbh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mad.g1.bui_minh_hieu.demo_chess.model.Match;
import com.mad.g1.bui_minh_hieu.demo_chess.model.Member;

import java.util.ArrayList;
import java.util.List;

public class DBHandle extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "chess.db";
    private static int DATABASE_VERSION = 1;
    private static final String TABLE_MEMBER = "member";
    private static final String TABLE_MATCH = "matches";
    private static final String COLUMN_ID = "_id";

    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    public DBHandle(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_MEMBER +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL " +
                ")";
        db.execSQL(createTable);
        String createMatchTable = "CREATE TABLE matches (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "date TEXT, " +
                "description TEXT, " +
                "level TEXT, " +
                "status BOOLEAN" +
                ")";
        db.execSQL(createMatchTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public void addMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, member.getUsername());
        values.put(COLUMN_PASSWORD, member.getPassword());
        db.insert(TABLE_MEMBER, null, values);
        db.close();
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_MEMBER, null, selection, selectionArgs, null, null, null);

        boolean loginSuccessful = cursor.moveToFirst();
        cursor.close();
        db.close();
        return loginSuccessful;
    }

    public List<Match> getAll() {
        List<Match> matchList = new ArrayList<>();
        String orderClause = "date DESC";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_MATCH, null, null, null, null, null, orderClause);
        while (cursor.moveToNext()) {
            Match match = new Match();
            match.setId(cursor.getInt(0));
            match.setName(cursor.getString(1));
            match.setDate(cursor.getString(2));
            match.setDescription(cursor.getString(3));
            match.setLevel(cursor.getString(4));
            match.setStatus(cursor.getInt(5) != 0);
            matchList.add(match);
        }
        return matchList;
    }

    public long add(Match match) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", match.getName());
        contentValues.put("date", match.getDate());
        contentValues.put("description", match.getDescription());
        contentValues.put("level", match.getLevel());
        contentValues.put("status", match.getStatus());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(TABLE_MATCH, null, contentValues);
    }

    public long update(Match match) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", match.getName());
        contentValues.put("date", match.getDate());
        contentValues.put("description", match.getDescription());
        contentValues.put("level", match.getLevel());
        contentValues.put("status", match.getStatus());
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(match.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update(TABLE_MATCH, contentValues, whereClause, whereArgs);
    }

    public long delete(Integer id) {
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_MATCH, whereClause, whereArgs);
    }

    public List<Match> searchByName(String key) {
        List<Match> matchList = new ArrayList<>();
        String orderClause = "date ASC";
        String whereClause = "name like ?";
        String[] whereArgs = {"%" + key + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_MATCH, null, whereClause, whereArgs, null, null, orderClause);
        while (cursor.moveToNext()) {
            Match match = new Match();
            match.setId(cursor.getInt(0));
            match.setName(cursor.getString(1));
            match.setDate(cursor.getString(2));
            match.setDescription(cursor.getString(3));
            match.setLevel(cursor.getString(4));
            match.setStatus(cursor.getInt(5) != 0);
            matchList.add(match);
        }
        return matchList;
    }

}
