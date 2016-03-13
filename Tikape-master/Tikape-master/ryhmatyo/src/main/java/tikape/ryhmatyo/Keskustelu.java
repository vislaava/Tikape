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
    private String paivamaara;
    private String nimi;
    private int aihealue;


    public Keskustelu(int id, String pvm, String nimi, int aihe) {
        this.id = id;
        this.nimi = nimi;
        this.paivamaara = pvm;
        this.aihealue = aihe;
    }

    public Keskustelu(String pvm, String nimi, int aihe) {
        this.nimi = nimi;
        this.paivamaara = pvm;
        this.aihealue = aihe;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public String getPaivamaara() {
        return paivamaara;
    }

    public int getAihealue() {
        return aihealue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setPaivamaara(String paivamaara) {
        this.paivamaara = paivamaara;
    }

    public void setAihealue(int aihealue) {
        this.aihealue = aihealue;
    }

}
