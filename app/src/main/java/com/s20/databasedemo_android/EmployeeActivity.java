package com.s20.databasedemo_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.s20.databasedemo_android.room.Employee;
import com.s20.databasedemo_android.room.EmployeeRoomDb;
import com.s20.databasedemo_android.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

//    SQLiteDatabase sqLiteDatabase;

    // instance of DatabaseOpenHelper class
    DatabaseHelper sqLiteDatabase;

    // Room db instance
    private EmployeeRoomDb employeeRoomDb;

    List<Employee> employeeList;
    ListView employeesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        employeesListView = findViewById(R.id.lv_employees);
        employeeList = new ArrayList<>();

        //sqLiteDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);

//        sqLiteDatabase = new DatabaseHelper(this);

        // Room
        employeeRoomDb = EmployeeRoomDb.getInstance(this);
        loadEmployees();
    }

    private void loadEmployees() {
        /*String sql = "SELECT * FROM employee";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);*/

        /*Cursor cursor = sqLiteDatabase.getAllEmployees();

        if (cursor.moveToFirst()) {
            do {
                // create an employee instance
                employeeList.add(new Employee(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getDouble(4)
                ));
            } while (cursor.moveToNext());
            cursor.close();
        }*/

        employeeList = employeeRoomDb.employeeDao().getAllEmployees();


        // create an adapter to display the employees
        /*EmployeeAdapter employeeAdapter = new EmployeeAdapter(this,
                R.layout.list_layout_employee,
                employeeList,
                sqLiteDatabase);*/
        EmployeeAdapter employeeAdapter = new EmployeeAdapter(this, R.layout.list_layout_employee, employeeList);
        employeesListView.setAdapter(employeeAdapter);
    }
}








