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
import java.util.List;

/**
 *
 * @author DELL
 */
public class KeskusteluDao implements Dao {
    
    
    private Database database;

    public KeskusteluDao(Database database) {
        this.database = database;
    }

    @Override
    public Object findOne(Object key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Pyora WHERE rekisterinumero = ?");
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
    public List findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
