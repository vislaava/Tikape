/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.ryhmatyo;

import java.sql.*;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:Keskustelupalsta.db");
        database.setDebugMode(true);

        List<Viesti> viestit = database.queryAndCollect("SELECT * FROM Viesti", new Viestikeraaja());

        for (Viesti pyora : viestit) {
            System.out.println(pyora.getId());
        }

    }
}
