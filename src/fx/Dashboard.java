package fx;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author mchouarbi
 */
public class Dashboard extends Application {
    
    private String getStyleSheet(String name) {
            return getClass().getResource(name).toExternalForm();
    }
    private Node getRootFromFxml(String fxml) {

        try {
            return FXMLLoader.load(getClass().getResource("gui/"+fxml));
        } catch (IOException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            return new Group();
        }

    }
    
    private void center(Stage primaryStage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }
    private void loadScene(Stage stage) {
        
        AnchorPane root =(AnchorPane)getRootFromFxml("main.fxml");
        Scene scene = new Scene(root);

        FadeTransition ft = new FadeTransition(Duration.millis(1000), root);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        
        stage.setScene(scene);
        center(stage);
        
    }   
                
    
    @Override
    public void start(Stage stage) {
//        Button btn = new Button();
//        btn.setText("Start!");
//        btn.setOnAction(new EventHandler<ActionEvent>() {         
//            @Override
//            public void handle(ActionEvent event) {
//                loadScene(primaryStage);
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 120, 100);
//        
//        primaryStage.setTitle("Dashboard");
//        primaryStage.setScene(scene);
        
        init(stage);
        stage.setResizable(false);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private double initX;
    private double initY;

    private void init(Stage stage) {
        stage.initStyle(StageStyle.TRANSPARENT);
        Group rootGroup = new Group();
        Scene scene = new Scene(rootGroup, 200, 200, Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();

        //create dragger with desired size
        Circle dragger = new Circle(100, 100, 100);
        //fill the dragger with some nice radial background
        dragger.setFill(new RadialGradient(-0.3, 135, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
            new Stop(0, Color.DARKGRAY),
            new Stop(1, Color.BLACK)
         }));
        //when mouse button is pressed, save the initial position of screen
        rootGroup.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                initX = me.getScreenX() - stage.getX();
                initY = me.getScreenY() - stage.getY();
            }
        });

        //when screen is dragged, translate it accordingly
        rootGroup.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                stage.setX(me.getScreenX() - initX);
                stage.setY(me.getScreenY() - initY);
            }
        });

        Button close = new Button("Start App.");
        close.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                loadScene(stage);
                //javafx.application.Platform.exit();
                //stage.close(); stage.setIconified(true);
            }
        });

        Text text = new Text("Dashboard"); //20, 110,
        text.setFill(Color.WHITESMOKE);
        text.setEffect(new Lighting());
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setFont(Font.font(Font.getDefault().getFamily(), 30));

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(60, 0, 0, 30));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(text, close);

        rootGroup.getChildren().addAll(dragger, vBox);

        FadeTransition ft = new FadeTransition(Duration.millis(2000), vBox);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

    }
    
}
