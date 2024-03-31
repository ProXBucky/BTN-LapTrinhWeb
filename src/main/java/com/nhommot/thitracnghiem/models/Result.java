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
@Table(name = "Result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Idketqua")
    private Long idketqua;

    @Column(name = "Idsinhvien")
    private Long idsinhvien;

    @Column(name = "Idkythi")
    private Long idkythi;

    @Column(name = "diem")
    private Float diem;
    
    @Column(name = "traloi")
    private String traloi;

    @ManyToOne
    @JoinColumn(name = "Idsinhvien", referencedColumnName = "Idsinhvien", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "Idkythi", referencedColumnName = "Idkythi", insertable = false, updatable = false)
    private Exam exam;
    
    
    

	public String getTraloi() {
		return traloi;
	}

	public void setTraloi(String traloi) {
		this.traloi = traloi;
	}

	public Long getIdketqua() {
		return idketqua;
	}

	public void setIdketqua(Long idketqua) {
		this.idketqua = idketqua;
	}

	public Long getIdsinhvien() {
		return idsinhvien;
	}

	public void setIdsinhvien(Long idsinhvien) {
		this.idsinhvien = idsinhvien;
	}

	public Long getIdkythi() {
		return idkythi;
	}

	public void setIdkythi(Long idkythi) {
		this.idkythi = idkythi;
	}

	public Float getDiem() {
		return diem;
	}

	public void setDiem(Float diem) {
		this.diem = diem;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

    
}
