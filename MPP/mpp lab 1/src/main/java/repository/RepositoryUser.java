package repository;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class RepositoryUser implements IRepositoryUser<Integer,User> {
    private JdbcUtils dbUtils;

    private static final Logger logger=LogManager.getLogger();
    public RepositoryUser(Properties props){
        logger.info("InitalizingUserRepository with properties:{}",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size(){
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Users")) {
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
    public void save(User entity) {
        logger.traceEntry("saving user {} ",entity);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Users values (?,?,?)")){
            preStmt.setString(1,entity.getID().toString());
            preStmt.setString(2,entity.getUsername());
            preStmt.setString(3,entity.getPassword());
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
        try(PreparedStatement preStmt=con.prepareStatement("delete from Users where username=?")){
            preStmt.setString(1,id.toString());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }
    @Override
    public void update(Integer id, User entity) {
        logger.traceEntry("updating user with {}",id);
        Connection con=dbUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("update Users " +
                "set username=?, password=?" +
                "where id=?")){
            preStmt.setString(3,id.toString());
            preStmt.setString(1,entity.getUsername());
            preStmt.setString(2,entity.getPassword());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public User findOne(Integer new_id) {
        logger.traceEntry("finding user with id {} ",new_id);
        Connection con=dbUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from Users where username=?")){
            preStmt.setString(1,new_id.toString());
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    String username = result.getString("username");
                    String id1 = result.getString("id");
                    String password = result.getString("password");
                    int id=Integer.parseInt(id1);
                    User user=new User(id,username,password);
                    logger.traceExit(user);
                    return user;
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
    public Iterable<User> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<User> users=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Users")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    String username = result.getString("username");
                    String id1 = result.getString("id");
                    String password = result.getString("password");
                    int id=Integer.parseInt(id1);
                    User user=new User(id,username,password);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(users);
        return users;
    }


}
