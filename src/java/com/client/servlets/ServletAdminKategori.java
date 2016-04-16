/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.servlets;

import com.client.entities.TblBuku;
import com.client.entities.TblKategori;
import com.client.util.UtilBuku;
import com.client.util.UtilKategori;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Emerio
 */
@WebServlet(name = "ServletAdminKategori", urlPatterns = {"/ServletAdminKategori"})
public class ServletAdminKategori extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parameter = request.getParameter("ACTION");
        if (parameter.equals("VIEW")) {
            List<TblKategori> kategori = new ArrayList<>();
            kategori = UtilKategori.getInstance().getAllKategori();

            SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");

            List<List<String>> jsonKategoriDataTable = new ArrayList<>();

            for (TblKategori tkategori : kategori) {
                List<String> tempp = new ArrayList<>();
                String action = "<a href='#' onclick='showKategoriDialog()'>Add</a>";
                action += " <a href='#' onclick='showKategoriEditDialog(" + tkategori.getId().toString() + ")'>Edit</a>";
                action += " <a href='#' onclick='deleteKategori(" + tkategori.getId() + ")'>Delete</a>";
                tempp.add(action);
                tempp.add(tkategori.getId().toString());
                tempp.add(tkategori.getDeskripsi());
                jsonKategoriDataTable.add(tempp);
            }

            Map<String, Object> json = new HashMap<>();
            //json.put("buku", jsonBuku);
            json.put("data", jsonKategoriDataTable);
            response.getWriter().println(new JSONObject(json));
        } else if (parameter.equals("INSERT")) {
            String id = request.getParameter("ID");
            String deskripsi = request.getParameter("DESKRIPSI");
            int result = UtilKategori.getInstance().insertKategori(id, deskripsi);
            if (result == 0) {
                response.getWriter().println("INSERT FAILED");
            } else {
                response.getWriter().println("INSERT SUCCESS");
            }
        } else if (parameter.equals("EDIT_SHOW")) {
            String id = request.getParameter("ID");
            TblKategori kategori = UtilKategori.getInstance().getKategoriById(id);
            Map<String, Object> json = new HashMap<>();
            //json.put("buku", jsonBuku);
            json.put("ID", String.valueOf(kategori.getId()));
            json.put("DESKRIPSI", kategori.getDeskripsi());
            response.getWriter().println(new JSONObject(json));
        } else if (parameter.equals("EDIT")) {
            String id = request.getParameter("ID");
            String deskripsi = request.getParameter("DESKRIPSI");
            int result = UtilKategori.getInstance().editKategori(id, deskripsi);
            if (result == 0) {
                response.getWriter().println("EDIT FAILED");
            } else {
                response.getWriter().println("EDIT SUCCESS");
            }
        } else if (parameter.equals("DELETE")) {
            String idDelete = request.getParameter("ID");
            int result = UtilKategori.getInstance().deleteKategori(Integer.parseInt(idDelete));
            if (result == 0) {
                response.getWriter().println("DELETE FAILED");
            } else {
                response.getWriter().println("DELETE SUCCESS");
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
