package com.infoplus.ibft.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infoplus.ibft.common.AppConst;
import com.infoplus.ibft.model.FileModel;
import com.infoplus.ibft.model.NapasFileNameModel;

@Service
public class FileHandler extends BaseService {

	FileModel model = new FileModel();

	public boolean isEmptyDirectory(String directory) {
		if (directory.isEmpty())
			return true;

		File dir = new File(directory);
		File[] dirContents = dir.listFiles();

		if (dirContents != null && dirContents.length > 1) {
			return false;
		}
		return true;
	}

	public List<File> getFilesFromDirectory(String directory) {
		try {
			File dir = new File(directory);
			File[] dirContents = dir.listFiles();

			if (dirContents != null && dirContents.length > 1) {
				List<File> files = new ArrayList<File>();

				for (File file : dirContents) {
					if (file.getName().contains(AppConst.DAT_TYPE)) {
						files.add(file);
					}
				}
				logger.info("Number files: " + files.size());
				return files;
			} else {
				logger.info("No file found " + directory);
				return null;
			}
		} catch (Exception ex) {
			logger.error("Error!" + ex.toString());
			return null;
		}
	}

	public FileModel readFile(String fileDir) {
		List<String> records = new ArrayList<String>();

		try {
			File file = new File(fileDir);

			model.setReceivedDate(new Date());
			model.setNapasFileName(new NapasFileNameModel(file.getName()));

			BufferedReader reader = new BufferedReader(new FileReader(fileDir));
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}
			model.setContents(records);

			reader.close();

		} catch (Exception ex) {
			System.err.format("Exception occurred trying to read '%s'.", fileDir);
			ex.printStackTrace();
			return null;
		}
		return model;
	}

	public FileModel readFile(File file) {
		List<String> records = new ArrayList<String>();

		try {
			model.setReceivedDate(new Date());
			model.setNapasFileName(new NapasFileNameModel(file.getName()));

			BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}
			model.setContents(records);

			reader.close();

		} catch (Exception ex) {
			System.err.format("Exception occurred trying to read '%s'.", file.getPath());
			ex.printStackTrace();
			return null;
		}
		return model;
	}
	
	/*
	 * public void writeFile(List<String> data, String fileDir) { try { File f = new
	 * File(fileDir); FileWriter fw = new FileWriter(f);
	 * 
	 * for (String record : data) { fw.write(record +
	 * System.getProperty("line.separator")); } fw.close();
	 * 
	 * } catch (Exception ex) {
	 * System.err.format("Exception occurred trying to write '%s'.", fileDir);
	 * ex.printStackTrace(); } }
	 */
	
	public boolean writeFile(List<String> data, String fileDir) {
		try {
			File f = new File(fileDir);
			FileWriter fw = new FileWriter(f);

			for (String record : data) {
				fw.write(record + System.getProperty("line.separator"));
			}
			fw.close();
			return true;
		} catch (Exception ex) {
			System.err.format("Exception occurred trying to write '%s'.", fileDir);
			ex.printStackTrace();
		}
		return false;
	}

	public void moveFile(String oldDir, String newDir) {
		File file = new File(oldDir);
		File newFile = new File(newDir);
		newFile.delete();

		// renaming the file and moving it to a new location
		if (file.renameTo(new File(newDir))) {
			// if file copied successfully then delete the original file
			file.delete();
			System.out.println("File moved successfully");
		} else {
			System.out.println("Failed to move the file");
		}
	}

}
