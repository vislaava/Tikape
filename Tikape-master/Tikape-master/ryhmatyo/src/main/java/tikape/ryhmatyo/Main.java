/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.ryhmatyo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
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
        KeskusteluDao keskuDao = new KeskusteluDao(database);
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
        get("/sivu", (req, res) -> {

            AihealueDao aihedao = new AihealueDao(database);
            List<Aihealue> aiheetlista = aihedao.findAll();

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

            map.put("keskustelualue", 1);

            return new ModelAndView(map, "yksiKeskustelu");
        }, new ThymeleafTemplateEngine());

        post("/uusiViesti", (req, res) -> {
            String nimi = req.queryParams("nimi");
            String viesti = req.queryParams("viesti");

            Viesti viestioljo = new Viesti("pvm", viesti, nimi, Integer.parseInt(req.queryParams("keskustelualue")));

            viestiDao.insert(viestioljo);

            return "Kerrotaan siitÃƒÂ¤ tiedon lÃƒÂ¤hettÃƒÂ¤jÃƒÂ¤lle: " + nimi + viesti;
        });

        post("/uusiKeskustelu", (req, res) -> {
            String nimi = req.queryParams("nimi");
            String viesti = req.queryParams("otsikko");

            Keskustelu keskuoljo = new Keskustelu("pvm", nimi, Integer.parseInt(req.queryParams("aiheelualue")));

            keskuDao.insert(keskuoljo);

            return "Viesti on vastaanotettu: " + nimi + viesti;
        });

        post("/sivu2", (req, res) -> {
            String id = req.queryParams("id");
            int id2 = Integer.parseInt(id);

            keskuDao.findOne(id2);

            return "Viesti on vastaanotettu: " + id;
        });

        get("/alue/:id", (req, res) -> {

            HashMap map = new HashMap<>();
            map.put("teksti", req.params("id"));
            Collection<Integer> kokoelma = new ArrayList<>();

            String id = req.params(":id");

            int idd = Integer.parseInt(id);
            kokoelma.add(idd);

            List<Keskustelu> keskustelut = keskuDao.findAllIn(kokoelma);

            map.put("keskustelut", keskustelut);

            return new ModelAndView(map, "keskustelut");
        }, new ThymeleafTemplateEngine());

        get("alue/:id1/keskustelu/:id", (req, res) -> {

            HashMap map = new HashMap<>();
            map.put("teksti", req.params("id"));
            Collection<Integer> kokoelma = new ArrayList<>();

            System.out.println("id");
            String id = req.params(":id");
            System.out.println(id);

            int idd = Integer.parseInt(id);
            kokoelma.add(idd);

            List<Viesti> viestit = viestiDao.findAllIn(kokoelma);

            map.put("teksti", "Viestit");
            map.put("viestit", viestit);
            map.put("keskustelualue", req.params(":id"));

            return new ModelAndView(map, "yksiKeskustelu");
        }, new ThymeleafTemplateEngine());

    }
}
