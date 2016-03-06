package tikape.ryhmatyo;

import java.sql.*;

public class Viestikeraaja implements Collector<Viesti> {

    @Override
    public Viesti collect(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Date paivamaara = rs.getDate("paivamaara");
        String teksti = rs.getString("teksti");
        String kirjoittaja = rs.getString("kirjoittaja");
        int keskustelu = rs.getInt("keskustelu");

        return new Viesti(id, paivamaara, teksti, kirjoittaja, keskustelu);
    }
}