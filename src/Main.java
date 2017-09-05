

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Main extends Application  {

    private int boardCount = 14;
    private Dominoe currentDominoe;
    private int dominoePostion = 0;
    private int rightX = 1;
    private int rightY = 1;
    private int leftY = -1;
    private int leftX =1;

    @Override
    public void start(Stage primaryStage){
        Canvas canvas = new Canvas(900,200);

        canvas.setStyle("-fx-border-color: white;");

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1200, 250);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        dominoeLibrary lib = new dominoeLibrary();

        Player person = new Player(lib);
        Player computers = new Player(lib);


       VBox top = new VBox();
        Button left = new Button("Left Placement");
        Button right = new Button("Right Placement");
        Button turn = new Button("Flip Dominoe");

        top.setSpacing(50);
        top.setAlignment(Pos.CENTER_RIGHT);



        Label boneyard = new Label("Boneyard: "  +  boardCount);
        boneyard.setAlignment(Pos.CENTER_RIGHT);
        boneyard.setPrefWidth(scene.getWidth()/2 + 35);
        boneyard.setFont(Font.font(20));

        primaryStage.setTitle("Domino Game");

        root.setTop(boneyard);
        root.setCenter(canvas);
        //bottom.setStyle("-fx-border-color: red;");

        root.setRight(top);

        Board board = new Board(canvas, person.getH().dom, gc);

        board.DrawHand(person.getH().dom);

        board.DrawBoardDominoe(lib.draw().getDominoPicture(), 0,0);
        board.DrawHand(person.getH().dom);
        boardCount--;
        boneyard.setText("Boneyard: " + boardCount);

        turn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                board.flipDominoe(person.getH().dom[dominoePostion].getDominoPicture(), dominoePostion,person.getH().dom[dominoePostion],currentDominoe.getRightNumber(),currentDominoe.getLeftNumber());
              //  person.getH().dom[dominoePostion] = currentDominoe;
                System.out.println(currentDominoe.toString());

            }
        });

        right.setOnAction(new EventHandler<ActionEvent>() {
                              @Override
                              public void handle(ActionEvent event) {
                                  if(boardCount > 0 && currentDominoe != null) {
                                      board.DrawBoardDominoe(currentDominoe.getDominoPicture(), rightX, rightY %2);
                                      person.getH().dom[dominoePostion] = lib.draw();
                                      board.DrawHand(person.getH().dom);
                                      boardCount--;
                                      rightX++;
                                      rightY++;
                                  }
                                  boneyard.setText("Boneyard: " + boardCount);
                              }
                          }

        );

        left.setOnAction(new EventHandler<ActionEvent>() {
                              @Override
                              public void handle(ActionEvent event) {
                                  if(boardCount > 0 && currentDominoe != null) {
                                      board.DrawBoardDominoe(currentDominoe.getDominoPicture(), leftY, leftX %2);
                                      person.getH().dom[dominoePostion] = lib.draw();
                                      board.DrawHand(person.getH().dom);
                                      boardCount--;
                                      leftY--;
                                      leftX++;
                                  }
                                  boneyard.setText("Boneyard: " + boardCount);
                              }
                          }

        );


        canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                if (mouseEvent.getY() > canvas.getHeight() - 54) {
                    for (int i = 0; i < 7; i++) {

                        if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 0 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (0 * 40) + 54
                                ) {
                            currentDominoe = person.getH().dom[0];
                            dominoePostion = 0;
                            System.out.println(currentDominoe.toString());

                            boneyard.setText("Boneyard: " + boardCount);
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 1 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (1 * 60) + 54
                                ){
                            currentDominoe = person.getH().dom[1];
                            dominoePostion = 1;

                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 2 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (2 * 60) + 54
                                ) {


                            currentDominoe = person.getH().dom[2];
                            dominoePostion = 2;

                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 3 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (3 * 60) + 54
                                ) {
                            currentDominoe = person.getH().dom[3];
                            dominoePostion = 3;
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 4 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (4 * 60) + 54
                                ) {

                            currentDominoe = person.getH().dom[4];
                            dominoePostion = 4;
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 5 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (5 * 60) + 54
                                ) {

                            currentDominoe = person.getH().dom[5];
                            dominoePostion = 5;
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 6 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (6 * 60) + 54
                                ) {

                            currentDominoe = person.getH().dom[6];
                            dominoePostion = 6;
                        }
                    }


                }


            }});

        root.setStyle("-fx-background-color: green");

        top.getChildren().addAll(right, left, turn);

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
    public static void main(String[] args)  {



launch(args);

    }
}
