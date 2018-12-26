package SQL_Verilerim;

import VeriSiniflari.Veri_Aktarimi;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kategori_Veri_Duzenle {

    //SQLITE TABLO ve SUTUN adlarını alıyoruz.
    public static final String TABLO_Kategori="Kategori";
    public static final String SUTUN_KategoriId="KategoriID";
    public static final String SUTUN_KategoriAdi="KategoriAdi";
    public static final String SUTUN_KategoriKulID="KullaniciID";

    public static final String TABLO_Kullanici="Kullanici";
    public static final String SUTUN_KullaniciId="KullaniciID";
    public static final String SUTUN_Kullanici_Adi="KullaniciAdi";
    public static final String SUTUN_Kullanici_Sifre="KullaniciSifre";

    //Constructor private haline getiriyoruz.
    private Kategori_Veri_Duzenle() {connection = Sql_Baglanma.getInstance().connection;}
    //instance adında private bir nesne oluşturuyoruz.
    private static Kategori_Veri_Duzenle instance=new Kategori_Veri_Duzenle();
    //getInstance ile dışardaki tüm sınıflarımdan getInstance ile Kategori_Veri_Duzenle tüm fonk ulaşabiliyoruz.
    public static Kategori_Veri_Duzenle getInstance(){return instance;}
    //connection değişkeni üretiyoruz.
    Connection connection;
    //Sql sorgusunu çalıştırdığımız Fonksiyon
    public boolean veriDuzenle(String kategori,String kategoriYeni) {
        try {
            //Sql_Baglanma sınıfımdan connection fonksiyonumu çağırıp Javada sql Sorgusu kullanmamı sağlayan Kütüphane
            //fonksiyonu olan Preparedstatement'a atıyoruz.
            //PreparedStatement Statement farklı olarak icine veri alabiliyor.
            System.out.println(kategori);
            System.out.println(KullaniciID());
            System.out.println(KategoriID(kategori));
            PreparedStatement prestatement = connection.prepareStatement("UPDATE "+TABLO_Kategori+" SET "+SUTUN_KategoriAdi+"=? WHERE "+SUTUN_KategoriId+"=? AND "+SUTUN_KategoriKulID+"=?");
            //? işaretleri 1 den başlıyarak sırayla gidiyor ve icine aktarmak istediğimiz değerleri yazdırıyoruz.
            prestatement.setString(1, kategoriYeni);
            prestatement.setString(2, KategoriID(kategori));
            prestatement.setInt(3,KullaniciID());
            //işlemimiz tamamlandıksan sonra executeUpdate komutunu çalıştırarak sorgumuz gercekleşir.
            prestatement.executeUpdate();
            //işlem doğruysa return döner.
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    //kategoriAdi'nı KategoriID fonksiyonuna verdiğimizde veridiğimiz kategoriAdi'nın ID'sini buluyor.
    public String KategoriID(String kategoriAdi) throws SQLException {
        PreparedStatement statement= Sql_Baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KategoriId+" FROM "+TABLO_Kategori+" WHERE "+SUTUN_KategoriAdi+"=? AND "+SUTUN_KategoriKulID+"=?");
        statement.setString(1, kategoriAdi);
        statement.setInt(2, KullaniciID());
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_KategoriId);
    }
    public int KullaniciID() throws SQLException {
        PreparedStatement statement= Sql_Baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KullaniciId+" FROM "+TABLO_Kullanici+" WHERE "+SUTUN_Kullanici_Adi+"="+"?");
        statement.setString(1, Veri_Aktarimi.getInstance().getVeri());
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getInt(SUTUN_KullaniciId);
    }

}
