package API.DTO.OshsMvdOfficialsDto;

public class RootOshsMvdOfficials {
    public MetaOshsMvdOfficials meta;

    public MetaOshsMvdOfficials getMeta() {
        return meta;
    }

    public RootOshsMvdOfficials(MetaOshsMvdOfficials meta, DatumOshsMvdOfficials[] data) {
        this.meta = meta;
        this.data = data;
    }

    public void setMeta(MetaOshsMvdOfficials meta) {
        this.meta = meta;
    }

    public DatumOshsMvdOfficials[] getData() {
        return data;
    }

    public void setData(DatumOshsMvdOfficials[] data) {
        this.data = data;
    }

    public DatumOshsMvdOfficials[] data;
}
