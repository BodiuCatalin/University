package model;

import java.util.Objects;

public class Client implements HasID<Integer> {
    private Integer idClient;
    private String numele;
    private Integer nr_locuri;

    public Client(int idClient,String numele,int nr_locuri){
        this.idClient=idClient;
        this.numele=numele;
        this.nr_locuri=nr_locuri;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getNr_locuri() {
        return nr_locuri;
    }

    public String getNumele() {
        return numele;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setNr_locuri(int nr_locuri) {
        this.nr_locuri = nr_locuri;
    }

    public void setNumele(String numele) {
        this.numele = numele;
    }

    @Override
    public String toString() {
        return idClient+" "+numele+" "+nr_locuri;
    }
    @Override
    public Integer getID(){
        return idClient;
    }
    @Override
    public void setID(Integer id){
        this.idClient=id;
    }
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof Client)) return false;
        Client that=(Client) o;
        return Objects.equals(idClient,that.getIdClient())&&
                Objects.equals(getNumele(),that.getNumele());

    }
    @Override
    public int hashCode(){
        return Objects.hash(getID(),getNumele(),getNr_locuri());
    }
}
