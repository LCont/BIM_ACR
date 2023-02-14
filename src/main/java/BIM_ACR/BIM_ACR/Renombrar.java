package BIM_ACR.BIM_ACR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Renombrar {
	private static String INPUT_DIR = "/avaya/input/";
	private static String UNZIP_DIR = "/avaya/temp/";
	private static String CALLS_DIR = "/avaya/output";

	//public String CALLS_DIR = "D:\\Proyectos\\BIM_ACR\\z3output\\";
	//public String INPUT_DIR = "D:\\Proyectos\\BIM_ACR\\z1input\\";
	//public String UNZIP_DIR = "D:\\Proyectos\\BIM_ACR\\z2temp\\";

	public Renombrar() {
		
		// TODO Auto-generated constructor stub
	}
	public String getinputdir() {
		return INPUT_DIR;
	}

	public static void main(String[] args) throws IOException {
		final String dir = System.getProperty("user.dir");
        char currentChar = dir.charAt(1);
		if (currentChar == ':') {
			Renombrar.INPUT_DIR = "D:\\Proyectos\\BIM_ACR\\input\\";
			Renombrar.UNZIP_DIR = "D:\\Proyectos\\BIM_ACR\\temp\\";
			Renombrar.CALLS_DIR = "D:\\Proyectos\\BIM_ACR\\output\\";
		} else {
			System.out.println("Linux " + Renombrar.CALLS_DIR);
		}
		
		String mask = "*.html";
		PathMatcher maskMatcher = FileSystems.getDefault().getPathMatcher("glob:**/" + mask);
		String[] recordings = Files.walk(Paths.get(Renombrar.CALLS_DIR)).filter(path -> maskMatcher.matches(path))
				.map(String::valueOf).toArray(String[]::new);
		int i = 0;
		while (i < recordings.length) {
			System.out.println("HTML Files " + i + ": " + recordings[i].toString());
			i++;
		}
		//HTMLParser("D:\\Proyectos\\BIM_ACR\\z3output\\Recordings.html");
	}

	
	private static void HTMLParser(String file) {

		Document htmlFile = null;
		try {
			htmlFile = Jsoup.parse(new File(file), "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		String title = htmlFile.title();
		System.out.println("Title : " + title);

		Element table = htmlFile.body().getElementById("recordings");
		System.out.println("table: " + table.text());

		Elements rows = table.select("tr");
		System.out.println("rows: " + rows.size());
		for (int i = 0; i < rows.size(); i++) {
			Element row = rows.get(i);
			Elements cols = row.select("TD");
			for (int ii = 0; ii < cols.size(); ii++) {
				if (ii == 9) {
					String audioriginal = cols.get(ii).getElementsByTag("button").attr("onclick");
					System.out.println(i + ":" + ii + "|"
							+ audioriginal.substring(audioriginal.indexOf("\'") + 1, audioriginal.lastIndexOf("\'")));
					RenameFile(audioriginal.substring(audioriginal.indexOf("\'") + 1, audioriginal.lastIndexOf("\'")),
							"2023_" + i + "_" + ii + "_" + cols.get(5).text() + cols.get(6).text() + ".opus");
				} else {
					System.out.println(i + ":" + ii + "|" + cols.get(ii).text());
				}
			}

		}

	}

	private static void RenameFile(String oldFileName, String newFileName) {
		String oldString = "D:\\Proyectos\\BIM_ACR\\z3output\\" + oldFileName;
		String newString = "D:\\Proyectos\\BIM_ACR\\z3output\\" + newFileName;

		Path oldFile = Paths.get(oldString);

		// Files newFile = new File(newString);
		System.out.println("Renaming " + oldFile.toString() + " to " + newFileName);
		try {
			Files.move(oldFile, oldFile.resolveSibling(newFileName));
			System.out.println("File Successfully Rename");
		} catch (IOException e) {
			System.out.println("operation failed");
			System.out.println(e);
		}

		/*
		 * if (oldFile.renameTo(newFile)) {
		 * 
		 * System.out.println("File renamed successfully"); } else {
		 * System.out.println("Rename failed"); }
		 */
	}


}