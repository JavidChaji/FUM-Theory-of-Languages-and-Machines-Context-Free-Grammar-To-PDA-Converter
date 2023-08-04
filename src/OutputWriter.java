import java.io.*;

public class OutputWriter {

	public static void writeToFile(String filePath, String string) {

		File fileToWrite = new File(filePath);
		Writer writer;
		
		try {
			if (!fileToWrite.createNewFile()) {
				System.out.println("File overwritten.");
			}
			writer = new PrintWriter(fileToWrite, "UTF-8");
			writer.write(string);
			writer.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
