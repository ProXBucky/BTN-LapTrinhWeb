package com.nhommot.thitracnghiem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Subject")
public class Subject {
    @Id
    @Column(name = "Mamon")
    private String mamon;

    @Column(name = "Tenmon")
    private String tenmon;

	public String getMamon() {
		return mamon;
	}

	public void setMamon(String mamon) {
		this.mamon = mamon;
	}

	public String getTenmon() {
		return tenmon;
	}

	public void setTenmon(String tenmon) {
		this.tenmon = tenmon;
	}

   
}
