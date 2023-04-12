import java.util.ArrayList;
import java.util.Random;

public class Izgara {

    public void ızgaraOlustur(ArrayList<String> urlVeri,int matris[][])
    {


        for (int i = 0; i < matris.length; i++) {

            for (int j = 0; j < matris[0].length; j++) {

                if(i == 0 || j == 0 || i == matris.length-1 || j == matris[0].length-1)
                {
                    matris[i][j] = 1;
                }

            }

        }


        for (int i = 0; i < urlVeri.size(); i++) {

            String veri = urlVeri.get(i);

            for (int j = 0; j < veri.length(); j++) {


                matris[i+1][j+1] = Integer.parseInt(String.valueOf(veri.charAt(j)));


            }

        }



    }




}
