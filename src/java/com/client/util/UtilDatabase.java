/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.util;

import com.mysql.jdbc.Connection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Emerio
 */
public class UtilDatabase {

    private static UtilDatabase utilDatabase;
    private EntityManagerFactory emf;
    private EntityManager em;
    private Connection connection;

    public static UtilDatabase getInstance() {
        if (utilDatabase == null) {
            utilDatabase = new UtilDatabase();
        }
        return utilDatabase;
    }

    public void openConnection() {
        try {
            Map<String, String> persistenceMap = new HashMap<>();
            InputStream is = UtilDatabase.class.getClassLoader().getResourceAsStream("config.properties");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();

            String persistenceUnitName = null;

            while (data != null) {
                String key = data.split("\\=")[0];
                String value = data.split("\\=")[1];
                if (key.equalsIgnoreCase("url")) {
                    persistenceMap.put("javax.persistence.jdbc.url", value);
                } else if (key.equalsIgnoreCase("username")) {
                    persistenceMap.put("javax.persistence.jdbc.user", value);
                } else if (key.equalsIgnoreCase("password")) {
                    persistenceMap.put("javax.persistence.jdbc.password", value);
                } else if (key.equalsIgnoreCase("driver")) {
                    persistenceMap.put("javax.persistence.jdbc.driver", value);
                } else if (key.equals("persistence.unit.name")) {
                    persistenceUnitName = value;
                }
                data = br.readLine();
            }

            emf = Persistence.createEntityManagerFactory(persistenceUnitName, persistenceMap);
            em = emf.createEntityManager();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openConnectionNative() {
        try {
            InputStream is = UtilDatabase.class.getClassLoader().getResourceAsStream("config.properties");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();

            String user = null;
            String password = null;
            String url = null;

            while (data != null) {
                String key = data.split("\\=")[0];
                String value = data.split("\\=")[1];
                if (key.equalsIgnoreCase("url")) {
                    url = value;
                } else if (key.equalsIgnoreCase("username")) {
                    user = value;
                } else if (key.equalsIgnoreCase("password")) {
                    password = value;
                } else if (key.equalsIgnoreCase("driver")) {

                }
                data = br.readLine();
            }
            br.close();
            isr.close();
            is.close();

            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Map<String, String>> executeQuery(String query) {
        List<Map<String, String>> result = new ArrayList<>();

        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            List<String> columnName = new ArrayList<>();

            while (rs.next()) {
                Map<String, String> temp = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String name = rsmd.getColumnLabel(i);
                    String value = rs.getString(name);
                    temp.put(name, value);
                }
                result.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
