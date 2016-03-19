/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.util;

import com.client.entities.TblKategori;
import java.util.List;
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
}
