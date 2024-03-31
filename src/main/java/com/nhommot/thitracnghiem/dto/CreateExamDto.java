package com.nhommot.thitracnghiem.dto;

import java.util.List;

import com.nhommot.thitracnghiem.models.Answers;
import com.nhommot.thitracnghiem.models.Questions;

public class CreateExamDto {

	 	private String tenMon;
	    private String maMon;
	    private String tenKythi;
	    private int tglambai;
	    private String tgmode;
	    private String tgdongde;
	    private int tongsocau;
	    private List<Questions> questions;
	    private List<Answers> answers;
	    
	    
	    
		public List<Answers> getAnswers() {
			return answers;
		}
		public void setAnswers(List<Answers> answers) {
			this.answers = answers;
		}
		public String getTenMon() {
			return tenMon;
		}
		public void setTenMon(String tenMon) {
			this.tenMon = tenMon;
		}
		public String getMaMon() {
			return maMon;
		}
		public void setMaMon(String maMon) {
			this.maMon = maMon;
		}
		public String getTenKythi() {
			return tenKythi;
		}
		public void setTenKythi(String tenKythi) {
			this.tenKythi = tenKythi;
		}
		public int getTglambai() {
			return tglambai;
		}
		public void setTglambai(int tglambai) {
			this.tglambai = tglambai;
		}
		public String getTgmode() {
			return tgmode;
		}
		public void setTgmode(String tgmode) {
			this.tgmode = tgmode;
		}
		public String getTgdongde() {
			return tgdongde;
		}
		public void setTgdongde(String tgdongde) {
			this.tgdongde = tgdongde;
		}
		public int getTongsocau() {
			return tongsocau;
		}
		public void setTongsocau(int tongsocau) {
			this.tongsocau = tongsocau;
		}
		public List<Questions> getQuestions() {
			return questions;
		}
		public void setQuestions(List<Questions> questions) {
			this.questions = questions;
		}

	    
}

