import Model.*;
import utils.*;


public class AdunareNThreads extends Thread {
    private int incepe;
    private int sfarseste;

    private Matrice matrice1;
    private Matrice matrice2;
    private Matrice rezultat;

    public AdunareNThreads(int incepe, int sfarseste, Matrice matrice1, Matrice matrice2, Matrice rezultat){
        this.incepe=incepe;
        this.sfarseste=sfarseste;
        this.matrice1=matrice1;
        this.matrice2=matrice2;
        this.rezultat=rezultat;
    }

    @Override
    public void run(){
        AdunareOperator op=new AdunareOperator();

        for (int y=incepe;y<sfarseste;y++){
            int index1=y%matrice1.getLatime();
            int index2=y/matrice2.getLungime();

            int valoare1=matrice1.getElement(index1,index2);
            int valoare2=matrice2.getElement(index1,index2);

            int valoareRez=op.apply(valoare1,valoare2);

            rezultat.setElement(index1,index2,valoareRez);
        }
    }
}
