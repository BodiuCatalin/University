package model;

public class Meci implements HasID<Integer>{
    private int idMeci;
    private String echipaA;
    private String echipaB;
    private int pret_bilet;
    private int numar_locuri;
    private String categorie;

    public Meci(int idMeci,String echipaA,String echipaB,int pret_bilet,int numar_locuri,String categorie){
        this.idMeci=idMeci;
        this.echipaA=echipaA;
        this.echipaB=echipaB;
        this.pret_bilet=pret_bilet;
        this.numar_locuri=numar_locuri;
        this.categorie=categorie;
    }

    @Override
    public Integer getID() {
        return idMeci;
    }

    @Override
    public void setID(Integer id) {
        this.idMeci=id;
    }


    public int getIdMeci() {
        return idMeci;
    }

    public String getEchipaA() {
        return echipaA;
    }

    public String getEchipaB() {
        return echipaB;
    }

    public int getPret_bilet() {
        return pret_bilet;
    }

    public int getNumar_locuri() {
        return numar_locuri;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setEchipaA(String echipaA) {
        this.echipaA = echipaA;
    }

    public void setEchipaB(String echipaB) {
        this.echipaB = echipaB;
    }

    public void setIdMeci(int idMeci) {
        this.idMeci = idMeci;
    }

    public void setNumar_locuri(int numar_locuri) {
        this.numar_locuri = numar_locuri;
    }

    public void setPret_bilet(int pret_bilet) {
        this.pret_bilet = pret_bilet;
    }

    @Override
    public String toString() {
        return " "+idMeci+" "+echipaA+" "+echipaB+" "+pret_bilet+" "+numar_locuri+" "+categorie;
    }
}
