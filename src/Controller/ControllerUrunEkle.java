package Controller;

import Text_Kontrol.JFXTextField_Kontrol;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import SQL_Verilerim.Birim_Veri_Al;
import SQL_Verilerim.Kategori_Veri_Al;
import SQL_Verilerim.Urun_Veri_Ekle;
import VeriSiniflari.Birim_Veri;
import VeriSiniflari.Kategori_Veri;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ControllerUrunEkle
{

    @FXML
    private JFXComboBox<String> comBox_Kategori;
    @FXML
    private JFXComboBox<String> comBox_Birim;

    @FXML
    private JFXTextField txt_UrunMiktari;

    @FXML
    private JFXTextField txt_UrunAdi;

    @FXML
    private Label lbl_Islem;

    //JFXComboBox'larımıza veritabanından gelen verilerin ComboBox'lara yazdırılma fonksiyonu
    public void VerileriYaz(){
        //veri tabanından gelen veriler ArrayList atanıyor
        ArrayList<Kategori_Veri> getKategoriler;
        getKategoriler = Kategori_Veri_Al.getInstance().KategoriGetir();
        //JFXComboBox'larına verileri atayabilmemiz icin ArrayList ObservableList dönüstürülmek üzere ObservableList acılıyor
        ObservableList<String> kategoriler = FXCollections.observableArrayList();
        //Veriler ObservableList atanıyor
        for (int i = 0; i < getKategoriler.size(); i++) {
            kategoriler.add(getKategoriler.get(i).getKategoriAdi());
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
    }

    @FXML
    void btn_UrunEkle(ActionEvent event) throws SQLException {
        //txt_UrunAdi TextFieldındaki verileri alıp urunAdi değişkenine atandı.
        String urunAdi;
        urunAdi= txt_UrunAdi.getText();
        //txt_UrunMiktari TextFieldındaki verileri alıp urunMiktari değişkenine atandı.
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
        //comBox_Kategori JFXComboBox secilen veriyi alıp kategori değişkenine atandı.
        String kategori;
        kategori = comBox_Kategori.getSelectionModel().getSelectedItem();
        //comBox_Birim JFXComboBox secilen veriyi alıp birim değişkenine atandı.
        String birim;
        birim = comBox_Birim.getSelectionModel().getSelectedItem();
        //Uyarı sağlanıyor ve şartlar eğer yerine geldiyse ic kısımdaki if giriyor karşılanmasıysa Label'a veri aktarılıyor
        if (txt_UrunAdi() && txt_UrunMiktar() && cmb_UrunKategori() && cmb_UrunBirim()) {
            //Urun veriler veritabanına yollanıyor eğer veritabanına gidiş gerçekleşirse alttaki komutlar aktif oluyor.
        if (Urun_Veri_Ekle.getInstance().UrunEkle(urunAdi,urunMiktari,zaman,kategori,birim)){
            //lbl_Islem Label renk yeşil yapılıyor
            lbl_Islem.setTextFill(Color.GREEN);
            //lbl_Islem Label mesaj dönderiliyor
            lbl_Islem.setText("Ürün Başarıyla Eklendi");
        }
        else{
            //lbl_Islem Label renk kırımızı yapılıyor
            lbl_Islem.setTextFill(Color.RED);
            //lbl_Islem Label mesaj gönderiliyor
            lbl_Islem.setText("Ürün Eklenemedi");
        }}else{
            //lbl_Islem Label renk yeşil yapılıyor
            lbl_Islem.setTextFill(Color.RED);
            //lbl_Islem Label mesaj gönderiliyor
            lbl_Islem.setText("Ürün Eklenemedi");
        }
    }
    //txt_UrunAdi içinde 50 karakterden fazla isim icerip icermediğini Uyarı ediyor
    @FXML
    boolean txt_UrunAdi() {
        boolean check;
        check= JFXTextField_Kontrol.getInstance().TextKontrol(txt_UrunAdi);
        return check;
    }
    //txt_UrunMiktar içinde 50 karakterden fazla isim icerip icermediğini Uyarı ediyor
    @FXML
    boolean txt_UrunMiktar() {
        boolean check;
        check= JFXTextField_Kontrol.getInstance().TextKontrol(txt_UrunMiktari);
        return check;
    }
    //cmb_UrunBirim seçilip seçilmediğini Uyarı ediyor.
    @FXML
    boolean cmb_UrunBirim() {
        boolean check;
        check= JFXTextField_Kontrol.getInstance().ComboBoxKontrol(comBox_Birim);
        return check;
    }
    //cmb_UrunBirim seçilip seçilmediğini Uyarı ediyor.
    @FXML
    boolean cmb_UrunKategori() {
        boolean check;
        check= JFXTextField_Kontrol.getInstance().ComboBoxKontrol(comBox_Kategori);
        return check;
    }
}
