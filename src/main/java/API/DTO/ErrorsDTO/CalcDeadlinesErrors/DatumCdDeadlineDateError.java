package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class DatumCdDeadlineDateError {
    public String[] deadline_date;

    public String[] getDeadline_date() {
        return deadline_date;
    }

    public DatumCdDeadlineDateError(String[] deadline_date) {
        this.deadline_date = deadline_date;
    }

    public void setDeadline_date(String[] deadline_date) {
        this.deadline_date = deadline_date;
    }
}
