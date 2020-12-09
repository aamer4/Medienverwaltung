package gui;

import fachlogik.Audio;
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

/**
 *
 * @author mhdal001
 */
public class Audioatenerfassung extends Stage {

    private Audio a;
    boolean isAudioValid;

    public Audioatenerfassung(Audio a, Stage stage) {
        this.a = a;
        this.initOwner(stage);
        this.initModality(Modality.WINDOW_MODAL);
    }

    public void showView() {
        isAudioValid = false;
        GridPane main_gp = new GridPane();
        main_gp.setHgap(5.0);
        main_gp.setVgap(5.0);
        main_gp.setPadding(new Insets(10.0));

        Label titel_L = new Label("Titel:");
        TextField titel_TF = new TextField();
        main_gp.addRow(1, titel_L, titel_TF);
        Label interpret_L = new Label("Interpret:");
        TextField interpret_TF = new TextField();
        main_gp.addRow(2, interpret_L, interpret_TF);
        Label aufnahmejahr_L = new Label("Aufnahmejahr:");
        TextField aufnahmejahr_TF = new TextField();
        main_gp.addRow(3, aufnahmejahr_L, aufnahmejahr_TF);
        Label dauer_L = new Label("Dauer:");
        TextField dauer_TF = new TextField();
        main_gp.addRow(4, dauer_L, dauer_TF);

        // alle TextFielder werden vergoeßern
        GridPane.setHgrow(titel_TF, Priority.ALWAYS);
        // alle Labels werden nach recht verscheben
        GridPane.setHalignment(titel_L, HPos.RIGHT);
        GridPane.setHalignment(interpret_L, HPos.RIGHT);
        GridPane.setHalignment(aufnahmejahr_L, HPos.RIGHT);
        GridPane.setHalignment(dauer_L, HPos.RIGHT);

        HBox knopfe_HB = new HBox();
        knopfe_HB.setPadding(new Insets(5.0));
        knopfe_HB.setSpacing(10);
        Button neu_B = new Button("Neu");

        neu_B.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    int x = Integer.parseInt(aufnahmejahr_TF.getText());
                    if (x < 1800) {
                        throw new NumberFormatException();
                    }
                    a.setJahr(x);
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "keine zulaessige Zahl");
                    close();
                    return;

                }

                try {
                    int y = Integer.parseInt(dauer_TF.getText());
                    if (y < 0) {
                        throw new NumberFormatException();
                    }
                    a.setDauer(y);
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "keine zulaessige Zahl");
                    close();
                    return;
                }
                a.setTitel(titel_TF.getText());
                a.setInterpret(interpret_TF.getText());
                isAudioValid = true;
                close();
            }
        });

        Button abrechen_B = new Button("Abrechen");
        abrechen_B.setOnAction(e -> close());

        knopfe_HB.getChildren().addAll(neu_B, abrechen_B);
        knopfe_HB.setAlignment(Pos.CENTER);
        main_gp.addColumn(1, knopfe_HB);

        main_gp.setAlignment(Pos.CENTER);

        Scene s = new Scene(main_gp);
        this.setTitle("Audioerfassung");
        this.setScene(s);
        this.showAndWait();
    }

    public boolean isAudioValid() {
        return isAudioValid;
    }
}
