package VeriSiniflari;

public class Veri_Aktarimi {

    private String veri;

    private Veri_Aktarimi() {}
    private static Veri_Aktarimi instance=new Veri_Aktarimi();
    public static Veri_Aktarimi getInstance(){return instance;}

    public String getVeri() {
        return veri;
    }

    public void setVeri(String veri) {
        this.veri = veri;
    }
}