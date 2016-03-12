/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.ryhmatyo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:Keskustelupalsta.db");
        ViestiDao viestiDao = new ViestiDao(database);
//        Viesti v = viestiDao.findOne(1);

//        AihealueDao aiheDao = new AihealueDao(database);
//        Aihealue a = aiheDao.findOne(1);
//
        database.setDebugMode(true);
//        
//        System.out.println(a.getId());
//        database.update("INSERT INTO Viesti (paivamaara, teksti, kirjoittaja, keskustelu) VALUES ('2016-02-22', 'Hola!', 'Tanja', '1')");
//
//        List<Viesti> viestit = database.queryAndCollect("SELECT * FROM Viesti",
//                rs -> new Viesti(rs.getInt("id"), rs.getString("paivamaara"), rs.getString("teksti"), rs.getString("kirjoittaja"), rs.getInt("keskustelu")));
//
//        for (Viesti viesti : viestit) {
//            System.out.println(viesti.getTeksti());
//        }
//        System.out.println("Moronääs!");
        get("/sivu", (req, res) -> {

            AihealueDao aihedao = new AihealueDao(database);
            List<Aihealue> aiheetlista = aihedao.findAll();

//            for(Aihealue alue : aiheetlista) {
//                System.out.println(alue.getId());
//                System.out.println(alue.getNimi());
//            }
            HashMap map = new HashMap<>();
            map.put("teksti", "Aihealueet");
            map.put("aihealueet", aiheetlista);

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/sivu2", (req, res) -> {
            KeskusteluDao keskudao = new KeskusteluDao(database);
            List<Keskustelu> keskulista = keskudao.findAll();
            
            HashMap map = new HashMap<>();
            map.put("teksti", "Keskustelut");
            map.put("keskustelut", keskulista);

            return new ModelAndView(map, "keskustelut");
        }, new ThymeleafTemplateEngine());

        get("/sivu3", (req, res) -> {
            ViestiDao viestidao = new ViestiDao(database);
            List<Viesti> viestitlista = viestidao.findAll();

            HashMap map = new HashMap<>();
            map.put("teksti", "Viestit");
            map.put("viestit", viestitlista);

            return new ModelAndView(map, "yksiKeskustelu");
        }, new ThymeleafTemplateEngine());
        
        post("/opiskelijat", (req, res) -> {
            String nimi = req.queryParams("nimi");            
            String viesti = req.queryParams("viesti");
            
            Viesti viestioljo = new Viesti("pvm", viesti, nimi, 1);
            
            viestiDao.insert(viestioljo);
            
            return "Kerrotaan siitä tiedon lähettäjälle: " + nimi + viesti;
        });
    }
}
