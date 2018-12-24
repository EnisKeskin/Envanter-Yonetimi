package Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import SQL_Verilerim.Kategori_Veri_Al;
import SQL_Verilerim.Kategori_Veri_Duzenle;
import VeriSiniflari.Kategori_Veri;

import java.util.ArrayList;

public class ControllerKategoriDuzenle {

    @FXML
    private Label lbl_islem;

    @FXML
    private JFXComboBox<String> comBox_Kategori;

    @FXML
    private JFXTextField txt_KategoriYeni;

    public void ComboBoxkategorileriYazdırma(String secilecekitem){
        //veri tabanından gelen veriler ArrayList atanıyor
        ArrayList<Kategori_Veri> getkategoriler;
        getkategoriler = Kategori_Veri_Al.getInstance().KategoriGetir();
        //JFXComboBox'larına verileri atayabilmemiz icin ArrayList ObservableList dönüstürülmek üzere ObservableList acılıyor
        ObservableList<String> kategoriler = FXCollections.observableArrayList();
        //Veriler ObservableList atanıyor
        for (int i = 0; i < getkategoriler.size(); i++) {
            kategoriler.add(getkategoriler.get(i).getKategoriAdi());
        }
        //JFXComboBox atanıyor
        comBox_Kategori.setItems(kategoriler);
        //Değiştirilen değer Combobox görünmesi sağlıyor.
        comBox_Kategori.setValue(secilecekitem);

    }
    //Kategori Düzenle Buttonuna Basılma Event'i
    @FXML
    void btn_KategoriDuzenle(ActionEvent event) {
        //ComboBox secilen item secilen değişkenine atıyoruz
        String secilen= comBox_Kategori.getSelectionModel().getSelectedItem();
        //Kategori_Veri_Duzenle sınıfına gidip veriDuzenle fonk harekete geciriyor.Gönderilen parametreler ise ComboxBox secilen ve TextField Yazılan
        Kategori_Veri_Duzenle.getInstance().veriDuzenle(secilen, txt_KategoriYeni.getText());
        //Fonk tekrar çağırarak ComboBox yenilenmesini sağlıyor.
        ComboBoxkategorileriYazdırma(txt_KategoriYeni.getText());
    }

}