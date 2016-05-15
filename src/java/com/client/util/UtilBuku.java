/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.util;

import com.client.entities.TblBuku;
import com.client.entities.TblNews;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

    public List<TblBuku> getAllBuku() {
        UtilDatabase.getInstance().openConnection();
        Query q = UtilDatabase.getInstance().getEntityManager().createQuery("SELECT T FROM TblBuku T");
        List<TblBuku> buku = q.getResultList();
        UtilDatabase.getInstance().getEntityManager().close();
        return buku;
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

    public List<TblBuku> getAllBukuNew() {
        UtilDatabase.getInstance().openConnection();
        EntityManager em = UtilDatabase.getInstance().getEntityManager();
        em.getEntityManagerFactory().getCache().evict(TblBuku.class);
        Query q = em.createQuery("SELECT T FROM TblBuku T ORDER BY T.createdDate DESC");
        q.setFirstResult(0);
        q.setMaxResults(UtilVariables.TOTAL_SHOW_DATA_NEW);
        List<TblBuku> buku = q.getResultList();
        return buku;
    }

    public TblNews getSpecialOffer() {
        UtilDatabase.getInstance().openConnection();
        Query q = UtilDatabase.getInstance().getEntityManager().createQuery("SELECT T FROM TblNews T WHERE T.isValid = 1 ORDER BY T.createdDate DESC");
        q.setFirstResult(0);
        q.setMaxResults(1);
        List<TblNews> news = q.getResultList();
        UtilDatabase.getInstance().getEntityManager().close();
        return news.get(0);
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

    public List<TblBuku> getAllBukuByFilter(String column, String key, String page) {
        UtilDatabase.getInstance().openConnection();
        Query q = UtilDatabase.getInstance().getEntityManager().createQuery("SELECT T FROM TblBuku T WHERE T." + column + " LIKE '%" + key + "%' ORDER BY T.createdDate DESC");
        q.setFirstResult((Integer.parseInt(page) - 1) * UtilVariables.TOTAL_SHOW_DATA);
        q.setMaxResults(UtilVariables.TOTAL_SHOW_DATA);
        List<TblBuku> buku = q.getResultList();
        UtilDatabase.getInstance().getEntityManager().close();
        return buku;
    }

    public List<TblBuku> getBukuById(String id) {
        UtilDatabase.getInstance().openConnection();
        Query q = UtilDatabase.getInstance().getEntityManager().createQuery("SELECT T FROM TblBuku T WHERE T.id = " + id);
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

    public int deleteBuku(int id) {
        int result = 0;
        UtilDatabase.getInstance().openConnection();
        TblBuku buku = new TblBuku(id);
        EntityManager em =  UtilDatabase.getInstance().getEntityManager();
        EntityTransaction q = em.getTransaction();
        q.begin();
        TblBuku bukuAfter = em.merge(buku);
        em.remove(bukuAfter);
        q.commit();
        UtilDatabase.getInstance().getEntityManager().close();
        result = 1;
        return result;
    }
}
