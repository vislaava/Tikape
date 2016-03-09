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
import java.util.List;

/**
 *
 * @author DELL
 */
public class ViestiDao {
     private Database database;

    public ViestiDao(Database database) {
        this.database = database;
    }
 
//    @Override
    public Viesti findOne(String key) throws SQLException {
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
	// ei toteutettu
	return null;
    }

//    @Override
    public void delete(String key) throws SQLException {
        // ei toteutettu
    }
}
