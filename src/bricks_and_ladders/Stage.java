package bricks_and_ladders;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Stage extends Thread {
    private Image completeSign = new ImageIcon(Main.class.getResource("../images/completeSign.png"))
            .getImage();

    // ������ ���� ����Ʈ
    private ArrayList<Fruit> itemList = new ArrayList<>();

    // ���� ���� ����Ʈ
    private ArrayList<Monster> monList = new ArrayList<>();

    // �������� Ŭ���� �޽��� ��ġ �ʱ�ȭ
    private int x = 440;
    private int y = 0;

    public static boolean stageClear = false;

    public static boolean eatGrape = false;

    // ���� Ż��� ����
    private boolean stop = false;

    public ArrayList<Monster> getMonList() {
        return monList;
    }

    public ArrayList<Fruit> getItemList() {
        return itemList;
    }

    public void makeItems(Fruit item) {
        itemList.add(item);
    }

    public void makeMons(Monster monster) {
        monList.add(monster);
    }

    public void drawItems(Graphics g) {
        for (int i=0; i < itemList.size(); i++) {
            if (itemList.get(i) instanceof Banana) {
                Banana ban = (Banana)itemList.get(i);
                g.drawImage(ban.getBanana(), ban.getPos_x(), ban.getPos_y(), null);
            }
            else if (itemList.get(i) instanceof Grape) {
                Grape gar = (Grape)itemList.get(i);
                g.drawImage(gar.getGrape(), gar.getPos_x(), gar.getPos_y(), null);
            }
        }
    }

    public void eatItems() {
        for (int i=itemList.size()-1; i >= 0; i--) {
            if ( // �������� ���ʿ��� ������ ��
                    itemList.get(i).getPos_x() <= BricksAndLadders.alfy.getPos_X()+64
                            && BricksAndLadders.alfy.getPos_X()+64 <= itemList.get(i).getPos_x() + 56
                            && BricksAndLadders.alfy.getPos_Y()+128 >= itemList.get(i).getPos_y()
                            && BricksAndLadders.alfy.getPos_Y() <= itemList.get(i).getPos_y()
                            // �������� �����ʿ��� ������ ��
                            || BricksAndLadders.alfy.getPos_X() <= itemList.get(i).getPos_x() + 56
                            && BricksAndLadders.alfy.getPos_X() >= itemList.get(i).getPos_x()
                            && BricksAndLadders.alfy.getPos_Y()+128 >= itemList.get(i).getPos_y()
                            && BricksAndLadders.alfy.getPos_Y() <= itemList.get(i).getPos_y())
            {
                if (itemList.get(i) instanceof Banana){}

                else if (itemList.get(i) instanceof Grape) {
                        eatGrape = true;
                    }


                itemList.remove(i);
            }
        }
    }

    public void drawMons(Graphics g) {
        for (int i=0; i < monList.size(); i++) {
            g.drawImage(monList.get(i).getNowState(), monList.get(i).getX(), monList.get(i).getY(), null);
        }
    }

    public void touchMons() {
        for (int i=monList.size()-1; i >= 0; i--) {
            if ( // ������ ���ʿ��� ������ ��
                    monList.get(i).getX() <= BricksAndLadders.alfy.getPos_X()+64
                            && BricksAndLadders.alfy.getPos_X()+64 <= monList.get(i).getX()+45
                            && BricksAndLadders.alfy.getPos_Y()+128 >= monList.get(i).getY()
                            && BricksAndLadders.alfy.getPos_Y() <= monList.get(i).getY()
                            // ������ �����ʿ��� ������ ��
                            || BricksAndLadders.alfy.getPos_X() <= monList.get(i).getX() + 45
                            && BricksAndLadders.alfy.getPos_X() >= monList.get(i).getX()
                            && BricksAndLadders.alfy.getPos_Y()+128 >= monList.get(i).getY()
                            && BricksAndLadders.alfy.getPos_Y() <= monList.get(i).getY())
            {
                if (eatGrape) {
                    monList.get(i).close();
                    monList.remove(i);
                }
                else if (!eatGrape) {

                    for (int j=0; j < monList.size(); j++) {
                        monList.get(j).close();
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {}

                    close();
                    Main.bal.restartStage();

                }
            }
        }
    }

    public void drawSign(Graphics g) {
        g.drawImage(completeSign, x, y, null);
    }

    public void close() {
        this.interrupt();
        stop = true;
    }

    @Override
    public void run() {
        makeItems(new Banana(8, 503));
        makeItems(new Banana(64, 503));
        makeItems(new Banana(1161, 503));
        makeItems(new Banana(1216, 503));
        makeItems(new Banana(110, 172));
        makeItems(new Banana(900, 172));
        makeItems(new Grape(690, 172));
        makeItems(new Grape(420, 172));

        monList.add(BricksAndLadders.m1);
        monList.add(BricksAndLadders.m2);
        monList.add(BricksAndLadders.m3);

        while (itemList.size() > 0 && !isInterrupted()) {
            eatItems();
            touchMons();
            BricksAndLadders.alfy.checkLaddering();
            BricksAndLadders.alfy.dropAlfy();
        }

        if (!stop) {
            stageClear = true;

            while (y < 320) {
                y += 20;

                try {
                    Thread.sleep(50);
                } catch (Exception e) {}
            }
        }
    }
}



