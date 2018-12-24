package Controller;

import Text_Kontrol.JFXTextField_Kontrol;
import Goruntuler.Formlar;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import SQL_Verilerim.Kullanici_Veri_Al;
import VeriSiniflari.Kullanici_Veri;
import VeriSiniflari.Veri_Aktarimi;

import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.Stage;

public class ControllerGiris
{
    //JFXTextField FXML ile classlar arası bağlantı için değişkene atandı
    @FXML
    private JFXTextField txt_kullanici;
    //JFXTextField FXML ile classlar arası bağlantı için değişkene atandı
    @FXML
    private JFXPasswordField txt_sifre;
    //Label FXML ile classlar arası bağlantı için değişkene atandı
    @FXML
    private Label lbl_bilgi;
    //FXML Index URL'si
    private String girisUrl="/Tasarim/AnaEkran.fxml";
    //FXML Index URL'si
    private String kayitUrl="/Tasarim/KullaniciKayit.fxml";
    //JFXTextField üzerine gelince mesaj çıkmasını sağlıyor
    private Tooltip tooltip_kullanici=new Tooltip();
    //JFXTextField üzerine gelince mesaj çıkmasını sağlıyor
    private Tooltip tooltip_sifre=new Tooltip();
    
    public static Stage stage;
    
    public void ilkCalisacakFonksiyon() {
          //Tooltip içindeki veriyi yazdırıyorum.
          tooltip_kullanici.setText("Kullanici_Veri_Al Adınız Örnek:'example@hotmail.com' ");
          //Tooltip'i txt_kullanicimi içine atıyorum.
          txt_kullanici.setTooltip(tooltip_kullanici);
          //Tooltip içindeki veriyi yazdırıyorum.
          tooltip_sifre.setText("Şifreniz Örnek:'Example.0'");
          //Tooltip'i txt_sifre içine atıyorum.
          txt_sifre.setTooltip(tooltip_sifre);

      }
    @FXML
    void btn_giris(ActionEvent event) throws IOException {
          //Kullanici TextFieldındaki verileri alıp kullanici değişkenine atandı.
          String kullanici;
          kullanici=txt_kullanici.getText();
          //Veri_Aktarimi sınıfımın içine verimi atıyorum ve Veri_Aktarimi sınıfı buffer gibi kullanıp classlar arası veri taşınıyor.
          Veri_Aktarimi.getInstance().setVeri(kullanici);
          //Sifre TextFieldındaki verileri alıp Sifre değişkenine atandı.
          String sifre;
          sifre=txt_sifre.getText();
          //TextFieldların boş olup olmadığını Uyarı ediyor ve yanlıs olup olmadıgını
          boolean bosKontrol;
          bosKontrol = !(kullanici.isEmpty()&&sifre.isEmpty() && kullaniciAdi() && sifre());
          //Kullanıcı adının ve sifreyi doğru olup olmadığını ve bosKonrol doğru olup olmadığını Uyarı
        if ((kontrol(kullanici, sifre))&& bosKontrol) {
            //Herşey başarılı olduysa ekranımı aktif ediyor.
            stage= Formlar.getInstance().EkranCıktısıAnaEkran(girisUrl,event,"Giriş Ekranı", "/Resimler/Logo.jpg");
        }
        else{//Kullanici adı ve sifre yanlıs girilmişse txt siliniyor ve bir hata msj veriyor
            txt_kullanici.clear();
            txt_sifre.clear();
            lbl_bilgi.setTextFill(Color.RED);
            lbl_bilgi.setText("Kullanıcı Adı veya Şifre Yanlış \nLütfen Tekrar Deneyiniz");
        }
    }
    //tıklandığında kayıt sayfasına yönleniyor
    @FXML
    void btn_kayıtOl(ActionEvent event) throws IOException {
          Formlar.getInstance().EkranCıktısı(kayitUrl,event,"Kayit Ekranı", "/Resimler/Logo.jpg");
    }
    //kullaniciAdi doğruluğunu Uyarı ediyor
    @FXML
    boolean kullaniciAdi() {
        boolean check;
        check= JFXTextField_Kontrol.getInstance().MailKontrol(txt_kullanici);
        return check;
    }
    //sifrenin doğruğunu Uyarı ediyor
    @FXML
    boolean sifre() {
        boolean check;
        check= JFXTextField_Kontrol.getInstance().TextKontrol(txt_sifre);
        return check;
    }
    //kullanici adi ve sifreyi veritabanından Uyarı yapıyor
    private boolean kontrol(String isim, String sifre)
    {
        ArrayList<Kullanici_Veri> verilerim= Kullanici_Veri_Al.getInstance().kullanicigetir();
        for (int i = 0; i < verilerim.size(); i++) {
                if (verilerim.get(i).kullaniciAdi.equals(isim) && verilerim.get(i).kullaniciSifresi.equals(sifre)) {
                    return true;
            }
        }
        return false;
    }
}
