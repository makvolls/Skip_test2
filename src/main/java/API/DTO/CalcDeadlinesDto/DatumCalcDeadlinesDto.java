package API.DTO.CalcDeadlinesDto;

public class DatumCalcDeadlinesDto {

    public String[] head_executor_deadlines;

    public String[] getHead_executor_deadlines() {
        return head_executor_deadlines;
    }

    public DatumCalcDeadlinesDto(String[] head_executor_deadlines, String[] executor_deadlines) {
        this.head_executor_deadlines = head_executor_deadlines;
        this.executor_deadlines = executor_deadlines;
    }

    public void setHead_executor_deadlines(String[] head_executor_deadlines) {
        this.head_executor_deadlines = head_executor_deadlines;
    }

    public String[] getExecutor_deadlines() {
        return executor_deadlines;
    }

    public void setExecutor_deadlines(String[] executor_deadlines) {
        this.executor_deadlines = executor_deadlines;
    }

    public String[] executor_deadlines;

}
