package SQL_Verilerim;

import VeriSiniflari.Kullanici_Veri;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Kullanici_Veri_Al
{
    //SQLITE TABLO ve SUTUN adlarını alıyoruz.
    public static final String TABLO_Kullanici="Kullanici";
    public static final String SUTUN_KullaniciId="KullaniciID";
    public static final String SUTUN_Kullanici_Adi="KullaniciAdi";
    public static final String SUTUN_Kullanici_Sifre="KullaniciSifre";

    //Constructor private haline getiriyoruz.
    private Kullanici_Veri_Al(){}
    //instance adında private bir nesne oluşturuyoruz.
    private static Kullanici_Veri_Al instance=new Kullanici_Veri_Al();
    //getInstance ile dışardaki tüm sınıflarımdan getInstance ile Kullanici_Veri_Al tüm fonk ulaşabiliyoruz.
    public static Kullanici_Veri_Al getInstance(){return instance;}
    //Sql sorgusunu çalıştırdığımız Fonksiyon
    public ArrayList<Kullanici_Veri> kullanicigetir() {
        //Sql_Baglanma sınıfımdan connection fonksiyonumu çağırıp Javada sql Sorgusu kullanmamı sağlayan Kütüphane
        //fonksiyonu olan statement'a atıyoruz.
        try(Statement statement= Sql_Baglanma.getInstance().connection.createStatement()) {
            //yazdığım sql sorgusunu sonuc değişkenine atıyoruz.
            ResultSet sonuc=statement.executeQuery("SELECT "+SUTUN_Kullanici_Adi+","+SUTUN_Kullanici_Sifre+" FROM "+TABLO_Kullanici);
            //gelen verileri atmak için ArrayList oluşturuyoruz.
            ArrayList<Kullanici_Veri> tumkullanicilar=new ArrayList<>();
            //gelen verileri tümünü gezmek icin while kullandık
            while(sonuc.next()) {
                //Kategori_Veri KategoriVeri nesnensini oluşturduk
                Kullanici_Veri kullaniciVeri=new Kullanici_Veri();
                //gelen veriyi kullaniciVeri setledik.
                kullaniciVeri.setKullaniciAdi(sonuc.getString(SUTUN_Kullanici_Adi));
                kullaniciVeri.setKullaniciSifresi(sonuc.getString(SUTUN_Kullanici_Sifre));
                //verilerimi tutmak için tanımladığım tumKategori ArrayList gelen verilerimi tek tek atıyoruz.
                tumkullanicilar.add(kullaniciVeri);
            }
            //geri ArrayList döndürüp dışarda verilerimin kullanılmasını sağlıyoruz.
            return tumkullanicilar;
        } catch (SQLException e) {
            System.out.println("Sorgu Başarısız");
            e.printStackTrace();
            return null;
        }
    }
}
