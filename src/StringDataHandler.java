import java.util.List;

public abstract class StringDataHandler {
	abstract public List<String> getData();
	abstract public boolean isLegalToAdd(String dataString);
	abstract public void addNewStringToData(String dataString);
}
