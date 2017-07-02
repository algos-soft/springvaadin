package it.algos.springvaadin.entity.versione;

import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gac on 27/06/17
 */
@SpringComponent
public class VersioneRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Versione vers = new Versione();
//        vers.setId(rs.getLong("id"));
//        vers.setOrdine(rs.getInt("ordine"));
//        vers.setTitolo(rs.getString("titolo"));
//        vers.setDescrizione(rs.getString("descrizione"));
//        vers.setModifica(DateConvertUtils.asLocalDateTime(rs.getTimestamp("modifica")));
//        return vers;
        return null;
    }// end of method

}// end of class