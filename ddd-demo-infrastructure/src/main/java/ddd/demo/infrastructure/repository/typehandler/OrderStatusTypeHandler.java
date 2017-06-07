package ddd.demo.infrastructure.repository.typehandler;

import ddd.demo.domain.order.model.OrderStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

/**
 * Created by lil.yang on 2017/6/4.
 */
public class OrderStatusTypeHandler extends BaseTypeHandler<OrderStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, OrderStatus orderStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, orderStatus.value());

    }

    @Override
    public OrderStatus getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int orderStatus = resultSet.getInt(s);
        return OrderStatus.parse(orderStatus);
    }

    @Override
    public OrderStatus getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int orderStatus = resultSet.getInt(i);
        return OrderStatus.parse(orderStatus);
    }

    @Override
    public OrderStatus getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int orderStatus = callableStatement.getInt(i);
        return OrderStatus.parse(orderStatus);
    }
}
