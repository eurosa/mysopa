package net.kerishapps.sopadeletrasdelabiblia.sopadeletrasbiblico.data;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import net.kerishapps.sopadeletrasdelabiblia.sopadeletrasbiblico.R;




public class WSDatabase extends SQLiteAssetHelper {


    private static final String DATABASE_NAME = "wordsearch.db";
    private static final int DATABASE_VERSION = 4;

    private Context context;
    private String selectedLangTable;
    private String temp_rows;
    private String word;
    private SQLiteDatabase database;


    public WSDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
        this.context = context;
    }


    public void open(){
        database = getReadableDatabase();
        selectedLangTable = Settings.getStringValue(context, context.getResources().getString(R.string.pref_key_language), null);
    }



    public String[] getRandomWords(){
        String[] words = new String[0];
        try {
        //    database.execSQL("CREATE TEMP TABLE "+temp_rows+"  AS SELECT "+word+"  FROM "+selectedLangTable+" WHERE random() % 4 = 0 LIMIT 2000");
    database.execSQL("CREATE TEMP TABLE temp_rows  AS SELECT word  FROM "+selectedLangTable+" WHERE random() % 4 = 0 LIMIT 2000");
    Cursor cursor = database.rawQuery("SELECT word FROM temp_rows ORDER BY random()", null);
          //  Cursor cursor = database.rawQuery("SELECT "+word+" FROM "+temp_rows+" ORDER BY random()", null);

    cursor.moveToFirst();

     words = new String[cursor.getCount()];
    int i = 0;
    while (!cursor.isAfterLast()) {
        words[i++] = cursor.getString(0);
        cursor.moveToNext();
    }

    database.execSQL("drop table "+temp_rows+"");
    cursor.close();

   //return words;
}catch (Exception e){}
return  words;
    }


}
