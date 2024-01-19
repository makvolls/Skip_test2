package API.DTO.DocumentsDto;

public class RootRequestDocumentDto {
    public String skip_reg_date;
    public String control_official_id;
    public int control_subject_id;
    public String document_name;
    public int document_execution_state_id;
    public int skip_document_type_id;
    public boolean internal;

    public RootRequestDocumentDto(String skip_reg_date, String control_official_id, int control_subject_id, String document_name, int document_execution_state_id, int skip_document_type_id, boolean internal, String short_description) {
        this.skip_reg_date = skip_reg_date;
        this.control_official_id = control_official_id;
        this.control_subject_id = control_subject_id;
        this.document_name = document_name;
        this.document_execution_state_id = document_execution_state_id;
        this.skip_document_type_id = skip_document_type_id;
        this.internal = internal;
        this.short_description = short_description;
    }

    public String getSkip_reg_date() {
        return skip_reg_date;
    }

    public void setSkip_reg_date(String skip_reg_date) {
        this.skip_reg_date = skip_reg_date;
    }

    public String getControl_official_id() {
        return control_official_id;
    }

    public void setControl_official_id(String control_official_id) {
        this.control_official_id = control_official_id;
    }

    public int getControl_subject_id() {
        return control_subject_id;
    }

    public void setControl_subject_id(int control_subject_id) {
        this.control_subject_id = control_subject_id;
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public int getDocument_execution_state_id() {
        return document_execution_state_id;
    }

    public void setDocument_execution_state_id(int document_execution_state_id) {
        this.document_execution_state_id = document_execution_state_id;
    }

    public int getSkip_document_type_id() {
        return skip_document_type_id;
    }

    public void setSkip_document_type_id(int skip_document_type_id) {
        this.skip_document_type_id = skip_document_type_id;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String short_description;
}
