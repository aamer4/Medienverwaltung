package fachlogik;


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
public class EmptyFilenameException extends Exception {
    public EmptyFilenameException() {
        super();
    }
    public EmptyFilenameException(String message) {
        super(message);
    }
}