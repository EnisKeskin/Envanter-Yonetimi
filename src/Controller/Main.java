package Controller;

import SQL_Verilerim.Sql_Baglanma;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Tasarim/Giris.fxml"));
        Parent root=loader.load();

        ControllerGiris cnt_Giris=loader.getController();
        cnt_Giris.ilkCalisacakFonksiyon();
        
        primaryStage.getIcons().add(new Image("/Resimler/Logo.jpg"));
        primaryStage.setTitle("Giriş Ekranı");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void init(){

        if (Sql_Baglanma.getInstance().veritabaninaBaglanma()) {
            System.out.println("Veri Tabanına Bağlandım");
        }
        else{
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        Sql_Baglanma.getInstance().veritabaniniKapat();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
