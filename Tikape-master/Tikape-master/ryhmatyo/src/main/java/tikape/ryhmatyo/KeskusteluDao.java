/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.ryhmatyo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author DELL
 */
public class KeskusteluDao implements Dao<Keskustelu, Integer> {

    private Database database;

    public KeskusteluDao(Database database) {
        this.database = database;
    }

    @Override
    public Keskustelu findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelu WHERE id = ?");

        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        int id = rs.getInt("id");
        String paivamaara = rs.getString("paivamaara");
        String nimi = rs.getString("nimi");
        int alue = rs.getInt("aihealue");

        Keskustelu k = new Keskustelu(id, paivamaara, nimi, alue);

        rs.close();
        stmt.close();
        connection.close();

        return k;
    }

    @Override
    public List<Keskustelu> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelu");

        ResultSet rs = stmt.executeQuery();
        List<Keskustelu> keskustelut = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String paivamaara = rs.getString("paivamaara");
            String nimi = rs.getString("nimi");
            int alue = rs.getInt("aihealue");

            Keskustelu k = new Keskustelu(id, paivamaara, nimi, alue);
            keskustelut.add(k);
        }

        rs.close();
        stmt.close();
        connection.close();

        return keskustelut;
    }

    @Override
    public List<Keskustelu> findAllIn(Collection<Integer> keys) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelu WHERE aihealue=?");
        List<Keskustelu> kesk = new ArrayList<>();
        for (int k : keys) {
            stmt.setInt(1, k);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String paivamaara = rs.getString("paivamaara");
                String nimi = rs.getString("nimi");
                int alue = rs.getInt("aihealue");

                Keskustelu ke = new Keskustelu(id, paivamaara, nimi, alue);
                kesk.add(ke);
            }

            rs.close();
            stmt.close();
            connection.close();

        }

        return kesk;

    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Keskustelu oljo) throws SQLException {
        Connection connection = database.getConnection();

        String paivamaara = oljo.getPaivamaara();
        String nimi = oljo.getNimi();
        int aihealue = oljo.getAihealue();

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Keskustelu (paivamaara, nimi, aihealue) VALUES (?, ?, ?);");

        stmt.setString(1, paivamaara);
        stmt.setString(2, nimi);
        stmt.setInt(3, aihealue);
        stmt.execute();

        stmt.close();
        connection.close();
    }
}
