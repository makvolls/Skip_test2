package API.DTO.RolesDto;

public class RootRolesDto {
    public MetaRolesDto meta;

    public MetaRolesDto getMeta() {
        return meta;
    }

    public RootRolesDto(MetaRolesDto meta, DatumRolesDto[] data) {
        this.meta = meta;
        this.data = data;
    }

    public void setMeta(MetaRolesDto meta) {
        this.meta = meta;
    }

    public DatumRolesDto[] getData() {
        return data;
    }

    public void setData(DatumRolesDto[] data) {
        this.data = data;
    }

    public DatumRolesDto[] data;
}
