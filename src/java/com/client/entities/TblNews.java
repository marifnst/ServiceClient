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
@Table(name = "tbl_news")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNews.findAll", query = "SELECT t FROM TblNews t"),
    @NamedQuery(name = "TblNews.findById", query = "SELECT t FROM TblNews t WHERE t.id = :id"),
    @NamedQuery(name = "TblNews.findByDescription", query = "SELECT t FROM TblNews t WHERE t.description = :description"),
    @NamedQuery(name = "TblNews.findByCreatedDate", query = "SELECT t FROM TblNews t WHERE t.createdDate = :createdDate"),
    @NamedQuery(name = "TblNews.findByIsValid", query = "SELECT t FROM TblNews t WHERE t.isValid = :isValid")})
public class TblNews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Lob
    @Column(name = "DETAIL_DESCRIPTION")
    private String detailDescription;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "IS_VALID")
    private int isValid;

    public TblNews() {
    }

    public TblNews(Integer id) {
        this.id = id;
    }

    public TblNews(Integer id, String description, String detailDescription, Date createdDate, int isValid) {
        this.id = id;
        this.description = description;
        this.detailDescription = detailDescription;
        this.createdDate = createdDate;
        this.isValid = isValid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
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
        if (!(object instanceof TblNews)) {
            return false;
        }
        TblNews other = (TblNews) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.client.entities.TblNews[ id=" + id + " ]";
    }
    
}
