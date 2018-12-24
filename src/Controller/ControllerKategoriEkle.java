package Controller;

import Text_Kontrol.JFXTextField_Kontrol;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import SQL_Verilerim.Kategori_Veri_Al;
import SQL_Verilerim.Kategori_Veri_Ekle;
import VeriSiniflari.Kategori_Veri;

import java.util.ArrayList;


public class ControllerKategoriEkle
{

    @FXML
    private Label lbl_Islem;

    @FXML
    private JFXTextField txt_Kategori;

    @FXML
    void btn_KategoriEkle(ActionEvent event) {
        //kategori TextField'daki veriyi kategori değişkenine atıyor.
        String kategori;
        kategori = txt_Kategori.getText();
        ArrayList<Kategori_Veri> getkategoriler;
        getkategoriler = Kategori_Veri_Al.getInstance().KategoriGetir();
        boolean kontrol=true;
        //Aynı isimde Kategori Olup Olmadığını Kontrol Ediyor
        for (int i = 0; i < getkategoriler.size(); i++) {
           if (kategori.equals(getkategoriler.get(i).getKategoriAdi())) {
               kontrol = false;
               break;
           }
        }
        if (kontrol) {
            //Kategori_veri_yolla sınıfımızdan kategoriEkle fonksiyonunu çağırarak kategori değişkenimizi eklemeyi sağlıyoruz.
            if (JFXTextField_Kontrol.getInstance().TextKontrol(txt_Kategori)) {
                //Kategori_Veri_Ekle txt_Kategori TextField veri tabanına kategori ekleniyor
                Kategori_Veri_Ekle.getInstance().KategoriVeriEkle(kategori);
                //lbl_Islem Label rengini değiştirerek mesaj yazdırılıyor.
                lbl_Islem.setTextFill(Color.GREEN);
                lbl_Islem.setText("Kategori Başarıyla Eklendi");
            } else {
                //lbl_Islem Label rengini değiştirerek mesaj yazdırılıyor.
                lbl_Islem.setTextFill(Color.RED);
                lbl_Islem.setText("Kategori Eklenemedi");
            }
        }else {
            lbl_Islem.setTextFill(Color.RED);
            lbl_Islem.setText("Kategori Bulunuyor.");
        }
    }

}
