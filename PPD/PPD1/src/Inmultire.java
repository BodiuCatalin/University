public class Inmultire  extends Thread{
    private int incepe;
    private int sfarseste;

    private Matrice matrice1;
    private Matrice matrice2;
    private Matrice rezultat;

    public Inmultire(int incepe, int sfarseste, Matrice matrice1, Matrice matrice2, Matrice rezultat){
        this.incepe=incepe;
        this.sfarseste=sfarseste;
        this.matrice1=matrice1;
        this.matrice2=matrice2;
        this.rezultat=rezultat;
    }

    @Override
    public void run(){
        for (int y=incepe;y<sfarseste;y++){
            int valoreaRezultat=0;

            int index1=y%matrice1.getLatime();
            int index2=y/matrice2.getLungime();

            for (int i=0; i<this.matrice1.getLatime();i++) {
                int valoare1 = matrice1.getElement(index1, i);
                int valoare2 = matrice2.getElement(i, index2);


                valoreaRezultat +=valoare1*valoare2;
            }

            rezultat.setElement(index1,index2,valoreaRezultat);
        }
    }

}
