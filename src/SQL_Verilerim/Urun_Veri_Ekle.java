package SQL_Verilerim;

import static SQL_Verilerim.Kategori_Veri_Duzenle.SUTUN_KategoriKulID;
import static SQL_Verilerim.Urun_Veri_Duzenle.SUTUN_KategoriAdi;
import static SQL_Verilerim.Urun_Veri_Duzenle.SUTUN_KategoriId;
import static SQL_Verilerim.Urun_Veri_Duzenle.TABLO_Kategori;
import VeriSiniflari.Veri_Aktarimi;

import java.sql.*;

public class Urun_Veri_Ekle {
    //SQLITE TABLO ve SUTUN adlarını alıyoruz.
    public static final String TABLO_Urun="Urun";
    public static final String SUTUN_urun_Adi="UrunAdi";
    public static final String SUTUN_urun_Miktari="UrunMiktari";
    public static final String SUTUN_urun_kategoriId="UrunKategoriId";
    public static final String SUTUN_urun_Tarih="UrunEklenmeTarihi";
    public static final String SUTUN_urun_BirimId="UrunBirimID";
    public static final String SUTUN_urun_KullaniciId="UrunKullaniciID";

    public static final String TABLO_Kategori="Kategori";
    public static final String SUTUN_KategoriId="KategoriID";
    public static final String SUTUN_KategoriAdi="KategoriAdi";

    public static final String TABLO_Birim="Birim";
    public static final String SUTUN_BirimId="BirimID";
    public static final String SUTUN_BirimAdi="BirimAdi";

    public static final String TABLO_Kullanici="Kullanici";
    public static final String SUTUN_KullaniciId="KullaniciID";
    public static final String SUTUN_Kullanici_Adi="KullaniciAdi";


    //Constructor private haline getiriyoruz.
    private Urun_Veri_Ekle(){conn = Sql_Baglanma.getInstance().connection;}
    //instance adında private bir nesne oluşturuyoruz.
    private static Urun_Veri_Ekle instance=new Urun_Veri_Ekle();
    //getInstance ile dışardaki tüm sınıflarımdan getInstance ile Kullanici_Veri_Ekle tüm fonk ulaşabiliyoruz.
    public static Urun_Veri_Ekle getInstance(){return instance;}
    //connection değişkeni üretiyoruz.
    private Connection conn;
    //kullaniciEkle eklediğimiz fonksiyon
    public boolean UrunEkle(String UrunAdi,String UrunMiktari, String UrunEklenmeTarihi,String Kategori, String Birim) throws SQLException {

        int UrunKullaniciID = KullaniciID();

        try  {
            //Sql_Baglanma sınıfımdan connection fonksiyonumu çağırıp Javada sql Sorgusu kullanmamı sağlayan Kütüphane
            //fonksiyonu olan Preparedstatement'a atıyoruz.
            //PreparedStatement Statement farklı olarak icine veri alabiliyor.
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO "+TABLO_Urun+"("+SUTUN_urun_Adi+","+SUTUN_urun_Miktari+","+SUTUN_urun_Tarih+","+SUTUN_urun_kategoriId+","+SUTUN_urun_BirimId+","+SUTUN_urun_KullaniciId+") VALUES(?,?,?,?,?,?)");
            //? işaretleri 1 den başlıyarak sırayla gidiyor ve icine aktarmak istediğimiz değerleri yazdırıyoruz.
            pstmt.setString(1, UrunAdi);
            pstmt.setString(2, UrunMiktari);
            pstmt.setString(3, UrunEklenmeTarihi);
            pstmt.setString(4, KategoriID(Kategori));
            pstmt.setString(5, BirimID(Birim));
            pstmt.setInt(6, UrunKullaniciID);
            //işlemimiz tamamlandıksan sonra executeUpdate komutunu çalıştırarak sorgumuz gercekleşir.
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
    //KullaniciAdi'nı KullanıcıID fonksiyonuna verdiğimizde veridiğimiz KullaniciAdi'nın ID'sini buluyor.
    public int KullaniciID() throws SQLException {
        PreparedStatement statement= Sql_Baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KullaniciId+" FROM "+TABLO_Kullanici+" WHERE "+SUTUN_Kullanici_Adi+"="+"?");
        statement.setString(1, Veri_Aktarimi.getInstance().getVeri());
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getInt(SUTUN_KullaniciId);
    }
    //KategoriAdi'nı KategoriID fonksiyonuna verdiğimizde veridiğimiz kategoriAdi'nın ID'sini buluyor.
    public String KategoriID(String kategoriAdi) throws SQLException {
        PreparedStatement statement= Sql_Baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KategoriId+" FROM "+TABLO_Kategori+" WHERE "+SUTUN_KategoriAdi+"=? AND "+SUTUN_KategoriKulID+"=?");
        statement.setString(1, kategoriAdi);
        statement.setInt(2, KullaniciID());
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_KategoriId);
    }
    //BirimAdi'nı BirimID fonksiyonuna verdiğimizde veridiğimiz BirimAdi'nın ID'sini buluyor.
    public String BirimID(String Birim) throws SQLException {
        PreparedStatement statement= Sql_Baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_BirimId+" FROM "+TABLO_Birim+" WHERE "+SUTUN_BirimAdi+"="+"?");
        statement.setString(1,Birim);
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_BirimId);
    }

}
