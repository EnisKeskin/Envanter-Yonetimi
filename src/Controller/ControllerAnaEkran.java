package Controller;

    import Goruntuler.Formlar;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.Alert;
    import javafx.scene.control.ButtonType;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.control.cell.PropertyValueFactory;
    import SQL_Verilerim.Urun_Veri_Al;
    import SQL_Verilerim.Urun_Veri_Sil;
    import VeriSiniflari.Urun_Veri;
    import VeriSiniflari.Veri_Aktarimiurun;

    import java.io.IOException;
    import java.sql.SQLException;
    import java.util.Optional;
import javafx.application.Platform;

public class ControllerAnaEkran {
        //Tableview'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableView<Urun_Veri> tableview;
        //TableColumn'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableColumn<Urun_Veri, String> tablec_urunAdi;
        //TableColumn'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableColumn<Urun_Veri, String> tablec_kategori;
        //TableColumn'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableColumn<Urun_Veri, String> tablec_miktar;
        //TableColumn'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableColumn<Urun_Veri, String> tablec_birim;
    //TableColumn'ı FXML ile classlar arası bağlantı için değişkene atandı
        @FXML
        private TableColumn<Urun_Veri, String> tablec_tarih;

        //FXML Urunekleme URL'si
        private String urunEkleme ="/Tasarim/UrunEkleme.fxml";
        //FXML urunduzenle URL'si
        private String urunDuzenle ="/Tasarim/UrunDuzenleme.fxml";
        //FXML Kategoriekleme URL'si
        private String kategori="/Tasarim/KategoriEkleme.fxml";
        //FXML Kategorisil URL'si
        private String kategoriSil ="/Tasarim/KategoriSil.fxml";
        //FXML Kategoriduzenle URL'si
        private String kategoriDuzenle ="/Tasarim/KategoriDuzenle.fxml";
        //FXML bilgi URL'si
        private String bilgi="/Tasarim/Hakkinda.fxml";
        //FXML bilgi URL'si
        private String cikis="/Tasarim/Giris.fxml";
        public void tableViewVeriEkleme(){
            //TableView başlangıçta temizliyor.
            tableview.getItems().clear();
            //TableView atanabilmesi için ObservableList denişgeni oluşturuluyor;
            ObservableList<Urun_Veri> urun= FXCollections.observableArrayList();
            //ObservableList Urun_Veri_Al veriler çekiliyor
            urun.addAll(Urun_Veri_Al.getInstance().UrunGetir());
            /*Urun_Veri degen degisken isimlerini her bir TableColumn atıyorum.
            * Bu sayede hangi verinin hangi kısma gidecegi belirleniyor*/
            tablec_urunAdi.setCellValueFactory(new PropertyValueFactory<>("UrunAdi"));
            tablec_kategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
            tablec_miktar.setCellValueFactory(new PropertyValueFactory<>("miktar"));
            tablec_birim.setCellValueFactory(new PropertyValueFactory<>("birim"));
            tablec_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
            //TableView'a veri aktarılıyor.
            tableview.setItems(urun);
        }
        //Menu Hakkında içindeki menuItem Nesnesine tıklandığında Bilgi form çağırıyor
        @FXML
        void menuItm_Bilgi(ActionEvent event) throws IOException {
            Formlar.getInstance().EkranCıktısı(bilgi,"Bilgi", "/Resimler/Logo.jpg");
        }
        //Menu İşlemler içindeki Ürün Menüsünün içindeki MenuItem form çağırıyor
        @FXML
        void menuItm_UrunEkle(ActionEvent event) throws IOException{
            //Urun urunEkleme formunu açıyor
            Formlar.getInstance().EkranCıktısıUrunEkleme(urunEkleme,"Ürün ekle", "/Resimler/Logo.jpg");
        }
        //Menu İşlemlerin içindeki Kategori menüsünü içindeki ekle MenuItem form çağırıyor
        @FXML
        void menuItm_KatEkle(ActionEvent event) throws IOException
        {
            //Kategori Ekleme formunu açıyor
            Formlar.getInstance().EkranCıktısı(kategori,"Kategori Ekle", "/Resimler/Logo.jpg");
        }
        //Menu İşlemlerin içindeki Kategori menüsünü içindeki sil MenuItem form çağırıyor
        @FXML
        void menuItm_KatSil(ActionEvent event) throws IOException {
            Formlar.getInstance().EkranCıktısıKatSil(kategoriSil,event,"Kategori Sil", "/Resimler/Logo.jpg");
        }
        //Menu İşlemlerin içindeki Kategori menüsünü içindeki duzenle MenuItem form çağırıyor
        @FXML
        void menuItm_KatDuzenle(ActionEvent event) throws IOException {
            Formlar.getInstance().EkranCıktısıKatDuzenle(kategoriDuzenle,event,"Kategori Düzenle", "/Resimler/Logo.jpg");
        }
        //Menu Tabloyu Güncelle MenuItem tableViewVeriEkleme() fonksiyonunu çağırıyor
        @FXML
        void menuItem_TabloyuGuncelle(ActionEvent event) {
            //İşlemler MenuBar Menu'sündeki MenuItem'daki Tabloyu Güncelleniyor
            tableViewVeriEkleme();
        }
        @FXML
        void menuItem_Cikis(ActionEvent event) throws IOException {
            ControllerGiris.stage.close();
           Goruntuler.Formlar.getInstance().EkranCıktısı(cikis,"Giriş Ekranı","/Resimler/Logo.jpg");
        }
        
