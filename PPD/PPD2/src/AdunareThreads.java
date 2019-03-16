import utils.*;
import Model.*;



public class AdunareThreads extends Thread {
    private int incepe;
    private int sfarsit;

    private ComplexMatrice matrice1;
    private ComplexMatrice matrice2;
    private ComplexMatrice result;

    public AdunareThreads(int inceput,int sfarsit, ComplexMatrice matrice1, ComplexMatrice matrice2, ComplexMatrice result){
        this.incepe=inceput;
        this.sfarsit=sfarsit;
        this.matrice1=matrice1;
        this.matrice2=matrice2;
        this.result=result;
    }

    @Override
    public void run(){
        AdunareOperator op=new AdunareOperator();

        for (int y=incepe;y<sfarsit;y++){
            int index1=y% matrice1.getLatime();
            int index2=y/matrice1.getLungime();

            Complex valueIndex1=matrice1.getElement(index1,index2);
            Complex valueIndex2=matrice2.getElement(index1,index2);

            Complex valueResult=op.applyComplex(valueIndex1,valueIndex2);

            result.setElement(index1,index2,valueResult);
        }


    }
}

