package Day_9_Postgres;

import java.sql.*;

//добавление, обновление, удаление, запрос по id, запрос по имени, запрос всего списка

/**
 * Created by Ирина on 16.02.2017.
 */
public class OperationsWithBD {
    String url;
    String login;
    String password;

    public OperationsWithBD(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public void selectDbAll(String table) {
        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            Statement query = conn.createStatement();
            String sqlQ = "select * from %s";
            sqlQ = String.format(sqlQ, table);
            System.out.println(sqlQ);

            ResultSet rs = query.executeQuery(sqlQ);
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void selectDbName(String table, String name) {
        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            Statement query = conn.createStatement();
            String sqlQ = "select * from %s where name = ?";
            sqlQ = String.format(sqlQ, table);

            PreparedStatement prepared = conn.prepareStatement(sqlQ);
            prepared.setString(1, name);
            System.out.println(prepared);
            ResultSet rs = prepared.executeQuery();
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void selectDbId(String table, int id) {
        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            Statement query = conn.createStatement();
            String sqlQ = "select * from %s where id = ?";
            sqlQ = String.format(sqlQ, table);

            PreparedStatement prepared = conn.prepareStatement(sqlQ);
            prepared.setInt(1, id);
            System.out.println(prepared);

            ResultSet rs = prepared.executeQuery();
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertDbString(String table, String columnName, String columnValue, String typeValue) {
        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            String sqlQ = "INSERT INTO %s (id, name, bithdate, sex, group_id) " +
                    "Values (?,?,?,?,?)";
            sqlQ = String.format(sqlQ, table);

            PreparedStatement prepared = conn.prepareStatement(sqlQ);
            prepared.setInt(1, 4);
            prepared.setString(2, "Arnold");
            prepared.setDate(3, new Date(1987, 5, 2));
            prepared.setString(4, "m");
            prepared.setInt(5, 1);
            prepared.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateDb(String table, String columnName, String columnValue, String newColumnValue) {
        try (Connection conn = DriverManager.getConnection(url, login, password)) {
            String sqlQ = "Update  %1$s set  %2$s = ? where %2$s = ?";
            sqlQ = String.format(sqlQ, table, columnName);

            PreparedStatement prepared = conn.prepareStatement(sqlQ);
            prepared.setString(1, newColumnValue);
            prepared.setString(2, columnValue);
            System.out.println(prepared);
            System.out.println(prepared.executeUpdate() + " rows updated");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
