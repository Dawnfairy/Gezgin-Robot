
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class Main extends JFrame{

    static BufferedImage duvar;
    static BufferedImage önduvar;
    static BufferedImage duvar1;
    static BufferedImage önduvar1;

    Robot robot = new Robot();

    File file ;


    int[][] matris;
    int[][] hafızaMatris;

    String [] str = {"evet","hayır"};
    ArrayList path = new ArrayList<Integer>();
    ArrayList<Integer> enKısaYol = new ArrayList<>();
    int sonuc;

    int başlangıçX;
    int başlangıçY;
    int bitişX;
    int bitişY;
    public Main(int matris1[][]) throws IOException {

        file = new File("Oyun1.txt");

        if(!file.exists())
        {
            file.createNewFile();
        }

        FileWriter fWritwer = new FileWriter(file,false);
        BufferedWriter bWriter = new BufferedWriter(fWritwer);


        sonuc =   JOptionPane.showConfirmDialog(null,"Oyunu hemen bitirmek istiyor musun?","game",JOptionPane.YES_NO_OPTION);

        matris = new int[matris1.length][matris1[0].length];


        for (int i = 0; i < matris1.length; i++) {

            for (int j = 0; j < matris[0].length; j++) {

                matris[i][j] = matris1[i][j];
            }

        }



        başlangıçX = (int) (Math.random() * matris[0].length);
        başlangıçY = (int) (Math.random() * matris.length);
        bitişX = (int) (Math.random() * matris[0].length);
        bitişY = (int) (Math.random() * matris.length);






        setTitle("Simple Maze Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1920, 1080);
        this.setLayout(null);
        setLocationRelativeTo(null);






        while (matris[başlangıçX][başlangıçY] != 0) {

            başlangıçX = (int) (Math.random() * matris[0].length);
            başlangıçY = (int) (Math.random() * matris.length);

        }

        while (matris[bitişX][bitişY] != 0) {
            bitişX = (int) (Math.random() * matris[0].length);
            bitişY = (int) (Math.random() * matris.length);

        }
        while (başlangıçX == bitişX && başlangıçY == bitişY) {

            başlangıçX = (int) (Math.random() * matris[0].length);
            başlangıçY = (int) (Math.random() * matris.length);
            bitişX = (int) (Math.random() * matris[0].length);
            bitişY = (int) (Math.random() * matris.length);

            while (matris[bitişX][bitişY] != 0) {
                bitişX = (int) (Math.random() * matris[0].length);
                bitişY = (int) (Math.random() * matris.length);

            }
            while (matris[başlangıçX][başlangıçY] != 0) {

                başlangıçX = (int) (Math.random() * matris[0].length);
                başlangıçY = (int) (Math.random() * matris.length);

            }


        }

        ArrayList<Integer> path2 = new ArrayList<>();

        YolAra(matris,başlangıçX,başlangıçY,bitişX,bitişY,path2);




        ArrayList<Integer> path4 = new ArrayList<>();


        for (int i = path2.size(); i >= 2; i = i - 2) {

            path4.add(path2.get(i - 2));
            path4.add(path2.get(i - 1));

        }

        for (int i = 0; i < path4.size(); i = i + 2) {
            path.add(path4.get(i));
            path.add(path4.get(i + 1));
            if (path4.get(i) == bitişX && path4.get(i + 1) + 1 == bitişY) {
                path.add(bitişX);
                path.add(bitişY);
                break;

            } else if (path4.get(i) - 1 == bitişX && path4.get(i + 1) == bitişY) {

                path.add(bitişX);
                path.add(bitişY);
                break;

            } else if (path4.get(i) + 1 == bitişX && path4.get(i + 1) == bitişY) {

                path.add(bitişX);
                path.add(bitişY);
                break;

            } else if (path4.get(i) == bitişX && path4.get(i + 1) - 1 == bitişY) {

                path.add(bitişX);
                path.add(bitişY);
                break;

            }

        }


/*
        for (int i = 0; i < path.size(); i = i+2) {

            System.out.println(path.get(i) + " " + path.get(i+1));

        }
*/
        for (int i = 0; i < matris.length; i++) {

            for (int j = 0; j < matris[0].length; j++) {

                if(matris[i][j] == 5)
                {
                    matris[i][j] = 0;
                }

            }

        }


        hafızaMatris = new int[matris.length][matris[0].length]; // hafıza matrisi oluşturdum

        hafızaTut(matris,hafızaMatris,path);  // hafıza matrisinin içini dolduran fonksiyonu çağırdım


        ArrayList<Integer> path3 = new ArrayList<>();


        EnKısaYolBul enKisa = new EnKısaYolBul();
        EnKısaYolBul.EnKısaYol(hafızaMatris,başlangıçX,başlangıçY,bitişX,bitişY,path3);




        for (int i = path3.size()-1; i > 0; i=i-2) {

            enKısaYol.add(path3.get(i-1));
            enKısaYol.add(path3.get(i));

        }

        bWriter.write("Robotun dolaştığı yol:");
        for (int i = 0; i < path2.size(); i=i+2) {

            bWriter.write("("+path2.get(i) + ", " + path2.get(i+1) + ") ");

        }

        bWriter.write("\nKısa yol: ");
        for (int i = 0; i < path3.size(); i=i+2) {

            bWriter.write("("+path3.get(i) + ", " + path3.get(i+1) + ") ");

        }

        bWriter.close();




    }

    public static void UrlOku(String urlAdres,ArrayList<String> urlVeri)
    {
        URL url = null;

        String input;
        try {
            url = new URL(urlAdres);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));


            while( (input = in.readLine())!= null )
            {
                urlVeri.add(input);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }



    @Override
    public void paint(Graphics g) {





        ImageIcon image1 = new ImageIcon("sag.png");
        ImageIcon   image2 = new ImageIcon("sol.png");
        ImageIcon image3 = new ImageIcon("asagı.png");
        ImageIcon image4 = new ImageIcon("yukarı.png");

        ImageIcon image5 = new ImageIcon("start.png");
        ImageIcon image6 = new ImageIcon("jerry.png");
        ImageIcon image7 = new ImageIcon("tom.png");
        ImageIcon image8 = new ImageIcon("tomvejerry.png");
        super.paint(g);
        long startTime = System.currentTimeMillis();

        long totalDuration = 0;
        int count = 0;

        g.translate(50, 50);
        int k = 0;

        // draw the maze
        for (int row = 0; row < matris.length; row++) {
            for (int col = 0; col < matris[0].length; col++) {


                g.setColor(Color.GRAY);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);



            }

        }
        g.setColor(Color.PINK);
        g.fillRect((int) path.get(1) * 30, (int) path.get(0) * 30, 30, 30);
        g.setColor(Color.BLACK);
        g.drawRect((int) path.get(1) * 30, (int) path.get(0) * 30, 30, 30);

        int x = (int) path.get(1) * 30;
        int y = (int) path.get(0) * 30;

        g.drawImage(image5.getImage(), x, y, 30, 30, this);



        if(matris[(int) path.get(0)][(int)path.get(1) +1] == 0)
        {
            g.setColor(Color.WHITE);
            g.fillRect( ((int)path.get(1) +1)*30,(int)path.get(0)*30, 30, 30);
            g.setColor(Color.BLACK);
            g.drawRect(((int)path.get(1) +1)*30,(int)path.get(0)*30, 30, 30);
        }

        if(matris[(int) path.get(0)][(int)path.get(1) -1] == 0)
        {
            g.setColor(Color.WHITE);
            g.fillRect( ((int)path.get(1) -1)*30,(int)path.get(0)*30, 30, 30);
            g.setColor(Color.BLACK);
            g.drawRect(((int)path.get(1) -1)*30,(int)path.get(0)*30, 30, 30);
        }

        if(matris[(int) path.get(0)+1][(int)path.get(1)] == 0)
        {
            g.setColor(Color.WHITE);
            g.fillRect( ((int)path.get(1))*30,((int)path.get(0)+1)*30, 30, 30);
            g.setColor(Color.BLACK);
            g.drawRect(((int)path.get(1))*30,((int)path.get(0)+1)*30, 30, 30);
        }

        if(matris[(int) path.get(0)-1][(int)path.get(1)] == 0)
        {
            g.setColor(Color.WHITE);
            g.fillRect( ((int)path.get(1))*30,((int)path.get(0)-1)*30, 30, 30);
            g.setColor(Color.BLACK);
            g.drawRect(((int)path.get(1))*30,((int)path.get(0)-1)*30, 30, 30);
        }




        if(matris[(int) path.get(0)][(int)path.get(1) +1] == 1)
        {
            g.setColor(Color.BLACK);
            g.fillRect( ((int)path.get(1) +1)*30,(int)path.get(0)*30, 30, 30);
            g.setColor(Color.BLACK);
            g.drawRect(((int)path.get(1) +1)*30,(int)path.get(0)*30, 30, 30);
        }

        if(matris[(int) path.get(0)][(int)path.get(1) -1] == 1)
        {
            g.setColor(Color.BLACK);
            g.fillRect( ((int)path.get(1) -1)*30,(int)path.get(0)*30, 30, 30);
            g.setColor(Color.BLACK);
            g.drawRect(((int)path.get(1) -1)*30,(int)path.get(0)*30, 30, 30);
        }

        if(matris[(int) path.get(0)+1][(int)path.get(1)] == 1)
        {
            g.setColor(Color.BLACK);
            g.fillRect( ((int)path.get(1))*30,((int)path.get(0)+1)*30, 30, 30);
            g.setColor(Color.BLACK);
            g.drawRect(((int)path.get(1))*30,((int)path.get(0)+1)*30, 30, 30);
        }

        if(matris[(int) path.get(0)-1][(int)path.get(1)] == 1)
        {
            g.setColor(Color.BLACK);
            g.fillRect( ((int)path.get(1))*30,((int)path.get(0)-1)*30, 30, 30);
            g.setColor(Color.BLACK);
            g.drawRect(((int)path.get(1))*30,((int)path.get(0)-1)*30, 30, 30);
        }


        for (int j = 2; j < path.size()-2; j = j + 2) {


            x = (int) path.get(j + 1) * 30;
            y = (int) path.get(j) * 30;
            count ++;
            int bayrak = 0;


            g.drawImage(image7.getImage(), x, y, 30, 30, this);
            if (matris[(int) path.get(j)][(int) path.get(j + 1) + 1] == 0) {

                for (int i = 0; i < j; i = i + 2) {

                    if ((int) path.get(i) == (int) path.get(j) && (int) path.get(i + 1) == (int) path.get(j + 1) + 1) {

                        bayrak = 1;
                    }

                }

                if (bayrak == 0) {

                    if(((int) path.get(j + 1) + 1) !=(int) path.get(path.size()-1) || (int) path.get(j) !=(int) path.get(path.size()-2))
                    {
                        g.setColor(Color.WHITE);
                        g.fillRect(((int) path.get(j + 1) + 1) * 30, (int) path.get(j) * 30, 30, 30);
                        g.setColor(Color.BLACK);
                        g.drawRect(30 * ((int) path.get(j + 1) + 1), 30 * (int) path.get(j), 30, 30);
                    }

                }

            }

            bayrak = 0;
            if (matris[(int) path.get(j)][(int) path.get(j + 1) - 1] == 0) {

                for (int i = 0; i < j; i = i + 2) {

                    if ((int) path.get(i) == (int) path.get(j) && (int) path.get(i + 1) == (int) path.get(j + 1) - 1) {
                        bayrak = 1;
                    }

                }

                if (bayrak == 0) {

                    if(((int) path.get(j + 1) - 1) !=(int) path.get(path.size()-1) || (int) path.get(j) !=(int) path.get(path.size()-2))
                    {
                        g.setColor(Color.WHITE);
                        g.fillRect(((int) path.get(j + 1) - 1) * 30, (int) path.get(j) * 30, 30, 30);
                        g.setColor(Color.BLACK);
                        g.drawRect(30 * ((int) path.get(j + 1) - 1), 30 * (int) path.get(j), 30, 30);
                    }

                }

            }

            bayrak = 0;

            if (matris[(int) path.get(j) + 1][(int) path.get(j + 1)] == 0) {

                for (int i = 0; i < j; i = i + 2) {

                    if ((int) path.get(i) == (int) path.get(j) + 1 && (int) path.get(i + 1) == (int) path.get(j + 1)) {
                        bayrak = 1;
                    }

                }

                if (bayrak == 0) {

                    if(((int) path.get(j + 1)) !=(int) path.get(path.size()-1) ||( (int) path.get(j) +1) !=(int) path.get(path.size()-2))
                    {
                        g.setColor(Color.WHITE);
                        g.fillRect(((int) path.get(j + 1)) * 30, ((int) path.get(j) + 1) * 30, 30, 30);
                        g.setColor(Color.BLACK);
                        g.drawRect(30 * ((int) path.get(j + 1)), ((int) path.get(j) + 1) * 30, 30, 30);
                    }

                }

            }

            bayrak = 0;
            if (matris[(int) path.get(j) - 1][(int) path.get(j + 1)] == 0) {

                for (int i = 0; i < j; i = i + 2) {

                    if ((int) path.get(i) == (int) path.get(j) - 1 && (int) path.get(i + 1) == (int) path.get(j + 1)) {
                        bayrak = 1;
                    }

                }

                if (bayrak == 0) {
                    if(((int) path.get(j + 1)) !=(int) path.get(path.size()-1) ||( (int) path.get(j) -1) !=(int) path.get(path.size()-2))
                    {
                        g.setColor(Color.WHITE);
                        g.fillRect(((int) path.get(j + 1)) * 30, ((int) path.get(j) - 1) * 30, 30, 30);
                        g.setColor(Color.BLACK);
                        g.drawRect(30 * ((int) path.get(j + 1)), ((int) path.get(j) - 1) * 30, 30, 30);
                    }

                }

            }

            if (matris[(int) path.get(j)][(int) path.get(j + 1) + 1] != 0) {
                g.setColor(Color.BLACK);
                g.fillRect(((int) path.get(j + 1) + 1) * 30, (int) path.get(j) * 30, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * ((int) path.get(j + 1) + 1), 30 * (int) path.get(j), 30, 30);
            }

            if (matris[(int) path.get(j)][(int) path.get(j + 1) - 1] != 0) {
                g.setColor(Color.BLACK);
                g.fillRect(((int) path.get(j + 1) - 1) * 30, (int) path.get(j) * 30, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * ((int) path.get(j + 1) - 1), 30 * (int) path.get(j), 30, 30);

            }
            if (matris[(int) path.get(j) + 1][(int) path.get(j + 1)] != 0) {
                g.setColor(Color.BLACK);
                g.fillRect(((int) path.get(j + 1)) * 30, ((int) path.get(j) + 1) * 30, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * ((int) path.get(j + 1)), ((int) path.get(j) + 1) * 30, 30, 30);
            }

            if (matris[(int) path.get(j) - 1][(int) path.get(j + 1)] != 0) {
                g.setColor(Color.BLACK);
                g.fillRect(((int) path.get(j + 1)) * 30, ((int) path.get(j) - 1) * 30, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * ((int) path.get(j + 1)), ((int) path.get(j) - 1) * 30, 30, 30);

            }


            if(sonuc == 1)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            long endTime = System.currentTimeMillis();
            totalDuration = endTime - startTime;

            g.setColor(Color.white);
            g.fillRect((int) path.get(j + 1) * 30, (int) path.get(j) * 30, 30, 30);
            g.setColor(Color.BLACK);
            g.drawRect((int) path.get(j + 1) * 30, (int) path.get(j) * 30, 30, 30);
            if ((int) path.get(j + 1) == (int) path.get(j + 3) && (int) path.get(j) > (int) path.get(j + 2)) {//yukarı
                g.setColor(Color.black);
                g.drawRect((int) path.get(j + 1) * 30, (int) path.get(j) * 30, 30, 30);

                int a = (int) path.get(j + 1) * 30;
                int b = (int) path.get(j) * 30;

                g.drawImage(image4.getImage(), a, b, 30, 30, this);

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

                g.drawImage(image2.getImage(), a, b, 30, 30, this);

            } else if ((int) path.get(j) == (int) path.get(j + 2) && (int) path.get(j + 1) < (int) path.get(j + 3)) {//sağ
                g.setColor(Color.BLACK);
                g.drawRect((int) path.get(j + 1) * 30, (int) path.get(j) * 30, 30, 30);

                int a = (int) path.get(j + 1) * 30;
                int b = (int) path.get(j) * 30;

                g.drawImage(image1.getImage(), a, b, 30, 30, this);

            }



        }




        g.setColor(Color.PINK);
        g.fillRect((int) path.get(path.size() - 1) * 30, (int) path.get(path.size() - 2) * 30, 30, 30);
        g.setColor(Color.BLACK);
        g.drawRect((int) path.get(path.size() - 1) * 30, (int) path.get(path.size() - 2) * 30, 30, 30);

        x = (int) path.get(path.size() - 1) * 30;
        y = (int) path.get(path.size() - 2) * 30;

        g.drawImage(image8.getImage(), x, y, 30, 30, this);



        g.translate(32* matris[0].length,0);


        for (int i = 0; i < matris.length; i++) {

            for (int j = 0; j < hafızaMatris[0].length; j++) {

                if(hafızaMatris[i][j] == 0)
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(30*j,30*i,30,30);
                    g.setColor(Color.BLACK);
                    g.drawRect(30*j,30*i,30,30);

                }

                if(hafızaMatris[i][j] == 1)
                {    g.setColor(Color.GRAY);
                    g.fillRect(30*j,30*i,30,30);
                    g.setColor(Color.BLACK);
                    g.drawRect(30*j,30*i,30,30);

                }

                if(hafızaMatris[i][j] == 2)
                {    g.setColor(Color.BLACK);
                    g.fillRect(30*j,30*i,30,30);
                    g.setColor(Color.BLACK);
                    g.drawRect(30*j,30*i,30,30);

                }


            }

        }

        g.setColor(Color.PINK);
        g.fillRect(30*başlangıçY,30*başlangıçX,30,30);
        g.setColor(Color.black);
        g.drawRect(30*başlangıçY,30*başlangıçX,30,30);

        for (int j = 2; j < enKısaYol.size()-2; j=j+2) {

            if ((int) enKısaYol.get(j + 1) == (int) enKısaYol.get(j + 3) && (int) enKısaYol.get(j) > (int) enKısaYol.get(j + 2)) {//yukarı
                g.setColor(Color.black);
                g.drawRect((int) enKısaYol.get(j + 1) * 30, (int) enKısaYol.get(j) * 30, 30, 30);

                int a = (int) enKısaYol.get(j + 1) * 30;
                int b = (int) enKısaYol.get(j) * 30;

                g.drawImage(image4.getImage(), a, b, 30, 30, this);

            } else if ((int) enKısaYol.get(j + 1) == (int) enKısaYol.get(j + 3) && (int) enKısaYol.get(j) < (int) enKısaYol.get(j + 2)) {//aşağı
                g.setColor(Color.BLACK);
                g.drawRect((int) enKısaYol.get(j + 1) * 30, (int) enKısaYol.get(j) * 30, 30, 30);

                int a = (int) enKısaYol.get(j + 1) * 30;
                int b = (int) enKısaYol.get(j) * 30;

                g.drawImage(image3.getImage(), a, b, 30, 30, this);

            } else if ((int) enKısaYol.get(j) == (int)enKısaYol.get(j + 2) && (int) enKısaYol.get(j + 1) > (int) enKısaYol.get(j + 3)) {//sol
                g.setColor(Color.BLACK);
                g.drawRect((int) enKısaYol.get(j + 1) * 30, (int) enKısaYol.get(j) * 30, 30, 30);

                int a = (int) enKısaYol.get(j + 1) * 30;
                int b = (int) enKısaYol.get(j) * 30;

                g.drawImage(image2.getImage(), a, b, 30, 30, this);

            } else if ((int) enKısaYol.get(j) == (int) enKısaYol.get(j + 2) && (int) enKısaYol.get(j + 1) < (int) enKısaYol.get(j + 3)) {//sağ
                g.setColor(Color.BLACK);
                g.drawRect((int) enKısaYol.get(j + 1) * 30, (int) enKısaYol.get(j) * 30, 30, 30);

                int a = (int) enKısaYol.get(j + 1) * 30;
                int b = (int) enKısaYol.get(j) * 30;

                g.drawImage(image1.getImage(), a, b, 30, 30, this);

            }


        }

        g.setColor(Color.PINK);
        g.fillRect(30*bitişY,30*bitişX,30,30);
        g.setColor(Color.black);
        g.drawRect(30*bitişY,30*bitişX,30,30);

        g.drawImage(image5.getImage(), 30*başlangıçY,30*başlangıçX , 30, 30, this);

        g.drawImage(image8.getImage(), 30*bitişY, 30*bitişX, 30, 30,this);


        int enKısaYolKareSayısı = enKısaYol.size()/2;


        Font labelFont = new Font("Ariel", Font.BOLD, 16);

        g.setFont(labelFont);

        g.drawString("Toplam Süre: " + Long.toString(totalDuration / 1000) + " sn", 0, (matris.length + 1) * 30);
        g.drawString("Toplam Geçilen Kare Sayısı:  " + Integer.toString(count+2), 0, (matris.length + 3) * 30);
        g.drawString("En kısa yol kare sayısı:  " + Integer.toString(enKısaYolKareSayısı), 0, (matris.length + 2) * 30);



    }





    public  void hafızaTut(int matris[][],int hafızaMatris[][],ArrayList<Integer> path)
    {


        for (int i = 0; i < matris.length; i++) {

            for (int j = 0; j < matris[0].length; j++) {

                hafızaMatris[i][j] = 1;
            }

        }


        for (int i = 0; i < path.size()-2; i=i+2) {


            if(hafızaMatris[path.get(i)][path.get(i+1)] == 0)
                hafızaMatris[path.get(i)][path.get(i+1)] = 0;



            if(matris[path.get(i)][path.get(i+1) +1] == 0)
            {
                hafızaMatris[path.get(i)][path.get(i+1) +1] = 0;
            }


            if(matris[path.get(i)][path.get(i+1) -1] == 0)
            {
                hafızaMatris[path.get(i)][path.get(i+1) -1] = 0;
            }

            if(matris[path.get(i) +1][path.get(i+1) ] == 0)
            {
                hafızaMatris[path.get(i) +1][path.get(i+1)] = 0;
            }

            if(matris[path.get(i) -1][path.get(i+1) ] == 0)
            {
                hafızaMatris[path.get(i) -1][path.get(i+1)] = 0;
            }



            if(matris[path.get(i)][path.get(i+1) +1] == 1   || matris[path.get(i)][path.get(i+1) +1] == 2 ||   matris[path.get(i)][path.get(i+1) +1] == 3 )
            {
                hafızaMatris[path.get(i)][path.get(i+1) +1] = 2;
            }


            if(matris[path.get(i)][path.get(i+1) -1] == 1 || matris[path.get(i)][path.get(i+1) -1] == 2 || matris[path.get(i)][path.get(i+1) -1] == 3)
            {
                hafızaMatris[path.get(i)][path.get(i+1) -1] = 2;
            }

            if(matris[path.get(i) +1][path.get(i+1) ] == 1 || matris[path.get(i) +1][path.get(i+1) ] == 2 ||  matris[path.get(i) +1][path.get(i+1) ] == 3)
            {
                hafızaMatris[path.get(i) +1][path.get(i+1)] =2;
            }

            if(matris[path.get(i) -1][path.get(i+1) ] == 1 || matris[path.get(i) -1][path.get(i+1) ] == 2 || matris[path.get(i) -1][path.get(i+1) ] == 3)
            {
                hafızaMatris[path.get(i) -1][path.get(i+1)] = 2;
            }

        }

    }


    public static void main(String[] args) {



        ArrayList<String> url1Veri = new ArrayList<>();
        ArrayList<String> url2Veri = new ArrayList<>();
        String adres1 = "http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt";
        String adres2 = "http://bilgisayar.kocaeli.edu.tr/prolab2/url2.txt";

        UrlOku(adres1, url1Veri);
        UrlOku(adres2, url2Veri);

        int matris1[][];
        int matris2[][];

        matris1 = new int[url1Veri.size() + 2][url1Veri.get(0).length() + 2];
        matris2 = new int[url2Veri.size() + 2][url2Veri.get(0).length() + 2];
        Izgara ızgara1 = new Izgara();
        Izgara ızgara2 = new Izgara();
        ızgara1.ızgaraOlustur(url1Veri, matris1);
        ızgara2.ızgaraOlustur(url2Veri, matris2);

        /*
        JFrame frame = new JFrame();

        String str = "C:/Users/TAYYİB/OneDrive/Masaüstü/intellij/YeniProje1/wall.jpg";
        String str2 = "C:/Users/TAYYİB/OneDrive/Masaüstü/intellij/YeniProje1/arka22.png";


        try {
            duvar = ImageIO.read(new File(str));
            duvar1 = Yeniboyut(duvar, 750, 750);
        } catch (IOException e) {
        }
        try {
            önduvar = ImageIO.read(new File(str2));
            önduvar1 = Yeniboyut(önduvar, 450, 450);
        } catch (IOException e) {
        }


        ImageIcon game2;
        game2 = new ImageIcon(duvar1);
        JLabel arka = new JLabel(game2);
        ImageIcon game3;
        game3 = new ImageIcon(önduvar1);
        JLabel arka1 = new JLabel(game3);
        arka.setSize(750, 750);

        arka1.setSize(750, 750);
        arka1.setLocation(0, -100);


        JButton btn1 = new JButton("OYUN 1");
        JButton btn2 = new JButton("OYUN 2");
        Font font = new Font("Ariel", Font.BOLD, 17);
        btn1.setBounds(240, 300, 120, 50);
        btn2.setBounds(390, 300, 120, 50);
        btn1.setBackground(Color.ORANGE);
        btn1.setForeground(Color.WHITE);
        btn2.setBackground(Color.ORANGE);
        btn2.setForeground(Color.WHITE);
        btn1.setFont(font);
        btn2.setFont(font);





        ImageIcon game1;
        game1 = new ImageIcon("letsPlay.png");
        JLabel game = new JLabel(game1);
        game.setSize(750, 750);
*/


        JFrame frame = new JFrame();

        JButton btn1 = new JButton("OYUN 1");
        JButton btn2 = new JButton("OYUN 2");
        Font font = new Font("Ariel", Font.BOLD, 17);
        btn1.setBounds(245, 330, 110, 50);
        btn2.setBounds(395, 330, 110, 50);

        Color buton = new Color(239, 120, 232);


        btn1.setBackground(buton);
        btn1.setForeground(Color.WHITE);
        btn2.setBackground(buton);
        btn2.setForeground(Color.WHITE);
        btn1.setFont(font);
        btn2.setFont(font);

        ImageIcon game1;
        game1 = new ImageIcon("letsPlay.png");
        JLabel game = new JLabel(game1);
        game.setSize(750, 750);




        ImageIcon game2;
        game2 = new ImageIcon("duvar.jpg");
        JLabel arka = new JLabel(game2);

        arka.setSize(750, 750);







        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);

                JFrame frame1 = new JFrame();


                JButton btn1 = new JButton("IZGARA 1");
                JButton btn2 = new JButton("IZGARA 2");

                btn1.setBounds(320, 245, 120, 40);
                btn2.setBounds(320, 425, 120, 40);

                btn1.setFont(font);
                btn2.setFont(font);

                btn1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {



                        frame1.setVisible(false);
                        Main view = null;
                        try {
                            view = new Main(matris1);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        view.setVisible(true);


                    }
                });

                btn2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame1.setVisible(false);
                        Main view = null;
                        try {
                            view = new Main(matris2);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        view.setVisible(true);


                    }
                });


                frame1.add(btn1);
                frame1.add(btn2);

                frame1.add(arka);
                frame1.setSize(750, 750);
                frame1.setLocationRelativeTo(null);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setLayout(null);
                frame1.setVisible(true);

            }
        });




        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);

                JFrame frame1 = new JFrame();
                frame1.setSize(750, 750);
                Font font1 = new Font("Ariel", Font.PLAIN, 17);

                JPanel panel = new JPanel();
                panel.setSize(750, 750);

                panel.setLayout(null);
                panel.setVisible(true);

                JLabel label = new JLabel();
                label.setText("<html><b><font color='white'>Labirent Boyutlarını Giriniz:</font></b>");
                label.setSize(500, 40);
                label.setLocation(120, 290);
                label.setFont(font);

                JTextField row = new JTextField();

                row.setSize(55, 40);
                row.setLocation(350, 290);
                row.setFont(font1);
                JLabel label1 = new JLabel();
                label1.setText("<html><b><font color='white'>X</font></b>");
                label1.setSize(20, 40);
                label1.setLocation(420, 290);
                label1.setFont(font);

                JTextField col = new JTextField();
                col.setSize(55, 40);
                col.setLocation(450, 290);
                col.setFont(font1);

                JLabel label2 = new JLabel();


                label2.setText("<html><b><font color='white'>(Max 34x34)</font></b>");

                label2.setSize(200, 40);
                label2.setLocation(520, 290);

                label2.setFont(font1);
                label2.setBackground(Color.WHITE);

                JButton btn1 = new JButton("Labirent Oluştur");
                btn1.setBounds(280, 360, 180, 40);
                btn1.setFont(font);
                btn1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int satir,sutun;

                        frame1.setVisible(false);

                        satir = Integer.parseInt(row.getText()) ;
                        sutun = Integer.parseInt(col.getText()) ;

                        if (satir % 2 == 0) {
                            satir = satir + 1;
                        }
                        if (sutun % 2 == 0) {
                            sutun = sutun + 1;
                        }

                        System.out.println(satir);
                        System.out.println(sutun);



                        int maze[][] = new int[satir][sutun];

                        engel labirent = null;
                        try {
                            labirent = new engel(maze,satir,sutun);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        labirent.setVisible(true);

                        panel.setVisible(false);


                    }
                });

                panel.add(btn1);
                panel.add(row);
                panel.add(col);
                panel.add(label);
                panel.add(label1);
                panel.add(label2);
                panel.add(arka);
                frame1.add(panel);

                frame1.setLocationRelativeTo(null);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setLayout(null);
                frame1.setVisible(true);
            }
        });



        frame.add(btn1);
        frame.add(btn2);
        frame.add(game);
        frame.setSize(750, 750);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);

    }


    public static boolean YolAra(int [][] matris, int başlangıçX,int başlangıçY,int bitişX,int bitişY, ArrayList<Integer> path)
    {

        Robot robot = new Robot();
        robot.x = başlangıçX;
        robot.y = başlangıçY;


        if(robot.x == bitişX && bitişY == robot.y)
        {
            path.add(robot.x);
            path.add(robot.y);
            return true;

        }

        if(matris[robot.x][robot.y] == 0)
        {
            matris[robot.x][robot.y] = 5;


            if(YolAra(matris, robot.x, robot.y +1,bitişX,bitişY, path))
            {
                path.add(robot.x);
                path.add(robot.y);
                return true;
            }


            if(YolAra(matris, robot.x, robot.y-1,bitişX,bitişY, path))
            {
                path.add(robot.x);
                path.add(robot.y);
                return true;

            }


            if(YolAra(matris, robot.x -1, robot.y,bitişX,bitişY ,path))
            {

                path.add(robot.x);
                path.add(robot.y);
                return true;

            }



            if(YolAra(matris, robot.x+1,robot.y,bitişX,bitişY, path))
            {
                path.add(robot.x);
                path.add(robot.y);
                return true;


            }





        }
        return false;
    }




    public static BufferedImage Yeniboyut(BufferedImage resim, int x, int y) {
        BufferedImage yeniresim = new BufferedImage(x, y, resim.getType());
        Graphics2D ty = yeniresim.createGraphics();
        ty.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        ty.drawImage(resim, 0, 0, x, y, null);
        ty.dispose();
        return yeniresim;
    }


}

