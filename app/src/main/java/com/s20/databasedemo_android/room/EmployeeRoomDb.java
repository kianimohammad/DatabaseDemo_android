package com.s20.databasedemo_android.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.s20.databasedemo_android.room.Employee;

@Database(entities = {Employee.class}, version = 1, exportSchema = false)
public abstract class EmployeeRoomDb extends RoomDatabase {

    private static final String DB_NAME = "employee_room_db";

    public abstract EmployeeDao employeeDao();

    private static volatile EmployeeRoomDb INSTANCE;

    public static EmployeeRoomDb getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EmployeeRoomDb.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        return INSTANCE;
    }
}
