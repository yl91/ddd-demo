package ddd.demo.infrastructure.repository.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.*;

/**
 * Created by lil.yang on 2017/6/4.
 */
public class DateTimeTypeHandler extends BaseTypeHandler<DateTime> {
    private final DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setString(i, null);
        } else {

            ps.setString(i, parameter.toString(format));
        }
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {

        Timestamp date = rs.getTimestamp(columnName);
        if (date == null) {
            return null;
        }

        return new DateTime(date.getTime());
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

        Timestamp date = rs.getTimestamp(columnIndex);
        if (date == null) {
            return null;
        }
        return new DateTime(date.getTime());
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp date = cs.getTimestamp(columnIndex);
        if (date == null) {
            return null;
        }
        return new DateTime(date.getTime());
    }
}
