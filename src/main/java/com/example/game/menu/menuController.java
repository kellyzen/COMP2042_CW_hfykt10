package com.example.game.menu;

import com.example.game.components.dialogComponent.QuitDialog;
import com.example.game.game.GameScene;
import com.example.game.theme.Theme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuController implements Initializable {
    static final int WIDTH = 900;
    static final int HEIGHT = 600;

    Stage primaryStage = Menu.getPrimaryStage();
    private Group gameRoot = new Group();
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT);
    Theme newTheme = new Theme();

    private Color backgroundScene;
    @FXML Label highScoreLabel;
    @FXML Label usernameLabel;
    @FXML private ChoiceBox<String> menuChoiceBox;

    private final String[] theme = {"Dark", "Light", "Fantasy", "Nymph", "Default"};

    public void setUsernameLabel(String text) {
        usernameLabel.setText(text);
    }

    public void setHighScoreLabel(Long score) {
        highScoreLabel.setText(String.valueOf(score));
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    public void setBackgroundScene(Color backgroundScene) {
        this.backgroundScene = backgroundScene;
    }

    public void switchToGame() {
        String themePath = Theme.getTheme();
        changeBackgroundScene();
        //set root and scene for game
        Group gameRoot = new Group();
        setGameRoot(gameRoot);
        gameRoot.getStylesheets().add(getClass().getResource(themePath).toString());
        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, backgroundScene);
        setGameScene(gameScene);
        primaryStage.setScene(gameScene);
        //set root and scene for end game
        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, backgroundScene);
        endgameRoot.getStylesheets().add(getClass().getResource(themePath).toString());

        //reset game  mode
        changeImage();

        //set grid number (n) and startGame method in GameScene
        GameScene game = new GameScene();
        GameScene.setN(n);
        game.startGame(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);

        //set title and full screen
        primaryStage.setTitle("2048 Game");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    public void switchToRank() throws IOException {
        String themePath = Theme.getTheme();
        Parent rankRoot = FXMLLoader.load(getClass().getResource("/com/example/game/GUI/rank.fxml"));
        Scene rankScene = new Scene(rankRoot);
        rankRoot.getStylesheets().add(getClass().getResource(themePath).toString());
        primaryStage.setScene(rankScene);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public void quitGame() {
        new QuitDialog();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        menuChoiceBox.getItems().addAll(theme);
        menuChoiceBox.setOnAction(this::changeTheme);

    }

    public void changeTheme(ActionEvent event) {
        gameScene = getGameScene();

        String theme = menuChoiceBox.getValue();
        menuChoiceBox.getScene().getRoot().getStylesheets().remove(getClass().getResource("/com/example/game/styling/dark.css").toString());
        menuChoiceBox.getScene().getRoot().getStylesheets().remove(getClass().getResource("/com/example/game/styling/light.css").toString());
        menuChoiceBox.getScene().getRoot().getStylesheets().remove(getClass().getResource("/com/example/game/styling/fantasy.css").toString());
        menuChoiceBox.getScene().getRoot().getStylesheets().remove(getClass().getResource("/com/example/game/styling/nymph.css").toString());
        menuChoiceBox.getScene().getRoot().getStylesheets().remove(getClass().getResource("/com/example/game/styling/default.css").toString());

        switch (theme) {
            case "Dark" -> {
                menuChoiceBox.getScene().getRoot().getStylesheets().add(getClass().getResource("/com/example/game/styling/dark.css").toString());
                newTheme.newTheme("Dark");
            }
            case "Light" -> {
                menuChoiceBox.getScene().getRoot().getStylesheets().add(getClass().getResource("/com/example/game/styling/light.css").toString());
                newTheme.newTheme("Light");
            }
            case "Fantasy" -> {
                menuChoiceBox.getScene().getRoot().getStylesheets().add(getClass().getResource("/com/example/game/styling/fantasy.css").toString());
                newTheme.newTheme("Fantasy");
            }
            case "Nymph" -> {
                menuChoiceBox.getScene().getRoot().getStylesheets().add(getClass().getResource("/com/example/game/styling/nymph.css").toString());
                newTheme.newTheme("Nymph");
            }
            case "Default" -> {
                menuChoiceBox.getScene().getRoot().getStylesheets().add(getClass().getResource("/com/example/game/styling/default.css").toString());
                newTheme.newTheme("Default");
            }
        }

        changeBackgroundScene();
    }

    private void changeBackgroundScene() {
        switch(Theme.getTheme()){
            case "/com/example/game/styling/dark.css" -> {
                setBackgroundScene(Color.web("#373e43"));
            }
            case "/com/example/game/styling/light.css" -> {
                setBackgroundScene(Color.web("#eee"));
            }
            case "/com/example/game/styling/fantasy.css" -> {
                setBackgroundScene(Color.web("#D6B9F3"));
            }
            case "/com/example/game/styling/nymph.css" -> {
                setBackgroundScene(Color.web("#5c9aaf"));
            }
            case "/com/example/game/styling/default.css" -> {
                setBackgroundScene(Color.rgb(189, 177, 92));
            }
        }
    }

    private int mode = 1;
    private static int n = 4;
    private final int numOfMode = 4;
    @FXML ImageView imageView;
    @FXML Button nextButton;
    @FXML Button prevButton;
    @FXML Label modeLabel;

    Image tinyMode = new Image(getClass().getResourceAsStream("/com/example/game/images/tiny.png"));
    Image classicMode = new Image(getClass().getResourceAsStream("/com/example/game/images/classic.png"));
    Image largeMode = new Image(getClass().getResourceAsStream("/com/example/game/images/large.png"));
    Image hugeMode = new Image(getClass().getResourceAsStream("/com/example/game/images/huge.png"));

    public void nextMode() {
        mode = (mode + 1) % numOfMode;
        changeImage();
    }

    public void prevMode() {
        mode = (mode - 1) % numOfMode;
        changeImage();
    }

    private void changeImage() {
        switch (mode) {
            case 0 -> {
                imageView.setImage(tinyMode);
                modeLabel.setText("Tiny (3x3)");
                n = 3;
            }
            case 1, -3 -> {
                imageView.setImage(classicMode);
                modeLabel.setText("Classic (4x4)");
                n = 4;
            }
            case 2, -2 -> {
                imageView.setImage(largeMode);
                modeLabel.setText("Large (5x5)");
                n = 5;
            }
            case 3, -1 -> {
                imageView.setImage(hugeMode);
                modeLabel.setText("Huge (6x6)");
                n = 6;
            }
        }
    }
}

