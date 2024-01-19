package API.DTO.DocumentsDto;

public class RootDocumentAllField {
    public String skip_reg_date;
    public String short_description;
    public String control_official_id;
    public int control_subject_id;
    public String document_name;
    public int document_execution_state_id;
    public int skip_document_type_id;
    public boolean internal;
    public boolean not_sed_document;
    public String sed_reg_number;
    public String sed_document_date;
    public int sed_security_stamp_id;
    public String single_number;
    public String ddo_external_document_date;
    public String ddo_external_document_number;
    public int ddo_security_stamp_id;
    public int urgency_id;
    public String came_from_name;
    public String came_from_id;
    public String came_from_type;
    public String external_reg_date;
    public String external_document_number;
    public int external_security_stamp_id;
    public String signer_name;
    public String signer_id;
    public String signer_type;
    public String recipient_name;
    public String recipient_id;
    public String recipient_type;
    public String sed_document_type;
    public String resolution;
    public String sed_note;
    public String control_from_official_id;
    public String control_official_head_id;

    public RootDocumentAllField(String skip_reg_date, String short_description, String control_official_id, int control_subject_id, String document_name, int document_execution_state_id, int skip_document_type_id, boolean internal, boolean not_sed_document, String sed_reg_number, String sed_document_date, int sed_security_stamp_id, String single_number, String ddo_external_document_date, String ddo_external_document_number, int ddo_security_stamp_id, int urgency_id, String came_from_name, String came_from_id, String came_from_type, String external_reg_date, String external_document_number, int external_security_stamp_id, String signer_name, String signer_id, String signer_type, String recipient_name, String recipient_id, String recipient_type, String sed_document_type, String resolution, String sed_note, String control_from_official_id, String control_official_head_id, String document_date, String document_number, int security_stamp_id, String note) {
        this.skip_reg_date = skip_reg_date;
        this.short_description = short_description;
        this.control_official_id = control_official_id;
        this.control_subject_id = control_subject_id;
        this.document_name = document_name;
        this.document_execution_state_id = document_execution_state_id;
        this.skip_document_type_id = skip_document_type_id;
        this.internal = internal;
        this.not_sed_document = not_sed_document;
        this.sed_reg_number = sed_reg_number;
        this.sed_document_date = sed_document_date;
        this.sed_security_stamp_id = sed_security_stamp_id;
        this.single_number = single_number;
        this.ddo_external_document_date = ddo_external_document_date;
        this.ddo_external_document_number = ddo_external_document_number;
        this.ddo_security_stamp_id = ddo_security_stamp_id;
        this.urgency_id = urgency_id;
        this.came_from_name = came_from_name;
        this.came_from_id = came_from_id;
        this.came_from_type = came_from_type;
        this.external_reg_date = external_reg_date;
        this.external_document_number = external_document_number;
        this.external_security_stamp_id = external_security_stamp_id;
        this.signer_name = signer_name;
        this.signer_id = signer_id;
        this.signer_type = signer_type;
        this.recipient_name = recipient_name;
        this.recipient_id = recipient_id;
        this.recipient_type = recipient_type;
        this.sed_document_type = sed_document_type;
        this.resolution = resolution;
        this.sed_note = sed_note;
        this.control_from_official_id = control_from_official_id;
        this.control_official_head_id = control_official_head_id;
        this.document_date = document_date;
        this.document_number = document_number;
        this.security_stamp_id = security_stamp_id;
        this.note = note;
    }

    public String document_date;
    public String document_number;

    public String getSkip_reg_date() {
        return skip_reg_date;
    }

