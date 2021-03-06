/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emerio
 */
@Entity
@Table(name = "tbl_kategori")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblKategori.findAll", query = "SELECT t FROM TblKategori t"),
    @NamedQuery(name = "TblKategori.findById", query = "SELECT t FROM TblKategori t WHERE t.id = :id"),
    @NamedQuery(name = "TblKategori.findByCreatedDate", query = "SELECT t FROM TblKategori t WHERE t.createdDate = :createdDate"),
    @NamedQuery(name = "TblKategori.findByLastUpdatedDate", query = "SELECT t FROM TblKategori t WHERE t.lastUpdatedDate = :lastUpdatedDate")})
public class TblKategori implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Lob
    @Column(name = "DESKRIPSI")
    private String deskripsi;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "LAST_UPDATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;

    public TblKategori() {
    }

    public TblKategori(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblKategori)) {
            return false;
        }
        TblKategori other = (TblKategori) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.client.entities.TblKategori[ id=" + id + " ]";
    }
    
}
