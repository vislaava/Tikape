/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.ryhmatyo;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ViestiDao implements Dao<Viesti, Integer> {

    private Database database;

    public ViestiDao(Database database) {
        this.database = database;
    }

//    @Override
    public Viesti findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        int id = rs.getInt("id");
        String paivamaara = rs.getString("paivamaara");
        String teksti = rs.getString("teksti");
        String kirjoittaja = rs.getString("kirjoittaja");
        int keskustelu = rs.getInt("keskustelu");

        Viesti p = new Viesti(id, paivamaara, teksti, kirjoittaja, keskustelu);

        rs.close();
        stmt.close();
        connection.close();

        return p;
    }

//    @Override
    public List<Viesti> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti");

        ResultSet rs = stmt.executeQuery();
        List<Viesti> viestit = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String paivamaara = rs.getString("paivamaara");
            String teksti = rs.getString("teksti");
            String kirjoittaja = rs.getString("kirjoittaja");
            int keskustelu = rs.getInt("keskustelu");

            Viesti v = new Viesti(id, paivamaara, teksti, kirjoittaja, keskustelu);
            viestit.add(v);
        }

        rs.close();
        stmt.close();
        connection.close();

        return viestit;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<Viesti> findAllIn(Collection<Integer> keys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Viesti oljo) throws SQLException {
        Connection connection = database.getConnection();

        String paivamaara = oljo.getPaivamaara();
        String teksti = oljo.getTeksti();
        String kirjoittaja = oljo.getKirjoittaja();
        int keskustelu = oljo.getKeskustelu();

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Viesti (paivamaara, teksti, kirjoittaja, keskustelu) VALUES (?, ?, ?, ?);");

        stmt.setString(1, paivamaara);
        stmt.setString(2, teksti);
        stmt.setString(3, kirjoittaja);
        stmt.setInt(4, keskustelu);

        stmt.execute();
        
        stmt.close();
        connection.close();
    }
}
