package model;

public class User implements HasID<Integer>{
    private int idUser;
    private String username;
    private String password;

    public User(int idUser, String username, String password){
        this.idUser=idUser;
        this.username=username;
        this.password=password;
    }

    @Override
    public Integer getID() {
        return idUser;
    }

    @Override
    public void setID(Integer id) {
        this.idUser=id;
    }

    public void setIdUser(int idUser){
        this.idUser=idUser;
    }

    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public int getIdUser(){
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return " "+idUser+" "+username+" "+password;
    }
}
