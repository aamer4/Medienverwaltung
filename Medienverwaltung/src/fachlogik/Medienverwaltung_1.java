package fachlogik;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.Year;
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activity.InvalidActivityException;
import javax.swing.JOptionPane;
import datenhaltung.PersistenzException;
import datenhaltung.SerialisierungIDao;

/**
 *
 * @author mhdal001
 */
public class Medienverwaltung_1 implements Serializable {

    // Array (nicht ArrayList!!!) geht auch aber wir müssen gröse deklarieren ; so lieber LinkedList 
    private List<Medium> list;
    private SerialisierungIDao sidoa;

    // wir können einfach hier list schreiben und im konstruktouzt zuweisen ob das list arrylist oder linkedlist sein sollte
    public Medienverwaltung_1() {
        this.list = new ArrayList();
    }
    
    public List<Medium> getList(){
        return new ArrayList<>(list);
    }

    public void aufnehmen(Medium med) {
        list.add(med);
    }

    // die Methode wurde für P09 ergänezt
    public void setAnzahlID() {
        if (this.list != null) {
            Medium.anzahlID = ((Medium) this.list.get(this.list.size() - 1)).getId();
        }
    }

    public void zeigeMedien(OutputStream stream) {
        /*
    public void zeigeMedien() {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Medium) it.next()).druckeDaten();
        }
        // foreach schleife
        //for (Object o : list) {
        //    ((Medium) o).druckeDaten();
        //}
    }*/
        // Praktikum 03
        // hier wird die Methode compareTo in Medium aufgerufen
        Collections.sort(list);
        Iterator<Medium> it = list.iterator();
        while (it.hasNext()) {
            ((Medium) it.next()).druckeDaten(stream);
        }
    }
    
        /** sucheNeuesMedium
        foreach schleife
        for (Object o : list) {
            if (((Medium) o).alter() < jung.alter()) {
                jung = (Medium) o;
            }
        }
        *wird auskommentiert wegen P09
        *jung.druckeDaten(stream);
         */
    
    public Medium sucheNeuesMedium() { // parameter wurde für P09 gelöscht
        if (list.size() == 0){
            System.out.println("Es gibt keine Medien");
            return null; //  null für P09
        }
        Medium jung = (Medium) list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (jung.alter() > ((Medium) list.get(i)).alter()) {
                jung = (Medium) list.get(i);
            }
        }
        return jung; // für P09
    }

    public void berechneErscheinungsjahr() {
        int durchSchnitt = 0;
        if (list.isEmpty()) {
            System.out.println("Durch Schnitt: " + durchSchnitt);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            durchSchnitt += ((Medium) list.get(i)).getJahr();
        }
        //System.out.println("Durch Schnitt: " + durchSchnitt / list.size());
        JOptionPane.showMessageDialog(null,"Durch Schnitt: " + durchSchnitt / list.size());
    }

    public Iterator<Medium> iterator() {
        return list.iterator();
    }

    public boolean dateiSchreiben() {
        boolean vaild = false;
        do {
            String eingabe = JOptionPane.showInputDialog(null, " Geben Sie ein Dateiname ein");
            if (eingabe == null) 
                return false;
            File f = new File(eingabe);
            FileOutputStream stream = null;
            try {
                if (eingabe.isEmpty()) {
                    throw new EmptyFilenameException();
                }
                stream = new FileOutputStream(f);
                this.zeigeMedien(stream);
                vaild = true;
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (EmptyFilenameException e) {
                int input = JOptionPane.showConfirmDialog(null, eingabe, "   möchten Sie neu Versuchen ", JOptionPane.YES_NO_OPTION);
                if (input == JOptionPane.YES_OPTION) {
                    vaild = false;
                } else if (input == JOptionPane.NO_OPTION) {
                    eingabe = null;
                    return false;
                }
            } finally {
                try {
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } while (!vaild);
        return true;
    }

    public void aufnehmenAudio() {
        String eingabe = JOptionPane.showInputDialog(null, "Titel");
        if (eingabe == null) {
            return;
        }
        String eingabe2 = JOptionPane.showInputDialog(null, "Interpret");
        if (eingabe2 == null) {
            return;
        }
        int x;
        try {
            x = setJahr();
        } catch (InvalidActivityException e) {
            return;
        }
        int y;
        try {
            y = setDauer();
        } catch (InvalidActivityException e) {
            return;
        }
        Audio au = new Audio(eingabe, eingabe2, x, y);
        aufnehmen(au);
    }

    public int setJahr() throws InvalidActivityException {
        boolean valid = false;
        int x = 0;
        while (!valid) {
            try {
                String jahr = JOptionPane.showInputDialog(null, "Jahr");
                if (jahr == null) {
                    throw new InvalidActivityException("abgebrochen");
                }
                x = Integer.parseInt(jahr);
                if (x < 1800 || x > Year.now().getValue()) {
                    throw new NumberFormatException();
                }
                valid = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Bitte geben Sie ein zulaessiges Jahr");
            }
        }
        return x;
    }

    public int setDauer() throws InvalidActivityException {
        boolean valid = false;
        int y = 0;
        while (!valid) {
            try {
                String dauer = JOptionPane.showInputDialog(null, "Bitte geben Sie die Dauer");
                if (dauer == null) {
                    throw new InvalidActivityException("abgebrochen");
                }
                y = Integer.parseInt(dauer);
                if (y <= 0) {
                    throw new NumberFormatException();
                }
                valid = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Bitte geben Sie eine zulaessige Zahl");
            }
        }
        return y;
    }

    public void aufnehmenBild() {
        String eingabe = JOptionPane.showInputDialog(null, "Titel");
        if (eingabe == null) {
            return;
        }
        String eingabe2 = JOptionPane.showInputDialog(null, "Ort");
        if (eingabe2 == null) {
            return;
        }
        int x;
        try {
            x = setJahr();
        } catch (InvalidActivityException e) {
            return;
        }
        Bild bi = new Bild(eingabe, x, eingabe2);
        aufnehmen(bi);
    }

    public void speichern() {
        /*
        //The transient keyword in Java is used to indicate that a field should not be part of the serialization (which means saved, like to a file) process.
        File file = new File("H:\\Serialisierung.ser");

        /* // klassische Try-catch-finally
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mv);
        } catch (IOException e) {
            System.out.println("Fehler bei der Serialisierung");
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         
        // Try-with resources/*
        try (FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(mv);
        } catch (IOException e) {
            e.printStackTrace();
        } // finally fäööt weg (close wird automatisch aufgerufen)
    }*/
        try {
                sidoa.speichern(list);    
        } catch (PersistenzException e) {     }   
    }

    public void laden() {
        
            /*
            File sfile = new File("H:\\Serialisierung.ser");
            
            try (FileInputStream fis = new FileInputStream(sfile);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            mv = (Medienverwaltung_1) ois.readObject();
            } catch (IOException e) {
            System.out.println("IO-Fehler bei der Deserialisierung");
            } catch (ClassNotFoundException e) {
            System.out.println("Fehler: class-Datei nicht gefunden");
            }
            // der code wurde ab hier zu MV verschoben da die list zu private gewehcselt
            //if (mv.list != null) {
            //  Medium.anzahlID = ((Medium) mv.list.get(mv.list.size() - 1)).getId();
            //}
            
            // pseudocode
            // Medium.anzahlID = /* letzte id ermitteln */
            /*setAnzahlID();    // P09*/
         try {
            list = sidoa.laden();
            setAnzahlID();
        } catch (PersistenzException ex) {
            Logger.getLogger(Medienverwaltung_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
