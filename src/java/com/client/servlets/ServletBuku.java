/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.servlets;

import com.client.entities.TblBuku;
import com.client.util.UtilBuku;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Emerio
 */
public class ServletBuku extends HttpServlet {

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
        response.setContentType("application/json");
        List<TblBuku> bukus = new ArrayList<>();
        String viewType = "VIEW_BY_CATEGORY";

        if (request.getParameter("view") == null) {
            String page = request.getParameter("page");
            bukus = UtilBuku.getInstance().getAllBuku(page);
        } else if (request.getParameter("view") != null) {
            if (request.getParameter("view").equalsIgnoreCase("VIEW_BY_CATEGORY")) {
                String page = request.getParameter("page");
                String id = request.getParameter("id");
                bukus = UtilBuku.getInstance().getAllBukuByCategory(page, id);
            } else if (request.getParameter("view").equalsIgnoreCase("DETAIL")) {
                String id = request.getParameter("id");
                bukus = UtilBuku.getInstance().getBukuById(id);
                viewType = request.getParameter("view");
            } else if (request.getParameter("view").equalsIgnoreCase("VIEW_BY_FILTER")) {
                String page = request.getParameter("page");
                String key = request.getParameter("key");
                String column = "";

                if (request.getParameter("filter").equalsIgnoreCase("Nama")) {
                    column = "namaBuku";
                } else if (request.getParameter("filter").equalsIgnoreCase("Deskripsi")) {
                    column = "shortDescription";
                } else if (request.getParameter("filter").equalsIgnoreCase("Detail Deskripsi")) {
                    column = "detailDescription";
                } else if (request.getParameter("filter").equalsIgnoreCase("Penulis")) {
                    column = "penulis";
                }

                bukus = UtilBuku.getInstance().getAllBukuByFilter(column, key, page);
            }
        }

        List<Map<String, Object>> jsonBuku = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
        for (TblBuku tbuku : bukus) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("id", tbuku.getId());
            temp.put("nama", tbuku.getNamaBuku());
            temp.put("harga", tbuku.getHarga());
            temp.put("penulis", tbuku.getPenulis());
            temp.put("deskripsi", tbuku.getShortDescription());
            temp.put("imagePath", tbuku.getImagePath());
            temp.put("detailDeskripsi", tbuku.getDetailDescription());
            temp.put("jumlahHalaman", tbuku.getJumlahHalaman());
            temp.put("waktuTerbit", sdf.format(tbuku.getWaktuTerbit()));
            temp.put("isbn10", tbuku.getIsbn10());
            temp.put("isbn13", tbuku.getIsbn13());
            jsonBuku.add(temp);
        }

        if (!viewType.equalsIgnoreCase("DETAIL")) {
            Map<String, Object> json = new HashMap<>();
            json.put("buku", jsonBuku);
            response.getWriter().println(new JSONObject(json));
        } else {
            response.getWriter().println(new JSONObject(jsonBuku.get(0)));
        }
        response.getWriter().flush();
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
