package bricks_and_ladders;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BricksAndLadders extends JFrame {
    // ���� ���۸��� Ȱ���ϱ� ���� ���� ����
    private Image screenImage;
    private Graphics screenGraphic;

    // ����� �̹���
    private Image background = new ImageIcon(Main.class.getResource("../images/background1.png"))
            .getImage();
    private Image brick = new ImageIcon(Main.class.getResource("../images/brick.png"))
            .getImage();
    private Image brick8 = new ImageIcon(Main.class.getResource("../images/brick8.png"))
            .getImage();
    private Image ladder1 = new ImageIcon(Main.class.getResource("../images/ladder1.png"))
            .getImage();

    // �������� ����
    public static Stage stage = new Stage();

    // ���� ĳ���� ����
    public static Alfy alfy = new Alfy();

    // ���� ����
    public static Monster m1 = new Monster(109, 175, 109, 485);
    public static Monster m2 = new Monster(643 ,175, 643, 1019);
    public static Monster m3 = new Monster(856, 505, 856, 1232);

    // ������
    public BricksAndLadders() {
        // Ÿ��Ʋ �ٿ� ��� �̸� ����
        setTitle("Bricks and Ladders");
        // ȭ�� ũ�� ����
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        // ũ�� ���� ���� ���� ����
        setResizable(false);
        // ���� �� ȭ�� ��� ��ġ ����(���)
        setLocationRelativeTo(null);
        // ȭ���� ������ ���α׷��� ���� ����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ȭ���� ���̰� ��
        setVisible(true);

        // Ű ������ ����
        addKeyListener(new KeyListener());

        // ���� �ʱ� ����
        alfy.setPosition(165, 425);
        alfy.setDirection("left");
        alfy.setState(alfy.getAlfyLeftMove());

        // �� ��ü�� ������ ����
        stage.start();
        m1.start();
        m2.start();
        m3.start();
    }

    public void paint(Graphics g) {
        // ������ũ�� ����
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        // ������ ������ũ���� ���Ͽ� Graphics ��ü ����
        screenGraphic = screenImage.getGraphics();
        // ������ screenGraphic ��ü�� �̿��Ͽ� ������ũ���� �׸�
        screenDraw(screenGraphic);
        // ������ũ���� �׸� ������ ���� ȭ�鿡 �׸�
        g.drawImage(screenImage, 0, 0, null);
    }

    // �ݺ��ؼ� �׸��� ������ stage�� monster�� ���� �����ص� ���� ���� ��� �׷������� => ���̰� �ǰ� �ᱹ ������ �ɸ�
    public void screenDraw(Graphics g) {
        // ���� ������ũ���� �׸�
        g.drawImage(background, 0, 0, null);
        g.drawImage(brick, 8, 550, null);
        g.drawImage(brick, 856, 550, null);
        g.drawImage(brick, 1063, 380, null);
        g.drawImage(brick8, 109, 220, null);
        g.drawImage(brick8, 643, 220, null);
        g.drawImage(ladder1, 316, 241, null);

        // ��, ���� ������ũ���� �׸�
        stage.drawItems(g);
        stage.drawMons(g);

        // �������� Ŭ���� �� �ش� �޽��� �׸���
        if (Stage.stageClear) {
            stage.drawSign(g);
        }

        // �ݺ��ؼ� ���� �׸���
        g.drawImage(alfy.getState(), alfy.getPos_X(), alfy.getPos_Y() , null);

        // paint() �Լ��� ���ư�
        this.repaint();
    }

    public void restartStage() {
        stage.getMonList().clear();
        stage.getItemList().clear();

        stage = new Stage();
        m1 = new Monster(109, 175, 109, 485);
        m2 = new Monster(643 ,175, 643, 1019);
        m3 = new Monster(856, 505, 856, 1232);

        alfy.setPosition(165, 425);
        alfy.setDirection("left");
        alfy.setState(alfy.getAlfyLeftMove());

        stage.start();
        m1.start();
        m2.start();
        m3.start();
    }
}





