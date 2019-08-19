package com.infoplus.ibft.service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoplus.ibft.common.AppConst;
import com.infoplus.ibft.model.FileModel;

@Service
public class FileHandler extends BaseService{
	
	@Autowired
	FileModel model;
	
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
		File dir = new File(directory);
		File[] dirContents = dir.listFiles();
		List<File> files = new ArrayList<File>();

		for (File file : dirContents) {
			if (file.getName().contains(AppConst.DAT_TYPE)) {
				files.add(file);
			}
		}

		return files;
	}

	/*
	 * public FileModel readFile(String fileDir) { List<String> records = new
	 * ArrayList<String>();
	 * 
	 * try { model.setFileInfo(new File(fileDir));
	 * 
	 * model.setReceivedDate(new Date());
	 * 
	 * BufferedReader reader = new BufferedReader(new FileReader(fileDir)); String
	 * line; while ((line = reader.readLine()) != null) { records.add(line); }
	 * model.setContents(records);
	 * 
	 * reader.close();
	 * 
	 * } catch (Exception ex) {
	 * System.err.format("Exception occurred trying to read '%s'.", fileDir);
	 * ex.printStackTrace(); return null; } return model; }
	 */

	public void writeFile(FileModel model, String outFileName) {
		try {
			File f = new File(outFileName);
			FileWriter fw = new FileWriter(f);

			for (String record : model.getContents()) {
				fw.write(record + System.getProperty("line.separator"));
			}
			fw.close();

		} catch (Exception ex) {
			System.err.format("Exception occurred trying to write '%s'.", model.getFileInfo().getPath());
			ex.printStackTrace();
		}
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
