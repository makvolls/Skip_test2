package API.DTO.ControlSubjects;

public class DatumControlSubjects {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFax_number() {
        return fax_number;
    }

    public void setFax_number(String fax_number) {
        this.fax_number = fax_number;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public DatumControlSubjects(int id, String name, String fax_number, String provider_id, boolean deleted) {
        this.id = id;
        this.name = name;
        this.fax_number = fax_number;
        this.provider_id = provider_id;
        this.deleted = deleted;
    }

    public int id;
    public String name;
    public String fax_number;
    public String provider_id;
    public boolean deleted;
}
