/**
 * Dattatray Bodhale
	22-Jun-2021
 */
package com.SKF2Maintenance.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SKF2Maintenance.dto.ImageCode;
import com.SKF2Maintenance.dto.ImageUploadDto;
import com.SKF2Maintenance.dto.ResponceObj;
import com.SKF2Maintenance.model.ChangeManagement;
import com.SKF2Maintenance.model.ChangeManagementImage;
import com.SKF2Maintenance.service.ChangeMangementService;
import com.SKF2Maintenance.service.ImageService;



/**
 * @author Dattatray Bodhale
 *
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/fileUpload")
public class FileUploadController {

	ImageService imageService;
	@Autowired
	ChangeMangementService changeMangementService;
	
	 String imagepath="ChangeMangementInage";
	@RequestMapping(value = "/addSOPImages", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponceObj addSOPImages(@RequestBody ImageUploadDto imageUploadDto) {
		ResponceObj status = new ResponceObj();
	
		
		try {
			System.out.println("Call Image Uplaod "); 
			   List<ChangeManagementImage> list = new ArrayList<ChangeManagementImage>();
			  
			   DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			   DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			   String lastBest64String="";
				if(imageUploadDto.getImages().size()!=0) {
					
					int i=0;
					 System.out.println("Image Size:: "+imageUploadDto.getImages().size());
					for(ImageCode code:imageUploadDto.getImages()) {
						
						if(lastBest64String.equalsIgnoreCase(code.getBase64Code())){
							System.out.println("SAME Bese 64 Code");
						}else{
							System.out.println("DIFF  Bese 64 Code");
						}
						
						//System.out.println("CODE DETIALS :: "+code.toString());
						i++;
						ChangeManagementImage changeManagementImage= new ChangeManagementImage();
						 
						  byte[] imageByte;
							BufferedImage image = null;
						  	BASE64Decoder decoder = new BASE64Decoder();
							 imageByte = decoder.decodeBuffer(code.getBase64Code());
					            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
					            image = ImageIO.read(bis);
					          
					            
					            
					            Optional<ChangeManagement> optional= changeMangementService.getChangemangementById(imageUploadDto.getChangeManagementId());
					            
					            
					            String imaageName=optional.get().getChangeManagementNo()+"-"+i;
					            changeManagementImage.setImageName(imaageName+".jpeg");
					            
					            
					            changeManagementImage.setAddedDate(new Date());
					            changeManagementImage.setBase64Code(code.getBase64Code());
					            changeManagementImage.setLocationUrl(imagepath);
					           
					            File outputfile = new File(imagepath+"/"+imaageName+".jpeg");
						          
					            ImageIO.write(image, "jpeg", outputfile);
					            changeMangementService.saveChangeManagementImage(changeManagementImage);
					           
								 
					  }
				}
				
			  status.setCode(200);
			  status.setMessage("Image Upload successfully ");
			  
		} catch (Exception e) {
			e.printStackTrace();
			  status.setCode(500);
			  status.setMessage(e.toString());
		}
		return status;
	}
	
	
	
	
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Resource> downloadFileFromLocal(@PathVariable String fileName) {
		String fileBasePath="/"+imagepath+"/";
		Path path = Paths.get(fileBasePath + fileName);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
			
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	
	
/*	@RequestMapping(value = "/getSOPImage", method = RequestMethod.GET)
	public @ResponseBody List<SOPImage> getSOPImage(@RequestParam("sopId") int sopId,@RequestParam("stageNo") int stageNo) {
		List<SOPImage> list= new  ArrayList<SOPImage>();
		try {	
			list=imageService.getSOPImage(sopId,stageNo);
			String lastb64="";
			for(SOPImage image : list){
				if(image.getBase64Code().equalsIgnoreCase(lastb64)){
					
					System.out.println("SAME Base 64");
				}else{
					System.out.println("DIFF Base 64");
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/

}



