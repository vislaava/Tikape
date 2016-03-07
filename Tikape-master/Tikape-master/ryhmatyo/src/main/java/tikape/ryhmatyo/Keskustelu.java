/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.ryhmatyo;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Keskustelu {

    private int id;
    private Date paivamaara;
    private String nimi;
    private Aihealue aihealue;

    public Keskustelu(int id, Date pvm, String nimi, Aihealue aihe) {
        this.id = id;
        this.nimi = nimi;
        this.paivamaara=pvm;
        this.aihealue=aihe;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public Date getPaivamaara() {
        return paivamaara;
    }

    public Aihealue getAihealue() {
        return aihealue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setPaivamaara(Date paivamaara) {
        this.paivamaara = paivamaara;
    }

    public void setAihealue(Aihealue aihealue) {
        this.aihealue = aihealue;
    }
    
    

}
