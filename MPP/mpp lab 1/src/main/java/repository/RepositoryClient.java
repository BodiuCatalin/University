package repository;

import model.Client;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryClient implements IRepositoryUser<Integer,Client> {
    private JdbcUtils dbUtils;

    private static final Logger logger=LogManager.getLogger();
    public RepositoryClient(Properties props){
        logger.info("InitalizingUserRepository with properties:{}",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size(){
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Client")) {
            try(ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    logger.traceExit(result.getInt("SIZE"));
                    return result.getInt("SIZE");
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        return 0;
    }

    @Override
    public void save(Client entity) {
        logger.traceEntry("saving user {} ",entity);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Client values (?,?,?)")){
            preStmt.setString(1,entity.getID().toString());
            preStmt.setString(2,entity.getNumele());
            preStmt.setString(3,String.valueOf(entity.getNr_locuri()));
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }
    @Override
    public void delete(Integer id) {
        logger.traceEntry("deleting user with {}",id);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Client where id=?")){
            preStmt.setString(1,id.toString());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }
    @Override
    public void update(Integer id, Client entity) {
        logger.traceEntry("updating user with {}",id);
        Connection con=dbUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("update Clinet " +
                "set nume=?, nr_locuri=?" +
                "where id=?")){
            preStmt.setString(3,id.toString());
            preStmt.setString(2,entity.getNumele());
            preStmt.setString(3,String.valueOf(entity.getNr_locuri()));
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public Client findOne(Integer new_id) {
        logger.traceEntry("finding user with id {} ",new_id);
        Connection con=dbUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from Meci where id=?")){
            preStmt.setString(1,new_id.toString());
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    String nume = result.getString("nume");
                    String id1 = result.getString("id");
                    String numar = result.getString("nr_locuri");
                    int nr_locuri=Integer.parseInt(numar);
                    int id=Integer.parseInt(id1);
                    Client client=new Client(id,nume,nr_locuri);
                    logger.traceExit(client);
                    return client;
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit("No user found with id {}", new_id);

        return null;
    }

    @Override
    public Iterable<Client> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Client> clienti=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Client")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    String nume = result.getString("nume");
                    String id1 = result.getString("id");
                    String numar = result.getString("nr_locuri");
                    int nr_locuri=Integer.parseInt(numar);
                    int id=Integer.parseInt(id1);
                    Client client=new Client(id,nume,nr_locuri);
                    clienti.add(client);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(clienti);
        return clienti;
    }

}
