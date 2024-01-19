package API.DTO.DocumentsDto;

import javax.xml.crypto.Data;
import java.util.Date;

public class DatumDocumentDto {
    public int id;
    public int skip_reg_number;
    public String skip_reg_date;
    public boolean not_sed_document;
    public String sed_reg_number;
    public String sed_document_date;
    public String single_number;
    public String ddo_external_document_date;
    public String ddo_external_document_number;
    public String short_description;
    public String external_reg_date;
    public String external_document_number;
    public String sed_document_type;
    public String resolution;
    public String sed_note;
    public String came_from_name;
    public String came_from_id;
    public String came_from_type;
    public String signer_name;
    public String signer_id;
    public String signer_type;
    public String recipient_name;
    public String recipient_id;
    public String recipient_type;
    public String document_name;
    public boolean internal;
    public String document_date;
    public String document_number;
    public String note;
    public ControlOfficialsDocumentDto control_official;
    public ControlSubjectDocumentsDto control_subject;
    public DocumentExecutionStateDto document_execution_state;
    public SkipDocumentTypeDocumentDto skip_document_type;
    public ControlFromOfficialDocumentDto control_from_official;
    public ControlOfficialHeadDocumentDto control_official_head;
    public SecurityStampDocumentDto security_stamp;
    public ExternalSecurityStampDocumentDto external_security_stamp;
    public SedSecurityStampDocumentDto sed_security_stamp;
    public DdoSecurityStampDocumentDto ddo_security_stamp;

    public DatumDocumentDto(int id, int skip_reg_number, String skip_reg_date, boolean not_sed_document, String sed_reg_number, String sed_document_date, String single_number, String ddo_external_document_date, String ddo_external_document_number, String short_description, String external_reg_date, String external_document_number, String sed_document_type, String resolution, String sed_note, String came_from_name, String came_from_id, String came_from_type, String signer_name, String signer_id, String signer_type, String recipient_name, String recipient_id, String recipient_type, String document_name, boolean internal, String document_date, String document_number, String note, ControlOfficialsDocumentDto control_official, ControlSubjectDocumentsDto control_subject, DocumentExecutionStateDto document_execution_state, SkipDocumentTypeDocumentDto skip_document_type, ControlFromOfficialDocumentDto control_from_official, ControlOfficialHeadDocumentDto control_official_head, SecurityStampDocumentDto security_stamp, ExternalSecurityStampDocumentDto external_security_stamp, SedSecurityStampDocumentDto sed_security_stamp, DdoSecurityStampDocumentDto ddo_security_stamp, UrgencyDocumentDto urgency, Object[] assignments) {
        this.id = id;
        this.skip_reg_number = skip_reg_number;
        this.skip_reg_date = skip_reg_date;
        this.not_sed_document = not_sed_document;
        this.sed_reg_number = sed_reg_number;
        this.sed_document_date = sed_document_date;
        this.single_number = single_number;
        this.ddo_external_document_date = ddo_external_document_date;
        this.ddo_external_document_number = ddo_external_document_number;
        this.short_description = short_description;
        this.external_reg_date = external_reg_date;
        this.external_document_number = external_document_number;
        this.sed_document_type = sed_document_type;
        this.resolution = resolution;
        this.sed_note = sed_note;
        this.came_from_name = came_from_name;
        this.came_from_id = came_from_id;
        this.came_from_type = came_from_type;
        this.signer_name = signer_name;
        this.signer_id = signer_id;
        this.signer_type = signer_type;
        this.recipient_name = recipient_name;
        this.recipient_id = recipient_id;
        this.recipient_type = recipient_type;
        this.document_name = document_name;
        this.internal = internal;
        this.document_date = document_date;
        this.document_number = document_number;
        this.note = note;
        this.control_official = control_official;
        this.control_subject = control_subject;
        this.document_execution_state = document_execution_state;
        this.skip_document_type = skip_document_type;
        this.control_from_official = control_from_official;
        this.control_official_head = control_official_head;
        this.security_stamp = security_stamp;
        this.external_security_stamp = external_security_stamp;
        this.sed_security_stamp = sed_security_stamp;
        this.ddo_security_stamp = ddo_security_stamp;
        this.urgency = urgency;
        this.assignments = assignments;
    }

    public UrgencyDocumentDto urgency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkip_reg_number() {
        return skip_reg_number;
    }

    public void setSkip_reg_number(int skip_reg_number) {
        this.skip_reg_number = skip_reg_number;
    }

    public String getSkip_reg_date() {
        return skip_reg_date;
    }

    public void setSkip_reg_date(String skip_reg_date) {
        this.skip_reg_date = skip_reg_date;
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

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
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

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ControlOfficialsDocumentDto getControl_official() {
        return control_official;
    }

    public void setControl_official(ControlOfficialsDocumentDto control_official) {
        this.control_official = control_official;
    }

    public ControlSubjectDocumentsDto getControl_subject() {
        return control_subject;
    }

    public void setControl_subject(ControlSubjectDocumentsDto control_subject) {
        this.control_subject = control_subject;
    }

    public DocumentExecutionStateDto getDocument_execution_state() {
        return document_execution_state;
    }

    public void setDocument_execution_state(DocumentExecutionStateDto document_execution_state) {
        this.document_execution_state = document_execution_state;
    }

    public SkipDocumentTypeDocumentDto getSkip_document_type() {
        return skip_document_type;
    }

    public void setSkip_document_type(SkipDocumentTypeDocumentDto skip_document_type) {
        this.skip_document_type = skip_document_type;
    }

    public ControlFromOfficialDocumentDto getControl_from_official() {
        return control_from_official;
    }

    public void setControl_from_official(ControlFromOfficialDocumentDto control_from_official) {
        this.control_from_official = control_from_official;
    }

    public ControlOfficialHeadDocumentDto getControl_official_head() {
        return control_official_head;
    }

    public void setControl_official_head(ControlOfficialHeadDocumentDto control_official_head) {
        this.control_official_head = control_official_head;
    }

    public SecurityStampDocumentDto getSecurity_stamp() {
        return security_stamp;
    }

    public void setSecurity_stamp(SecurityStampDocumentDto security_stamp) {
        this.security_stamp = security_stamp;
    }

    public ExternalSecurityStampDocumentDto getExternal_security_stamp() {
        return external_security_stamp;
    }

    public void setExternal_security_stamp(ExternalSecurityStampDocumentDto external_security_stamp) {
        this.external_security_stamp = external_security_stamp;
    }

    public SedSecurityStampDocumentDto getSed_security_stamp() {
        return sed_security_stamp;
    }

    public void setSed_security_stamp(SedSecurityStampDocumentDto sed_security_stamp) {
        this.sed_security_stamp = sed_security_stamp;
    }

    public DdoSecurityStampDocumentDto getDdo_security_stamp() {
        return ddo_security_stamp;
    }

    public void setDdo_security_stamp(DdoSecurityStampDocumentDto ddo_security_stamp) {
        this.ddo_security_stamp = ddo_security_stamp;
    }

    public UrgencyDocumentDto getUrgency() {
        return urgency;
    }

    public void setUrgency(UrgencyDocumentDto urgency) {
        this.urgency = urgency;
    }

    public Object[] getAssignments() {
        return assignments;
    }

    public void setAssignments(Object[] assignments) {
        this.assignments = assignments;
    }

    public Object[] assignments;
}
