/**
 * 
 */
package BIM_ACR.BIM_ACR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;

/**
 * @author lcont
 *
 */
public class Descomprimir {
	private static String prueba;
	private static String[] recordings;
	private static String INPUT_DIR = "/avaya/zipped/";
	private static String UNZIP_DIR = "/avaya/tars/";
	private static String CALLS_DIR = "/avaya/recordings";

	public Descomprimir() {
		String[] recordings = null;
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		final String dir = System.getProperty("user.dir");
		char currentChar = dir.charAt(1);
		if (currentChar == ':') {
			Descomprimir.INPUT_DIR = "D:\\Proyectos\\BIM_ACR\\zipped\\";
			Descomprimir.UNZIP_DIR = "D:\\Proyectos\\BIM_ACR\\tars\\";
			Descomprimir.CALLS_DIR = "D:\\Proyectos\\BIM_ACR\\recordings\\";
		}
		System.out.println("Carpeta para archivos de entrada " + INPUT_DIR);
		// listar(INPUT_DIR);
		Descomprimir.recordings = unziprecordings(INPUT_DIR, UNZIP_DIR, CALLS_DIR);
	}

	private static void listar(String dir) throws IOException {
		String mask = "Recordings*.tar.gz";
		PathMatcher maskMatcher = FileSystems.getDefault().getPathMatcher("glob:**/" + mask);
		Files.walk(Paths.get(dir)).filter(path -> maskMatcher.matches(path)).forEach(System.out::println);
	}

	private static String[] unziprecordings(String input, String temp, String output) throws IOException {
		String mask = "Recordings*.tar.gz";
		PathMatcher maskMatcher = FileSystems.getDefault().getPathMatcher("glob:**/" + mask);
		String[] recordings = Files.walk(Paths.get(input)).filter(path -> maskMatcher.matches(path))
				.map(String::valueOf).toArray(String[]::new);
		System.out.println("Records files: " + recordings.length);
		System.out.println(Arrays.toString(recordings));

		// para cada archivo recordings.tar.gz
		int i = 0;
		while (i < recordings.length) {
			try {
				// a) obtener nombre descomprimido en la carpeta destino
				System.out.println("tar.gz file " + i + ":" + recordings[i].toString());
				// b) descomprimir
				File inputFile = new File(recordings[i].toString());
				String outputFile = getFileName(inputFile, temp);
				System.out.println("tar file " + outputFile);
				File tarFile = new File(outputFile);
				tarFile = Descomprimir.deCompressGZipFile(inputFile, tarFile);

				// c) untar
				File destFile = new File(output);
				if (!destFile.exists()) {
					destFile.mkdir();
				}
				// Calling method to untar file
				Descomprimir.unTarFile(tarFile, destFile);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		return recordings;
	}

	private static String getFileName(File inputFile, String outputFolder) {
		return outputFolder + File.separator + inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.'));
	}

	private static void unTarFile(File tarFile, File destFile) throws IOException {
		FileInputStream fis = new FileInputStream(tarFile);
		TarArchiveInputStream tis = new TarArchiveInputStream(fis);
		TarArchiveEntry tarEntry = null;

		// tarIn is a TarArchiveInputStream
		while ((tarEntry = tis.getNextTarEntry()) != null) {
			File outputFile = new File(destFile + File.separator + tarEntry.getName());
			if (tarEntry.isDirectory()) {
				System.out.println("outputFile Directory ---- " + outputFile.getAbsolutePath());
				if (!outputFile.exists()) {
					outputFile.mkdirs();
				}
			} else {
				// File outputFile = new File(destFile + File.separator + tarEntry.getName());
				System.out.println("outputFile File ---- " + outputFile.getAbsolutePath());
				// outputFile.getParentFile().mkdirs();
				// outputFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(outputFile);
				IOUtils.copy(tis, fos);
				fos.close();
			}
		}
		tis.close();
		File oldfile = new File("D:\\Proyectos\\BIM_ACR\\output\\Recordings.html");
		String newname = destFile.getAbsolutePath();
		if (destFile.exists()) {
			System.out.println("Ya existe :" + destFile.getAbsolutePath());
		} else {
			newname = newname.concat(File.separator)
					.concat(tarFile.getName().substring(0, tarFile.getName().lastIndexOf('.'))).concat(".html");
			File torename = new File(newname);
			System.out.println("Rename " + oldfile.getAbsolutePath() + " to " + newname);
			boolean flag = oldfile.renameTo(torename);
			if (flag == true) {
				System.out.println("File Successfully Rename");
			}
			// if renameTo() return false then else block is executed
			else {
				System.out.println("Operation Failed");
			}
		}
	}

	private static File deCompressGZipFile(File gZippedFile, File tarFile) throws IOException {
		FileInputStream fis = new FileInputStream(gZippedFile);
		GZIPInputStream gZIPInputStream = new GZIPInputStream(fis);
		FileOutputStream fos = new FileOutputStream(tarFile);
		byte[] buffer = new byte[1024];
		int len;
		while ((len = gZIPInputStream.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		gZIPInputStream.close();
		return tarFile;
	}

}
