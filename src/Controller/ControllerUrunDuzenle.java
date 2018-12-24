package Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import SQL_Verilerim.Birim_Veri_Al;
import SQL_Verilerim.Kategori_Veri_Al;
import SQL_Verilerim.Urun_Veri_Duzenle;
import VeriSiniflari.Birim_Veri;
import VeriSiniflari.Kategori_Veri;
import VeriSiniflari.Veri_Aktarimiurun;

import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ControllerUrunDuzenle {

    @FXML
    private JFXTextField txt_UrunAdi;

    @FXML
    private JFXComboBox<String> comBox_Kategori;

    @FXML
    private JFXTextField txt_UrunMiktari;

    @FXML
    private JFXComboBox<String> comBox_Birim;


    public void VeriYaz() throws SQLException {
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
        //veri tabanından gelen veriler ArrayList atanıyor
        ArrayList<Birim_Veri> getBirim;
        getBirim= Birim_Veri_Al.getInstance().BirimGetir();
        //JFXComboBox'larına verileri atayabilmemiz icin ArrayList ObservableList dönüstürülmek üzere ObservableList acılıyor
        ObservableList<String> birim=FXCollections.observableArrayList();
        //Veriler ObservableList atanıyor
        for (int i = 0; i < getBirim.size(); i++) {
            birim.add(getBirim.get(i).getBirimAdi());
        }
        //JFXComboBox atanıyor.
        comBox_Birim.setItems(birim);
        //VeriAktarimiurun gelen değerleri direkt TextFiel ve ComboBox yazdırıyoruz.
        txt_UrunAdi.setText(Veri_Aktarimiurun.getInstance().getUrunAdi());
        txt_UrunMiktari.setText(Veri_Aktarimiurun.getInstance().getUrunMiktar());
        comBox_Birim.setValue(Veri_Aktarimiurun.getInstance().getUrunBirim());
        comBox_Kategori.setValue(Veri_Aktarimiurun.getInstance().getUrunKategori());
    }
    //Düzenle buttonun basılma event'i
    @FXML
    void btn_urunduzenle(ActionEvent event) throws IOException {
        //txt_UrunAdi gelen veriyi urunadi atıyoruz
        String urunAdi;
        urunAdi= txt_UrunAdi.getText();
        //txt_UrunMiktar gelen veriyi urunMikatari atıyoruz
        String urunMiktari;
        urunMiktari= txt_UrunMiktari.getText();
        //Zamanı yıl-ay-gun saat-dakika-saniye göre formatlanıyor.
        Format formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Buttona basıldığı andaki zamanı alıyor.
        Date today;
        today = Calendar.getInstance().getTime();
        //Bilgisayar zamanını veritabanına göndermek üzere zaman değişkenine atanıyor.
        String zaman;
        zaman = formatter.format(today);
        //ComboBox secilen veriyi urunKategori değişkenine atıyoruz.
        String urunKategori;
        urunKategori = comBox_Kategori.getSelectionModel().getSelectedItem();
        //ComboBox secilen veriyi urunBirim değişkenine atıyoruz.
        String urunBirim;
        urunBirim = comBox_Birim.getSelectionModel().getSelectedItem();
        //VeriAktarimiurun aldığımız veriyi urunID değişkenine atıyoruz.
        String urunID;
        urunID= Veri_Aktarimiurun.getInstance().getUrunID();
        //Veri_Veri_Guncelle classındaki UrunGucelle fonk çağırıyor.
        Urun_Veri_Duzenle.getInstance().UrunGuncelle(urunID,urunAdi,urunMiktari,zaman,urunKategori,urunBirim);

    }

}
