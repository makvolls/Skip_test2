package API.DTO.ThemesDto;

public class CreateValueClassThemes {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public CreateValueClassThemes(String name, boolean excluded) {
        this.name = name;
        this.excluded = excluded;
    }

    public String name;
    public boolean excluded;
}
