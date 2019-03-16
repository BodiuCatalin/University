package repository;



import model.Meci;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class RepositoryMeci implements IRepositoryUser<Integer,Meci> {
    private JdbcUtils dbUtils;

    private static final Logger logger=LogManager.getLogger();
    public RepositoryMeci(Properties props){
        logger.info("InitalizingUserRepository with properties:{}",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public int size(){
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Meci")) {
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
    public void save(Meci entity) {
        logger.traceEntry("saving user {} ",entity);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Meci values (?,?,?,?,?,?)")){
            preStmt.setString(1,entity.getID().toString());
            preStmt.setString(2,entity.getEchipaA());
            preStmt.setString(3,entity.getEchipaB());
            int bilete=entity.getPret_bilet();
            preStmt.setString(4,String.valueOf(bilete));
            int lcuri=entity.getNumar_locuri();
            preStmt.setString(5,String.valueOf(lcuri));
            preStmt.setString(6,entity.getCategorie());
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
        try(PreparedStatement preStmt=con.prepareStatement("delete from Meci where id=?")){
            preStmt.setString(1,id.toString());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }
    @Override
    public void update(Integer id, Meci entity) {
        logger.traceEntry("updating user with {}",id);
        Connection con=dbUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("update Meci " +
                "set echipaA=?, echipaB=?, nr_locuri=?,pret_bilet=?,categorie=?" +
                "where id=?")){
            preStmt.setString(6,entity.getID().toString());
            preStmt.setString(1,entity.getEchipaA());
            preStmt.setString(2,entity.getEchipaB());
            int bilete=entity.getPret_bilet();
            preStmt.setString(3,String.valueOf(bilete));
            int lcuri=entity.getNumar_locuri();
            preStmt.setString(4,String.valueOf(lcuri));
            preStmt.setString(5,entity.getCategorie());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public Meci findOne(Integer new_id) {
        logger.traceEntry("finding user with id {} ",new_id);
        Connection con=dbUtils.getConnection();

        try(PreparedStatement preStmt=con.prepareStatement("select * from Meci where id=?")){
            preStmt.setString(1,new_id.toString());
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    String echipaA = result.getString("echipaA");
                    String echipaB = result.getString("echipaB");
                    String id1 = result.getString("id");
                    String pret_bilet1 = result.getString("pret_bilet");
                    String numar_louri=result.getString("numar_locuri");
                    String categorie = result.getString("categorie");
                    int id=Integer.parseInt(id1);
                    int pret_bilete=Integer.parseInt(pret_bilet1);
                    int numar_locuri=Integer.parseInt(numar_louri);
                    Meci meci=new Meci(id,echipaA,echipaB,pret_bilete,numar_locuri,categorie);
                    logger.traceExit(meci);
                    return meci;
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
    public Iterable<Meci> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Meci> meciuri=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Meci")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    String echipaA = result.getString("echipaA");
                    String echipaB = result.getString("echipaB");
                    String id1 = result.getString("id");
                    String pret_bilet1 = result.getString("pret_bilet");
                    String numar_louri=result.getString("numar_locuri");
                    String categorie = result.getString("categorie");
                    int id=Integer.parseInt(id1);
                    int pret_bilete=Integer.parseInt(pret_bilet1);
                    int numar_locuri=Integer.parseInt(numar_louri);
                    Meci meci=new Meci(id,echipaA,echipaB,pret_bilete,numar_locuri,categorie);
                    meciuri.add(meci);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(meciuri);
        return meciuri;
    }

}
