package com.example.exam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 */
public class DBService {

    private SQLiteDatabase db;

    public DBService() {
        db = SQLiteDatabase.openDatabase("/data/data/com.example.exam/databases/question.db",
                null, SQLiteDatabase.OPEN_READWRITE);
    }

    public List<Question> getQuestion() {
        List<Question> list = new ArrayList<Question>();
        Cursor cursor = db.rawQuery("select * from question", null);
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                Question question = new Question();
                question.question = cursor.getString(cursor.getColumnIndex("question"));
                question.answerA = cursor.getString(cursor.getColumnIndex("answerA"));
                question.answerB = cursor.getString(cursor.getColumnIndex("answerB"));
                question.answerC = cursor.getString(cursor.getColumnIndex("answerC"));
                question.answerD = cursor.getString(cursor.getColumnIndex("answerD"));
                question.answer = cursor.getInt(cursor.getColumnIndex("answer"));
                question.ID = cursor.getInt(cursor.getColumnIndex("ID"));
                question.explaination = cursor.getString(cursor.getColumnIndex("explaination"));
                // -1表示没有选择任何选项
                question.selectedAnswer = -1;
                list.add(question);
            }
        }
        return list;
    }

}
