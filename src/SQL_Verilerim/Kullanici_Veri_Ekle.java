package SQL_Verilerim;

import java.sql.*;

public class Kullanici_Veri_Ekle {
    //SQLITE TABLO ve SUTUN adlarını alıyoruz.
    public static final String TABLO_Kullanici="Kullanici";
    public static final String SUTUN_KullaniciId="KullaniciID";
    public static final String SUTUN_KullaniciIsim="KullaniciIsim";
    public static final String SUTUN_KullaniciSoyisim="KullaniciSoyisim";
    public static final String SUTUN_Kullanici_Adi="KullaniciAdi";
    public static final String SUTUN_Kullanici_Sifre="KullaniciSifre";

    //Constructor private haline getiriyoruz.
    private Kullanici_Veri_Ekle(){conn = Sql_Baglanma.getInstance().connection;}
    //instance adında private bir nesne oluşturuyoruz.
    private static Kullanici_Veri_Ekle instance=new Kullanici_Veri_Ekle();
    //getInstance ile dışardaki tüm sınıflarımdan getInstance ile Kullanici_Veri_Ekle tüm fonk ulaşabiliyoruz.
    public static Kullanici_Veri_Ekle getInstance(){return instance;}
    //connection değişkeni üretiyoruz.
    private Connection conn;
    //kullaniciEkle eklediğimiz fonksiyon
    public boolean kullaniciEkle(String isim, String soyisim, String kullaniciadi, String sifre)
    {
        try {
            //Sql_Baglanma sınıfımdan connection fonksiyonumu çağırıp Javada sql Sorgusu kullanmamı sağlayan Kütüphane
            //fonksiyonu olan Preparedstatement'a atıyoruz.
            //PreparedStatement Statement farklı olarak icine veri alabiliyor.
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO "+TABLO_Kullanici+"("+SUTUN_KullaniciIsim+","+SUTUN_KullaniciSoyisim+","+SUTUN_Kullanici_Adi+","+SUTUN_Kullanici_Sifre+") VALUES(?,?,?,?)");
            //? işaretleri 1 den başlıyarak sırayla gidiyor ve icine aktarmak istediğimiz değerleri yazdırıyoruz.
            pstmt.setString(1, isim);
            pstmt.setString(2, soyisim);
            pstmt.setString(3, kullaniciadi);
            pstmt.setString(4, sifre);
            //işlemimiz tamamlandıksan sonra executeUpdate komutunu çalıştırarak sorgumuz gercekleşir.
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
