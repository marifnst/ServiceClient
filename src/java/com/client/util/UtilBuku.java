/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.util;

import com.client.entities.TblBuku;
import com.client.entities.TblKategori;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Emerio
 */
public class UtilBuku {

    private static UtilBuku utilBuku;

    public static UtilBuku getInstance() {
        if (utilBuku == null) {
            utilBuku = new UtilBuku();
        }
        return utilBuku;
    }

    public List<TblBuku> getAllBuku(String page) {
        UtilDatabase.getInstance().openConnection();
        Query q = UtilDatabase.getInstance().getEntityManager().createQuery("SELECT T FROM TblBuku T ORDER BY T.createdDate DESC");
        q.setFirstResult((Integer.parseInt(page) - 1) * UtilVariables.TOTAL_SHOW_DATA);
        q.setMaxResults(UtilVariables.TOTAL_SHOW_DATA);
        List<TblBuku> buku = q.getResultList();
        UtilDatabase.getInstance().getEntityManager().close();
        return buku;
    }

    public List<TblBuku> getAllBukuByCategory(String page, String id) {
        UtilDatabase.getInstance().openConnection();
        Query q = UtilDatabase.getInstance().getEntityManager().createQuery("SELECT T FROM TblBuku T WHERE T.idKategori = " + id + " ORDER BY T.createdDate DESC");
        q.setFirstResult((Integer.parseInt(page) - 1) * UtilVariables.TOTAL_SHOW_DATA);
        q.setMaxResults(UtilVariables.TOTAL_SHOW_DATA);
        List<TblBuku> buku = q.getResultList();
        UtilDatabase.getInstance().getEntityManager().close();
        return buku;
    }

    public List<TblBuku> getBestSeller() {
        UtilDatabase.getInstance().openConnection();
        Query q = UtilDatabase.getInstance().getEntityManager().createQuery("SELECT T FROM TblBuku T WHERE T.isBestSeller = 1 ORDER BY T.createdDate DESC");
        q.setFirstResult(0);
        q.setMaxResults(UtilVariables.TOTAL_SHOW_BEST_SELLER);
        List<TblBuku> buku = q.getResultList();
        UtilDatabase.getInstance().getEntityManager().close();
        return buku;
    }

    public int getTotalBuku() {
        UtilDatabase.getInstance().openConnection();
        Query q = UtilDatabase.getInstance().getEntityManager().createQuery("SELECT COUNT(T.id) FROM TblBuku T");
        int result = (int) q.getFirstResult();
        UtilDatabase.getInstance().getEntityManager().close();
        return result;
    }
}
