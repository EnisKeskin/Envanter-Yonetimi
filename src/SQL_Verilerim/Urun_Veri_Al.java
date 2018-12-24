package SQL_Verilerim;

import VeriSiniflari.Urun_Veri;
import VeriSiniflari.Veri_Aktarimi;

import java.sql.*;
import java.util.ArrayList;

public class Urun_Veri_Al {

    //SQLITE TABLO ve SUTUN adlarını alıyoruz.
    public static final String TABLO_Urun="Urun";
    public static final String SUTUN_urun_Id="UrunID";
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
    public static final String SUTUN_Kullanici_Sifre="KullaniciSifre";


    //Constructor private haline getiriyoruz.
    private Urun_Veri_Al() {connection=Sql_Baglanma.getInstance().connection;}
    //instance adında private bir nesne oluşturuyoruz.
    private static Urun_Veri_Al instance=new Urun_Veri_Al();
    //getInstance ile dışardaki tüm sınıflarımdan getInstance ile Urun_Veri_Al tüm fonk ulaşabiliyoruz.
    public static Urun_Veri_Al getInstance(){return instance;}
    Connection connection;
    //Sql sorgusunu çalıştırdığımız Fonksiyon
    public ArrayList<Urun_Veri> UrunGetir() {
        try {
            //Sql_Baglanma sınıfımdan connection fonksiyonumu çağırıp Javada sql Sorgusu kullanmamı sağlayan Kütüphane
            //fonksiyonu olan Preparedstatement'a atıyoruz.
            //PreparedStatement Statement farklı olarak icine veri alabiliyor.
            PreparedStatement prestatement=connection.prepareStatement("SELECT * FROM "+TABLO_Urun+" WHERE "+SUTUN_urun_KullaniciId+"=?");
            //? işaretleri 1 den başlıyarak sırayla gidiyor ve icine aktarmak istediğimiz değerleri yazdırıyoruz.
            prestatement.setInt(1, KullaniciID());
            //yazdığım sql sorgusunu sonuc değişkenine atıyoruz.
            ResultSet sonuc=prestatement.executeQuery();
            //gelen verileri atmak için ArrayList oluşturuyoruz.
            ArrayList<Urun_Veri> tumUrunVeri=new ArrayList<>();
            //gelen verileri tümünü gezmek icin while kullandık
            while(sonuc.next()) {
                //Kategori_Veri KategoriVeri nesnensini oluşturduk
                Urun_Veri urunVeri=new Urun_Veri();
                //gelen veriyi UrunVeri setledik.
                urunVeri.setUrunId(sonuc.getString(SUTUN_urun_Id));
                urunVeri.setUrunAdi(sonuc.getString(SUTUN_urun_Adi));
                urunVeri.setKategori(KategoriID(sonuc.getString(SUTUN_urun_kategoriId)));
                urunVeri.setMiktar(sonuc.getString(SUTUN_urun_Miktari));
                urunVeri.setBirim(BirimID(sonuc.getString(SUTUN_urun_BirimId)));
                urunVeri.setTarih(sonuc.getString(SUTUN_urun_Tarih));
                //verilerimi tutmak için tanımladığım tumUrunVeri ArrayList gelen verilerimi tek tek atıyoruz.
                tumUrunVeri.add(urunVeri);
            }
            return tumUrunVeri;
        } catch (SQLException e) {
            System.out.println("Sorgu Başarısız");
            e.printStackTrace();
            return null;
        }
    }

    //kategoriAdi'nı KategoriID fonksiyonuna verdiğimizde veridiğimiz kategoriAdi'nın ID'sini buluyor.
    public String KategoriID(String id) throws SQLException {
        PreparedStatement statement= Sql_Baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KategoriAdi+" FROM "+TABLO_Kategori+" WHERE "+SUTUN_KategoriId+"="+"?");
        statement.setString(1, id);
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_KategoriAdi);
    }
    //BirimAdi'nı BirimID fonksiyonuna verdiğimizde veridiğimiz BirimAdi'nın ID'sini buluyor.
    public String BirimID(String birimAdi) throws SQLException {
        PreparedStatement statement= Sql_Baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_BirimAdi+" FROM "+TABLO_Birim+" WHERE "+SUTUN_BirimId+"="+"?");
        statement.setString(1, birimAdi);
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getString(SUTUN_BirimAdi);
    }
    public int KullaniciID() throws SQLException {
        PreparedStatement statement= Sql_Baglanma.getInstance().connection.prepareStatement("SELECT "+SUTUN_KullaniciId+" FROM "+TABLO_Kullanici+" WHERE "+SUTUN_Kullanici_Adi+"="+"?");
        statement.setString(1, Veri_Aktarimi.getInstance().getVeri());
        ResultSet sonuc=statement.executeQuery();
        return sonuc.getInt(SUTUN_KullaniciId);
    }

}
