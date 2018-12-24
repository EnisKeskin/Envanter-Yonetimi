package SQL_Verilerim;

import VeriSiniflari.Veri_Aktarimi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kategori_Veri_Ekle {
    //SQLITE TABLO ve SUTUN adlarını alıyoruz.
    public static final String TABLO_Kategori="Kategori";
    public static final String SUTUN_KategoriId="KategoriID";
    public static final String SUTUN_KategoriAdi="KategoriAdi";

    public static final String TABLO_Kullanici="Kullanici";
    public static final String SUTUN_KullaniciId="KullaniciID";
    public static final String SUTUN_Kullanici_Adi="KullaniciAdi";
    public static final String SUTUN_Kullanici_Sifre="KullaniciSifre";

    //Constructor private haline getiriyoruz.
    private Kategori_Veri_Ekle(){conn = Sql_Baglanma.getInstance().connection;}
    //instance adında private bir nesne oluşturuyoruz.
    private static Kategori_Veri_Ekle instance=new Kategori_Veri_Ekle();
    //getInstance ile dışardaki tüm sınıflarımdan getInstance ile Kategori_Veri_Ekle tüm fonk ulaşabiliyoruz.
    public static Kategori_Veri_Ekle getInstance(){return instance;}
    //connection değişkeni üretiyoruz.
    private Connection conn;
    //Verileri eklediğimiz fonksiyon
    public boolean KategoriVeriEkle(String Kategori_Adi)
    {
        try  {
            //Sql_Baglanma sınıfımdan connection fonksiyonumu çağırıp Javada sql Sorgusu kullanmamı sağlayan Kütüphane
            //fonksiyonu olan Preparedstatement'a atıyoruz.
            //PreparedStatement Statement farklı olarak icine veri alabiliyor.
            PreparedStatement prestatement = conn.prepareStatement("INSERT INTO "+TABLO_Kategori+"("+SUTUN_KategoriAdi+","+SUTUN_KullaniciId+") VALUES(?,?)");
            //? işaretleri 1 den başlıyarak sırayla gidiyor ve icine aktarmak istediğimiz değerleri yazdırıyoruz.
            prestatement.setString(1, Kategori_Adi);
            prestatement.setInt(2,KullaniciID());
            //işlemimiz tamamlandıksan sonra executeUpdate komutunu çalıştırarak sorgumuz gercekleşir.
            prestatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
    public int KullaniciID() throws SQLException {
        PreparedStatement statement= Sql_Baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KullaniciId+" FROM "+TABLO_Kullanici+" WHERE "+SUTUN_Kullanici_Adi+"="+"?");
        statement.setString(1, Veri_Aktarimi.getInstance().getVeri());
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getInt(SUTUN_KullaniciId);
    }
}
