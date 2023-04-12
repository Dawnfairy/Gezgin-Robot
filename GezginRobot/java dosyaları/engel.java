import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class engel extends JFrame {


    File file;
    int matris[][];
    int maze[][];
    int x;
    int y;
    int sonuc;
    ArrayList<Integer> path = new ArrayList<>();

    Color mor = new Color(126, 105, 173);
    Color kmor = new Color(59, 0, 91);
    Color gri = new Color(127, 127, 127);
    Color yol = mor;
    Color bulut = gri;
    Color imleç = kmor;
    int count = 0;
    public engel(int matris[][], int satir, int sutun) throws IOException {

        file = new File("oyun2.txt");
        if(!file.exists())
        {
            file.createNewFile();
        }

        FileWriter fWriter = new FileWriter(file,false);
        BufferedWriter bWriter = new BufferedWriter(fWriter);



        setTitle("Simple Maze Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setSize(1920, 1080);
        sonuc = JOptionPane.showConfirmDialog(null, "Sonucu hemen görmek ister misin?", "game", JOptionPane.YES_NO_OPTION);

        this.setLayout(null);
        setLocationRelativeTo(null);


        maze = new int[matris.length][matris[0].length];
        x = satir;
        y = sutun;


        LabirentOluştur(y, x,maze);
        LabirentYol();

        bWriter.write("Labirentte robotun gittiği yollar: ");
        for (int i = 0; i < path.size(); i=i+2) {

            bWriter.write("(" + path.get(i) + " " + path.get(i+1) + ") ");
        }
        bWriter.close();

    }


    public static void LabirentOluştur(int satir, int sutun,int matris[][]) {


        for (int i = 0; i < sutun; i++) {
            for (int j = 0; j < satir; j++) {
                matris[i][j] = 1;
            }
        }




        Random random = new Random();

        LinkedList<int[]> konumlar = new LinkedList<>();


        int başlangıçX = 1;
        int başlangıçY = 1;

        int[] ilkKonum = {başlangıçY, başlangıçX, başlangıçY, başlangıçX};

        System.out.println(ilkKonum[0] + " " + ilkKonum[1] + " " + ilkKonum[2] + ilkKonum[3]);

        konumlar.add(ilkKonum);

        System.out.println(konumlar.isEmpty());



        while (konumlar.isEmpty()==false) {

            int seçim = random.nextInt(konumlar.size());
            int[] p = konumlar.remove(seçim);  // rastgele bir elemanı seç onu çıkar ve p ye at



            başlangıçY = p[2];
            başlangıçX = p[3];

            if (matris[başlangıçY][başlangıçX] == 1) {


                matris[p[0]][p[1]] = 0;
                matris[başlangıçY][başlangıçX] = 0;


                if (başlangıçY >= 2 && matris[başlangıçY - 2][başlangıçX] == 1 && başlangıçY >= 2) {

                    if(matris[başlangıçY - 2][başlangıçX] == 1 )
                    {
                        int[] yeniKonum = {başlangıçY - 1, başlangıçX, başlangıçY - 2, başlangıçX};
                        konumlar.add(yeniKonum);
                    }

                }

                if (başlangıçX >= 2 && matris[başlangıçY][başlangıçX - 2] == 1 && başlangıçX >= 2) {
                    if(matris[başlangıçY][başlangıçX - 2] == 1)
                    {
                        int[] yeniKonum = {başlangıçY, başlangıçX - 1, başlangıçY, başlangıçX - 2};
                        konumlar.add(yeniKonum);
                    }

                }

                if (başlangıçY < sutun - 2 && matris[başlangıçY + 2][başlangıçX] == 1 && başlangıçY < sutun - 2 ) {

                    if(matris[başlangıçY + 2][başlangıçX] == 1)
                    {
                        int[] yeniKonum = {başlangıçY + 1, başlangıçX, başlangıçY + 2, başlangıçX};
                        konumlar.add(yeniKonum);
                    }

                }

                if (başlangıçX < satir - 2 && matris[başlangıçY][başlangıçX + 2] == 1 && başlangıçX < satir - 2 ) {
                    if(matris[başlangıçY][başlangıçX + 2] == 1)
                    {
                        int[] yeniKonum = {başlangıçY, başlangıçX + 1, başlangıçY, başlangıçX + 2};
                        konumlar.add(yeniKonum);
                    }

                }
            }
        }

        konumlar.clear();

        for (int i = 0; i < satir; i++) {
            matris[sutun - 1][i] = 1;
            matris[0][i] = 1;
        }

        for (int i = 0; i < sutun; i++) {

            matris[i][satir - 1] = 1;
            matris[i][0] = 1;

        }

        konumlar.clear();


        /*
        for (int i = 0; i < matris.length; i++) {

            for (int j = 0; j < matris[0].length; j++) {
                System.out.print(matris[i][j] + " ");

            }
            System.out.println();
        }
*/




    }



    public void LabirentYol() {

        Stack stack = new Stack();
        matris = new int[maze.length][maze[0].length];

        //int matris[][] = new int[maze.length][maze[0].length];



        for (int i = 0; i < maze.length; i++) {

            for (int j = 0; j < maze[0].length; j++) {

                matris[i][j] = maze[i][j];

            }

        }

        Robot robot = new Robot();
        robot.x = 1;
        robot.y = 1;
        matris[1][1] = 2;
        path.add(1);
        path.add(1);

        int count = 0;
        int sayac = 0;
        while (robot.x != matris.length - 2 || robot.y != matris[0].length - 2) {

            if (matris[robot.x][robot.y + 1] == 0) {
                sayac++;
            }

            if (matris[robot.x][robot.y - 1] == 0) {
                sayac++;
            }

            if (matris[robot.x + 1][robot.y] == 0) {
                sayac++;
            }

            if (matris[robot.x - 1][robot.y] == 0) {
                sayac++;
            }

            if (sayac >= 1) {

                if (sayac > 1) {
                    stack.add(robot.y);
                    stack.add(robot.x);
                }


                if (matris[robot.x][robot.y + 1] == 0) {
                    robot.y = robot.y + 1;
                    matris[robot.x][robot.y] = 2;

                } else if (matris[robot.x][robot.y - 1] == 0) {
                    robot.y = robot.y - 1;
                    matris[robot.x][robot.y] = 2;
                } else if (matris[robot.x + 1][robot.y] == 0) {
                    robot.x = robot.x + 1;
                    matris[robot.x][robot.y] = 2;
                } else if (matris[robot.x - 1][robot.y] == 0) {
                    robot.x = robot.x - 1;
                    matris[robot.x][robot.y] = 2;
                }

            }


            if (sayac == 0) {


                robot.x = (int) stack.pop();
                robot.y = (int) stack.pop();



                for (int i = 0; i < path.size(); i=i+2) {

                    if(path.get(i) == robot.x && path.get(i+1) == robot.y)
                    {

                        for (int j = path.size()-3; j > i+2; j=j-2) {
                            if( matris[path.get(j-1)][path.get(j)] != 3)
                            {
                                matris[path.get(j-1)][path.get(j)] = 3;
                                matris[path.get(j+1)][path.get(j+2)] = 3;
                                path.add(path.get(j-1));
                                path.add(path.get(j));
                            }


                        }
                        count = i+2;
                        break;
                    }

                }



            }
            sayac = 0;
            path.add(robot.x);
            path.add(robot.y);



        }


        for (int i = 0; i < matris.length; i++) {

            for (int j = 0; j < matris[0].length; j++) {

                System.out.print(matris[i][j] + " ");

            }

            System.out.println();
        }





    }

    @Override
    public void paint(Graphics g) {


        ImageIcon   image2 = new ImageIcon("yukarı.png");
        ImageIcon image3 = new ImageIcon("asagı.png");
        ImageIcon image4 = new ImageIcon("sol.png");
        ImageIcon image5 = new ImageIcon("sag.png");

        super.paint(g);

        g.translate(50, 50);


        long startTime = System.currentTimeMillis();


        long totalDuration = 0;


        for (int i = 0; i < maze.length; i++) {

            for (int j = 0; j < maze[0].length; j++) {

                if (maze[i][j] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawRect(20 * j, 20 * i, 20, 20);

                }

                if (maze[i][j] == 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawRect(20 * j, 20 * i, 20, 20);

                }

                if(matris[i][j] == 2 || matris[i][j] == 3)
                {
                    count++;
                }
            }

        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        for (int i = 0; i < maze.length; i++) {

            for (int j = 0; j < maze[0].length; j++) {

                if (maze[i][j] == 1) {
                    g.setColor(bulut);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawRect(20 * j, 20 * i, 20, 20);

                }

                if (maze[i][j] == 0) {
                    g.setColor(bulut);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawRect(20 * j, 20 * i, 20, 20);

                }

            }

        }

        g.setColor(yol);
        g.fillRect(20 * path.get(1), 20 * path.get(0), 20, 20);
        g.setColor(Color.BLACK);
        g.drawRect(20 * path.get(1), 20 * path.get(0), 20, 20);



        for (int j = 2; j < path.size() - 2; j = j + 2) {

            g.setColor(imleç);
            g.fillRect(20 * path.get(j + 1), 20 * path.get(j), 20, 20);
            g.setColor(Color.BLACK);
            g.drawRect(20 * path.get(j + 1), 20 * path.get(j), 20, 20);

            int bayrak = 0;

            if (maze[(int) path.get(j)][(int) path.get(j + 1) + 1] == 0) {

                for (int i = 0; i < j; i = i + 2) {

                    if ((int) path.get(i) == (int) path.get(j) && (int) path.get(i + 1) == (int) path.get(j + 1) + 1) {
                        bayrak = 1;
                    }

                }

                if (bayrak == 0) {
                    if (((int) path.get(j + 1) + 1) != (int) path.get(path.size() - 1) || (int) path.get(j) != (int) path.get(path.size() - 2)) {
                        g.setColor(Color.WHITE);
                        g.fillRect(((int) path.get(j + 1) + 1) * 20, (int) path.get(j) * 20, 20, 20);

                        g.setColor(Color.BLACK);
                        g.drawRect(20 * ((int) path.get(j + 1) + 1), 20 * (int) path.get(j), 20, 20);
                    }
                }

            }

            bayrak = 0;
            if (maze[(int) path.get(j)][(int) path.get(j + 1) - 1] == 0) {

                for (int i = 0; i < j; i = i + 2) {

                    if ((int) path.get(i) == (int) path.get(j) && (int) path.get(i + 1) == (int) path.get(j + 1) - 1) {
                        bayrak = 1;
                    }

                }

                if (bayrak == 0) {
                    if (((int) path.get(j + 1) - 1) != (int) path.get(path.size() - 1) || (int) path.get(j) != (int) path.get(path.size() - 2)) {
                        g.setColor(Color.WHITE);
                        g.fillRect(((int) path.get(j + 1) - 1) * 20, (int) path.get(j) * 20, 20, 20);
                        g.setColor(Color.BLACK);
                        g.drawRect(20 * ((int) path.get(j + 1) - 1), 20 * (int) path.get(j), 20, 20);
                    }
                }

            }

            bayrak = 0;

            if (maze[(int) path.get(j) + 1][(int) path.get(j + 1)] == 0) {

                for (int i = 0; i < j; i = i + 2) {

                    if ((int) path.get(i) == (int) path.get(j) + 1 && (int) path.get(i + 1) == (int) path.get(j + 1)) {
                        bayrak = 1;
                    }

                }

                if (bayrak == 0) {
                    if (((int) path.get(j + 1)) != (int) path.get(path.size() - 1) || ((int) path.get(j) + 1) != (int) path.get(path.size() - 2)) {
                        g.setColor(Color.WHITE);
                        g.fillRect(((int) path.get(j + 1)) * 20, ((int) path.get(j) + 1) * 20, 20, 20);
                        g.setColor(Color.BLACK);
                        g.drawRect(20 * ((int) path.get(j + 1)), ((int) path.get(j) + 1) * 20, 20, 20);
                    }
                }

            }

            bayrak = 0;
            if (maze[(int) path.get(j) - 1][(int) path.get(j + 1)] == 0) {

                for (int i = 0; i < j; i = i + 2) {

                    if ((int) path.get(i) == (int) path.get(j) - 1 && (int) path.get(i + 1) == (int) path.get(j + 1)) {
                        bayrak = 1;
                    }

                }

                if (bayrak == 0) {
                    if (((int) path.get(j + 1)) != (int) path.get(path.size() - 1) || ((int) path.get(j) - 1) != (int) path.get(path.size() - 2)) {
                        g.setColor(Color.WHITE);
                        g.fillRect(((int) path.get(j + 1)) * 20, ((int) path.get(j) - 1) * 20, 20, 20);
                        g.setColor(Color.BLACK);
                        g.drawRect(20 * ((int) path.get(j + 1)), ((int) path.get(j) - 1) * 20, 20, 20);
                    }
                }

            }

            if (maze[(int) path.get(j)][(int) path.get(j + 1) + 1] == 1) {
                g.setColor(Color.BLACK);
                g.fillRect(((int) path.get(j + 1) + 1) * 20, (int) path.get(j) * 20, 20, 20);
                g.setColor(Color.BLACK);
                g.drawRect(20 * ((int) path.get(j + 1) + 1), 20 * (int) path.get(j), 20, 20);
            }

            if (maze[(int) path.get(j)][(int) path.get(j + 1) - 1] == 1) {
                g.setColor(Color.BLACK);
                g.fillRect(((int) path.get(j + 1) - 1) * 20, (int) path.get(j) * 20, 20, 20);
                g.setColor(Color.BLACK);
                g.drawRect(20 * ((int) path.get(j + 1) - 1), 20 * (int) path.get(j), 20, 20);

            }
            if (maze[(int) path.get(j) + 1][(int) path.get(j + 1)] == 1) {
                g.setColor(Color.BLACK);
                g.fillRect(((int) path.get(j + 1)) * 20, ((int) path.get(j) + 1) * 20, 20, 20);
                g.setColor(Color.BLACK);
                g.drawRect(20 * ((int) path.get(j + 1)), ((int) path.get(j) + 1) * 20, 20, 20);
            }

            if (maze[(int) path.get(j) - 1][(int) path.get(j + 1)] == 1) {
                g.setColor(Color.BLACK);
                g.fillRect(((int) path.get(j + 1)) * 20, ((int) path.get(j) - 1) * 20, 20, 20);
                g.setColor(Color.BLACK);
                g.drawRect(20 * ((int) path.get(j + 1)), ((int) path.get(j) - 1) * 20, 20, 20);

            }

//
//            g.setColor(Color.GREEN);
//            g.fillRect(30*path.get(j+1),30*path.get(j),30,30);
//            g.setColor(Color.BLACK);
//            g.drawRect(30*path.get(j+1),30*path.get(j),30,30);

            if(sonuc == 1)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            g.setColor(yol);
            g.fillRect(20 * path.get(j + 1), 20 * path.get(j), 20, 20);
            g.setColor(Color.BLACK);
            g.drawRect(20 * path.get(j + 1), 20 * path.get(j), 20, 20);


/*

            if ((int) path.get(j + 1) == (int) path.get(j + 3) && (int) path.get(j) > (int) path.get(j + 2)) {//yukarı
                g.setColor(Color.black);
                g.drawRect((int) path.get(j + 1) * 30, (int) path.get(j) * 30, 30, 30);

                int a = (int) path.get(j + 1) * 30;
                int b = (int) path.get(j) * 30;

                g.drawImage(image2.getImage(), a, b, 30, 30, this);

            } else if ((int) path.get(j + 1) == (int) path.get(j + 3) && (int) path.get(j) < (int) path.get(j + 2)) {//aşağı
                g.setColor(Color.BLACK);
                g.drawRect((int) path.get(j + 1) * 30, (int) path.get(j) * 30, 30, 30);

                int a = (int) path.get(j + 1) * 30;
                int b = (int) path.get(j) * 30;

                g.drawImage(image3.getImage(), a, b, 30, 30, this);

            } else if ((int) path.get(j) == (int) path.get(j + 2) && (int) path.get(j + 1) > (int) path.get(j + 3)) {//sol
                g.setColor(Color.BLACK);
                g.drawRect((int) path.get(j + 1) * 30, (int) path.get(j) * 30, 30, 30);

                int a = (int) path.get(j + 1) * 30;
                int b = (int) path.get(j) * 30;

                g.drawImage(image4.getImage(), a, b, 30, 30, this);

            } else if ((int) path.get(j) == (int) path.get(j + 2) && (int) path.get(j + 1) < (int) path.get(j + 3)) {//sağ
                g.setColor(Color.BLACK);
                g.drawRect((int) path.get(j + 1) * 30, (int) path.get(j) * 30, 30, 30);

                int a = (int) path.get(j + 1) * 30;
                int b = (int) path.get(j) * 30;

                g.drawImage(image5.getImage(), a, b, 30, 30, this);

      }
      */


            long endTime = System.currentTimeMillis();
            totalDuration = endTime - startTime;
        }

        g.setColor(Color.RED);
        g.fillRect(20 * path.get(path.size() - 1), 20 * path.get(path.size() - 2), 20, 20);
        g.setColor(Color.BLACK);
        g.drawRect(20 * path.get(path.size() - 1), 20 * path.get(path.size() - 2), 20, 20);



        g.translate(22* maze[0].length,0);


        int sayac = 0;
        for (int i = 0; i < maze.length; i++) {

            for (int j = 0; j < maze[0].length; j++) {

                if (maze[i][j] == 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawRect(20 * j, 20 * i, 20, 20);


                }

                if (maze[i][j] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawRect(20 * j, 20 * i, 20, 20);

                }

                if(matris[i][j] == 2)
                {
                    g.setColor(yol);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawRect(20 * j, 20 * i, 20, 20);
                    sayac++;
                }

                if(matris[i][j] == 3 && matris[i][j+1] == 3 && matris[i][j-1] == 3 && matris[i+1][j] == 2 && matris[i-1][j]== 2)
                {
                    g.setColor(yol);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawRect(20 * j, 20 * i, 20, 20);
                    sayac++;
                }

                if(matris[i][j] == 3 && matris[i][j+1] == 3 && matris[i][j-1] == 2 && matris[i+1][j] == 3 && matris[i-1][j]== 2)
                {
                    g.setColor(yol);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawRect(20 * j, 20 * i, 20, 20);
                    sayac++;
                }


            }

        }
        Font labelFont = new Font("Ariel", Font.BOLD, 16);
        g.setFont(labelFont);
        g.drawString("Toplam Süre: " + Long.toString(totalDuration / 1000) + " sn", 0, (maze.length + 1) * 20);
        g.drawString("Toplam Geçilen Kare Sayısı:  " + Integer.toString(count), 0, (maze.length + 3) * 20);
        g.drawString("Kısa Yol Kare Sayısı:  " + Integer.toString(sayac), 0, (maze.length + 2) * 20);

        g.setColor(Color.RED);
        g.fillRect(20 * (maze[0].length - 2), 20 * (maze.length - 2), 20, 20);
        g.setColor(Color.BLACK);
        g.drawRect(20 * (maze[0].length - 2), 20 * (maze.length - 2), 20, 20);




    }



}



