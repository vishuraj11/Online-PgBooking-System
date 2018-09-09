package com.pg.book;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class HelperClass implements RowMapper<PGDetails> {

	@Override
	public PGDetails mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		String pg_area=arg0.getString("pg_area");
		PGDetails pg=new PGDetails();
		pg.setPg_area(pg_area);
		return pg;
	}

}
