

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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


        root.setRight(top);

        Board board = new Board(canvas, person.getH().dom, gc);
        board.DrawHand(person.getH().dom);



        board.setCenterDominoe(lib.draw());
        board.setRightDominoe(board.getCenterDominoe());
        board.DrawBoardDominoe(board.getCenterDominoe().getDominoPicture(), 0,0, 1);
        board.DrawHand(person.getH().dom);
        boardCount--;
        boneyard.setText("Boneyard: " + boardCount);

        turn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (currentDominoe != null) {
                    if (currentDominoe.getIsFlipped() == -1) {
                        board.flipDominoe(currentDominoe.getDominoPicture(), dominoePostion, currentDominoe, currentDominoe.getRightNumber(), currentDominoe.getLeftNumber(), currentDominoe.getIsFlipped() * -1);

                        System.out.println(currentDominoe.toString());
                        currentDominoe.setIsFlipped(1);
                    } else if (currentDominoe.getIsFlipped() == 0) {
                        currentDominoe.setIsFlipped(-1);
                        board.flipDominoe(currentDominoe.getDominoPicture(), dominoePostion,currentDominoe, currentDominoe.getRightNumber(), currentDominoe.getLeftNumber(), currentDominoe.getIsFlipped());

                        System.out.println(currentDominoe.toString());

                    } else if (currentDominoe.getIsFlipped() == 1) {
                        board.flipDominoe(currentDominoe.getDominoPicture(), dominoePostion, currentDominoe, currentDominoe.getRightNumber(), currentDominoe.getLeftNumber(), currentDominoe.getIsFlipped() * -1);

                        System.out.println(currentDominoe.toString());
                        currentDominoe.setIsFlipped(-1);
                    }


                }
            }
            });


        right.setOnAction(new EventHandler<ActionEvent>() {
                              @Override
                              public void handle(ActionEvent event) {
                                  if (currentDominoe != null && board.isLegal(board.getRightDominoe().getRightNumber(), currentDominoe.getLeftNumber())) {
                                      if (currentDominoe != null && currentDominoe.getIsFlipped() != -1 && currentDominoe.getLeftNumber() != -2) {



                                         if(boardCount > 0 ) {
                                             board.DrawBoardDominoe(currentDominoe.getDominoPicture(), rightX, rightY % 2, 1);
                                             //new dominoe in the hand
                                             person.getH().dom[dominoePostion] = lib.draw();
                                             rightX++;
                                             rightY++;
                                             boardCount--;
                                             board.setRightDominoe(currentDominoe);
                                         } else if(boardCount <= 0 && currentDominoe != board.getRightDominoe()){
                                             board.DrawBoardDominoe(currentDominoe.getDominoPicture(), rightX, rightY % 2, 1);
                                             System.out.println("currnet dominoe is " +currentDominoe.toString() + "\n boneyard = " + boardCount);
                                             board.setRightDominoe(currentDominoe);
                                             System.out.println("right dominoe is " + board.getRightDominoe());
                                             person.getH().dom[dominoePostion] = lib.getBlankDominoe();
                                             board.flipDominoe(lib.getBlankDominoePic(),dominoePostion,lib.getBlankDominoe() , -2, -2, 1);
                                             System.out.println("after flip current dominoe is " +  currentDominoe);
                                             rightX++;
                                             rightY++;

                                             System.out.println("current dominoe now is " + currentDominoe.toString());
                                         }





                                         //sets current dominoe
                                        //currentDominoe = person.getH().dom[dominoePostion];
                                        //draw hand
                                       board.DrawHand(person.getH().dom);




                                      } else if (currentDominoe != null && currentDominoe.getIsFlipped() == -1 && currentDominoe.getLeftNumber() != -2) {



                                          if(boardCount > 0 ) {
                                              board.DrawBoardDominoe(currentDominoe.getDominoPicture(), rightX, rightY % 2, -1);
                                              //new dominoe in the hand
                                              person.getH().dom[dominoePostion] = lib.draw();
                                              rightX++;
                                              rightY++;
                                              boardCount--;
                                              board.setRightDominoe(currentDominoe);
                                          } else if(boardCount <= 0 && currentDominoe != board.getRightDominoe()){
                                              board.DrawBoardDominoe(currentDominoe.getDominoPicture(), rightX, rightY % 2, -1);
                                              System.out.println("currnet dominoe is " +currentDominoe.toString() + "\n boneyard = " + boardCount);
                                              board.setRightDominoe(currentDominoe);
                                              System.out.println("right dominoe is " + board.getRightDominoe());
                                              person.getH().dom[dominoePostion] = lib.getBlankDominoe();
                                              board.flipDominoe(lib.getBlankDominoePic(),dominoePostion,lib.getBlankDominoe() , -2, -2, 1);
                                              System.out.println("after flip current dominoe is " +  currentDominoe);
                                              rightX++;
                                              rightY++;

                                              System.out.println("current dominoe now is " + currentDominoe.toString());
                                          }





                                          //sets current dominoe
                                          //currentDominoe = person.getH().dom[dominoePostion];
                                          //draw hand
                                          board.DrawHand(person.getH().dom);
                                      }
                                      boneyard.setText("Boneyard: " + boardCount);
                                  }
                              }
                          }
        );

        left.setOnAction(new EventHandler<ActionEvent>() {
                              @Override
                              public void handle(ActionEvent event) {
                                  if(currentDominoe != null && currentDominoe.getIsFlipped() != -1) {

                                      board.DrawBoardDominoe(currentDominoe.getDominoPicture(), leftY, leftX %2, 1);
                                      person.getH().dom[dominoePostion] = lib.draw();

                                      board.DrawHand(person.getH().dom);
                                      boardCount--;
                                      leftY--;
                                      leftX++;
                                      currentDominoe = null;
                                  }else  if(currentDominoe != null && currentDominoe.getIsFlipped() == -1) {

                                      board.DrawBoardDominoe(currentDominoe.getDominoPicture(), leftY, leftX %2, -1);
                                      person.getH().dom[dominoePostion] = lib.draw();

                                      board.DrawHand(person.getH().dom);
                                      boardCount--;
                                      leftY--;
                                      leftX++;
                                      currentDominoe = null;
                                  }

                                  boneyard.setText("Boneyard: " + boardCount);
                              }

                          }

        );


        canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                if (mouseEvent.getY() > canvas.getHeight() - 54) {


                        if ((mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 0 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (0 * 40) + 54
                                ) && person.getH().dom[0] !=lib.getBlankDominoe()) {
                            currentDominoe = person.getH().dom[0];
                            dominoePostion = 0;
                            System.out.println(currentDominoe.toString());

                            boneyard.setText("Boneyard: " + boardCount);
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 1 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (1 * 60) + 54
                                ){
                            currentDominoe = person.getH().dom[1];
                            dominoePostion = 1;
                            System.out.println(currentDominoe.toString());
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 2 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (2 * 60) + 54
                                ) {


                            currentDominoe = person.getH().dom[2];
                            dominoePostion = 2;
                            System.out.println(currentDominoe.toString());
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 3 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (3 * 60) + 54
                                ) {
                            currentDominoe = person.getH().dom[3];
                            dominoePostion = 3;
                            System.out.println(currentDominoe.toString());
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 4 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (4 * 60) + 54
                                ) {

                            currentDominoe = person.getH().dom[4];
                            dominoePostion = 4;
                            System.out.println(currentDominoe.toString());
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 5 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (5 * 60) + 54
                                ) {

                            currentDominoe = person.getH().dom[5];
                            dominoePostion = 5;
                            System.out.println(currentDominoe.toString());
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) -180) + 6 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2)-180) + (6 * 60) + 54
                                ) {

                            currentDominoe = person.getH().dom[6];
                            dominoePostion = 6;
                            System.out.println(currentDominoe.toString());
                        }



                }


            }});

        root.setStyle("-fx-background-color: green");

        top.getChildren().addAll(right, left, turn);

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }


    public static void main(String[] args)  {



launch(args);

    }
}
