package dao;

import dao.Interface.IMagazineDAO;
import doMain.Magazine;
import doMain.Subscribe;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MagazineDAO implements IMagazineDAO {

    private Logger log = Logger.getLogger(MagazineDAO.class);

    @Override
    public Magazine insert(Magazine magazine)
            throws DAOException {
        log.info("Creating new magazine in database...");
        String sqlQuery = "insert into magazine(`title`, `description`, `publish_date`, `subscribe_price`) values (?, ?, ?, ?)";

        Magazine newMagazine = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            log.trace("Opening connection...");
            connection = DaoConnection.getInstance().getConnection();

            log.trace("Creating prepared statement...");
            statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, magazine.getTitle());
            statement.setString(2, magazine.getDescription());
            statement.setDate(3, Date.valueOf(magazine.getDate()));
            statement.setInt(4, magazine.getPriceofSubscribe());

            log.trace("Executing database update...");
            int rows = statement.executeUpdate();
            log.trace(String.format("%d row(s) added!", rows));

            log.trace("Getting result set...");
            if (rows == 0) {
                log.error("Creating magazine failed, no rows affected!");
                throw new DAOException("Creating magaziner failed, no rows affected!");
            } else {
                resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    log.trace("Creating Magazine to return...");
                    newMagazine = new Magazine(resultSet.getInt(1), magazine.getTitle(), magazine.getDescription(),
                            magazine.getDate(), magazine.getPriceofSubscribe());
                }
            }
        } catch (SQLException e) {
            log.error("Creating magazine failed!");
            throw new DAOException("Creating magazine failed!", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                log.trace("Result set is closed!");
            } catch (SQLException e) {
                log.error("Result set can't be closed!", e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
                log.trace("Prepared statement is closed!");
            } catch (SQLException e) {
                log.error("Prepared statement can't be closed!", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
                log.trace("Connection is closed!");
            } catch (SQLException e) {
                log.error("Connection can't be closed!", e);
            }
        }

        log.trace("Returning Magazine...");
        log.info(newMagazine + " is added to database!");
        return newMagazine;
    }

    @Override
    public List<Magazine> readAll() throws DAOException {
        log.info("Getting all magazines from database...");
        String sqlQuery = "select * from magazine";

        List<Magazine> magazineList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            log.trace("Opening connection...");
            connection = DaoConnection.getInstance().getConnection();

            log.trace("Creating prepared statement...");
            statement = connection.prepareStatement(sqlQuery);

            log.trace("Getting result set...");
            resultSet = statement.executeQuery();

            log.trace("Creating list of magazines to return...");
            while (resultSet.next()) {
                magazineList.add(new Magazine(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("description"), resultSet.getDate("publish_date").toLocalDate(),
                        resultSet.getInt("subscribe_price")));
            }
        } catch (SQLException e) {
            log.error("Getting list of magazines failed!");
            throw new DAOException("Getting list of magazines failed!", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                log.trace("Result set is closed!");
            } catch (SQLException e) {
                log.error("Result set can't be closed!", e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
                log.trace("Prepared statement is closed!");
            } catch (SQLException e) {
                log.error("Prepared statement can't be closed!", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
                log.trace("Connection is closed!");
            } catch (SQLException e) {
                log.error("Connection can't be closed!", e);
            }
        }

        log.trace("Returning list of magazines...");
        return magazineList;
    }

    @Override
    public Magazine readByID(int id) throws DAOException {
        log.info("Getting magazine by id from database...");
        String sqlQuery = "select * from magazine where id = ?";

        Magazine magazine = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            log.trace("Opening connection...");
            connection = DaoConnection.getInstance().getConnection();

            log.trace("Creating prepared statement...");
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);

            log.trace("Getting result set...");
            resultSet = statement.executeQuery();

            log.trace("Creating Magazine to return...");
            while (resultSet.next()) {
                magazine = new Magazine(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("description"), resultSet.getDate("publish_date").toLocalDate(),
                        resultSet.getInt("subscribe_price"));
            }
        } catch (SQLException e) {
            log.error("Getting magazine by id failed!");
            throw new DAOException("Getting magazine by id failed!", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                log.trace("Result set is closed!");
            } catch (SQLException e) {
                log.error("Result set can't be closed!", e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
                log.trace("Prepared statement is closed!");
            } catch (SQLException e) {
                log.error("Prepared statement can't be closed!", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
                log.trace("Connection is closed!");
            } catch (SQLException e) {
                log.error("Connection can't be closed!", e);
            }
        }

        log.trace("Returning Magazine...");
        log.info(magazine + " is getted from database!");
        return magazine;
    }

    @Override
    public boolean updateByID(Magazine magazine)
            throws DAOException {
        log.info("Updating magazine by id in database...");
        String sqlQuery = "update magazine set title = ?, description = ?, publish_date = ?, subscribe_price = ? where id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;

        try {
            log.trace("Opening connection...");
            connection = DaoConnection.getInstance().getConnection();

            log.trace("Creating prepared statement...");
            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, magazine.getTitle());
            statement.setString(2, magazine.getDescription());
            statement.setDate(3, Date.valueOf(magazine.getDate()));
            statement.setInt(4, magazine.getPriceofSubscribe());
            statement.setInt(5, magazine.getId());

            log.trace("Executing database update...");
            int rows = statement.executeUpdate();
            log.trace(String.format("%d row(s) updated!", rows));

            if (rows == 0) {
                log.error("Updating magazine failed, no rows affected!");
                throw new DAOException("Updating magazine failed, no rows affected!");
            } else {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Updating magazine failed!");
            throw new DAOException("Updating magazine failed!", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                log.trace("Prepared statement is closed!");
            } catch (SQLException e) {
                log.error("Prepared statement can't be closed!", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
                log.trace("Connection is closed!");
            } catch (SQLException e) {
                log.error("Connection can't be closed!", e);
            }
        }

        if (result == false) {
            log.info("Updating magazine failed, no rows affected!");
        } else {
            log.trace("Returning result...");
            log.info("Magazine with ID#" + magazine.getId() + " is updated in database!");
        }
        return result;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        log.info("Deleting magazine by id from database...");
        String sqlQuery = "delete from magazine where id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;

        try {
            log.trace("Opening connection...");
            connection = DaoConnection.getInstance().getConnection();

            log.trace("Creating prepared statement...");
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);

            log.trace("Executing database update...");
            int rows = statement.executeUpdate();
            log.trace(String.format("%d row(s) deleted!", rows));

            if (rows == 0) {
                log.error("Deleting magazine failed, no rows affected!");
                throw new DAOException("Deleting magazine failed, no rows affected!");
            } else {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Deleting magazine failed!");
            throw new DAOException("Deleting magazine failed!", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                log.trace("Prepared statement is closed!");
            } catch (SQLException e) {
                log.error("Prepared statement can't be closed!", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
                log.trace("Connection is closed!");
            } catch (SQLException e) {
                log.error("Connection can't be closed!", e);
            }
        }

        if (result == false) {
            log.info("Deleting magazine failed, no rows affected!");
        } else {
            log.trace("Returning result...");
            log.info("Magazine with ID#" + id + " is deleted from database!");
        }
        return result;
    }

}
