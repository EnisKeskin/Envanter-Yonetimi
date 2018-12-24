package SQL_Verilerim;

import VeriSiniflari.Veri_Aktarimi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Urun_Veri_Sil
{

    public static final String TABLO_Urun="Urun";
    public static final String SUTUN_urun_Id="UrunID";
    public static final String SUTUN_urun_kategoriId="UrunKategoriId";
    public static final String SUTUN_KategoriKulID="KullaniciID";

    public static final String TABLO_Kategori="Kategori";
    public static final String SUTUN_KategoriId="KategoriID";
    public static final String SUTUN_KategoriAdi="KategoriAdi";

    public static final String TABLO_Kullanici="Kullanici";
    public static final String SUTUN_KullaniciId="KullaniciID";
    public static final String SUTUN_Kullanici_Adi="KullaniciAdi";
    public static final String SUTUN_Kullanici_Sifre="KullaniciSifre";

    //Constructor private haline getiriyoruz.
    private Urun_Veri_Sil(){conn = Sql_Baglanma.getInstance().connection;}
    //instance adında private bir nesne oluşturuyoruz.
    private static Urun_Veri_Sil instance=new Urun_Veri_Sil();
    //getInstance ile dışardaki tüm sınıflarımdan getInstance ile Kategori_Veri_Sil tüm fonk ulaşabiliyoruz.
    public static Urun_Veri_Sil getInstance(){return instance;}
    private Connection conn;
    //Verileri sildiğimiz fonksiyon.
    public boolean UrunVeriSil(String id){
        try {
            //Sql_Baglanma sınıfımdan connection fonksiyonumu çağırıp Javada sql Sorgusu kullanmamı sağlayan Kütüphane
            //fonksiyonu olan Preparedstatement'a atıyoruz.
            //PreparedStatement Statement farklı olarak icine veri alabiliyor.
            PreparedStatement prestatement=conn.prepareStatement("DELETE FROM "+TABLO_Urun+" WHERE "+SUTUN_urun_Id+"="+"?");
            //? işaretleri 1 den başlıyarak sırayla gidiyor ve icine aktarmak istediğimiz değerleri yazdırıyoruz.
            prestatement.setString(1, id);
            //işlemimiz tamamlandıksan sonra executeUpdate komutunu çalıştırarak sorgumuz gercekleşir.
            prestatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    //Eğer Herhangi bir kategori silinirse ve o kategoriye tamınlı ürünler varsa o kategoriye bağlı tüm ürünler silinir.
    public boolean UrunVeriSilKategori(String id){
        try {
            PreparedStatement prestatement=conn.prepareStatement("DELETE FROM "+TABLO_Urun+" WHERE "+SUTUN_urun_kategoriId+"="+"? and "+SUTUN_KategoriKulID+"=?");
            prestatement.setString(1, KategoriID(id));
            prestatement.setInt(2,KullaniciID());
            prestatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    //kategoriAdi'nı KategoriID fonksiyonuna verdiğimizde veridiğimiz kategoriAdi'nın ID'sini buluyor.
    public String KategoriID(String id) throws SQLException {
        PreparedStatement statement= Sql_Baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KategoriId+" FROM "+TABLO_Kategori+" WHERE "+SUTUN_KategoriAdi+"="+"?");
        statement.setString(1, id);
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
