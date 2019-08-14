package com.infoplus.ibft.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infoplus.ibft.model.FileModel;
import com.infoplus.ibft.rabbitmq.Publisher;

@Service
public class NapasReconciliationService {
	final Logger logger = LoggerFactory.getLogger(NapasReconciliationService.class);

	@Value("${file.inputDir}")
	private String inputDir;

	@Value("${file.backupDir}")
	private String backupDir;

	@Value("${file.outputDir}")
	private String outputDir;

	@Autowired
	Publisher publisher;
	
	@Autowired
	FileHandler fileHandler;

	public void readNapsReconciliationFiles() {
		List<File> inputFiles = new ArrayList<File>();
		List<FileModel> lstFileModel = new ArrayList<FileModel>();
		FileModel fileModel;

		try {
			// check file in inputDir
			if (fileHandler.isEmptyDirectory(inputDir))
				logger.debug("Khong tim thay file");
			else {
				inputFiles = fileHandler.getFilesFromDirectory(inputDir);

				if (inputFiles != null && inputFiles.size() > 0) {
					for (File inputFile : inputFiles) {
						logger.debug("Filename: " + inputFile.getName());

						fileModel = fileHandler.readFile(inputFile.getPath());

						String fileBackupDir = backupDir + inputFile.getName();
						fileHandler.moveFile(inputFile.getPath(), fileBackupDir);

						String outFileName = outputDir + inputFile.getName();
						fileHandler.writeFile(fileModel, outFileName);

						lstFileModel.add(fileModel);
					}

					// send to RabbitMQ
					publisher.produceMsg("Test");
				}
			}
		} catch (Exception ex) {
			logger.debug("Error: " + ex.toString());
		}
	}
}
