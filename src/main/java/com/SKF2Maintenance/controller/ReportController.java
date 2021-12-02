package com.SKF2Maintenance.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SKF2Maintenance.model.Breakdown;
import com.SKF2Maintenance.model.BreakdownReport;
import com.SKF2Maintenance.model.DowntimeReport;
import com.SKF2Maintenance.model.MTBFReport;
import com.SKF2Maintenance.model.MTTRReport;
import com.SKF2Maintenance.service.ReportServices;

@RestController
@CrossOrigin("*")
@RequestMapping("/report")
public class ReportController {
	@Autowired
	ReportServices reportServices;
	
	
	@RequestMapping(value = "/getMTBFReportByYear", method = RequestMethod.GET)
	public @ResponseBody List<MTBFReport>  getMTBFReportByYear(@RequestParam("year") String year) {
		List<MTBFReport> reports= new ArrayList<MTBFReport>();
		try {
			reports= reportServices.getMTBFReportByYear(year);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}
	@RequestMapping(value = "/getMTTRReportByYear", method = RequestMethod.GET)
	public @ResponseBody List<MTTRReport>  getMTTRReportByYear(@RequestParam("year") String year) {
		List<MTTRReport> reports= new ArrayList<MTTRReport>();
		try {
			reports= reportServices.getMTTRReportByYear(year);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}
	@RequestMapping(value = "/getDowntimeReportByYear", method = RequestMethod.GET)
	public @ResponseBody List<DowntimeReport>  getDowntimeReportByYear(@RequestParam("year") String year) {
		List<DowntimeReport> reports= new ArrayList<DowntimeReport>();
		try {
			reports= reportServices.getDowntimeReportByYear(year);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}
	@RequestMapping(value = "/getBreakdownReportByYear", method = RequestMethod.GET)
	public @ResponseBody List<BreakdownReport>  getBreakdownReportByYear(@RequestParam("year") String year) {
		List<BreakdownReport> reports= new ArrayList<BreakdownReport>();
		try {
			reports= reportServices.getBreakdownReportByYear(year);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}

}
