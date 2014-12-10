import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class TableModelForStringData extends AbstractTableModel{
	
	private StringDataHandler dataHandler;
	
	private List<String> tableContentData;
			
	private String[] columnNames = new String[]{"#", Localization.TABLE_MODEL_DATA_COLUMN_NAME};

	public TableModelForStringData(StringDataHandler dataHandler) {
		this.dataHandler = dataHandler;
		tableContentData = new ArrayList<>();
		refreshData();
	}
	
	public void refreshData() {
		tableContentData = dataHandler.getData();
	}
	
	public void replaceTableDataWith(List<String> data) {
		tableContentData = data;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return tableContentData.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex >= tableContentData.size() || rowIndex < 0) {
			return null;
		}
		
		switch (columnIndex) {
		case 0:
			return (rowIndex + 1); //0-base to 1-base
		case 1:
			return tableContentData.get(rowIndex);
		default:
			return null;
		}
	}

}
