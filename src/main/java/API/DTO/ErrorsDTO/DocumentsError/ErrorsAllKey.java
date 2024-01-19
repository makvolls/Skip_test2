package API.DTO.ErrorsDTO.DocumentsError;

public class ErrorsAllKey {
    public String[] skip_reg_date;
    public String[] control_official_id;
    public String[] control_subject_id;
    public String[] document_name;
    public String[] document_execution_state_id;

    public ErrorsAllKey(String[] skip_reg_date, String[] control_official_id, String[] control_subject_id, String[] document_name, String[] document_execution_state_id, String[] skip_document_type_id, String[] internal) {
        this.skip_reg_date = skip_reg_date;
        this.control_official_id = control_official_id;
        this.control_subject_id = control_subject_id;
        this.document_name = document_name;
        this.document_execution_state_id = document_execution_state_id;
        this.skip_document_type_id = skip_document_type_id;
        this.internal = internal;
    }

    public String[] getSkip_reg_date() {
        return skip_reg_date;
    }

    public void setSkip_reg_date(String[] skip_reg_date) {
        this.skip_reg_date = skip_reg_date;
    }

    public String[] getControl_official_id() {
        return control_official_id;
    }

    public void setControl_official_id(String[] control_official_id) {
        this.control_official_id = control_official_id;
    }

    public String[] getControl_subject_id() {
        return control_subject_id;
    }

    public void setControl_subject_id(String[] control_subject_id) {
        this.control_subject_id = control_subject_id;
    }

    public String[] getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String[] document_name) {
        this.document_name = document_name;
    }

    public String[] getDocument_execution_state_id() {
        return document_execution_state_id;
    }

    public void setDocument_execution_state_id(String[] document_execution_state_id) {
        this.document_execution_state_id = document_execution_state_id;
    }

    public String[] getSkip_document_type_id() {
        return skip_document_type_id;
    }

    public void setSkip_document_type_id(String[] skip_document_type_id) {
        this.skip_document_type_id = skip_document_type_id;
    }

    public String[] getInternal() {
        return internal;
    }

    public void setInternal(String[] internal) {
        this.internal = internal;
    }

    public String[] skip_document_type_id;
    public String[] internal;
}
