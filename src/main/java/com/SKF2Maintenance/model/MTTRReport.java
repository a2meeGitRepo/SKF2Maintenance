package com.SKF2Maintenance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mttr_report")
public class MTTRReport {
	

	@Id
	@GeneratedValue
	@Column(name="mttr_report_id")
	private int mttrReportId;
	
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine  machine;
	
	
	@Column(name="channel")
	private String channel;
	
	
	@Column(name="year")
	private String year;
	
	
	@Column(name="jan")
	private String jan;
	
	@Column(name="feb")
	private String feb;
	
	@Column(name="mar")
	private String mar;
	
	@Column(name="apr")
	private String apr;
	
	@Column(name="may")
	private String may;
	
	@Column(name="jun")
	private String jun;
	
	
	@Column(name="jul")
	private String jul;
	
	@Column(name="aug")
	private String aug;
	
	@Column(name="sep")
	private String sep;
	
	@Column(name="oct")
	private String oct;
	
	@Column(name="nov")
	private String nov;
	
	@Column(name="decm")
	private String decm;

	public int getMttrReportId() {
		return mttrReportId;
	}

	public void setMttrReportId(int mttrReportId) {
		this.mttrReportId = mttrReportId;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getJan() {
		return jan;
	}

	public void setJan(String jan) {
		this.jan = jan;
	}

	public String getFeb() {
		return feb;
	}

	public void setFeb(String feb) {
		this.feb = feb;
	}

	public String getMar() {
		return mar;
	}

	public void setMar(String mar) {
		this.mar = mar;
	}

	public String getApr() {
		return apr;
	}

	public void setApr(String apr) {
		this.apr = apr;
	}

	public String getMay() {
		return may;
	}

	public void setMay(String may) {
		this.may = may;
	}

	public String getJun() {
		return jun;
	}

	public void setJun(String jun) {
		this.jun = jun;
	}

	public String getJul() {
		return jul;
	}

	public void setJul(String jul) {
		this.jul = jul;
	}

	public String getAug() {
		return aug;
	}

	public void setAug(String aug) {
		this.aug = aug;
	}

	public String getSep() {
		return sep;
	}

	public void setSep(String sep) {
		this.sep = sep;
	}

	public String getOct() {
		return oct;
	}

	public void setOct(String oct) {
		this.oct = oct;
	}

	public String getNov() {
		return nov;
	}

	public void setNov(String nov) {
		this.nov = nov;
	}

	public String getDecm() {
		return decm;
	}

	public void setDecm(String decm) {
		this.decm = decm;
	}

	
	

}
