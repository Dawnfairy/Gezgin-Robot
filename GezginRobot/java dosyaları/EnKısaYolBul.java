

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class EnKısaYolBul {


    //   public static ArrayList<Integer> path2 = new ArrayList<Integer>();

    private static class Hücre {
        Hücre önceki;
        int mesafe;
        int satir;
        int sutun;



        public   Hücre(int x1, int y1, int mesafe1, Hücre önceki1) {

            sutun = y1;
            mesafe = mesafe1;
            satir = x1;
            önceki = önceki1;
        }


    }


    public static void EnKısaYol(int[][] matris, int başlangıçX,int başlangıçY, int bitişX,int bitişY,ArrayList<Integer> path2) {




        Hücre[][] kareler = new Hücre[matris.length][matris[0].length];

        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[0].length; j++) {
                if (matris[i][j] == 0) {
                    kareler[i][j] = new Hücre(i, j,200, null);
                }
            }
        }

        Hücre başlangıç = kareler[başlangıçX][başlangıçY];
        başlangıç.mesafe = 0;

        LinkedList<Hücre> kuyruk = new LinkedList<>();
        kuyruk.add(başlangıç);


        Hücre A = null;
        Hücre pr;

        while ((pr = kuyruk.poll()) != null) {

            if (pr.satir == bitişX && pr.sutun == bitişY) {
                A = pr;
                break;
            }



            ZiyaretEt(pr.satir + 1, pr.sutun, pr,kareler,kuyruk);

            ZiyaretEt(pr.satir - 1, pr.sutun, pr,kareler,kuyruk);

            ZiyaretEt( pr.satir, pr.sutun + 1, pr,kareler,kuyruk);

            ZiyaretEt(pr.satir, pr.sutun - 1, pr,kareler,kuyruk);


        }


        LinkedList<Hücre> path = new LinkedList<>();

        pr = A;


        do {
            path.addFirst(pr);
            path2.add(pr.satir);
            path2.add(pr.sutun);

        } while ((pr = pr.önceki) != null);//bir sonraki boş olana kadar




    }


    private static void ZiyaretEt(int x, int y, Hücre pr,Hücre[][] kareler,LinkedList<Hücre> kuyruk) {


        if (x >= kareler.length || x < 0 || y < 0 || y >= kareler[0].length || kareler[x][y] == null) {
            System.out.println("Sınır dışına çıkıldı");
            return;

        }


        int mesafe = pr.mesafe + 1;

        if(mesafe>200)
        {
            return;
        }

        Hücre p = kareler[x][y];
        if (mesafe < p.mesafe) {
            p.mesafe = mesafe;
            p.önceki = pr;
            kuyruk.add(p);
        }

    }

}