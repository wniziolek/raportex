package pl.agh.edu.raportex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.ss.usermodel.Workbook;



public class WorkbookConverter {
	public static ArrayList<Record> getRecordsFromManyFiles(ArrayList<String> paths) throws IOException {
		ArrayList<Record> dane = new ArrayList<Record>();
		for (String sciezka : paths) {
			dane.addAll(getRecordsFromOneFile(sciezka));
		}
		return dane;
	}
	

	public static ArrayList<Record> getRecordsFromOneFile(String pathname) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(pathname));
		File file = new File(pathname);
		File monthDirectory = new File(file.getAbsoluteFile().getParent());
		File yearDirectory = new File(monthDirectory.getAbsoluteFile().getParent());
		String year = yearDirectory.getName();
		String month = monthDirectory.getName();
		String fileName=file.getName();
		String sname = fileName.split("//.")[0].split("_")[0]+" "+fileName.split("//.")[0].split("_")[1];
		ArrayList<Record> data = new ArrayList<Record>();
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			HSSFSheet sheet = wb.getSheetAt(i);
			String projectName = sheet.getSheetName();
			for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
				HSSFRow wiersz = sheet.getRow(j);
				if (wiersz.getPhysicalNumberOfCells() > 2) {
					String task = wiersz.getCell(1).getStringCellValue();
					double thetime = wiersz.getCell(2).getNumericCellValue();
					Record rek = new Record(year, month, sname, projectName, task, thetime, pathname);
					data.add(rek);
				}
			}
		}
		return data;
	}
}