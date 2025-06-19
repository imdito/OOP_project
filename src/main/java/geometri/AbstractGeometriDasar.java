package geometri;

public abstract class AbstractGeometriDasar implements BangunGeometri {
    public String namaBangun;
    public AbstractGeometriDasar(String namaBangun) {
        this.namaBangun = namaBangun;
    }
    public String getNamaBangun() {
        return namaBangun;
    }

}