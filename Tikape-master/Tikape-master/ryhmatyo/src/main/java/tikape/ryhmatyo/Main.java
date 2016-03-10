/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.ryhmatyo;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import static spark.Spark.get;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:Keskustelupalsta.db");
//        ViestiDao viestiDao = new ViestiDao(database);
//        Viesti v = viestiDao.findOne(1);

        AihealueDao aiheDao = new AihealueDao(database);
        Aihealue a = aiheDao.findOne(1);

        database.setDebugMode(true);
        
        System.out.println(a.getId());

//        database.update("INSERT INTO Viesti (paivamaara, teksti, kirjoittaja, keskustelu) VALUES ('2016-02-22', 'Hola!', 'Tanja', '1')");
//
//        List<Viesti> viestit = database.queryAndCollect("SELECT * FROM Viesti",
//                rs -> new Viesti(rs.getInt("id"), rs.getString("paivamaara"), rs.getString("teksti"), rs.getString("kirjoittaja"), rs.getInt("keskustelu")));
//
//        for (Viesti viesti : viestit) {
//            System.out.println(viesti.getTeksti());
//        }

        System.out.println("Moronääs!");

        /////////get("/sivu", (req, res) -> {
        /////////    HashMap map = new HashMap<>();
        /////////
        /////////    return new ModelAndView(map, "index");
        /////////}, new ThymeleafTemplateEngine());.
    }
}