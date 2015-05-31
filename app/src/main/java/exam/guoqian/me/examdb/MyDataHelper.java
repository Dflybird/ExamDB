package exam.guoqian.me.examdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gq on 2015/5/31.
 */
public class MyDataHelper extends SQLiteOpenHelper {

    public static final String CREATE_STUDENT = "create table Student ("
            + "SNO varchar(20) primary key,"
            + "Name varchar(20)"
            + "Age integer"
            + "College varchar(30))";
    public static final String CREATE_COURSE = "create table Course ("
            + "CourseID varchar(15) primary key,"
            + "CourseName varchar(20)"
            + "CourseBeforeID varchar(15))";
    public static final String CREATE_CHOOSE = "create table Choose ("
            + "SNO varchar(20) primary key,"
            + "CourseID varchar(30)"
            + "Score decimal(5,2))";

    private Context mContext;

    public MyDataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_STUDENT);
        db.execSQL(CREATE_COURSE);
        db.execSQL(CREATE_CHOOSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
