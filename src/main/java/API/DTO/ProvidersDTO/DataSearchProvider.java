package API.DTO.ProvidersDTO;

public class DataSearchProvider {
    public String id;
    public String name;
    public boolean active;
    public boolean training;

    public String getId() {
        return id;
    }

    public DataSearchProvider(String id, String name, boolean active, boolean training, String code, boolean archive, String ddo_short_title, String organization_short_title, String in_organization_name, String[] control_subjects, String[] organizations) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.training = training;
        this.code = code;
        this.archive = archive;
        this.ddo_short_title = ddo_short_title;
        this.organization_short_title = organization_short_title;
        this.in_organization_name = in_organization_name;
        this.control_subjects = control_subjects;
        this.organizations = organizations;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isTraining() {
        return training;
    }

    public void setTraining(boolean training) {
        this.training = training;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public String getDdo_short_title() {
        return ddo_short_title;
    }

    public void setDdo_short_title(String ddo_short_title) {
        this.ddo_short_title = ddo_short_title;
    }

    public String getOrganization_short_title() {
        return organization_short_title;
    }

    public void setOrganization_short_title(String organization_short_title) {
        this.organization_short_title = organization_short_title;
    }

    public String getIn_organization_name() {
        return in_organization_name;
    }

    public void setIn_organization_name(String in_organization_name) {
        this.in_organization_name = in_organization_name;
    }

    public String[] getControl_subjects() {
        return control_subjects;
    }

    public void setControl_subjects(String[] control_subjects) {
        this.control_subjects = control_subjects;
    }

    public String[] getOrganizations() {
        return organizations;
    }

    public void setOrganizations(String[] organizations) {
        this.organizations = organizations;
    }

    public String code;
    public boolean archive;
    public String ddo_short_title;
    public String organization_short_title;
    public String in_organization_name;
    public String[] control_subjects;
    public String[] organizations;
}
