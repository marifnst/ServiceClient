/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.util;

import com.client.entities.TblBuku;
import com.client.entities.TblKategori;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Emerio
 */
public class UtilKategori {

    private static UtilKategori utilKategori;

    public static UtilKategori getInstance() {
        if (utilKategori == null) {
            utilKategori = new UtilKategori();
        }
        return utilKategori;
    }

    public List<TblKategori> getAllKategori() {
        UtilDatabase.getInstance().openConnection();
        Query q = UtilDatabase.getInstance().getEntityManager().createNamedQuery("TblKategori.findAll");
        List<TblKategori> kategori = q.getResultList();
        UtilDatabase.getInstance().getEntityManager().close();
        return kategori;
    }

    public int deleteKategori(int id) {
        int result = 0;
        UtilDatabase.getInstance().openConnection();
        TblKategori kategori = new TblKategori(id);
        EntityManager em = UtilDatabase.getInstance().getEntityManager();
        EntityTransaction q = em.getTransaction();
        q.begin();
        TblKategori kategoriAfter = em.merge(kategori);
        em.remove(kategoriAfter);
        q.commit();
        UtilDatabase.getInstance().getEntityManager().close();
        result = 1;
        return result;
    }

    public int insertKategori(String id, String deskripsi) {
        int result = 0;
        UtilDatabase.getInstance().openConnection();
        TblKategori kategori = new TblKategori();
        //kategori.setId(result);
        kategori.setDeskripsi(deskripsi);
        kategori.setCreatedDate(new Date());

        EntityManager em = UtilDatabase.getInstance().getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(kategori);
        et.commit();
        UtilDatabase.getInstance().getEntityManager().close();
        result = 1;
        return result;
    }

    public TblKategori getKategoriById(String id) {
        UtilDatabase.getInstance().openConnection();
        EntityManager em = UtilDatabase.getInstance().getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TblKategori result = em.find(TblKategori.class, Integer.parseInt(id));
        et.commit();
        UtilDatabase.getInstance().getEntityManager().close();
        return result;
    }

    public int editKategori(String id, String deskripsi) {
        int result = 0;
        UtilDatabase.getInstance().openConnection();
        EntityManager em = UtilDatabase.getInstance().getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TblKategori kategori = em.find(TblKategori.class, Integer.parseInt(id));
        kategori.setDeskripsi(deskripsi);
        kategori.setLastUpdatedDate(new Date());
        et.commit();
        UtilDatabase.getInstance().getEntityManager().close();
        result = 1;
        return result;
    }
}
