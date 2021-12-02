package com.SKF2Maintenance.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.multipart.MultipartException;

import com.SKF2Maintenance.config.FileStorageProperties;
import com.SKF2Maintenance.dto.UploadFileResponse;
import com.SKF2Maintenance.model.BreakdownReport;
import com.SKF2Maintenance.model.DBFile;
import com.SKF2Maintenance.model.DowntimeReport;
import com.SKF2Maintenance.model.Drawing;
import com.SKF2Maintenance.model.Location;
import com.SKF2Maintenance.model.MTBFReport;
import com.SKF2Maintenance.model.MTTRReport;
import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.model.MachineSpares;
import com.SKF2Maintenance.model.MachineSpindle;
import com.SKF2Maintenance.model.Maintenance;
import com.SKF2Maintenance.model.SetupChart;
import com.SKF2Maintenance.model.Spare;
import com.SKF2Maintenance.model.SpindleDetails;
import com.SKF2Maintenance.model.Task;
import com.SKF2Maintenance.model.TaskHead;
import com.SKF2Maintenance.repository.DrawingRepo;
import com.SKF2Maintenance.service.DBFileStorageService;
import com.SKF2Maintenance.service.FileStorageService;
import com.SKF2Maintenance.service.LocationService;
import com.SKF2Maintenance.service.MachineMappingService;
import com.SKF2Maintenance.service.MachineService;
import com.SKF2Maintenance.service.MaintenanceServcie;
import com.SKF2Maintenance.service.ReportServices;
import com.SKF2Maintenance.service.SetupChartService;
import com.SKF2Maintenance.service.SparesService;
import com.SKF2Maintenance.service.SpindleDetailsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/upload")
public class UploadController {
	
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MachineService machineService;
	@Autowired
	MachineMappingService machineMappingService;
	
	@Autowired
	private SpindleDetailsService spindleDetailsService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private SparesService sparesService;
	
	@Autowired
	private MaintenanceServcie maintenanceServcie;
	
	@Autowired
	private ReportServices reportServices;
	
	@Autowired
	SetupChartService setupChartService;
	
