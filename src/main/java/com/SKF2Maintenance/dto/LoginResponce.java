/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.dto;

import com.SKF2Maintenance.model.User;

/**
 * @author lenovo
 *
 */
public class LoginResponce {
	private int responceCode;
	private String responceMsg;
	private User data;
	/**
	 * @return the responceCode
	 */
	public int getResponceCode() {
		return responceCode;
	}
	/**
	 * @param responceCode the responceCode to set
	 */
	public void setResponceCode(int responceCode) {
		this.responceCode = responceCode;
	}
	/**
	 * @return the responceMsg
	 */
	public String getResponceMsg() {
		return responceMsg;
	}
	/**
	 * @param responceMsg the responceMsg to set
	 */
	public void setResponceMsg(String responceMsg) {
		this.responceMsg = responceMsg;
	}
	/**
	 * @return the data
	 */
	public User getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(User data) {
		this.data = data;
	}
	
	
}
