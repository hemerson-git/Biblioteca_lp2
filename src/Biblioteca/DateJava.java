/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

/**
 *
 * @author giova
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateJava {

    public static void main(String[] args) {
        Date data=new Date();//data do sistema
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada=dateFormat.format(data);
        System.out.println("Data: "+dataFormatada);
        
        
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.add(Calendar.DATE, +2);//acrescentando 2 dias à data
        Date novaData= c.getTime();
        dataFormatada=dateFormat.format(novaData);
        System.out.println("Data com mais 2 dias: "+dataFormatada);
    }    

}
