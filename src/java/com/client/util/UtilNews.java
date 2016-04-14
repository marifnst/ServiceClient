/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.util;

import com.client.entities.TblKategori;
import com.client.entities.TblNews;
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
}
