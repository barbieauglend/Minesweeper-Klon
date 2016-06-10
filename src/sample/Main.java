package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import Field.Field;

public class Main extends Application {
    final private int menuBarHeight = 31;
    Stage primaryStage;
    Canvas canvas;
    GraphicsContext graphicsContext;
    Field field;

    private void setPrimaryStageZise() {
        primaryStage.setMinWidth(canvas.getWidth() + 40);
        primaryStage.setMinHeight(canvas.getHeight() + menuBarHeight + 40);
        primaryStage.setMaxWidth(canvas.getWidth() + 40);
        primaryStage.setMaxHeight(canvas.getHeight() + menuBarHeight + 40);
    }

    private void checkForEnd() {
        boolean end = false;
        String message = "";

        if (field.hasWon()) {
            message = "Yay! Sehr gur gemacht!";
            end = true;
        }
        else if (field.hasLost()) {
            field.setLostFieldView();
            message = "Ownnnn! K.O.! :(";
            end = true;
        }

        if (end) {
            StartDialog dialog = new StartDialog(message, false);
            Settings settings = dialog.getSettings();
            if(settings == null){
                System.exit(0);
            }
            field.setNewField(settings);
            setPrimaryStageZise();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Minesweeper Klon");
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.color(0.87, 0.87, 0.87), CornerRadii.EMPTY, Insets.EMPTY)));

        MenuBar menuBar = new MenuBar();
        Menu optionsMenu = new Menu("Menu");
        MenuItem newGameMenuItem = new MenuItem("Level einstellen");
        MenuItem exitMenuItem = new MenuItem("Exit");
        optionsMenu.getItems().addAll(newGameMenuItem, exitMenuItem);
        Menu levelMenu = new Menu("Level");
        MenuItem easyMenuItem = new MenuItem("Leicht");
        MenuItem mediumMenuItem = new MenuItem("Medium");
        MenuItem hardMenuItem = new MenuItem("McGyver");
        levelMenu.getItems().addAll(easyMenuItem, mediumMenuItem, hardMenuItem);
        menuBar.getMenus().add(optionsMenu);
        menuBar.getMenus().add(levelMenu);
        
        root.setTop(menuBar);

        canvas = new Canvas();
        root.setCenter(canvas);
        graphicsContext = canvas.getGraphicsContext2D();

        menuBar.setPrefHeight(menuBarHeight);

        Level level = Persist.loadLevel3();
        Settings settings = new Settings(level.getWidth(), level.getHeight(), level.getMines());
        if (settings == null) {
            return;
        }
        else {
            field = new Field(canvas, graphicsContext, settings);
            setPrimaryStageZise();
        }

        primaryStage.setScene(new Scene(root, field.getCanvasWidth(), field.getCanvasHeight()));
        primaryStage.show();

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            if (event.isPrimaryButtonDown()) {
                field.leftButtonPressed((int)event.getX(), (int)event.getY());
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                field.leftButtonReleased((int)event.getX(), (int)event.getY());
                checkForEnd();
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                field.rightButtonClicked((int) event.getX(), (int) event.getY());
                checkForEnd();
            }
        });

        newGameMenuItem.setOnAction((event) -> {
            StartDialog newGameDialog = new StartDialog("Stelle selbst ein: ", true);
            Settings newGameSettings = newGameDialog.getSettings();
            if (newGameSettings != null) {
                field.setNewField(newGameSettings);
                setPrimaryStageZise();
            }
        });

        exitMenuItem.setOnAction((event) -> {
            System.exit(0);
        });
        
        easyMenuItem.setOnAction((event) -> {
            Level choosen = Persist.loadLevel3();
            Settings newGameSettings = new Settings(choosen.getWidth(), choosen.getHeight(), choosen.getMines());
            if (newGameSettings != null) {
                field.setNewField(newGameSettings);
                setPrimaryStageZise();
            }
        });
        
        mediumMenuItem.setOnAction((event) -> {
            Level choosen = Persist.loadLevel2();
            Settings newGameSettings = new Settings(choosen.getWidth(), choosen.getHeight(), choosen.getMines());
            if (newGameSettings != null) {
                field.setNewField(newGameSettings);
                setPrimaryStageZise();
            }
        });
        
        hardMenuItem.setOnAction((event) -> {
            Level choosen = Persist.loadLevel();
            Settings newGameSettings = new Settings(choosen.getWidth(), choosen.getHeight(), choosen.getMines());
            if (newGameSettings != null) {
                field.setNewField(newGameSettings);
                setPrimaryStageZise();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
