package SQL_Verilerim;

import VeriSiniflari.Kategori_Veri;
import VeriSiniflari.Veri_Aktarimi;

import java.sql.*;
import java.util.ArrayList;


public class Kategori_Veri_Al {
    //SQLITE TABLO ve SUTUN adlarını alıyoruz.
    public static final String TABLO_Kategori="Kategori";
    public static final String SUTUN_KategoriAdi="KategoriAdi";
    public static final String SUTUN_KategoriKulID="KullaniciID";

    public static final String TABLO_Kullanici="Kullanici";
    public static final String SUTUN_KullaniciId="KullaniciID";
    public static final String SUTUN_Kullanici_Adi="KullaniciAdi";
    public static final String SUTUN_Kullanici_Sifre="KullaniciSifre";

    //Constructor private haline getiriyoruz.
    private Kategori_Veri_Al() {connection=Sql_Baglanma.getInstance().connection;}
    //instance adında private bir nesne oluşturuyoruz.
    private static Kategori_Veri_Al instance=new Kategori_Veri_Al();
    //getInstance ile dışardaki tüm sınıflarımdan getInstance ile Kategori_Veri_Al tüm fonk ulaşabiliyoruz.
    public static Kategori_Veri_Al getInstance(){return instance;}
    //Sql sorgusunu çalıştırdığımız Fonksiyon
    Connection connection;
    public ArrayList<Kategori_Veri> KategoriGetir() {

        try {
            //Sql_Baglanma sınıfımdan connection fonksiyonumu çağırıp Javada sql Sorgusu kullanmamı sağlayan Kütüphane
            //fonksiyonu olan Preparedstatement'a atıyoruz.
            //PreparedStatement Statement farklı olarak icine veri alabiliyor.
            PreparedStatement statement= connection.prepareStatement("SELECT "+SUTUN_KategoriAdi+" FROM "+TABLO_Kategori+" WHERE "+SUTUN_KategoriKulID+"= ?");
            statement.setInt(1,KullaniciID());
            ResultSet sonuc=statement.executeQuery();
            //gelen verileri atmak için ArrayList oluşturuyoruz.
            ArrayList<Kategori_Veri> tumKategori=new ArrayList<>();
            //gelen verileri tümünü gezmek icin while kullandık
            while(sonuc.next()) {
                //Kategori_Veri KategoriVeri nesnensini oluşturduk
                Kategori_Veri KategoriVeri=new Kategori_Veri();
                //gelen veriyi KategoriVeri setledik.
                KategoriVeri.setKategoriAdi(sonuc.getString(SUTUN_KategoriAdi));
                //verilerimi tutmak için tanımladığım tumKategori ArrayList gelen verilerimi tek tek atıyoruz.
                tumKategori.add(KategoriVeri);
            }
            //geri ArrayList döndürüp dışarda verilerimin kullanılmasını sağlıyoruz.
            return tumKategori;
        } catch (SQLException e) {
            System.out.println("Sorgu Başarısız");
            e.printStackTrace();
            return null;
        }
    }
    public int KullaniciID() throws SQLException {
        PreparedStatement statement= Sql_Baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KullaniciId+" FROM "+TABLO_Kullanici+" WHERE "+SUTUN_Kullanici_Adi+"="+"?");
        statement.setString(1, Veri_Aktarimi.getInstance().getVeri());
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getInt(SUTUN_KullaniciId);
    }
}
