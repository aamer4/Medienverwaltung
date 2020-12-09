package gui;

import fachlogik.Audio;
import fachlogik.Bild;
import fachlogik.Medienverwaltung_1;
import fachlogik.Medium;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author mhdal001
 */
public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        Medienverwaltung_1 mv = new Medienverwaltung_1();
        MenuBar mb = new MenuBar();
        ListView<Medium> lv = new ListView<>();
        ObservableList listModel = lv.getItems();

        Menu datei = new Menu("Datei");
        MenuItem laden = new MenuItem("Laden");
        MenuItem speichern = new MenuItem("Speichern");
        MenuItem mId = new MenuItem("Medienliste in Datei");
        MenuItem beenden = new MenuItem("Beenden");
        datei.getItems().addAll(laden, speichern, new SeparatorMenuItem(), mId, new SeparatorMenuItem(), beenden);
        Menu medium = new Menu("Medium");
        MenuItem audioNeu = new MenuItem("Audio neu");
        MenuItem bildNeu = new MenuItem("Bild neu");
        medium.getItems().addAll(audioNeu, bildNeu);
        Menu anzeige = new Menu("Anzeige");
        MenuItem erscheinungsJahr = new MenuItem("Erscheinungsjahr");
        MenuItem neuestesMedium = new MenuItem("neuestes Medium");
        anzeige.getItems().addAll(erscheinungsJahr, neuestesMedium);
        mb.getMenus().addAll(datei, medium, anzeige);

        laden.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                mv.laden();
            }
        });
        speichern.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent g) {
                mv.speichern();
            }
        });
        mId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent g) {
                mv.dateiSchreiben();
            }
        });
        audioNeu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent g) {
                Audio a = new Audio();
                Audioatenerfassung ade = new Audioatenerfassung(a, primaryStage);
                ade.showView();
                if(ade.isAudioValid()) {
                    mv.aufnehmen(a);
                    syncLists(listModel,mv);
                }
            }
        });
        bildNeu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent g) {
                Bild b = new Bild();
                Bilddatenerfassung bde = new Bilddatenerfassung(b, primaryStage);
                bde.showView();
                if(bde.getIsBildValie())
                    mv.aufnehmen(b);
                syncLists(listModel, mv);
            }
        });
        erscheinungsJahr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                mv.berechneErscheinungsjahr();
            }
        });
        neuestesMedium.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                JOptionPane.showMessageDialog(null, mv.sucheNeuesMedium());
            }
        });

        BorderPane bp = new BorderPane();
        bp.setCenter(lv);
        bp.setTop(mb);
        Scene scene = new Scene(bp, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Medienverwaltung");
    }
    /**
    public void refresh() {
        lv.getItems().clear();
        Iterator<Medium> it = mv.iterator();
        while (it.hasNext()) {
            lv.getItems().add(it.next());
        }
    }
     */

    public void syncLists(ObservableList<Medium> listModel ,Medienverwaltung_1 mv ) {
        // listview verwendet toString methode zu darstellung
        //listModel.clear();
        /*
        Iterator<Medium> it = mv.iterator();
        while (it.hasNext()) {
            listModel.add(it.next());
        }
        */
        listModel.setAll(mv.getList());
        // Elemente zu model hinzufügen (Medium.toString)
    }

    public static void main(String[] args) {
        launch(args);
        /** 
        *P09
        *wäre list nicht private , würde keine methode für iterator brauchen
        *Medienverwaltung_1 mv = new Medienverwaltung_1();
        *Iterator<Medium> it = mv.list.iterator();
        */
    }
}

/**
 * return raus der ganzen Methode
 * break aus der außen {}
 * 
 * lambda Ausdrueke
 * nur fur interface mit einer Methode
 * 
 * showAndwait waten bis alles geklappt
 */