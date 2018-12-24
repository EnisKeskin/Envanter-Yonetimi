package Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import SQL_Verilerim.Kategori_Veri_Al;
import SQL_Verilerim.Kategori_Veri_Sil;
import SQL_Verilerim.Urun_Veri_Sil;
import VeriSiniflari.Kategori_Veri;

import java.util.ArrayList;

public class ControllerKategoriSil {

    @FXML
    private JFXComboBox<String> combox_kategori;

    public void ComboBoxAddCategory(){
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
        combox_kategori.setItems(kategoriler);
    }

    @FXML
    void btn_kategorisil(ActionEvent event) {
        String veri = combox_kategori.getSelectionModel().getSelectedItem();
        Urun_Veri_Sil.getInstance().UrunVeriSilKategori(veri);
        Kategori_Veri_Sil.getInstance().KategoriVeriSil(veri);
        ComboBoxAddCategory();
    }

}
