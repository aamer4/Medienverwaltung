/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenhaltung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import fachlogik.Medium;

/**
 *
 * @author mhdal001
 */
public class SerialisierungIDao implements IDao {

    @Override
    public void speichern(List<Medium> liste) throws PersistenzException {
        //The transient keyword in Java is used to indicate that a field should not be part of the serialization (which means saved, like to a file) process.
        File file = new File("H:\\Serialisierung.ser");
        // Try-with resources
        try (FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(liste);
        } catch (IOException e) {
            e.printStackTrace();
        } // finally fäööt weg (close wird automatisch aufgerufen)
    }

    @Override
    public List<Medium> laden() throws PersistenzException {
        List liste = null;
        File sfile = new File("H:\\Serialisierung.ser");
        try (FileInputStream fis = new FileInputStream(sfile);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
                liste = (List<Medium>) ois.readObject();    
        } catch (IOException e) {
            System.out.println("IO-Fehler bei der Deserialisierung");
        } catch (ClassNotFoundException e) {
            System.out.println("Fehler: class-Datei nicht gefunden");
        }
        return liste;
    }
}
