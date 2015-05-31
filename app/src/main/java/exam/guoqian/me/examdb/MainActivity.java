package exam.guoqian.me.examdb;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{

    private MyDataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDataHelper(this, "Exam.db", null, 1);
        Button buttonCreat = (Button) findViewById(R.id.create);
        Button buttonAdd = (Button) findViewById(R.id.add);
        Button buttonquery = (Button) findViewById(R.id.query);
        Button buttonIncreaseAge = (Button) findViewById(R.id.increase_age);
        Button buttonDelete = (Button) findViewById(R.id.delete);

        buttonCreat.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonquery.setOnClickListener(this);
        buttonIncreaseAge.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        switch (v.getId()){
            case R.id.create:
                dbHelper.getWritableDatabase();
                break;
            case R.id.add:
                //插入学生数据
                addStudent(db, values);

                //插入课程数据
                addCourse(db, values);

                //插入选项数据
                addChoose(db, values);
                break;
            case R.id.query:
                Cursor cursor = null;
                cursor = db.query("Student", null, "Name = ?", new String[]{"王五"}, null, null , null);
                String Sno = cursor.getString(cursor.getColumnIndex("SNO"));
                cursor = db.query("Choose", null, "SNO = ?", new String[]{Sno},null, null, null);
                String courseId = cursor.getString(cursor.getColumnIndex("CourseID"));
                cursor = db.query("Course", null, "CourseID = ?", new String[]{courseId} , null, null, null);
                if (cursor.moveToFirst()){
                    do {
                        String id = cursor.getString(cursor.getColumnIndex("CourseID"));
                        String name = cursor.getString(cursor.getColumnIndex("CourseName"));
                        String beforeId = cursor.getString(cursor.getColumnIndex("CourseBeforeID"));
                        Log.d("DB", "CourseID: " + id);
                        Log.d("DB", "CourseName: " + name);
                        Log.d("DB", "CourseBeforeID: " + beforeId);
                    }while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case R.id.increase_age:
                cursor = db.query("Student", null, "College = ?", new String[]{"计算机学院"}, null, null , null);
                if (cursor.moveToFirst()){
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("Name"));
                        values.put("Age", cursor.getInt(cursor.getColumnIndex("Age")) + 2);
                        db.update("Student", values, "Name = ?", new String[]{name});
                        values.clear();
                    }while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case R.id.delete:
                cursor = db.query("Student", null, "Name = ?", new String[]{"李四"}, null, null , null);
                String sno = cursor.getString(cursor.getColumnIndex("SNO"));
                cursor = db.query("Choose", null, "SNO = ?", new String[]{sno}, null, null , null);
                String courseID = cursor.getString(cursor.getColumnIndex("CourseID"));
                cursor.close();
                db.delete("Student", "SNO = ?", new String[]{sno});
                db.delete("Course", "CourseID = ?", new String[]{courseID});
                db.delete("Choose", "SNO = ?", new String[]{sno});
                break;
        }
    }

    private void addChoose(SQLiteDatabase db, ContentValues values) {
        //第一组
        values.put("SNO", "S0001");
        values.put("CourseID", "C1");
        values.put("Score", 95);
        db.insert("Choose", null, values);
        values.clear();
        //第二组
        values.put("SNO", "S0001");
        values.put("CourseID", "C2");
        values.put("Score", 80);
        db.insert("Choose", null, values);
        values.clear();
        //第三组
        values.put("SNO", "S0001");
        values.put("CourseID", "C3");
        values.put("Score", 84);
        db.insert("Choose", null, values);
        values.clear();
        //第四组
        values.put("SNO", "S0002");
        values.put("CourseID", "C1");
        values.put("Score", 80);
        db.insert("Choose", null, values);
        values.clear();
        //第五组
        values.put("SNO", "S0002");
        values.put("CourseID", "C2");
        values.put("Score", 85);
        db.insert("Choose", null, values);
        values.clear();
        //第六组
        values.put("SNO", "S0003");
        values.put("CourseID", "C1");
        values.put("Score", 78);
        db.insert("Choose", null, values);
        values.clear();
        //第七组
        values.put("SNO", "S0003");
        values.put("CourseID", "C3");
        values.put("Score", 70);
        db.insert("Choose", null, values);
        values.clear();
    }

    private void addCourse(SQLiteDatabase db, ContentValues values) {
        //第一组
        values.put("CourseID", "C1");
        values.put("CourseName", "计算机引论");
        values.put("CourseBeforeID", "NULL");
        db.insert("Course", null, values);
        values.clear();
        //第二组
        values.put("CourseID", "C2");
        values.put("CourseName", "C语言");
        values.put("CourseBeforeID", "C1");
        db.insert("Course", null, values);
        values.clear();
        //第三组
        values.put("CourseID", "C3");
        values.put("CourseName", "数据结构");
        values.put("CourseBeforeID", "C2");
        db.insert("Course", null, values);
        values.clear();
    }

    private void addStudent(SQLiteDatabase db, ContentValues values) {
        //第一组
        values.put("SNO", "S00001");
        values.put("Name", "张三");
        values.put("Age", 20);
        values.put("College", "计算机学院");
        db.insert("Student", null, values);
        values.clear();
        //第二组
        values.put("SNO", "S00002");
        values.put("Name", "李四");
        values.put("Age", 19);
        values.put("College", "通信学院");
        db.insert("Student", null, values);
        values.clear();
        //第三组
        values.put("SNO", "S00003");
        values.put("Name", "王五");
        values.put("Age", 21);
        values.put("College", "计算机学院");
        db.insert("Student", null, values);
        values.clear();
    }
}