        //Sağ tıklanıldığında açılan ContextMenu içindeki Ekle'ye basıldığında Ürün Ekleme formunu çağırıyor
        @FXML
        void conMen_Ekle(ActionEvent event) throws IOException {
            Formlar.getInstance().EkranCıktısıUrunEkleme(urunEkleme,"Ürün ekle", "/Resimler/Logo.jpg");
        }
        //Sağ tıklanıldığında açılan ContextMenu içindeki Sil'ye basıldığında Silme sınıfını çağırıyor
        @FXML
        void conMen_Sil(ActionEvent event) {
            //TableView üzerine tıkladığımız nesnenin bilgilerini veriyor
            Urun_Veri id=tableview.getSelectionModel().getSelectedItem();
            if(id != null)
            {
            //Uyarı cıkıyor eğer Ok basılırsa nesneyi siliyor
            if (Uyarı()) {
                //Urun_Veri_Sil sınıfından verisil çağırıyor ve icine seçtirdiğimiz nesnenin id atıyoruz.
                Urun_Veri_Sil.getInstance().UrunVeriSil(id.getUrunId());
                //TableView yenilemek için tekrardan çağırıyoruz.
                tableViewVeriEkleme();
            }}
            
        }
        //Sağ tıklanıldığında açılan ContextMenu içindeki Duzenle'ye basıldığında Ürün Duzenleme formunu çağırıyor
        @FXML
        void conMen_Duzenle(ActionEvent event) throws IOException, SQLException {
            //TableView üzerine tıkladığımız nesnenin bilgilerini veriyor
            Urun_Veri id=tableview.getSelectionModel().getSelectedItem();
            //Seçtiğimiz nesnenin tüm verilerini aktarma metodu ile setliyoruz düzenledeki TextField ve ComboBox içinde verilerimizi yerleştirebilmek için
            if (id != null) {
            Veri_Aktarimiurun.getInstance().setUrunID(id.getUrunId());
            Veri_Aktarimiurun.getInstance().setUrunAdi(id.getUrunAdi());
            Veri_Aktarimiurun.getInstance().setUrunMiktar(id.getMiktar());
            Veri_Aktarimiurun.getInstance().setUrunBirim(id.getBirim());
            Veri_Aktarimiurun.getInstance().setUrunKategori(id.getKategori());
            //Ürün Düzenle formunu çağırıyor.
            Formlar.getInstance().EkranCıktısıUrunDuzenle(urunDuzenle,event,"Ürün Düzenle", "/Resimler/Logo.jpg");}
            }
         //tableView fonk çağırarak yenilenen fonk tekrardan ekrana yazdırıyoruz.
        @FXML
        void conMen_Guncelle(ActionEvent event) throws IOException {
            tableViewVeriEkleme();
        }
        //Ekrana bir Uyarı çıkartıp Silinip Silinmeyeceğini Kontrol Ediyor.
         public boolean Uyarı() {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Silme İşlemi");
             alert.setHeaderText("Silmek istediğinize");
             alert.setContentText("Emin misiniz ?");
             Optional<ButtonType> button= alert.showAndWait();
             //Ekrana gelen buttonlardan OK basarsanız true döndürüp nesneyi silecek.
             if (button.get()==ButtonType.OK) {
                 return true;
             }else{
                 return false;
             }
         }

}
