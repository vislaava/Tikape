/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.ryhmatyo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Tanja
 */
public interface Collector<T> {
    T collect(ResultSet rs) throws SQLException;
}
