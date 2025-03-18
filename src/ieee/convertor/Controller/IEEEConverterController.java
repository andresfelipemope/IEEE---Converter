package ieee.convertor.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ieee.convertor.Facade.IEEEConverterFacade;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres Monsalve
 */

public class IEEEConverterController {
    
    private IEEEConverterFacade facade;
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cbxPrecision1"
    private ComboBox<String> cbxPrecision1; // Value injected by FXMLLoader

    @FXML // fx:id="cbxPrecision2"
    private ComboBox<String> cbxPrecision2; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConvertirToDecimal"
    private Button cmdConvertirToDecimal; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConvertirToIEEE"
    private Button cmdConvertirToIEEE; // Value injected by FXMLLoader

    @FXML // fx:id="txtExponente1"
    private TextField txtExponente1; // Value injected by FXMLLoader

    @FXML // fx:id="txtExponente2"
    private TextField txtExponente2; // Value injected by FXMLLoader

    @FXML // fx:id="txtMantisa1"
    private TextField txtMantisa1; // Value injected by FXMLLoader

    @FXML // fx:id="txtMantisa2"
    private TextField txtMantisa2; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumeroDecimal"
    private TextField txtNumeroDecimal; // Value injected by FXMLLoader

    @FXML // fx:id="txtResultado"
    private TextField txtResultado; // Value injected by FXMLLoader

    @FXML // fx:id="txtSigno1"
    private TextField txtSigno1; // Value injected by FXMLLoader

    @FXML // fx:id="txtSigno2"
    private TextField txtSigno2; // Value injected by FXMLLoader

    @FXML
    void convertirToDecimal(ActionEvent event) {
        try {
            if(cbxPrecision2.getValue() != null){
                if(!txtSigno2.getText().isEmpty() || !txtExponente2.getText().isEmpty()
                        || !txtMantisa2.getText().isEmpty()){
                    String[] numero = {txtSigno2.getText(), txtExponente2.getText(),
                                        txtMantisa2.getText()};
                    double rta = facade.convertirToDecimal(cbxPrecision2.getValue(), numero);
                    txtResultado.setText(String.valueOf(rta));
                }
                else{
                    JOptionPane.showMessageDialog(null, "Algunos de los campos estan vacios!!", 
                        "Erorr", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No ha seleccionado la precision!!", 
                        "Erorr", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erorr", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void convertirToIEEE(ActionEvent event) {
        try {
            if(cbxPrecision1.getValue() != null){
                if(!txtNumeroDecimal.getText().isEmpty()){
                    String[] rta = facade.convertirToIEEE(cbxPrecision1.getValue(), 
                            txtNumeroDecimal.getText());
                    txtSigno1.setText(rta[0]);
                    txtExponente1.setText(rta[1]);
                    txtMantisa1.setText(rta[2]);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No ha ingresado un numero", 
                        "Erorr", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No ha seleccionado la precision!!", 
                        "Erorr", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erorr", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void precision1(ActionEvent event) {
        txtSigno1.promptTextProperty().set("0");
        
        if(cbxPrecision1.getValue().equals("Sencilla (32 bits)")){
            txtExponente1.promptTextProperty().set("00000000");
            txtMantisa1.promptTextProperty().set("00000000000000000000000");
        }
        if(cbxPrecision1.getValue().equals("Doble (64 bits)")){
            txtExponente1.promptTextProperty().set("00000000000");
            txtMantisa1.promptTextProperty().set("0000000000000000000000000000000000000000000000000000"); 
            
            txtSigno1.clear();
            txtExponente1.clear();
            txtMantisa1.clear();
        }
    }

    @FXML
    void precision2(ActionEvent event) {
        txtSigno2.promptTextProperty().set("0");
        
        if(cbxPrecision2.getValue().equals("Sencilla (32 bits)")){
            txtExponente2.promptTextProperty().set("00000000");
            txtMantisa2.promptTextProperty().set("00000000000000000000000");
        }
        if(cbxPrecision2.getValue().equals("Doble (64 bits)")){
            txtExponente2.promptTextProperty().set("00000000000");
            txtMantisa2.promptTextProperty().set("0000000000000000000000000000000000000000000000000000");            
        }
        
        txtSigno2.clear();
        txtExponente2.clear();
        txtMantisa2.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cbxPrecision1 != null : "fx:id=\"cbxPrecision1\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";
        assert cbxPrecision2 != null : "fx:id=\"cbxPrecision2\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";
        assert cmdConvertirToDecimal != null : "fx:id=\"cmdConvertirToDecimal\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";
        assert cmdConvertirToIEEE != null : "fx:id=\"cmdConvertirToIEEE\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";
        assert txtExponente1 != null : "fx:id=\"txtExponente1\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";
        assert txtExponente2 != null : "fx:id=\"txtExponente2\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";
        assert txtMantisa1 != null : "fx:id=\"txtMantisa1\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";
        assert txtMantisa2 != null : "fx:id=\"txtMantisa2\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";
        assert txtNumeroDecimal != null : "fx:id=\"txtNumeroDecimal\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";
        assert txtResultado != null : "fx:id=\"txtResultado\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";
        assert txtSigno1 != null : "fx:id=\"txtSigno1\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";
        assert txtSigno2 != null : "fx:id=\"txtSigno2\" was not injected: check your FXML file 'IEEEConverterView.fxml'.";

        facade = new IEEEConverterFacade();
        
        cbxPrecision1.getItems().addAll("Sencilla (32 bits)", "Doble (64 bits)");
        cbxPrecision2.getItems().addAll("Sencilla (32 bits)", "Doble (64 bits)");               
    }

}
