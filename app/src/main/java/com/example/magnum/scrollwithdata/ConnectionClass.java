package com.example.magnum.scrollwithdata;

/**
 * Created by magnum on 31/7/16.
 */

import android.database.SQLException;
import android.util.Log;

import android.annotation.SuppressLint;
import android.database.SQLException;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by magnum on 21/6/16.
 */
public class ConnectionClass {
    String ip = "SQL5027.myASP.NET";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "DB_A05DA5_temp";
    String un = "DB_A05DA5_temp_admin";
    String password = "sksmanju16";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        Log.d("TAG", "CONN: ");
        return conn;
    }
}