    public void setSkip_reg_date(String skip_reg_date) {
        this.skip_reg_date = skip_reg_date;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
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

    public boolean isNot_sed_document() {
        return not_sed_document;
    }

    public void setNot_sed_document(boolean not_sed_document) {
        this.not_sed_document = not_sed_document;
    }

    public String getSed_reg_number() {
        return sed_reg_number;
    }

    public void setSed_reg_number(String sed_reg_number) {
        this.sed_reg_number = sed_reg_number;
    }

    public String getSed_document_date() {
        return sed_document_date;
    }

    public void setSed_document_date(String sed_document_date) {
        this.sed_document_date = sed_document_date;
    }

    public int getSed_security_stamp_id() {
        return sed_security_stamp_id;
    }

    public void setSed_security_stamp_id(int sed_security_stamp_id) {
        this.sed_security_stamp_id = sed_security_stamp_id;
    }

    public String getSingle_number() {
        return single_number;
    }

    public void setSingle_number(String single_number) {
        this.single_number = single_number;
    }

    public String getDdo_external_document_date() {
        return ddo_external_document_date;
    }

    public void setDdo_external_document_date(String ddo_external_document_date) {
        this.ddo_external_document_date = ddo_external_document_date;
    }

    public String getDdo_external_document_number() {
        return ddo_external_document_number;
    }

    public void setDdo_external_document_number(String ddo_external_document_number) {
        this.ddo_external_document_number = ddo_external_document_number;
    }

    public int getDdo_security_stamp_id() {
        return ddo_security_stamp_id;
    }

    public void setDdo_security_stamp_id(int ddo_security_stamp_id) {
        this.ddo_security_stamp_id = ddo_security_stamp_id;
    }

    public int getUrgency_id() {
        return urgency_id;
    }

    public void setUrgency_id(int urgency_id) {
        this.urgency_id = urgency_id;
    }

    public String getCame_from_name() {
        return came_from_name;
    }

    public void setCame_from_name(String came_from_name) {
        this.came_from_name = came_from_name;
    }

    public String getCame_from_id() {
        return came_from_id;
    }

    public void setCame_from_id(String came_from_id) {
        this.came_from_id = came_from_id;
    }

    public String getCame_from_type() {
        return came_from_type;
    }

    public void setCame_from_type(String came_from_type) {
        this.came_from_type = came_from_type;
    }

    public String getExternal_reg_date() {
        return external_reg_date;
    }

    public void setExternal_reg_date(String external_reg_date) {
        this.external_reg_date = external_reg_date;
    }

    public String getExternal_document_number() {
        return external_document_number;
    }

    public void setExternal_document_number(String external_document_number) {
        this.external_document_number = external_document_number;
    }

    public int getExternal_security_stamp_id() {
        return external_security_stamp_id;
    }

    public void setExternal_security_stamp_id(int external_security_stamp_id) {
        this.external_security_stamp_id = external_security_stamp_id;
    }

    public String getSigner_name() {
        return signer_name;
    }

    public void setSigner_name(String signer_name) {
        this.signer_name = signer_name;
    }

    public String getSigner_id() {
        return signer_id;
    }

    public void setSigner_id(String signer_id) {
        this.signer_id = signer_id;
    }

    public String getSigner_type() {
        return signer_type;
    }

    public void setSigner_type(String signer_type) {
        this.signer_type = signer_type;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getRecipient_type() {
        return recipient_type;
    }

    public void setRecipient_type(String recipient_type) {
        this.recipient_type = recipient_type;
    }

    public String getSed_document_type() {
        return sed_document_type;
    }

    public void setSed_document_type(String sed_document_type) {
        this.sed_document_type = sed_document_type;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSed_note() {
        return sed_note;
    }

    public void setSed_note(String sed_note) {
        this.sed_note = sed_note;
    }

    public String getControl_from_official_id() {
        return control_from_official_id;
    }

    public void setControl_from_official_id(String control_from_official_id) {
        this.control_from_official_id = control_from_official_id;
    }

    public String getControl_official_head_id() {
        return control_official_head_id;
    }

    public void setControl_official_head_id(String control_official_head_id) {
        this.control_official_head_id = control_official_head_id;
    }

    public String getDocument_date() {
        return document_date;
    }

    public void setDocument_date(String document_date) {
        this.document_date = document_date;
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }

    public int getSecurity_stamp_id() {
        return security_stamp_id;
    }

    public void setSecurity_stamp_id(int security_stamp_id) {
        this.security_stamp_id = security_stamp_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int security_stamp_id;
    public String note;
}
