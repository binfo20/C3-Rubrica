/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import IOF.ReadWriteFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

/**
 *
 * @author Ale Vandelli
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField cognome;
    @FXML
    private TextField email;
    @FXML
    private List<Contatto> rubrica = new ArrayList<>();
    @FXML
    private ListView listView = new ListView();
    // private 

    @FXML
    private void AggiungiOnClickButtonAction(ActionEvent event) {
        Contatto c = new Contatto(cognome.getText(), email.getText());
        aggiungi(c);
        stampa();
        SalvaOnClickButtonAction(null);
    }

    @FXML
    private void SalvaOnClickButtonAction(ActionEvent event) {
        ReadWriteFile iof = new ReadWriteFile();
        iof.serializzaOggetto(rubrica, "rubrica.obj");
    }

    @FXML
    private void ApriOnClickButtonAction(ActionEvent event) {
        ReadWriteFile iof = new ReadWriteFile();
        this.rubrica = (List<Contatto>) iof.deserializzaOggetto("rubrica.obj");
        stampa();
    }

    @FXML
    private void RimuoviOnClickButtonAction(ActionEvent event) {
        rimuovi(cognome.getText());
        stampa();
        SalvaOnClickButtonAction(null);
    }

    @FXML
    private void RicercaOnClickButtonAction(ActionEvent event) {
        email.setText(cerca(cognome.getText()).getEmail());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ApriOnClickButtonAction(null);
    }

    public void aggiungi(Contatto contatto) {
        rubrica.add(contatto);
    }

    private void stampa() {
        listView.getItems().clear();
        for (Contatto contatto : rubrica) {
            listView.getItems().add("Cognome: " + contatto.getCognome() + " Mail: " + contatto.getEmail());
        }
    }

    public void rimuovi(String cognome) {
        rubrica.removeIf(n -> (n.getCognome().equals(cognome)));
    }

    public Contatto cerca(String cognome) {
        for (Contatto c : rubrica) {
            if (c.getCognome().equalsIgnoreCase(cognome)) {
                return c;
            }
        }
        return null;
    }

}
