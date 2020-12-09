package gui;

import fachlogik.Bild;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mhdal001
 */
public class Bilddatenerfassung extends Stage {

    private Bild b;
    private boolean isBildValid;

    public Bilddatenerfassung(Bild b, Stage stage) {
        this.b = b;
        this.initOwner(stage);
        this.initModality(Modality.WINDOW_MODAL);
    }

    public void showView() {
        isBildValid = false;
        GridPane main_gp = new GridPane();
        main_gp.setHgap(5.0);
        main_gp.setVgap(5.0);
        main_gp.setPadding(new Insets(10.0));

        Label titel_L = new Label("Titel:");
        TextField titel_TF = new TextField();
        main_gp.addRow(1, titel_L, titel_TF);
        Label ort_L = new Label("Ort:");
        TextField ort_TF = new TextField();
        main_gp.addRow(2, ort_L, ort_TF);
        Label aufnahmejahr_L = new Label("Aufnahmejahr:");
        TextField aufnahmejahr_TF = new TextField();
        main_gp.addRow(3, aufnahmejahr_L, aufnahmejahr_TF);

        // alle TextFielder werden vergoeßern
        GridPane.setHgrow(titel_TF, Priority.ALWAYS);
        // alle Labels werden nach recht verscheben
        GridPane.setHalignment(titel_L, HPos.RIGHT);
        GridPane.setHalignment(ort_L, HPos.RIGHT);
        GridPane.setHalignment(aufnahmejahr_L, HPos.RIGHT);

        HBox knopfe_HB = new HBox();
        knopfe_HB.setPadding(new Insets(5.0));
        knopfe_HB.setSpacing(10);
        Button neu_B = new Button("Neu");
        Button abrechen_B = new Button("Abrechen");
        knopfe_HB.getChildren().addAll(neu_B, abrechen_B);
        knopfe_HB.setAlignment(Pos.CENTER);
        main_gp.addColumn(1, knopfe_HB);
        //old : main_gp.add(knopfe_HB, 1, 4);
        main_gp.setAlignment(Pos.CENTER);

        neu_B.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    int x = Integer.parseInt(aufnahmejahr_TF.getText());
                    if (x < 0) {
                        throw new NumberFormatException();
                    }
                    b.setJahr(Integer.parseInt(aufnahmejahr_TF.getText()));
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "keine zulaessige Zahl");
                    close();
                    return;
                }
                b.setOrt(ort_TF.getText());
                b.setTitel(titel_TF.getText());
                isBildValid = true;
                close();
            }

        });

        abrechen_B.setOnAction(e -> close());
        Scene s = new Scene(main_gp);
        this.setTitle("Bilderfassung");
        this.setScene(s);
        this.showAndWait();
    }

    public boolean getIsBildValie() {
        return isBildValid;
    }
}
