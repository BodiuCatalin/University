
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Model.*;
import utils.*;

public class Main {





    public static void main(String[] args) throws IOException{
        System.out.println("Comenzi:");
        System.out.println("1.AdunareComplex");
        System.out.println("2.AdunareNumar");
        int c;
        System.out.println("Comanda:");

        Scanner in= new Scanner(System.in);
        c=in.nextInt();

        if(c==1){
            ComplexMatrice matrice1=new ComplexMatrice("matrix1.txt");
            ComplexMatrice matrice2=new ComplexMatrice("matrix2.txt");

            int n=matrice1.getLungime();
            int m=matrice2.getLatime();

            ComplexMatrice result= new ComplexMatrice(n,m);

            int p;

            int inceput=0;
            int sfarsit=0;

            System.out.println("Numarul de threaduri:");
            p=in.nextInt();
            in.close();

            Thread[] threads=new Thread[p];


            int trNum=(n*m)/p;

            if (matrice1.getLungime() != matrice2.getLungime() ||
                    matrice1.getLatime() != matrice2.getLatime()) {
                System.out.println("Nu se potrivesc!");
                return;
            }

            long start = System.currentTimeMillis() % 1000;

            for (int i = 0; i < p; i++) {
                sfarsit = inceput + trNum;
                threads[i] = new AdunareThreads(inceput, sfarsit, matrice1, matrice2, result);
                threads[i].start();
                inceput = sfarsit;
            }

            for (int i = 0; i < p; i++)
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            long end = System.currentTimeMillis() % 1000;

            System.out.println("Start time: " + String.valueOf(start));
            System.out.println("End time: " + String.valueOf(end));
            System.out.println("Global time: " + String.valueOf(end - start));

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    Complex complex = result.getElement(i, j);
                    double real = complex.getReal();
                    double imaginary = complex.getImaginary();

                    if (imaginary >= 0.0)
                        System.out.print(real + "+" + imaginary + "i ");
                    else
                        System.out.print(real + "" + imaginary + "i ");
                }

                System.out.println();
            }
        }
        if(c==2){
            Matrice matrice1=new Matrice("m1.txt");
            Matrice matrice2=new Matrice("m2.txt");




            int w= matrice1.getLungime();
            int v= matrice2.getLatime();


            for( int i=0;i<w;i++){
                for(int j=0;j<v;j++){
                    System.out.print(matrice1.getElement(i,j)+" ");
                }
                System.out.println();
            }
            System.out.println();
            for( int i=0;i<w;i++){
                for(int j=0;j<v;j++){
                    System.out.print(matrice2.getElement(i,j)+" ");
                }
                System.out.println();
            }

            Matrice result=new Matrice(w,v);

            int p,l;

            int start=0;
            int sfarsit=0;

            System.out.println("Numar de thread-uri:");

            p=in.nextInt();
            in.close();

            Thread[] threads=new Thread[p];

            int nrThread=(w*v)/p;

            if(matrice1.getLungime()!=matrice2.getLungime()|| matrice1.getLatime()!=matrice2.getLatime()){
                System.out.println("Dimensiunile matricelor nu sunt egale");
            }

            long st=System.currentTimeMillis()% 1000;

            for (int i=0;i<p;i++){
                sfarsit=start+nrThread;
                threads[i]=new AdunareNThreads(start,sfarsit,matrice1,matrice2,result);
                threads[i].start();
                start=sfarsit;
            }


            for(int i=0; i<p;i++)
                try{
                    threads[i].join();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            long et=System.currentTimeMillis()%1000;

            System.out.println("Inceput:"+String.valueOf(st));
            System.out.println("Sfarsit"+String.valueOf(et));
            System.out.println("Global time:"+String.valueOf(et-st));

            for( int i=0;i<w;i++){
                for(int j=0;j<v;j++){
                    System.out.print(result.getElement(i,j)+" ");
                }
                System.out.println();
            }

        }

    }

}
