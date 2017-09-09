import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application  {

            private int boardCount = 14;
            private Dominoe currentDominoe;
            private int dominoePostion = 0;
            private int temp;
            private Dominoe tempp;
            int counter = 1;

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
                computers.getH().printHand(computers.getH().doms);


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
                board.DrawHand(person.getH().doms);



                board.setCenterDominoe(lib.draw());
                board.setRightDominoe(board.getCenterDominoe());
                board.setLeftDominoe(board.getCenterDominoe());
                board.DrawBoardDominoe(board.getCenterDominoe().getDominoPicture(), 0,0, 1);

                board.DrawHand(person.getH().doms);
                boardCount--;
                System.out.println(boardCount);
                boneyard.setText("Boneyard: " + boardCount);


            turn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (currentDominoe != null) {

                            board.flipDominoe(currentDominoe.getDominoPicture(), dominoePostion, currentDominoe, currentDominoe.getRightNumber(), currentDominoe.getLeftNumber(), counter *= -1);

                            System.out.println(currentDominoe.toString());



                    }
                }
            });


            right.setOnAction(new EventHandler<ActionEvent>() {
                                          @Override
                                          public void handle(ActionEvent event) {
                                            if(currentDominoe != null && board.isLegal(board.getRightDominoe().getRightNumber(), currentDominoe.getLeftNumber())){
                                             //draw player dominoe
                                                board.DrawBoardDominoe(currentDominoe.getDominoPicture(),board.getRightX(),board.getRightY() % 2,counter);
                                               //set right dominoe on board
                                               board.setRightDominoe(currentDominoe);
                                                System.out.println("right dominoe is " + board.getRightDominoe());
                                               //set currentDominoe
                                                currentDominoe = null;
                                                //replace dominoe at that position
                                               if(boardCount > 0) {
                                                   person.getH().doms.set(dominoePostion, lib.draw());
                                               } else{
                                                   person.getH().doms.set(dominoePostion, lib.getBlankDominoe());
                                               }
                                               //redraw hand
                                                board.DrawHand(person.getH().doms);
                                                //changes coordinates
                                                board.setRightX(board.getRightX() + 1);
                                               board.setRightY(board.getRightY() + 1);
                                               boardCount--;
                                                counter = 1;
                                            }
                                              if(boardCount >=0){
                                                  boneyard.setText("Boneyard = " + boardCount);
                                              }
                                          }
                                      }

                    );



                    left.setOnAction(new EventHandler<ActionEvent>() {
                                         @Override
                                         public void handle(ActionEvent event) {

                                             if(currentDominoe != null && board.isLegal(board.getLeftDominoe().getLeftNumber(), currentDominoe.getRightNumber())){
                                                 //draw player dominoe
                                                 board.DrawBoardDominoe(currentDominoe.getDominoPicture(),board.getLeftY(), board.getLeftX()% 2,counter);
                                                 //set left dominoe on board
                                                 board.setLeftDominoe(currentDominoe);
                                                 System.out.println("left dominoe is " + board.getRightDominoe());
                                                 //set currentDominoe
                                                 currentDominoe = null;
                                                 //replace dominoe at that position
                                                 if(boardCount > 0) {
                                                     person.getH().doms.set(dominoePostion, lib.draw());
                                                 } else{
                                                     person.getH().doms.set(dominoePostion, lib.getBlankDominoe());
                                                 }
                                                 //redraw hand
                                                 board.DrawHand(person.getH().doms);
                                                 //changes coordinates
                                                 board.setLeftY(board.getLeftY() - 1);
                                                 board.setLeftX(board.getLeftX() + 1);
                                                 boardCount--;
                                                 counter = 1;
                                             }
                                             if(boardCount >=0){
                                             boneyard.setText("Boneyard = " + boardCount);
                                         }}
                                     }
                    );




            canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {


                    if (mouseEvent.getY() > canvas.getHeight() - 54) {


                        if ((mouseEvent.getX() > (((canvas.getWidth() / 2) - 180) + 0 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2) - 180) + (0 * 40) + 54
                        ) && person.getH().dom[0] != lib.getBlankDominoe()) {

                            currentDominoe = person.getH().doms.get(0);
                            dominoePostion = 0;
                            System.out.println(currentDominoe.toString());


                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) - 180) + 1 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2) - 180) + (1 * 60) + 54
                                ) {
                            currentDominoe = person.getH().doms.get(1);
                            dominoePostion = 1;
                            System.out.println(currentDominoe.toString());
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) - 180) + 2 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2) - 180) + (2 * 60) + 54
                                ) {


                            currentDominoe = person.getH().doms.get(2);
                            dominoePostion = 2;
                            System.out.println(currentDominoe.toString());
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) - 180) + 3 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2) - 180) + (3 * 60) + 54
                                ) {
                            currentDominoe = person.getH().doms.get(3);
                            dominoePostion = 3;
                            System.out.println(currentDominoe.toString());
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) - 180) + 4 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2) - 180) + (4 * 60) + 54
                                ) {

                            currentDominoe = person.getH().doms.get(4);
                            dominoePostion = 4;
                            System.out.println(currentDominoe.toString());
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) - 180) + 5 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2) - 180) + (5 * 60) + 54
                                ) {

                            currentDominoe = person.getH().doms.get(5);
                            dominoePostion = 5;
                            System.out.println(currentDominoe.toString());
                        } else if (mouseEvent.getX() > (((canvas.getWidth() / 2) - 180) + 6 * 60) &&
                                mouseEvent.getX() < ((canvas.getWidth() / 2) - 180) + (6 * 60) + 54
                                ) {

                            currentDominoe = person.getH().doms.get(6);
                            dominoePostion = 6;
                            System.out.println(currentDominoe.toString());
                        }


                    }


                }
            });


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
