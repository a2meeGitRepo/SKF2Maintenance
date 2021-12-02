package com.SKF2Maintenance.dto;

public class DashbordCount {

	private int thisWeekcount;
	private int todayCount;
	private int doneCount;
	
	
	private int openBreakdon;
	private int closedBreakdon;
	
	
	public int getThisWeekcount() {
		return thisWeekcount;
	}
	public void setThisWeekcount(int thisWeekcount) {
		this.thisWeekcount = thisWeekcount;
	}
	public int getTodayCount() {
		return todayCount;
	}
	public void setTodayCount(int todayCount) {
		this.todayCount = todayCount;
	}
	public int getDoneCount() {
		return doneCount;
	}
	public void setDoneCount(int doneCount) {
		this.doneCount = doneCount;
	}
	public int getOpenBreakdon() {
		return openBreakdon;
	}
	public void setOpenBreakdon(int openBreakdon) {
		this.openBreakdon = openBreakdon;
	}
	public int getClosedBreakdon() {
		return closedBreakdon;
	}
	public void setClosedBreakdon(int closedBreakdon) {
		this.closedBreakdon = closedBreakdon;
	}
	
}
