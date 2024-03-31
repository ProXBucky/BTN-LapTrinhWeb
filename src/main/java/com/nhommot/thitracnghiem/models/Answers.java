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
@Table(name = "Answers")
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Iddapan")
    private Long iddapan;

    @Column(name = "Idcauhoi")
    private Long idcauhoi;

    @Column(name = "Noidung")
    private String noidung;

    @Column(name = "DungSai")
    private Boolean dungSai;

    @ManyToOne
    @JoinColumn(name = "Idcauhoi", referencedColumnName = "Idcauhoi", insertable = false, updatable = false)
    private Questions question;

	public Long getIddapan() {
		return iddapan;
	}

	public void setIddapan(Long iddapan) {
		this.iddapan = iddapan;
	}

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

	public Boolean getDungSai() {
		return dungSai;
	}

	public void setDungSai(Boolean dungSai) {
		this.dungSai = dungSai;
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

    
}
