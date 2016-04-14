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
@Table(name = "tbl_buku")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblBuku.findAll", query = "SELECT t FROM TblBuku t"),
    @NamedQuery(name = "TblBuku.findById", query = "SELECT t FROM TblBuku t WHERE t.id = :id"),
    @NamedQuery(name = "TblBuku.findByNamaBuku", query = "SELECT t FROM TblBuku t WHERE t.namaBuku = :namaBuku"),
    @NamedQuery(name = "TblBuku.findByShortDescription", query = "SELECT t FROM TblBuku t WHERE t.shortDescription = :shortDescription"),
    @NamedQuery(name = "TblBuku.findByHarga", query = "SELECT t FROM TblBuku t WHERE t.harga = :harga"),
    @NamedQuery(name = "TblBuku.findByPenulis", query = "SELECT t FROM TblBuku t WHERE t.penulis = :penulis"),
    @NamedQuery(name = "TblBuku.findByWaktuTerbit", query = "SELECT t FROM TblBuku t WHERE t.waktuTerbit = :waktuTerbit"),
    @NamedQuery(name = "TblBuku.findByStok", query = "SELECT t FROM TblBuku t WHERE t.stok = :stok"),
    @NamedQuery(name = "TblBuku.findByIsBestSeller", query = "SELECT t FROM TblBuku t WHERE t.isBestSeller = :isBestSeller"),
    @NamedQuery(name = "TblBuku.findByIsSpecialOffer", query = "SELECT t FROM TblBuku t WHERE t.isSpecialOffer = :isSpecialOffer"),
    @NamedQuery(name = "TblBuku.findByJumlahHalaman", query = "SELECT t FROM TblBuku t WHERE t.jumlahHalaman = :jumlahHalaman"),
    @NamedQuery(name = "TblBuku.findByIsNewBook", query = "SELECT t FROM TblBuku t WHERE t.isNewBook = :isNewBook"),
    @NamedQuery(name = "TblBuku.findByIdKategori", query = "SELECT t FROM TblBuku t WHERE t.idKategori = :idKategori"),
    @NamedQuery(name = "TblBuku.findByCreatedDate", query = "SELECT t FROM TblBuku t WHERE t.createdDate = :createdDate"),
    @NamedQuery(name = "TblBuku.findByIsbn10", query = "SELECT t FROM TblBuku t WHERE t.isbn10 = :isbn10"),
    @NamedQuery(name = "TblBuku.findByIsbn13", query = "SELECT t FROM TblBuku t WHERE t.isbn13 = :isbn13")})
public class TblBuku implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAMA_BUKU")
    private String namaBuku;
    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;
    @Lob
    @Column(name = "DETAIL_DESCRIPTION")
    private String detailDescription;
    @Lob
    @Column(name = "IMAGE_PATH")
    private String imagePath;
    @Column(name = "HARGA")
    private Integer harga;
    @Column(name = "PENULIS")
    private String penulis;
    @Column(name = "WAKTU_TERBIT")
    @Temporal(TemporalType.DATE)
    private Date waktuTerbit;
    @Column(name = "STOK")
    private Integer stok;
    @Column(name = "IS_BEST_SELLER")
    private Integer isBestSeller;
    @Column(name = "IS_SPECIAL_OFFER")
    private Integer isSpecialOffer;
    @Column(name = "JUMLAH_HALAMAN")
    private Integer jumlahHalaman;
    @Column(name = "IS_NEW_BOOK")
    private Integer isNewBook;
    @Column(name = "ID_KATEGORI")
    private Integer idKategori;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "ISBN_10")
    private String isbn10;
    @Basic(optional = false)
    @Column(name = "ISBN_13")
    private String isbn13;

    public TblBuku() {
    }

    public TblBuku(Integer id) {
        this.id = id;
    }

    public TblBuku(Integer id, Date createdDate, String isbn10, String isbn13) {
        this.id = id;
        this.createdDate = createdDate;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaBuku() {
        return namaBuku;
    }

    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public Date getWaktuTerbit() {
        return waktuTerbit;
    }

    public void setWaktuTerbit(Date waktuTerbit) {
        this.waktuTerbit = waktuTerbit;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Integer getIsBestSeller() {
        return isBestSeller;
    }

    public void setIsBestSeller(Integer isBestSeller) {
        this.isBestSeller = isBestSeller;
    }

    public Integer getIsSpecialOffer() {
        return isSpecialOffer;
    }

    public void setIsSpecialOffer(Integer isSpecialOffer) {
        this.isSpecialOffer = isSpecialOffer;
    }

    public Integer getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(Integer jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public Integer getIsNewBook() {
        return isNewBook;
    }

    public void setIsNewBook(Integer isNewBook) {
        this.isNewBook = isNewBook;
    }

    public Integer getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Integer idKategori) {
        this.idKategori = idKategori;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
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
        if (!(object instanceof TblBuku)) {
            return false;
        }
        TblBuku other = (TblBuku) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.client.entities.TblBuku[ id=" + id + " ]";
    }
    
}
