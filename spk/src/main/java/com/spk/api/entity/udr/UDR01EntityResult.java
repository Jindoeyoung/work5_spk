package com.spk.api.entity.udr;

import java.util.List;

import lombok.Data;

@Data
public class UDR01EntityResult {
	private String apikey;
	private String user_id;
	List<UDR01Entity> data;
}
