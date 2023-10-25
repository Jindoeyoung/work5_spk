package com.spk.api.controller.colele;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spk.api.entity.colele.RequestTablesEntity;
import com.spk.api.service.colele.ColumnElementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/col-element", produces = "application/json; charset=utf8")
public class ColumnElementControllers {
	private final ColumnElementService columnElementService;

    //============================================================
    //< SELECT - 시스템메뉴사용자 리스트 조회
    //============================================================	
	@PostMapping("")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getColumnElementList(@RequestBody RequestTablesEntity requestTablesEntity) throws Exception {
		return columnElementService.getColumnElementList(requestTablesEntity);
	}	
}
