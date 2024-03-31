package com.nhommot.thitracnghiem.models;

import java.util.Date;

import org.springframework.data.annotation.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Idkythi")
    private Long idkythi;

    @Column(name = "Mamon")
    private String mamon;

    @Column(name = "Tenkythi")
    private String tenkythi;

    @Column(name = "Tglambai")
    private int tglambai;

    @Column(name = "Tgmode")
    private Date tgmode;

    @Column(name = "Tgdongde")
    private Date tgdongde;

    @Column(name = "Tongsocau")
    private int tongsocau;
    
    @Column(name = "ketqua")
    private String ketqua;
    
    @Transient // Để bỏ qua việc ánh xạ cột trong cơ sở dữ liệu
    @Column(name = "resultcount")
    private Float resultsCount;
    
    @Transient // Để bỏ qua việc ánh xạ cột trong cơ sở dữ liệu
    @Column(name = "averagescore")
    private Float averageScore;

    @ManyToOne
    @JoinColumn(name = "Mamon", referencedColumnName = "Mamon", insertable = false, updatable = false)
    private Subject subject;
    
    
	public Float getResultsCount() {
		return resultsCount;
	}

	public void setResultsCount(Float resultsCount) {
		this.resultsCount = resultsCount;
	}

	

	public Float getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Float averageScore) {
		this.averageScore = averageScore;
	}

	public String getKetqua() {
		return ketqua;
	}

	public void setKetqua(String ketqua) {
		this.ketqua = ketqua;
	}

	public Long getIdkythi() {
		return idkythi;
	}

	public void setIdkythi(Long idkythi) {
		this.idkythi = idkythi;
	}

	public String getMamon() {
		return mamon;
	}

	public void setMamon(String mamon) {
		this.mamon = mamon;
	}

	public String getTenkythi() {
		return tenkythi;
	}

	public void setTenkythi(String tenkythi) {
		this.tenkythi = tenkythi;
	}

	public int getTglambai() {
		return tglambai;
	}

	public void setTglambai(int tglambai) {
		this.tglambai = tglambai;
	}

	public Date getTgmode() {
		return tgmode;
	}

	public void setTgmode(Date tgmode) {
		this.tgmode = tgmode;
	}

	public Date getTgdongde() {
		return tgdongde;
	}

	public void setTgdongde(Date tgdongde) {
		this.tgdongde = tgdongde;
	}

	public int getTongsocau() {
		return tongsocau;
	}

	public void setTongsocau(int tongsocau) {
		this.tongsocau = tongsocau;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

    
}
