package Goruntuler;

import Controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Formlar
{

    private Formlar(){}
    private static Formlar instance=new Formlar();
    public static Formlar getInstance(){return instance;}


    //Gelen url event title ve image parametreleri ile FXML yazdığımız kodunu ekrana çıkartıyor
    public void EkranCıktısı(String url, ActionEvent event, String title, String image) throws IOException {
        //FXML bir class oluşturuyor
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        //roota yüklüyor
        Parent root = loader.load();
        //stage ile üst pencereyi açıyor
        Stage stage = new Stage();
        //başlığı yazıyor
        stage.setTitle(title);
        //logomuzu icona olarak ekranın sol üst köşesine yerleştiriyor
        stage.getIcons().add(new Image(image));
        //oluşturduğumuz FXML'i ekrana alıyor
        stage.setScene(new Scene(root));
        //ekrana alınan FXML gösteriyor
        stage.show();
        //Fonksiyon çalıştığında diğer formu gizliyor.
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    //Gelen url event title ve image parametreleri ile FXML yazdığımız kodunu ekrana çıkartıyor
    public void EkranCıktısı(String url, String title, String image) throws IOException {
        //FXML bir class oluşturuyor
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        //roota yüklüyor
        Parent root = loader.load();
        //stage ile üst pencereyi açıyor
        Stage stage = new Stage();
        //başlığı yazıyor
        stage.setTitle(title);
        //logomuzu icona olarak ekranın sol üst köşesine yerleştiriyor
        stage.getIcons().add(new Image(image));
        //oluşturduğumuz FXML'i ekrana alıyor
        stage.setScene(new Scene(root));
        //ekrana alınan FXML gösteriyor
        stage.show();
    }

    public Stage EkranCıktısıAnaEkran(String url, ActionEvent event, String title, String image) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        //ControllerAnaEkran oluşturduğumuz controller icine FXML başlangıcını atıyoruz bu sayede istediğimiz fonksiyonu
        //daha form yüklenmeden başlatmış oluyoruz
        ControllerAnaEkran controller=loader.getController();
        controller.tableViewVeriEkleme();

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image(image));
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        return stage;
    }

    public void EkranCıktısıUrunEkleme(String url, String title, String image) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();

        ControllerUrunEkle controller=loader.getController();
        controller.VerileriYaz();

        Stage stage = new Stage();
        stage.getIcons().add(new Image(image));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void EkranCıktısıKatSil(String url, ActionEvent event, String title, String image) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();

        ControllerKategoriSil controller=loader.getController();
        controller.ComboBoxAddCategory();

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image(image));
        stage.setScene(new Scene(root));
        stage.show();

    }
    public void EkranCıktısıKatDuzenle(String url, ActionEvent event, String title, String image) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();

        ControllerKategoriDuzenle controller=loader.getController();
        controller.ComboBoxkategorileriYazdırma("");

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image(image));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void EkranCıktısıUrunDuzenle(String url, ActionEvent event, String title, String image) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();

        ControllerUrunDuzenle controller=loader.getController();
        controller.VeriYaz();

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image(image));
        stage.setScene(new Scene(root));
        stage.show();
    }

}
