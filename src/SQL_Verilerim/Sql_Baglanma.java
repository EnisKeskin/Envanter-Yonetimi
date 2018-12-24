package SQL_Verilerim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sql_Baglanma
{
    //SQLITE gelen database isimini DB_NAME atıyoruz.
    public static final String DB_NAME="EnvanterTakip.db";
    //SQLITE bağlanma komutunu iceren String yazıyoruz.
    public static final String Connection_String="jdbc:sqlite:"+DB_NAME;
    //Constructor private haline getiriyoruz.
    private Sql_Baglanma(){}
    //instance adında private bir nesne oluşturuyoruz.
    private static Sql_Baglanma instance=new Sql_Baglanma();
    //getInstance ile dışardaki tüm sınıflarımdan getInstance ile Birim_Veri_Al tüm fonk ulaşabiliyoruz.
    public static Sql_Baglanma getInstance(){return instance;}
    //connection değişkeni üretiyoruz.
    public Connection connection;
    /*sqlite bağlanması*/
    public boolean veritabaninaBaglanma()
    {
        try {
            //connection degiskenini icine DiverManger.getConnection class ve fonk birlikte kullanarak database bağlantı sağlıyoruz.
            connection= DriverManager.getConnection(Connection_String);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /*sqlite çıkış sağlanması*/
    public void veritabaniniKapat()
    {
        if(connection!=null) {
            try {
                //.close fonk veritabanımı kapatıyor.
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}





















