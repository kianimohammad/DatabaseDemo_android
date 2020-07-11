package com.s20.databasedemo_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.s20.databasedemo_android.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;

    List<Employee> employeeList;
    ListView employeesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        employeesListView = findViewById(R.id.lv_employees);
        employeeList = new ArrayList<>();

        sqLiteDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);
        loadEmployees();
    }

    private void loadEmployees() {
        String sql = "SELECT * FROM employee";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
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
        }

        // create an adapter to display the employees
        EmployeeAdapter employeeAdapter = new EmployeeAdapter(this,
                R.layout.list_layout_employee,
                employeeList,
                sqLiteDatabase);
        employeesListView.setAdapter(employeeAdapter);
    }
}