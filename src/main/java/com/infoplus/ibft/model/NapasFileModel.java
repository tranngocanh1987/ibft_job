package com.infoplus.ibft.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class NapasFileModel extends FileModel{
	private String header;
	private List<NapasFileTransactionlModel> detail;
	private String trailer;
	
	private String trnDate;
}
