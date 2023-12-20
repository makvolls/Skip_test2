package API.DTO.ProvidersDTO;

public class RootPermissions {
    public MetaPermissions getMeta() {
        return meta;
    }

    public void setMeta(MetaPermissions meta) {
        this.meta = meta;
    }

    public DatumPermissions[] getData() {
        return data;
    }

    public void setData(DatumPermissions[] data) {
        this.data = data;
    }

    public RootPermissions(MetaPermissions meta, DatumPermissions[] data) {
        this.meta = meta;
        this.data = data;
    }

    public MetaPermissions meta;
    public DatumPermissions[] data;
}
