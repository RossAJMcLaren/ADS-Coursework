import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Created by Ross Mclaren on 17/11/2016.
 */
public class JRisk {

    private JFrame mainMap;
    private Polygon poly;

    public JRisk(ArrayList<City> cityList) {

        initComponents(cityList);

    }


    public void initComponents(ArrayList<City> cityList) {
        mainMap = new JFrame();
        mainMap.setResizable(false);

        mainMap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int[] xPoly = new int[cityList.size()];
        int[] yPoly = new int[cityList.size()];


        for (int i = 0; i < cityList.size(); i++) {
            xPoly[i] = (int) cityList.get(i).cityCoOrdinates.getX()/5;
            yPoly[i] = (int) cityList.get(i).cityCoOrdinates.getY()/5;
        }

        poly = new Polygon(xPoly, yPoly, xPoly.length);

        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.drawPolygon(poly);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1980/4, 1080/4);
            }
        };
        mainMap.add(p);
        mainMap.pack();
        mainMap.setVisible(true);

        BufferedImage bImg = new BufferedImage(mainMap.getWidth(), mainMap.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        mainMap.paintAll(cg);
        try {
            if (ImageIO.write(bImg, "png", new File("./output_image.png")))
            {
                System.out.println("-- saved");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
