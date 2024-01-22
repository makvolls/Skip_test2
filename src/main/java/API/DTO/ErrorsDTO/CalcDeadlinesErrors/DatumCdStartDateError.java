package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class DatumCdStartDateError {
    public String[] getStart_date() {
        return start_date;
    }

    public DatumCdStartDateError(String[] start_date) {
        this.start_date = start_date;
    }

    public void setStart_date(String[] start_date) {
        this.start_date = start_date;
    }

    public String[] start_date;
}
