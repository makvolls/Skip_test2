package API.DTO.ProvidersDTO;

public class RootUpProvider {
    public boolean isTraining() {
        return training;
    }

    public void setTraining(boolean training) {
        this.training = training;
    }

    public RootUpProvider(boolean training) {
        this.training = training;
    }

    public boolean training;
}
