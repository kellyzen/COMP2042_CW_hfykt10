package com.example.demo.game;

public class Down implements Direction{
    int n = GameScene.getN();
    Cell[][] cells = GameScene.getCell();
    Movement movement = new Movement();

    @Override
    public void move() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                movement.moveVertically(i, j, passDestination(i, j), 1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }
    }

    private int passDestination(int i, int j) {
        int coordinate = i;
        for (int k = i + 1; k <= n - 1; k++) {
            if (cells[k][j].getNumber() != 0) {
                coordinate = k - 1;
                break;
            } else if (k == n - 1) {
                coordinate = n - 1;
            }
        }
        return coordinate;
    }
}
