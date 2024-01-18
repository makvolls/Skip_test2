package API.DTO.OrganizationsDto;

public class RootOrganizations {
    public MetaOrganizations meta;

    public MetaOrganizations getMeta() {
        return meta;
    }

    public RootOrganizations(MetaOrganizations meta, DatumOrganizations[] data) {
        this.meta = meta;
        this.data = data;
    }

    public void setMeta(MetaOrganizations meta) {
        this.meta = meta;
    }

    public DatumOrganizations[] getData() {
        return data;
    }

    public void setData(DatumOrganizations[] data) {
        this.data = data;
    }

    public DatumOrganizations[] data;
}
