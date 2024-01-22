package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class DatumStartDatePeriodicityIdError {

    public String[] start_date;
    public String[] periodicity_id;

    public String[] getStart_date() {
        return start_date;
    }

    public DatumStartDatePeriodicityIdError(String[] start_date, String[] periodicity_id) {
        this.start_date = start_date;
        this.periodicity_id = periodicity_id;
    }

    public void setStart_date(String[] start_date) {
        this.start_date = start_date;
    }

    public String[] getPeriodicity_id() {
        return periodicity_id;
    }

    public void setPeriodicity_id(String[] periodicity_id) {
        this.periodicity_id = periodicity_id;
    }
}
