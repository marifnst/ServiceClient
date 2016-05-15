/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.util;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Emerio
 */
public class UtilResult {

    private static UtilResult utilResult;

    public static UtilResult getInstance() {
        if (utilResult == null) {
            utilResult = new UtilResult();
        }
        return utilResult;
    }

    public List<Map<String, String>> getAllThread() {
        String query = "SELECT DISTINCT ID_THREAD FROM TBL_PROCESS";
        UtilDatabase.getInstance().openConnectionNative();
        List<Map<String, String>> result = UtilDatabase.getInstance().executeQuery(query);
        UtilDatabase.getInstance().closeConnection();
        return result;
    }

    public List<Map<String, String>> getAll() {
        String query = "SELECT "
                + "ID_THREAD, "
                + "SUM(CASE WHEN NO_SIMULASI = 1 THEN ELAPSED_TIME ELSE 0 END) AS SIMULASI_1, "
                + "SUM(CASE WHEN NO_SIMULASI = 2 THEN ELAPSED_TIME ELSE 0 END) AS SIMULASI_2, "
                + "SUM(CASE WHEN NO_SIMULASI = 3 THEN ELAPSED_TIME ELSE 0 END) AS SIMULASI_3, "
                + "SUM(CASE WHEN NO_SIMULASI = 4 THEN ELAPSED_TIME ELSE 0 END)  AS SIMULASI_4 "
                + "FROM TBL_PROCESS "
                + "GROUP BY ID_THREAD";
        UtilDatabase.getInstance().openConnectionNative();
        List<Map<String, String>> result = UtilDatabase.getInstance().executeQuery(query);
        UtilDatabase.getInstance().closeConnection();
        return result;
    }

    public List<Map<String, String>> getByThreadId(String threadId) {
        String query = "SELECT "
                + "SUM(CASE WHEN NO_SIMULASI = 1 THEN ELAPSED_TIME ELSE 0 END) AS SIMULASI_1, "
                + "SUM(CASE WHEN NO_SIMULASI = 2 THEN ELAPSED_TIME ELSE 0 END) AS SIMULASI_2, "
                + "SUM(CASE WHEN NO_SIMULASI = 3 THEN ELAPSED_TIME ELSE 0 END) AS SIMULASI_3, "
                + "SUM(CASE WHEN NO_SIMULASI = 4 THEN ELAPSED_TIME ELSE 0 END)  AS SIMULASI_4 "
                + "FROM TBL_PROCESS "
                + "WHERE ID_THREAD = '" + threadId + "'";
        UtilDatabase.getInstance().openConnectionNative();
        List<Map<String, String>> result = UtilDatabase.getInstance().executeQuery(query);
        UtilDatabase.getInstance().closeConnection();
        return result;
    }

    public List<Map<String, String>> getByThreadIdDetail(String threadId) {
        String query = "SELECT "
                + "SUM(CASE WHEN NO_SIMULASI = 1 THEN ELAPSED_TIME ELSE 0 END) AS SIMULASI_1, "
                + "SUM(CASE WHEN NO_SIMULASI = 2 THEN ELAPSED_TIME ELSE 0 END) AS SIMULASI_2, "
                + "SUM(CASE WHEN NO_SIMULASI = 3 THEN ELAPSED_TIME ELSE 0 END) AS SIMULASI_3, "
                + "SUM(CASE WHEN NO_SIMULASI = 4 THEN ELAPSED_TIME ELSE 0 END)  AS SIMULASI_4 "
                + "FROM TBL_PROCESS "
                + "WHERE ID_THREAD = '" + threadId + "'";
        UtilDatabase.getInstance().openConnectionNative();
        List<Map<String, String>> result = UtilDatabase.getInstance().executeQuery(query);
        UtilDatabase.getInstance().closeConnection();
        return result;
    }
}
