import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Matrice {


    private int lungime, latime;
    private int[][] matrice= new int [1000][1000];

    public Matrice(String filename) throws FileNotFoundException, IOException{
        try (BufferedReader br= new BufferedReader(new FileReader(filename))){
            lungime=Integer.parseInt(br.readLine());
            latime=Integer.parseInt(br.readLine());

            for (int i=0; i<lungime; i++){
                String[] elements= br.readLine().split(" ");

                for (int j=0; j<latime;j++)
                    matrice[i][j]=Integer.parseInt(elements[j]);
            }
        }
    }

    public Matrice(int lungime, int latime){
        this.lungime=lungime;
        this.latime=latime;
    }

    public int getLatime() {
        return latime;
    }

    public int getLungime() {
        return lungime;
    }
    public int getElement(int x, int y){
        return matrice[x][y];
    }
    public void setElement(int x, int y,int value){
        matrice[x][y]=value;
    }
}
