/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenhaltung;

/**
 *
 * @author mhdal001
 */
public class PersistenzException  extends  Exception{
    public  PersistenzException(){
        super();
    }
     public  PersistenzException(String msg){
        super(msg);
    }
}