	@Autowired
	DrawingRepo drawingRepo;
	
	
	 @Autowired
	    private FileStorageService fileStorageService;
	  @Autowired
	  private FileStorageProperties fileStorageProperties;
	
	
	
	
	@ExceptionHandler(value = MultipartException.class)
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)

    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String path3=fileStorageProperties.getUploadDir();
  //      log.warn("Failed to upload attachment", e);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(path3)
                .path(fileName)
                .toUriString();
    
       

        return new UploadFileResponse();
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/uploadTest", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadTest(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request,@RequestParam("name") String name) {
		try {
			Drawing drwawing= new Drawing();
			
			
			System.out.println("Drawing Name   "+name );
			 String fileName = fileStorageService.storeFile(file);
		        String path3=fileStorageProperties.getUploadDir();
		  //      log.warn("Failed to upload attachment", e);
		        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path(path3)
		                .path(fileName)
		                .toUriString();
		        drwawing.setFileType("Drawing");
		        drwawing.setAddedDate(new Date());
				drwawing.setFileName(fileName);
				drwawing.setLocationUrl(path3);
				drwawing.setAddedBy("System");
				drwawing.setName(name);
				drawingRepo.save(drwawing);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@RequestMapping(value = "/uploadSetUpChart", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadSetUpChart(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request,@RequestParam("name") String name) {
		try {
			Drawing drwawing= new Drawing();
			
			
			System.out.println("Drawing Name   "+name );
			 String fileName = fileStorageService.storeFile(file);
		        String path3=fileStorageProperties.getUploadDir();
		  //      log.warn("Failed to upload attachment", e);
		        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path(path3)
		                .path(fileName)
		                .toUriString();
		        drwawing.setFileType("Setup Chart");
		        drwawing.setAddedDate(new Date());
				drwawing.setFileName(fileName);
				drwawing.setLocationUrl(path3);
				drwawing.setAddedBy("System");
				drwawing.setName(name);
				drawingRepo.save(drwawing);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@RequestMapping(value = "/downloadFile/{fileName:.+}", method = RequestMethod.GET)

    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
	 
	  fileStorageProperties.setMachineImages(fileStorageProperties.getMachineImages());
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {

            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
          //  logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	  String uploadPath1="C:\\BitBucketRepo\\SKF2MaintenanceNew\\SKF2Maintenance\\src\\main\\resources\\static\\uploads";
	
	@RequestMapping(value = "/uploadMachine", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadMachine(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) {
		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0); 
						int i = 1;
						while (i <= datatypeSheet.getLastRowNum()) { 

							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							String str = row.getCell(0).toString();
							if(str.length()==0) {
								continue;
							}
							System.out.println("ROW 0  "+row.getCell(0).toString());
							System.out.println("ROW 1  "+row.getCell(1).toString());
							
							String channelNo=row.getCell(0).toString();
							//String machineId=row.getCell(1).toString();
							String machineName=row.getCell(1).toString();
							String assetType=row.getCell(2).toString();
							String application=row.getCell(4).toString();
							String assetNumber=row.getCell(3).toString();

							String currentCostCenter=row.getCell(5).toString();
							String currentCostCenter1=currentCostCenter.substring(0, currentCostCenter.length() - 2);

							String purchaseDate =row.getCell(6).toString();
							System.out.println("purchaseDate   "+purchaseDate);

							Date pDate=new SimpleDateFormat("dd-MMM-yyyy").parse(purchaseDate); 
							
							String assetNumber1="";
							
							if(assetNumber.contains(".0")){
								assetNumber1=assetNumber.substring(0, assetNumber.length() - 2);

							}else{
								assetNumber1=assetNumber;
							}
							Optional<Machine> machine= machineService.getMachineByAssetNumber(assetNumber1);
							Machine newSpindle= new Machine();
							System.out.println("machine   "+machine);

							if(machine.isPresent()){
								newSpindle=machine.get();
								newSpindle.setActive(1);
								newSpindle.setChennelNo(channelNo);
								newSpindle.setMachineName(machineName);
								newSpindle.setAssetType(assetType);
								newSpindle.setApplication(application);
								newSpindle.setAssetNumber(assetNumber1);
								newSpindle.setPurchaseDate(pDate);
								newSpindle.setCurrentCostCenter(currentCostCenter1);
								
							}else{
								newSpindle.setChennelNo(channelNo);
								newSpindle.setMachineName(machineName);
								newSpindle.setAssetType(assetType);
								newSpindle.setApplication(application);
								newSpindle.setAssetNumber(assetNumber1);
								newSpindle.setPurchaseDate(pDate);
								newSpindle.setCurrentCostCenter(currentCostCenter1);
								newSpindle.setDeleteBit(0);
								newSpindle.setAddedDate(new Date());
							}
							machineService.addMachine(newSpindle);
						}
						logger.info("Successfully imported");
						workbook.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}  catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/uploadSpindle", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadSpindle(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) {
		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0); 
						int i = 1;
						while (i <= datatypeSheet.getLastRowNum()) { 

							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							String str = row.getCell(0).toString();
							if(str.length()==0) {
								continue;
							}
							System.out.println("ROW 0  "+row.getCell(0).toString());
							System.out.println("ROW 1  "+row.getCell(1).toString());
							
							
							String spindleName=row.getCell(1).toString();
							String spindleType=row.getCell(2).toString();
							String spindleDetails=row.getCell(3,XSSFRow.CREATE_NULL_AS_BLANK).toString();
							@SuppressWarnings("deprecation")
							XSSFCell sparesInfoCell=row.getCell(4,XSSFRow.CREATE_NULL_AS_BLANK);
							System.out.println("sparesInfoCell   ::  "+sparesInfoCell=="null");
							String sparesInfo="";
							if(sparesInfoCell!=null|| !sparesInfoCell.equals(null)){
								 sparesInfo=row.getCell(4).toString();

							}
							
							String machineName= row.getCell(5).toString();
							SpindleDetails newSpindle = new  SpindleDetails();
							newSpindle.setActive(1);
							newSpindle.setSparesInfo(sparesInfo);
							newSpindle.setSpindleDetails(spindleDetails);
							newSpindle.setSpindleType(spindleType);
							newSpindle.setSpindleName(spindleName);

									
							
							SpindleDetails spindle2=spindleDetailsService.addSpindle(newSpindle);
							
							List<Machine> machines=machineService.getMachinesByame(machineName);
							
							if(machines.size()!=0){
								for(Machine machine:machines){
									MachineSpindle machineSpindle= new MachineSpindle();
									machineSpindle.setActive(1);
									machineSpindle.setAddedDate(new Date());
									machineSpindle.setMachine(machine);
									machineSpindle.setSpindle(spindle2);
									
									machineMappingService.addMachineSpindle(machineSpindle);
									
								}
							}
						}
						logger.info("Successfully imported");
						workbook.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/uploadSpares", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadSpares(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) {
		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0); 
						int i = 1;
						while (i <= datatypeSheet.getLastRowNum()) { 

							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							String str = row.getCell(0).toString();
							if(str.length()==0||str==null) {
								continue;
							}
							System.out.println("ROW 0  "+row.getCell(0).toString());
							System.out.println("ROW 1  "+row.getCell(1).toString());
							
							
							String spareType=row.getCell(1).toString();
							String spareName=row.getCell(2).toString();
							String spareInfo=row.getCell(3).toString();
							String stockCode=row.getCell(4).toString();
							String location=row.getCell(5).toString();
							String machineName=row.getCell(6).toString();
							
							
							Optional<Spare> optional=sparesService.getSpareBySpareName(spareName);
							System.out.println("spareName  "+spareName);
							Spare spares=new Spare();
									
						
								spares.setActive(1);
								spares.setSpareInfo(spareInfo);
								spares.setSpareName(spareName);
								spares.setStockCode(stockCode);
								spares.setLocation(location);
								spares.setSpareType(spareType);
								Spare spare2=sparesService.addSpare(spares);
								List<Machine> machines=machineService.getMachinesByame(machineName);
								
								if(machines.size()!=0){
									for(Machine machine:machines){
										MachineSpares machineSpares = new MachineSpares();
										machineSpares.setActive(1);
										machineSpares.setAddedDate(new Date());
										machineSpares.setMachine(machine);
										machineSpares.setSpare(spare2);
										
										machineMappingService.addMmachineSpare(machineSpares);
										
									}
								}
							
							
						}
						logger.info("Successfully imported");
						workbook.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	@RequestMapping(value = "/uploadLocation", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadLocation(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) {
		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0); 
						int i = 1;
						while (i <= datatypeSheet.getLastRowNum()) { 

							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							String str = row.getCell(0).toString();
							if(str.length()==0) {
								continue;
							}
							System.out.println("ROW 0  "+row.getCell(0).toString());
							System.out.println("ROW 1  "+row.getCell(1).toString());
							  Location location= new Location();
							
							  String locationCode= row.getCell(1).toString();
							  String locationName= row.getCell(2).toString();

							  location.setActive(1);
							  location.setLocationCode(locationCode);
							  location.setLocationName(locationName);
							
							locationService.addLocation(location);
						}
						logger.info("Successfully imported");
						workbook.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	@RequestMapping(value = "/uploadMMMaintemance", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadMMMaintemance(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) throws ParseException {
		Machine machine= new Machine();
		TaskHead head= new TaskHead();
		

		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0); 
						int i = 0;
						while (i <= datatypeSheet.getLastRowNum()) { 

							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							String str = row.getCell(0).toString();
							if(str.length()==0) {
								continue;
							}
							String machineName="";
							if(i==1){
								machineName=row.getCell(1).toString();
								String assetNumber=row.getCell(1).toString();
								System.out.println("assetNumber::"+assetNumber);

								Optional<Machine> optional= machineService.getMachineByAssetNumber(assetNumber);
								System.out.println("Machine  ::  "+optional.isPresent()); 
								if(optional.isPresent()){
									 machine=optional.get();
								 }else{
									 Machine machine2= new Machine();
									 machine2.setActive(1);
									 machine2.setAddedDate(new Date());
									 machine2.setMachineName(machineName);
									 machine=machineService.saveMachine(machine2);
								 }
								 //System.out.println("I   "+i);

							}
							if(i>=3){
								//System.out.println("MachineName   "+machine.getMachineName()+"---"+machine.getMachineId());

								String serNo=row.getCell(0).toString();
								//System.out.println("serNo "+serNo);
								
								//System.out.println("serNo "+serNo);
								String serNo1 = serNo.split("\\.")[1];
							//	System.out.println("DDDD "+serNo1);
								String taskHead=row.getCell(1).toString();
								if(serNo1.equalsIgnoreCase("0")){
									
									
									Optional<TaskHead> thoptional= maintenanceServcie.getTaskHeadByNameAndMachine(taskHead,machine.getMachineId());
									if(thoptional.isPresent()){
										head=thoptional.get();
									}else{
										TaskHead head2= new TaskHead();
										head2.setTaskHeadName(taskHead);
										head2.setSrNo(serNo);
										head2.setActive(1);
										head2.setMachine(machine);
										head2.setAddedDate(new Date());
										head= maintenanceServcie.saveTaskHead(head2);
										
									}
									
								//	System.out.println("Task Head "+head.getTaskHeadName()+"  ---    "+head.getTaskHeadId());
								
								
								
								}else{
								//	System.out.println("TASK ");
									String task=row.getCell(1).toString();
									Task task2 = new Task();
									Optional<Task> toptional= maintenanceServcie.getTaskByNameAndTaskHead(task,head.getTaskHeadId());
									if(toptional.isPresent()){
										task2=toptional.get();
									}else{
										Task task3= new Task();
										task3.setActive(1);
										task3.setAddedDate(new Date());
										task3.setMachineId(machine.getMachineId());
										task3.setFrequency(row.getCell(2).toString());
										task3.setTaskType("MMT");
									//	task3.setMachine(machine);
										//task3.setTaskHead(head);
										task3.setTaskHeadId(head.getTaskHeadId());
										task3.setTaskName(task);
										task3.setSrNo(serNo);
										task2= maintenanceServcie.saveTask(task3);
										
									}
									System.out.println("Machine : "+machine.getMachineName());
									System.out.println("Maintenance Mode : MM Maintenance");
									System.out.println("Task Head : "+head.getSrNo()+" "+head.getTaskHeadName());
									System.out.println("Task : "+task2.getSrNo()+" "+task2.getTaskName());
									System.out.println("Frequency  : "+row.getCell(2).toString());
									
									Maintenance maintenance = new Maintenance();
									maintenance.setActive(1);
									maintenance.setCreatedDate(new Date());
									maintenance.setFrequency(row.getCell(2).toString());
									maintenance.setMachine(machine);
									maintenance.setMaintenanceMode("MM");
									maintenance.setStatus("Open");
									maintenance.setTask(task2);
									maintenance.setTaskHead(head);
									if(row.getCell(2).toString().equalsIgnoreCase("M")){
										
										
										 if(row.getCell(3).toString()!=""){
											 Date jan=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(3).toString());
											 maintenance.setSheduleDate(jan);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(4).toString()!=""){
											 Date frb=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(4).toString());
											 maintenance.setSheduleDate(frb);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(5).toString()!=""){
											 Date mar=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(5).toString());
											 maintenance.setSheduleDate(mar);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(6).toString()!=""){
											 Date apr=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(6).toString());
											 maintenance.setSheduleDate(apr);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(7).toString()!=""){
											 Date may=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(7).toString());
											 maintenance.setSheduleDate(may);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(8).toString()!=""){
											 Date jun=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(8).toString());
											 maintenance.setSheduleDate(jun);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(9).toString()!=""){
											 Date jul=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(9).toString());
											 maintenance.setSheduleDate(jul);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(10).toString()!=""){
											 Date aug=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(10).toString());
											 maintenance.setSheduleDate(aug);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(11).toString()!=""){
											 Date sep=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(11).toString());
											 maintenance.setSheduleDate(sep);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(12).toString()!=""){
											 Date oct=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(12).toString());
											 maintenance.setSheduleDate(oct);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(13).toString()!=""){
											 Date nov=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(13).toString());
											 maintenance.setSheduleDate(nov);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(14).toString()!=""){
											 Date dec=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(14).toString());
											 maintenance.setSheduleDate(dec);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 
										 

										System.out.println("JAN   : "+row.getCell(3).toString());
										
										
									}else if(row.getCell(2).toString().equalsIgnoreCase("Q")){
										
										
										 if(row.getCell(3).toString()!=""){
											 Date fst=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(3).toString());
											 maintenance.setSheduleDate(fst);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(6).toString()!=""){
											 Date snd=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(6).toString());
											 maintenance.setSheduleDate(snd);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(9).toString()!=""){
											 Date trd=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(9).toString());
											 maintenance.setSheduleDate(trd);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 if(row.getCell(12).toString()!=""){
											 Date fth=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(12).toString());
											 maintenance.setSheduleDate(fth);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
									/*	System.out.println("1 St Q   : "+row.getCell(3).toString());
										System.out.println("2 nd Q   : "+row.getCell(6).toString());
										System.out.println("3 rd Q : "+row.getCell(9).toString());
										System.out.println("4 th Q   : "+row.getCell(12).toString());
*/
									}
									else if(row.getCell(2).toString().equalsIgnoreCase("HY")){
										
										 if(row.getCell(3).toString()!=""){
											 Date fst=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(3).toString());
											 maintenance.setSheduleDate(fst);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										 
										 
										 if(row.getCell(9).toString()!=""){
											 Date snd=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(9).toString());
											 maintenance.setSheduleDate(snd);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
									/*	System.out.println("1 St H   : "+row.getCell(3).toString());
										System.out.println("2 nd H   : "+row.getCell(9).toString());
									*/	

									}
									else if(row.getCell(2).toString().equalsIgnoreCase("Y")){
									//	System.out.println("1 St y   : "+row.getCell(3).toString());
										 if(row.getCell(3).toString()!=""){
											 Date yr=new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(3).toString());
											 maintenance.setSheduleDate(yr);
											 maintenanceServcie.addMaintenance(maintenance);
										 }
										

									}
									System.out.println("--------------------------------------------------------------");
									
								
								}
								
								
							}
							
							
							
							
							
							
							/*System.out.println("ROW 0  "+row.getCell(0).toString());
							System.out.println("ROW 1  "+row.getCell(1).toString());
							 */
						}
						logger.info("Successfully imported");
						workbook.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	@RequestMapping(value = "/uploadTBMMaintemance", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadTBMMaintemance(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) throws ParseException {
		Machine machine= new Machine();
		TaskHead head= new TaskHead();
		

		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0); 
						int i = 1;
						while (i <= datatypeSheet.getLastRowNum()) { 

							XSSFRow row = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							String str = row.getCell(0).toString();
							if(str.length()==0) {
								continue;
							}
							String machineName="";
							machineName=row.getCell(0).toString();
							String assetNumber=row.getCell(0).toString();
							System.out.println("Asset Number ::  "+machineName);
							Optional<Machine> optional= machineService.getMachineByAssetNumber(assetNumber);
							 if(optional.isPresent()){
								 machine=optional.get();
							 }else{
								 Machine machine2= new Machine();
								 machine2.setActive(1);
								 machine2.setAddedDate(new Date());
								 machine2.setMachineName(machineName);
								 machine=machineService.saveMachine(machine2);
							 }
							String task=row.getCell(1).toString();
							
							System.out.println("TASK");
							Task task2= new Task();
							Optional<Task> tsOptional=maintenanceServcie.getTBMTaskByMachineAndTaskName(task,machine.getMachineId());
							if (tsOptional.isPresent()) {
								task2=tsOptional.get();
							}else{
								Task task3= new Task();
								task3.setActive(1);
								task3.setAddedDate(new Date(0));
								task3.setTaskName(task);
								task3.setTaskType("TBM");
								task2=maintenanceServcie.saveTask(task3);
							}
							
							
							
							
							
							System.out.println("Machine   "+machine.getMachineName());
							System.out.println("Task   "+task2.getTaskName());
							String freq=row.getCell(2).toString();
						
						String frequecy="";
						if(freq.substring(0,freq.length()-2).equalsIgnoreCase("12")){
							frequecy="M";
						}
						if(freq.substring(0,freq.length()-2).equalsIgnoreCase("1")){
							frequecy="Y";
						}
						if(freq.substring(0,freq.length()-2).equalsIgnoreCase("2")){
							frequecy="HY";
						}
						if(freq.substring(0,freq.length()-2).equalsIgnoreCase("6")){
							frequecy="2M";
						}
						System.out.println("Frequency   "+frequecy);
							System.out.println("Schedule "+row.getCell(3).toString());
							Maintenance maintenance= new Maintenance();
							if(row.getCell(3).toString()!=""){
								 Date scheduleDate;
								try {
									scheduleDate = new SimpleDateFormat("dd-MMMM-yyyy").parse(row.getCell(3).toString());
									 maintenance.setSheduleDate(scheduleDate);
									 maintenance.setActive(1);
									 maintenance.setCreatedDate(new Date());
									 maintenance.setFrequency(frequecy);
									 maintenance.setMaintenanceMode("TBM");
									 maintenance.setStatus("Open");
									 maintenance.setTask(task2);
									 maintenance.setMachine(machine);
									 maintenanceServcie.addMaintenance(maintenance);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							
						}
						logger.info("Successfully imported");
						workbook.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "/uploadMTBF", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadMTBF(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) throws ParseException {
		String year="";
		Machine machine= new Machine();
		

		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0); 
						int i = 1;
						
						while (i <= datatypeSheet.getLastRowNum()) { 

							XSSFRow row = null;
							XSSFRow row1 = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							
							String str = row.getCell(0).toString();
							if(i==2){
								String my = row.getCell(2).toString();
								String[] daterr=my.split("-");
								year=daterr[2];
							}else{
								if(str.length()==0) {
									continue;
								}
								String machineName="";
								machineName=row.getCell(1).toString();
								Optional<Machine> optional= machineService.getMachineByName(machineName);
								 if(optional.isPresent()){
									 machine=optional.get();
								 }else{
									 Machine machine2= new Machine();
									 machine2.setActive(1);
									 machine2.setAddedDate(new Date());
									 machine2.setMachineName(machineName);
									 machine=machineService.saveMachine(machine2);
								 }
								 MTBFReport mtbfReport= new MTBFReport();
								 Optional<MTBFReport> optional2=reportServices.getMTBFReportBYMachineAndYear(machine.getMachineId(),year);
								 if(optional2.isPresent()){
									 mtbfReport=optional2.get();
								 }else{
									  mtbfReport= new MTBFReport();
										 mtbfReport.setMachine(machine);
										 mtbfReport.setYear(year);
										 mtbfReport.setChannel(row.getCell(0).toString());

								 }
								 mtbfReport.setJan(row.getCell(2).toString());
								 mtbfReport.setFeb(row.getCell(3).toString());
								 mtbfReport.setMar(row.getCell(4).toString());
								 mtbfReport.setApr(row.getCell(5).toString());
								 mtbfReport.setMay(row.getCell(6).toString());
								 mtbfReport.setJun(row.getCell(7).toString());
								 mtbfReport.setJul(row.getCell(8).toString());
								 mtbfReport.setAug(row.getCell(9).toString());
								 mtbfReport.setSep(row.getCell(10).toString());
								 mtbfReport.setOct(row.getCell(11).toString());
								 mtbfReport.setNov(row.getCell(12).toString());
								 mtbfReport.setDecm(row.getCell(12).toString());
								 reportServices.saveMTBFReport(mtbfReport);
									
							}
							
						}
						logger.info("Successfully imported");
						workbook.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/uploadDowntime", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadDowntime(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) throws ParseException {
		String year="";
		Machine machine= new Machine();
		

		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0); 
						int i = 1;
						
						while (i <= datatypeSheet.getLastRowNum()) { 

							XSSFRow row = null;
							XSSFRow row1 = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							
							String str = row.getCell(0).toString();
							if(i==2){
								String my = row.getCell(2).toString();
								String[] daterr=my.split("-");
								year=daterr[2];
							}else{
								if(str.length()==0) {
									continue;
								}
								String machineName="";
								machineName=row.getCell(1).toString();
								Optional<Machine> optional= machineService.getMachineByName(machineName);
								 if(optional.isPresent()){
									 machine=optional.get();
								 }else{
									 Machine machine2= new Machine();
									 machine2.setActive(1);
									 machine2.setAddedDate(new Date());
									 machine2.setMachineName(machineName);
									 machine=machineService.saveMachine(machine2);
								 }
								 DowntimeReport mtbfReport= new DowntimeReport();
								 Optional<DowntimeReport> optional2=reportServices.getDowntimeReportBYMachineAndYear(machine.getMachineId(),year);
								 if(optional2.isPresent()){
									 mtbfReport=optional2.get();
								 }else{
									  mtbfReport= new DowntimeReport();
										 mtbfReport.setMachine(machine);
										 mtbfReport.setYear(year);
										 mtbfReport.setChannel(row.getCell(0).toString());

								 }
								 mtbfReport.setJan(row.getCell(2).toString());
								 mtbfReport.setFeb(row.getCell(3).toString());
								 mtbfReport.setMar(row.getCell(4).toString());
								 mtbfReport.setApr(row.getCell(5).toString());
								 mtbfReport.setMay(row.getCell(6).toString());
								 mtbfReport.setJun(row.getCell(7).toString());
								 mtbfReport.setJul(row.getCell(8).toString());
								 mtbfReport.setAug(row.getCell(9).toString());
								 mtbfReport.setSep(row.getCell(10).toString());
								 mtbfReport.setOct(row.getCell(11).toString());
								 mtbfReport.setNov(row.getCell(12).toString());
								 mtbfReport.setDecm(row.getCell(12).toString());
								 reportServices.saveDowntimeReport(mtbfReport);
									
							}
							
						}
						logger.info("Successfully imported");
						workbook.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/uploadMMTR", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadMMTR(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) throws ParseException {
		String year="";
		Machine machine= new Machine();
		

		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0); 
						int i = 1;
						
						while (i <= datatypeSheet.getLastRowNum()) { 

							XSSFRow row = null;
							XSSFRow row1 = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							
							String str = row.getCell(0).toString();
							if(i==2){
								String my = row.getCell(2).toString();
								String[] daterr=my.split("-");
								year=daterr[2];
							}else{
								if(str.length()==0) {
									continue;
								}
								String machineName="";
								machineName=row.getCell(1).toString();
								Optional<Machine> optional= machineService.getMachineByName(machineName);
								 if(optional.isPresent()){
									 machine=optional.get();
								 }else{
									 Machine machine2= new Machine();
									 machine2.setActive(1);
									 machine2.setAddedDate(new Date());
									 machine2.setMachineName(machineName);
									 machine=machineService.saveMachine(machine2);
								 }
								 MTTRReport mtbfReport= new MTTRReport();
								 Optional<MTTRReport> optional2=reportServices.getMTTRReportBYMachineAndYear(machine.getMachineId(),year);
								 if(optional2.isPresent()){
									 mtbfReport=optional2.get();
								 }else{
									  mtbfReport= new MTTRReport();
										 mtbfReport.setMachine(machine);
										 mtbfReport.setYear(year);
										 mtbfReport.setChannel(row.getCell(0).toString());

								 }
								 mtbfReport.setJan(row.getCell(2).toString());
								 mtbfReport.setFeb(row.getCell(3).toString());
								 mtbfReport.setMar(row.getCell(4).toString());
								 mtbfReport.setApr(row.getCell(5).toString());
								 mtbfReport.setMay(row.getCell(6).toString());
								 mtbfReport.setJun(row.getCell(7).toString());
								 mtbfReport.setJul(row.getCell(8).toString());
								 mtbfReport.setAug(row.getCell(9).toString());
								 mtbfReport.setSep(row.getCell(10).toString());
								 mtbfReport.setOct(row.getCell(11).toString());
								 mtbfReport.setNov(row.getCell(12).toString());
								 mtbfReport.setDecm(row.getCell(12).toString());
								 reportServices.saveMTTRReport(mtbfReport);
									
							}
							
						}
						logger.info("Successfully imported");
						workbook.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "/uploadBreakdown", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadBreakdown(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) throws ParseException {
		String year="";
		Machine machine= new Machine();
		

		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0); 
						int i = 1;
						
						while (i <= datatypeSheet.getLastRowNum()) { 

							XSSFRow row = null;
							XSSFRow row1 = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							
							String str = row.getCell(0).toString();
							if(i==2){
								String my = row.getCell(2).toString();
								String[] daterr=my.split("-");
								year=daterr[2];
							}else{
								if(str.length()==0) {
									continue;
								}
								String assetNumber="";
								assetNumber=row.getCell(1).toString();
								Optional<Machine> optional= machineService.getMachineByAssetNumber(assetNumber);
								 if(optional.isPresent()){
									 machine=optional.get();
								 }
								 BreakdownReport mtbfReport= new BreakdownReport();
								 Optional<BreakdownReport> optional2=reportServices.getBreakdownReportBYMachineAndYear(machine.getMachineId(),year);
								 if(optional2.isPresent()){
									 mtbfReport=optional2.get();
								 }else{
									  mtbfReport= new BreakdownReport();
										 mtbfReport.setMachine(machine);
										 mtbfReport.setYear(year);
										 mtbfReport.setChannel(row.getCell(0).toString());

								 }
								 mtbfReport.setJan(row.getCell(2).toString());
								 mtbfReport.setFeb(row.getCell(3).toString());
								 mtbfReport.setMar(row.getCell(4).toString());
								 mtbfReport.setApr(row.getCell(5).toString());
								 mtbfReport.setMay(row.getCell(6).toString());
								 mtbfReport.setJun(row.getCell(7).toString());
								 mtbfReport.setJul(row.getCell(8).toString());
								 mtbfReport.setAug(row.getCell(9).toString());
								 mtbfReport.setSep(row.getCell(10).toString());
								 mtbfReport.setOct(row.getCell(11).toString());
								 mtbfReport.setNov(row.getCell(12).toString());
								 mtbfReport.setDecm(row.getCell(12).toString());
								 reportServices.saveBreakdownReport(mtbfReport);
									
							}
							
						}
						logger.info("Successfully imported");
						workbook.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/*
	
	
	@RequestMapping(value = "/uploadSetupChart", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity uploadSetUpChart(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) throws ParseException {
		String year="";
		Machine machine= new Machine();
		String machineName="";
		String fname="";
		String channel="";
		String type="";
		String tableHeader="";

		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
						Workbook workbook = new XSSFWorkbook(excelFile);
						Sheet datatypeSheet = workbook.getSheetAt(0); 
						int i = 1;
						
						while (i <= datatypeSheet.getLastRowNum()) { 

							XSSFRow row = null;
							XSSFRow row1 = null;
							row = (XSSFRow) datatypeSheet.getRow(i++);
							String str = row.getCell(0).toString();
							
							if(i==7){
								 machineName = row.getCell(1).toString();
								 Optional<Machine> optional= machineService.getMachineByName(machineName);
									if(optional.isPresent()){
										 machine=optional.get();
									 }else{
										 Machine machine2= new Machine();
										 machine2.setActive(1);
										 machine2.setAddedDate(new Date());
										 machine2.setMachineName(machineName);
										 machine=machineService.saveMachine(machine2);
									 }
									
									System.out.println("MachineName 11 ::   "+machine.getMachineName());
							}else{
								if(str.length()==0) {
									continue;
								}
								

							}
							if(i==8){
								fname=row.getCell(0).toString();
								System.out.println("fname "+fname);

							}
							if(i==9){
								channel=row.getCell(0).toString();
								System.out.println("channel "+channel);
 
							}
							
							if(i==10){
								type=row.getCell(1).toString();
								System.out.println("type "+type);
 
							}
							System.out.println("/////////////////////////////////////////////////////////////////////////////////// ");
							if(i>=11){
								String stStr=row.getCell(0).toString();
								System.out.println("TB   "+stStr.substring(0,1));
								System.out.println("TB  CHeck  "+stStr.substring(0,1).equalsIgnoreCase("*"));
								if(stStr.substring(0,1).equalsIgnoreCase("*")){
									
									tableHeader=stStr.substring(1,stStr.length());
									System.out.println("TABLE HEAFER :: "+tableHeader);
									i++;
									continue;
								}
								
								SetupChart chart = new SetupChart();
								System.out.println("Type ::   "+type);
								System.out.println("Name ::   "+fname);
								System.out.println("Channel ::   "+channel);
								System.out.println("Table Header ::   "+tableHeader);
								
								
								
								System.out.println("SR Value ::   "+row.getCell(0));
								System.out.println("Set-up  data Description ::   "+row.getCell(1));
								System.out.println("Value ::   "+row.getCell(2));
								System.out.println("Unit ::   "+row.getCell(3));
								System.out.println("TOL ::   "+row.getCell(3));
								chart.setActive(1);
								chart.setAddedDate(new Date());
								chart.setChannel(channel);
								chart.setHeadernName(tableHeader);
								chart.setMachine(machine);
								chart.setName(fname);
								chart.setSetupDescription(row.getCell(1).toString());
								chart.setSrValue(row.getCell(0).toString());
								chart.setTol(row.getCell(3).toString());
								chart.setType(type);
								chart.setUnit(row.getCell(3).toString());
								chart.setValue(row.getCell(2).toString());
								setupChartService.saveSetupchart(chart);
							}
							
	
							
							
							/*if(i==2){
								String my = row.getCell(2).toString();
								String[] daterr=my.split("-");
								year=daterr[2];
							}else{
								if(str.length()==0) {
									continue;
								}*/
								
								/* if(optional.isPresent()){
									 machine=optional.get();
								 }else{
									 Machine machine2= new Machine();
									 machine2.setActive(1);
									 machine2.setAddedDate(new Date());
									 machine2.setMachineName(machineName);
									 machine=machineService.saveMachine(machine2);
								 }
								
							
							System.out.println("--------------------------------------------------------------------------------------------------------");

						}
						logger.info("Successfully imported");
						workbook.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	*/
	
	
	
	
	

	//  @GetMapping("/downloadFile1/{fileName:.+}")
		@RequestMapping(value ="/downloadFile1/{fileName:.+}", method = RequestMethod.GET)

	    public ResponseEntity<Resource> downloadFile1(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
		 
		  fileStorageProperties.setMachineImages(fileStorageProperties.getMachineImages());
	        Resource resource = fileStorageService.loadFileAsResource(fileName);
	        // Try to determine file's content type
	        String contentType = null;
	        try {

	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	          //  logger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }
	  
	
	
	
	
	
	
	/* @Autowired
	    private DBFileStorageService dbFileStorageService;
	@RequestMapping(value = "/uploadDrawing", method = RequestMethod.POST)
	public @ResponseBody UploadFileResponse uploadDrawing(ModelMap model, @ModelAttribute(value = "file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		Machine machine= new Machine();
		TaskHead head= new TaskHead();
		

		try {
			if (!(file == null)) {
				if (file.isEmpty()) {
					logger.info("File not found");
				} else {
					logger.info(file.getOriginalFilename());
					try {
						File dir = new File(System.getProperty("catalina.base"), "uploads");
						File uplaodedFile = new File(dir + file.getOriginalFilename());
						file.transferTo(uplaodedFile);
						FileInputStream excelFile = new FileInputStream(uplaodedFile);
						logger.info("hiiii@  :: " + excelFile);
				      
						  DBFile dbFile = dbFileStorageService.storeFile(file);

					        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					                .path("/downloadFile/")
					                .path(dbFile.getId())
					                .toUriString();

					        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
					                file.getContentType(), file.getSize());
						
						
					//	logger.info("Successfully imported");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	*/
}
 