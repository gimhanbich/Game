package bricks_and_ladders;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {


    // ���� �� ���� or ������ ����Ű�� ���ȴ��� Ȯ���ϱ� ���� ����
    public static boolean pressedLeftKey = false;
    public static boolean pressedRightKey = false;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            BricksAndLadders.alfy.moveToLeft();
            pressedLeftKey = true;
        }

        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            BricksAndLadders.alfy.moveToRight();
            pressedRightKey = true;
        }

        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            BricksAndLadders.alfy.upper();
        }

        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            BricksAndLadders.alfy.lower();
        }

        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            BricksAndLadders.alfy.jump();


        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            BricksAndLadders.alfy.keyRelease();
            pressedLeftKey = false;
        }

        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            BricksAndLadders.alfy.keyRelease();
            pressedRightKey = false;
        }

        else if (e.getKeyCode() == KeyEvent.VK_UP) {

        }

        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

        }

        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            BricksAndLadders.alfy.descend();
        }
    }
}


