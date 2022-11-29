package com.example.game.game.cell;

import com.example.game.game.GameScene;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.Random;

public class CreateRandomCell{
    private Group root;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    int n = GameScene.getN();
    Cell[][] cells = GameScene.getCell();

    /**
     * create new cells
     *
     * @param root Group root
     */
    public long createNewCell(Group root) {
        this.root = root;

        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound = 0, bForBound = 0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < n-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }
        return randomFillNumber(aForBound, bForBound, emptyCells);
    }

    /**
     * randomly fill number on new cells
     */
    private long randomFillNumber(int aForBound, int bForBound, Cell[][] emptyCells) {
        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
        int xCell, yCell;
        xCell = random.nextInt(aForBound+1);
        yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
            return 2;
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
            return 4;
        }
    }
}
