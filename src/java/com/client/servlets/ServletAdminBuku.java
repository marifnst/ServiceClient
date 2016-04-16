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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Emerio
 */
@WebServlet(name = "ServletAdminBuku", urlPatterns = {"/ServletAdminBuku"})
public class ServletAdminBuku extends HttpServlet {

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
            List<TblBuku> bukus = new ArrayList<>();
            bukus = UtilBuku.getInstance().getAllBuku();

            List<Map<String, Object>> jsonBuku = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");

            List<List<String>> jsonBukuDataTable = new ArrayList<>();

            for (TblBuku tbuku : bukus) {

                List<String> tempp = new ArrayList<>();
                String action = "<a href='#' onclick='showInsertDialog()'>Add</a>";
                action += " <a href='#'>Edit</a>";
                action += " <a href='#' onclick='deleteBuku(" + tbuku.getId() + ")'>Delete</a>";
                tempp.add(action);
                tempp.add(String.valueOf(tbuku.getId()));
                tempp.add(String.valueOf(tbuku.getNamaBuku()));
                tempp.add(String.valueOf(tbuku.getShortDescription()));
                tempp.add(String.valueOf(tbuku.getDetailDescription()));
                tempp.add(String.valueOf(tbuku.getImagePath()));
                tempp.add(String.valueOf(tbuku.getHarga()));
                tempp.add(String.valueOf(tbuku.getPenulis()));
                tempp.add(String.valueOf(sdf.format(tbuku.getWaktuTerbit())));
                tempp.add(String.valueOf(sdf.format(tbuku.getStok())));
                tempp.add(String.valueOf(sdf.format(tbuku.getIsBestSeller())));
                tempp.add(String.valueOf(sdf.format(tbuku.getIsSpecialOffer())));
                tempp.add(String.valueOf(tbuku.getJumlahHalaman()));
                tempp.add(String.valueOf(tbuku.getIsNewBook()));
                tempp.add(String.valueOf(tbuku.getIdKategori()));
                tempp.add(String.valueOf(tbuku.getCreatedDate()));
                tempp.add(String.valueOf(tbuku.getIsbn10()));
                tempp.add(String.valueOf(tbuku.getIsbn13()));
                jsonBukuDataTable.add(tempp);
            }

            Map<String, Object> json = new HashMap<>();
            //json.put("buku", jsonBuku);
            json.put("data", jsonBukuDataTable);
            response.getWriter().println(new JSONObject(json));
        } else if (parameter.equals("INSERT")) {
            String idDelete = request.getParameter("ID");
            int result = UtilBuku.getInstance().deleteBuku(Integer.parseInt(idDelete));
            if (result == 0) {
                response.getWriter().println("DELETE FAILED");
            } else {
                response.getWriter().println("DELETE SUCCESS");
            }

        } else if (parameter.equals("DELETE")) {
            String idDelete = request.getParameter("ID");
            int result = UtilBuku.getInstance().deleteBuku(Integer.parseInt(idDelete));
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
