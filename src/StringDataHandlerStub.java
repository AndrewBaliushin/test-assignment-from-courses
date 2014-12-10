import java.util.*;

/**
 * Simple stub for tests.
 */
public class StringDataHandlerStub extends StringDataHandler{
	
	List<String> stubData = new ArrayList<String>(Arrays.asList("stub1", "stub2"));

	@Override
	public List<String> getData() {
		return stubData;
	}

	@Override
	public boolean isLegalToAdd(String stringData) {
		return !stubData.contains(stringData);
	}

	@Override
	public void addNewStringToData(String dataString) {
		stubData.add(dataString);		
	}	
}
