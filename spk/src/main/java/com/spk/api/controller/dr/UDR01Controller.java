package com.spk.api.controller.dr;

//import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spk.api.entity.udr.UDR01Entity;
import com.spk.api.entity.udr.UDR01EntityResult;
import com.spk.api.service.udr.UDR01Service;
import com.spk.api.util.ReturnException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/udr/udr01", produces = "application/json; charset=utf8")
public class UDR01Controller {
	private final UDR01Service udr01Service;

    //============================================================
    //< SELECT (List)
    //============================================================	
	@PostMapping("/getRegistAmtList")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getRegistAmtList(@RequestBody UDR01Entity pUdr01Entity) throws Exception {
		return udr01Service.getRegistAmtList(pUdr01Entity);
	}
	
    //============================================================
    //< INSERT
    //============================================================
	@PostMapping("/insertRegistAmt")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String insertRegistAmt(@RequestBody UDR01Entity pUdr01Entity) throws Exception {
		return udr01Service.insertRegistAmt(pUdr01Entity);
	}
	
    //============================================================
    //< UPDATE
    //============================================================
	@PutMapping("/updateRegistAmt")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String updateRegistAmt(@RequestBody UDR01Entity pUdr01Entity) throws Exception {
		return udr01Service.updateRegistAmt(pUdr01Entity);
	}
	
    //============================================================
    //< DELETE
    //============================================================
	@DeleteMapping("/deleteRegistAmt")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String deleteRegistAmt(@RequestBody UDR01Entity pUdr01Entity) throws Exception {
		return udr01Service.deleteRegistAmt(pUdr01Entity);
	}
	
    //============================================================
    //< SAVE
    //============================================================
	@PostMapping("/saveRegistAmt")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String saveRegistAmt(@RequestBody UDR01EntityResult pUdr01Entity) throws Exception {
		try{
			return udr01Service.saveRegistAmt(pUdr01Entity);
		} catch (ReturnException e) {
			return (String)e.getValue();
		}
	}		
	
//	@PostMapping("/saveRegistAmt")
//	@CrossOrigin(origins = "*", allowedHeaders = "*")
//	public String saveRegistAmt(@RequestBody UDR01EntityResult pUdr01Entity) throws Exception {
////	public String saveRegistAmt(@RequestBody List<UDR01Entity> pUdr01Entity) throws Exception {
//		return udr01Service.saveRegistAmt(pUdr01Entity);
//	}	
}
