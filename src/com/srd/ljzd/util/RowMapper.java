package com.srd.ljzd.util;

import java.sql.ResultSet;
import java.sql.SQLException;
 
/**
 * RowMapper
 * @author Bruce Tan
 * @version v1.0
 */
public interface RowMapper
{
    public Object mapRow(ResultSet rs, int index)
        throws SQLException;
}