package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ComplexMatrice {
    private int lungime,latime;
    private Complex[][] matrice= new Complex[2000][2000];

    public ComplexMatrice(String fileName) throws FileNotFoundException, IOException{
        double real, imaginary;

        try (BufferedReader br= new BufferedReader(new FileReader(fileName))){
            lungime= Integer.parseInt(br.readLine());
            latime= Integer.parseInt(br.readLine());

            for (int i=0;i<lungime;i++){
                String[] elements=br.readLine().split(" ");

                for (int j=0;j<latime;j++){
                    String[] split= elements[j].split(";");
                    real=Double.parseDouble(split[0]);
                    imaginary=Double.parseDouble(split[1]);

                    matrice[i][j]=new Complex(real,imaginary);
                }
            }

        }
    }

    public ComplexMatrice(int lungime,int latime){
        this.lungime=lungime;
        this.latime=latime;

    }

    public int getLungime(){return lungime;}

    public int getLatime(){return latime;}

    public Complex getElement(int x, int y){return matrice[x][y];}

    public void setElement(int x,int y, Complex value){matrice[x][y]=value;}

}
