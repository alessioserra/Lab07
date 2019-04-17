package it.polito.tdp.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.Model;
import it.polito.tdp.model.PowerOutages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {
	
	Model model = new Model();
	
	public void setModel(Model model) {
		this.model=model;
		this.getValoriBoxMese();
	}
	
	public void getValoriBoxMese() {
	    choiceBox.getItems().addAll(model.getNercList());
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnAnalysis;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalysis(ActionEvent event) {

    	txtResult.clear();
    	
    	int anni = Integer.parseInt(txtYears.getText());
    	int ore = Integer.parseInt(txtHours.getText());
    	String nerc = choiceBox.getValue();
    	
    	List<PowerOutages> soluzione = model.risolviProblema(nerc,ore,anni);
    	if (soluzione!=null) {
    	for (PowerOutages p : soluzione) {
    		txtResult.appendText(p.getDataInizio()+" - "+p.getDataFine()+" - "+p.getPersoneCoinvolte()+"\n");
    	}
    	txtResult.appendText("Numero totale persone coinvolte: "+model.getBestPeopleAffected());
    	}
    	else txtResult.appendText("Soluzione non trovata");
    }

    @FXML
    void initialize() {
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnAnalysis != null : "fx:id=\"btnAnalysis\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }
}
