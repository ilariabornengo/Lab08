/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Flight;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	String imput=this.distanzaMinima.getText();
    	Integer imputI=0;
    	String s="";
    	try {
    		 imputI=Integer.parseInt(imput);
    	}catch(NumberFormatException e)
    	{
    		e.printStackTrace();
    	}
    	this.model.creaGrafo(imputI);
    	Integer contoV=this.model.getContoV();
    	Integer contoA=this.model.getContoA();
    	s+="Il numero dei vertici è :"+contoV+"\n";
    	s+="Il numero degli archi è: "+contoA+"\n";
    	Map<Integer,Flight> mappaVoli=new HashMap<Integer,Flight>();
    	for(Flight f1:this.model.getFlightDAO().loadAllFlights(imputI))
    	{
    		if(!mappaVoli.containsKey(f1.getFlightNumber()))
    		{
    			mappaVoli.put(f1.getFlightNumber(), f1);
    		}
    		
    		
    	}
    	for(Flight f:mappaVoli.values())
    	{
    		s+=this.model.getMappa().get(f.getOriginAirportId()).getAirportName()+"-"+this.model.getMappa().get(f.getDestinationAirportId()).getAirportName()+" "+f.getDistance()+"\n";
    	}
    	//System.out.println(s);
    	txtResult.setText(s);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
