package com.s20.databasedemo_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.s20.databasedemo_android.util.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Database name
    /*public static final String DATABASE_NAME = "my_database";
    SQLiteDatabase sqLiteDatabase;*/

    // sqLite openHelper instance
    DatabaseHelper sqLiteDatabase;

    EditText etName, etSalary;
    Spinner spinnerDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etSalary = findViewById(R.id.et_salary);
        spinnerDept = findViewById(R.id.spinner_dept);

        findViewById(R.id.btn_add_employee).setOnClickListener(this);
        findViewById(R.id.tv_view_employees).setOnClickListener(this);

        // initialise our database
        /*sqLiteDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createTable();*/

        // initializing the instance of sqLLite openHelper class
        sqLiteDatabase = new DatabaseHelper(this);
    }

    /*private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS employee (" +
                "id INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(20) NOT NULL, " +
                "department VARCHAR(20) NOT NULL, " +
                "joining_date DATETIME NOT NULL, " +
                "salary DOUBLE NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_employee:
                addEmployee();
                break;
            case R.id.tv_view_employees:
                startActivity(new Intent(this, EmployeeActivity.class));
                break;
        }

    }

    private void addEmployee() {
        String name = etName.getText().toString().trim();
        String salary = etSalary.getText().toString().trim();
        String department = spinnerDept.getSelectedItem().toString();

        // getting the current time
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());

        if (name.isEmpty()) {
            etName.setError("name field cannot be empty");
            etName.requestFocus();
            return;
        }

        if (salary.isEmpty()) {
            etSalary.setError("salary cannot be empty");
            etSalary.requestFocus();
            return;
        }

        /*String sql = "INSERT INTO employee (name, department, joining_date, salary)" +
                "VALUES (?, ?, ?, ?)";
        sqLiteDatabase.execSQL(sql, new String[]{name, department, joiningDate, salary});*/

        // insert employee into database table with the help of database openHelper class
        if (sqLiteDatabase.addEmployee(name, department, joiningDate, Double.valueOf(salary)))
            Toast.makeText(this, "Employee Added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Employee NOT Added", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        etName.setText("");
        etSalary.setText("");
        spinnerDept.setSelection(0);
        etSalary.clearFocus();
        etName.requestFocus();
    }
}










