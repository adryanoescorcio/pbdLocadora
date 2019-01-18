package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IModel {

    IModel montarObjeto(ResultSet obj) throws SQLException;

    int getId();

    void setId(int id);
}
