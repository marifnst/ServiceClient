/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.util;

import com.client.entities.TblKategori;
import com.client.entities.TblNews;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Emerio
 */
public class UtilNews {

    private static UtilNews utilNews;

    public static UtilNews getInstance() {
        if (utilNews == null) {
            utilNews = new UtilNews();
        }
        return utilNews;
    }

    public List<TblNews> getAllNews() {
        UtilDatabase.getInstance().openConnection();
        Query q = UtilDatabase.getInstance().getEntityManager().createNamedQuery("TblNews.findAll");
        List<TblNews> news = q.getResultList();
        UtilDatabase.getInstance().getEntityManager().close();
        return news;
    }

    public int deleteNews(int id) {
        int result = 0;
        UtilDatabase.getInstance().openConnection();
        TblNews news = new TblNews(id);
        EntityManager em = UtilDatabase.getInstance().getEntityManager();
        EntityTransaction q = em.getTransaction();
        q.begin();
        TblNews newsAfter = em.merge(news);
        em.remove(newsAfter);
        q.commit();
        UtilDatabase.getInstance().getEntityManager().close();
        result = 1;
        return result;
    }

    public int insertNews(String description, String detailDescription) {
        int result = 0;
        UtilDatabase.getInstance().openConnection();
        TblNews news = new TblNews();

        news.setDescription(description);
        news.setDetailDescription(detailDescription);
        news.setCreatedDate(new Date());

        EntityManager em = UtilDatabase.getInstance().getEntityManager();
        EntityTransaction q = em.getTransaction();
        q.begin();
        em.persist(news);
        q.commit();
        UtilDatabase.getInstance().getEntityManager().close();
        result = 1;
        return result;
    }

    public TblNews getNewsById(String id) {
        UtilDatabase.getInstance().openConnection();
        EntityManager em = UtilDatabase.getInstance().getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TblNews result = em.find(TblNews.class, Integer.parseInt(id));
        et.commit();
        UtilDatabase.getInstance().getEntityManager().close();
        return result;
    }

    public int editNews(String id, String description, String detailDescription) {
        int result = 0;
        UtilDatabase.getInstance().openConnection();
        EntityManager em = UtilDatabase.getInstance().getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TblNews news = em.find(TblNews.class, Integer.parseInt(id));
        news.setDescription(description);
        news.setDetailDescription(detailDescription);
        et.commit();
        UtilDatabase.getInstance().getEntityManager().close();
        result = 1;
        return result;
    }
}
