/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenhaltung;

import java.util.List;
import fachlogik.Medium;

/**
 *
 * @author mhdal001
 */
public interface IDao {
    void speichern(List<Medium> liste) throws PersistenzException;
    List<Medium> laden() throws PersistenzException;
}
