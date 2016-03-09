/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.ryhmatyo;

import java.sql.Date;

/**
 *
 * @author Tanja
 */
public class Viesti {
    private int id;
    private String paivamaara;
    private String teksti;
    private String kirjoittaja;
    private int keskustelu;

    public Viesti(int id, String paivamaara, String teksti, String kirjoittaja, int keskustelu) {
        this.id = id;
        this.paivamaara = paivamaara;
        this.teksti = teksti;
        this.kirjoittaja = kirjoittaja;
        this.keskustelu = keskustelu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaivamaara() {
        return paivamaara;
    }

    public void setPaivamaara(String paivamaara) {
        this.paivamaara = paivamaara;
    }
    
    public String getTeksti() {
        return teksti;
    }
    
    public void setTeksti(String teksti) {
        this.teksti = teksti;
    }
    
    public String getKirjoittaja() {
        return kirjoittaja;
    }
    
    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
    }
    
    public int getKeskustelu() {
        return keskustelu;
    }
    
    public void setKeskustelu(int keskustelu) {
        this.keskustelu = keskustelu;
    }
}
