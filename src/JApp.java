import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class JApp extends JFrame{
	
	StringDataHandler dataHandler;
	
	private JTextField inputField;
	private JButton addStringButton;
	private JButton clearInputFieldButton;
	private JButton exitButton;
	private JTextArea statusMessage;
	
	private TableModelForStringData tableModel;

	public JApp(StringDataHandler dataHandler){
		super(Config.APP_TITLE);
		
		this.dataHandler = dataHandler;
		createTableModelWithDataHandler();
		
		createLayout();
		
		addInputAndControllSection();
		addStatusSection();
		addDataViewSection();
		
		addButtonListners();
		
		statusUpdate(StatusMessages.WELCOME);
		
		fireStandartJFrameRoutines();
	}
	
	private void fireStandartJFrameRoutines() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private void createTableModelWithDataHandler() {
		tableModel = new TableModelForStringData(dataHandler);
	}

	private void refreshDataAndTableModel() {
		tableModel.refreshData();
		tableModel.fireTableDataChanged();
	}

	private void createLayout() {
		setLayout(new BorderLayout(4, 4));
	}
	
	private void addInputAndControllSection() {
		JPanel inputFieldPanel = new JPanel();
		inputFieldPanel.setLayout(new FlowLayout());
		
		JLabel label = new JLabel(Localization.INPUT_FIELD_LABEL_TEXT);
	    inputFieldPanel.add(label);
	    
	    inputField = new JTextField(Config.INPUT_FIELD_LENGTH);
	    inputFieldPanel.add(inputField);
	    
	    addStringButton = new JButton(Localization.ADD_BUTTON_TEXT);
	    inputFieldPanel.add(addStringButton);
	    
	    clearInputFieldButton = new JButton(Localization.CLEAR_INPUT_FIELD_BTN_TEXT);
	    inputFieldPanel.add(clearInputFieldButton);
	    
	    exitButton = new JButton(Localization.EXIT_BUTTON_TEXT);
	    inputFieldPanel.add(exitButton);
	    
	    add(inputFieldPanel, BorderLayout.NORTH);
	}
	
	private void addStatusSection() {
		statusMessage = new JTextArea();
		statusMessage.setEditable(false);
		statusMessage.setMargin(new Insets(12, 4, 12, 4));
		statusMessage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		add(statusMessage, BorderLayout.CENTER);
	}
	
	private void addDataViewSection() {
		JTable table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setMaxWidth(Config.WIDTH_OF_ROW_NUMBER_COLUMN);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.SOUTH);		
	}
	
	private void addButtonListners() {
		addInputFieldSubmitListner();
		addClearButtonListner();
		addExitButtonListner();
	}
	
	private void addInputFieldSubmitListner() {
		addStringButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tryToWriteDataAndUpdateGUI();
			}
		});
		
		inputField.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	tryToWriteDataAndUpdateGUI();
		    }
		});
		
	}
	
	private void addClearButtonListner() {
		clearInputFieldButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearInputField();
			}
		});
	}
	
	private void addExitButtonListner() {
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exitApp();
			}
		});
	}
	
	private void tryToWriteDataAndUpdateGUI() {
		String status = tryWriteDataFromInputAndReturnStatus();
		
		if(status == StatusMessages.SUCCES) {
			refreshDataAndTableModel();
			clearInputField();
		} 
		
		statusUpdate(status);		
	}
	
	private String tryWriteDataFromInputAndReturnStatus() {
		String newData = inputField.getText();
		if(newData.equals("")) {
			return StatusMessages.EMPTY_DATA_ERR;
		} else if(dataHandler.isLegalToAdd(newData)) {
			dataHandler.addNewStringToData(newData);
			return StatusMessages.SUCCES;
		} else {
			return StatusMessages.FAIL;
		}
	}
	
	private void statusUpdate(String msg) {
		String newStatus = String.format(StatusMessages.FORMAT, msg);
		statusMessage.setText(newStatus);
	}
	
	private void clearInputField() {
		inputField.setText("");
	}
	
	private void exitApp() {
		System.exit(0);
	}

}
