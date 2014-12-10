import java.io.*;
import java.util.*;

public class StringDataFileHandler extends StringDataHandler {
	
	private String pathToFile;
	
	private List<String> dataStrings;
	
	public StringDataFileHandler(String pathToFile) {
		this.pathToFile = pathToFile;
		dataStrings = new ArrayList<>();
		parseDataFromFile();
	}

	@Override
	public List<String> getData() {
		parseDataFromFile();
		return dataStrings;
	}

	@Override
	public boolean isLegalToAdd(String dataString) {
		return isDataUniq(dataString);
	}

	@Override
	public void addNewStringToData(String dataString) {
		try(PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(pathToFile, true)))) {
		    out.println(dataString);
		}catch (IOException e) {
		    e.printStackTrace();
		}		
	}
	
	private void parseDataFromFile() {
		try(BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
			dataStrings = new ArrayList<>();
			
	        String line = br.readLine();

	        while (line != null) {
	            dataStrings.add(line);
	            line = br.readLine();
	        }
	    } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isDataUniq(String newData) {
		for (String data : dataStrings) {
			if(data.equalsIgnoreCase(newData)) {
				return false;
			}
		}
		return true;
	}

}
