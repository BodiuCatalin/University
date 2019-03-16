import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class MainA {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        //Main pentru adunarea a doua matricii.



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

        Scanner in= new Scanner(System.in);
        p=in.nextInt();




        in.close();

        Thread[] threads=new Thread[w*v];

        if(p>w*v){
            System.out.println("Numarul de thread-uri prea mare decat nr operatiilor!");
            return;
        }

        int nrThread=(w*v)/p;
        int rest=(w*v)%p;

        if(matrice1.getLungime()!=matrice2.getLungime()|| matrice1.getLatime()!=matrice2.getLatime()){
            System.out.println("Dimensiunile matricelor nu sunt egale");
        }

        long st=System.currentTimeMillis()% 1000;

        for (int i=0;i<p;i++){
            sfarsit=start+nrThread;
            threads[i]=new MatriceAdd(start,sfarsit,matrice1,matrice2,result);
            threads[i].start();
            start=sfarsit;
        }

        for (int i=0; i<rest;i++){
            sfarsit++;
            threads[i]=new MatriceAdd(start,sfarsit,matrice1,matrice2,result);
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
