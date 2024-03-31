package com.nhommot.thitracnghiem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Idcauhoi")
    private Long idcauhoi;

    @Column(name = "Noidung")
    private String noidung;

    @Column(name = "Mamon")
    private String mamon;

    @ManyToOne
    @JoinColumn(name = "Mamon", referencedColumnName = "Mamon", insertable = false, updatable = false)
    private Subject subject;

	public Long getIdcauhoi() {
		return idcauhoi;
	}

	public void setIdcauhoi(Long idcauhoi) {
		this.idcauhoi = idcauhoi;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public String getMamon() {
		return mamon;
	}

	public void setMamon(String mamon) {
		this.mamon = mamon;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

    
}
