package API.DTO.PeriodicityDto;

public class RootPeriodicityPut {
    public String name;
    public boolean excluded;

    public String getName() {
        return name;
    }

    public RootPeriodicityPut(String name, boolean excluded) {
        this.name = name;
        this.excluded = excluded;
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
}
