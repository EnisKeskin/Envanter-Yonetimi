package Controller;

import Text_Kontrol.JFXTextField_Kontrol;
import Goruntuler.Formlar;
import com.jfoenix.controls.JFXPasswordField;
import javafx.scene.control.Alert;
import SQL_Verilerim.Kullanici_Veri_Ekle;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControllerKayitOl
{
    @FXML
    private JFXTextField txt_Isim;

    @FXML
    private JFXTextField txt_Soyisim;

    @FXML
    private JFXTextField txt_Mail;

    @FXML
    private JFXPasswordField txt_Sifre;

    private String girisUrl="/Tasarim/Giris.fxml";

    @FXML
    void btn_kayit(ActionEvent event) throws IOException {
        //İsim TextFieldındaki verileri alıp isim değişkenine atandı.
        String isim;
        isim = txt_Isim.getText();
        //Soyisim TextFieldındaki verileri alıp soyisim değişkenine atandı.
        String soyisim;
        soyisim = txt_Isim.getText();
        //Mail TextFieldındaki verileri alıp mail değişkenine atandı.
        String mail;
        mail = txt_Mail.getText();
        //sifre TextPasswordFieldındaki verileri alıp sifre değişkenine atandı.
        String sifre;
        sifre = txt_Sifre.getText();
        //degerKontrol degerinin icine txt bos olup olmadıgını atandı
        boolean degerKontrol;
        degerKontrol= isimKontrol() && soyIsimKontrol() && mailKontrol() && sifreKontrol();
        //degerKontrol if sartına sokarak boslugunu kontrolunu saglandı
        if (degerKontrol) {
            //Uyarı icine verilerimi atayarak kullanıcının eklenip eklenmediğini Uyarı ediyor
            boolean kontrol;
            kontrol = Kullanici_Veri_Ekle.getInstance().kullaniciEkle(isim,soyisim,mail,sifre);
            //Uyarı if şartına sokarak islemin saglanıp sağlanamadığını Uyarı ediyor
            if(kontrol) {
                //Herşey doğru ise giriş ekranına döner.
                Formlar.getInstance().EkranCıktısı(girisUrl,event,"Giriş Ekranı", "/Resimler/Logo.jpg");
            }
        }
        //degerKontrol eğer boşsa ekrana mesaj cıkarmasını saglandı
        else{
            //Ekrana mesaj çıkartma parametreleri.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hata");
            alert.setHeaderText(null);
            alert.setContentText("Kayıt Olunamadı \nLütfen Bilgilerinizin Doğruluğunu Kontrol Ediniz");
            alert.showAndWait();
        }
    }

    //isim textfield içinde 50 karakterden fazla isim icerip icermediğini Uyarı ediyor
    @FXML
    boolean isimKontrol() {
       boolean check;
        check = JFXTextField_Kontrol.getInstance().TextKontrol(txt_Isim);
        return check;
    }
    //soyisim textfield içinde 50 karakterden fazla isim icerip icermediğini Uyarı ediyor
    @FXML
    boolean soyIsimKontrol() {
       boolean check;
       check= JFXTextField_Kontrol.getInstance().TextKontrol(txt_Soyisim);
       return check;
    }
    //mail textfield içinde 50 karakterden fazla isim icerip icermediğini ve @ isaretini Uyarı ediyor
    @FXML
    boolean mailKontrol() {
        boolean check;
        check= JFXTextField_Kontrol.getInstance().MailKontrol(txt_Mail);
        return check;
    }
    //Sifre JFXPasswordField içinde 50 karakterden fazla isim icerip icermediğini Uyarı ediyor
    @FXML
    boolean sifreKontrol() {
        boolean check;
        check= JFXTextField_Kontrol.getInstance().TextKontrol(txt_Sifre);
        return check;
    }

}
