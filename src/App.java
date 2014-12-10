
public class App {

	public static void main(String[] args) {
		StringDataHandler dataHandler = new StringDataFileHandler(Config.PATH_TO_DATA_FILE);
		
		JApp GUIapp = new JApp(dataHandler);
	}

}
