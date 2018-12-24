package SQL_Verilerim;

import VeriSiniflari.Birim_Veri;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Birim_Veri_Al
{
    //SQLITE TABLO ve SUTUN adlarını alıyoruz.
    public static final String TABLO_Birim="Birim";
    public static final String SUTUN_Adi="BirimAdi";

    //Constructor private haline getiriyoruz.
    private Birim_Veri_Al(){}
    //instance adında private bir nesne oluşturuyoruz.
    private static Birim_Veri_Al instance=new Birim_Veri_Al();
    //getInstance ile dışardaki tüm sınıflarımdan getInstance ile Birim_Veri_Al tüm fonk ulaşabiliyoruz.
    public static Birim_Veri_Al getInstance(){return instance;}
    //Sql sorgusunu çalıştırdığım Fonksiyon.
    public ArrayList<Birim_Veri> BirimGetir() {
            //Sql_Baglanma sınıfımdan connection fonksiyonumu çağırıp Javada sql Sorgusu kullanmamı sağlayan Kütüphane
            //fonksiyonu olan statement'a atıyoruz.
        try(Statement statement= Sql_Baglanma.getInstance().connection.createStatement()) {
            //yazdığım sql sorgusunu sonuc değişkenine atıyoruz.
            ResultSet sonuc=statement.executeQuery("SELECT "+SUTUN_Adi+" FROM "+TABLO_Birim);
            //gelen verileri atmak için ArrayList oluşturuyoruz.
            ArrayList<Birim_Veri> tumBirim=new ArrayList<>();
            //gelen verileri tümünü gezmek icin while yolladık
            while(sonuc.next()) {
                //Birim_Veri BirimVeri nesnensini oluşturduk
                Birim_Veri BirimVeri=new Birim_Veri();
                //gelen veriyi BirimVeri setledik.
                BirimVeri.setBirimAdi(sonuc.getString(SUTUN_Adi));
                //verilerimi tutmak için tanımladığım tumBirim ArrayList gelen verilerimi tek tek atıyoruz.
                tumBirim.add(BirimVeri);
            }
            //geri ArrayList döndürüp dışarda verilerimin kullanılmasını sağlıyoruz.
            return tumBirim;
        } catch (SQLException e) {
            System.out.println("Sorgu Başarısız");
            e.printStackTrace();
            return null;

        }
    }
}
