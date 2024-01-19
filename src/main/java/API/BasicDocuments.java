package API;

import API.DTO.DocTypeDto.RootDocTypePostDto;
import API.DTO.DocTypeDto.RootDocTypePostRequest;
import API.DTO.DocumentsDto.*;
import API.DTO.ErrorsDTO.DocumentsError.RootErrorsAllKey;
import API.DTO.ErrorsDTO.DocumentsError.RootErrorsShortDescriptions;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.OshsMvdOfficialsDto.RootOshsMvdOfficials;

import javax.xml.crypto.Data;
import java.util.Date;

public class BasicDocuments {

    public static String API_DOCUMENTS = "http://api.skip.rtech.ru/v1/documents";

    public static RootDocumentDto createDocument(int id, String skip_reg_date, String control_official_id,
                                                 int control_subject_id, String document_name, int document_execution_state_id,
                                                 int skip_document_type_id, boolean internal, String short_description) {
        RootRequestDocumentDto newDocument = new RootRequestDocumentDto(skip_reg_date, control_official_id,
                control_subject_id, document_name, document_execution_state_id, skip_document_type_id, internal,
                short_description);
        newDocument.setSkip_reg_date(skip_reg_date);
        newDocument.setControl_official_id(control_official_id);
        newDocument.setInternal(internal);
        newDocument.setControl_subject_id(control_subject_id);
        newDocument.setDocument_name(document_name);
        newDocument.setDocument_execution_state_id(document_execution_state_id);
        newDocument.setSkip_document_type_id(skip_document_type_id);
        newDocument.setInternal(internal);
        newDocument.setShort_description(short_description);
        RootDocumentDto newDType = BasicApi.post(API_DOCUMENTS, id, newDocument).as(RootDocumentDto.class);
        return newDType;
    }
    public static RootErrorsShortDescriptions createDocumentWithOutShortDescription(int id, String skip_reg_date, String control_official_id,
                                                                                    int control_subject_id, String document_name, int document_execution_state_id,
                                                                                    int skip_document_type_id, boolean internal) {
        RootRequestDocumentWithOutShortDescription newDocument = new RootRequestDocumentWithOutShortDescription(skip_reg_date, control_official_id,
                control_subject_id, document_name, document_execution_state_id, skip_document_type_id, internal);
        newDocument.setSkip_reg_date(skip_reg_date);
        newDocument.setControl_official_id(control_official_id);
        newDocument.setInternal(internal);
        newDocument.setControl_subject_id(control_subject_id);
        newDocument.setDocument_name(document_name);
        newDocument.setDocument_execution_state_id(document_execution_state_id);
        newDocument.setSkip_document_type_id(skip_document_type_id);
        newDocument.setInternal(internal);
        RootErrorsShortDescriptions newDType = BasicApi.postError(API_DOCUMENTS, id, newDocument).as(RootErrorsShortDescriptions.class);
        return newDType;
    }

    public static RootErrorsAllKey createDocumentOnlyShortDescription(int id, String short_description) {
        RootShortDescriptionRequest newDocument = new  RootShortDescriptionRequest(short_description);
        newDocument.setShort_description(short_description);
        RootErrorsAllKey newDType = BasicApi.postError(API_DOCUMENTS, id, newDocument).as(RootErrorsAllKey.class);
        return newDType;
    }

    public static RootDocumentDto createDocumentAllField(int id,String skip_reg_date, String short_description,
                                                         String control_official_id,
                                                         int control_subject_id, String document_name,
                                                         int document_execution_state_id, int skip_document_type_id,
                                                         boolean internal, boolean not_sed_document,
                                                         String sed_reg_number, String sed_document_date,
                                                         int sed_security_stamp_id, String single_number,
                                                         String ddo_external_document_date,
                                                         String ddo_external_document_number, int ddo_security_stamp_id,
                                                         int urgency_id, String came_from_name, String came_from_id,
                                                         String came_from_type, String external_reg_date,
                                                         String external_document_number, int external_security_stamp_id,
                                                         String signer_name, String signer_id, String signer_type,
                                                         String recipient_name, String recipient_id,
                                                         String recipient_type, String sed_document_type,
                                                         String resolution, String sed_note,
                                                         String control_from_official_id, String control_official_head_id,
                                                         String document_date, String document_number,
                                                         int security_stamp_id, String note) {
        RootDocumentAllField newDocument = new RootDocumentAllField(skip_reg_date,  short_description,
                control_official_id,  control_subject_id,  document_name,  document_execution_state_id,
                skip_document_type_id,  internal,  not_sed_document,  sed_reg_number,  sed_document_date,
                sed_security_stamp_id,  single_number,  ddo_external_document_date,  ddo_external_document_number,
                ddo_security_stamp_id, urgency_id, came_from_name, came_from_id, came_from_type, external_reg_date,
                external_document_number, external_security_stamp_id, signer_name, signer_id, signer_type,
                recipient_name, recipient_id, recipient_type, sed_document_type, resolution, sed_note,
                control_from_official_id, control_official_head_id, document_date, document_number, security_stamp_id,
                note);
        newDocument.setSkip_reg_date(skip_reg_date);
        newDocument.setShort_description(short_description);
        newDocument.setControl_official_id(control_official_id);
        newDocument.setControl_subject_id(control_subject_id);
        newDocument.setDocument_name(document_name);
        newDocument.setDocument_execution_state_id(document_execution_state_id);
        newDocument.setSkip_document_type_id(skip_document_type_id);
        newDocument.setInternal(internal);
        newDocument.setNot_sed_document(not_sed_document);
        newDocument.setSed_reg_number(sed_reg_number);
        newDocument.setSed_document_date(sed_document_date);
        newDocument.setSed_security_stamp_id(sed_security_stamp_id);
        newDocument.setSingle_number(single_number);
        newDocument.setDdo_external_document_date(ddo_external_document_date);
        newDocument.setDdo_external_document_number(ddo_external_document_number);
        newDocument.setDdo_security_stamp_id(ddo_security_stamp_id);
        newDocument.setUrgency_id(urgency_id);
        newDocument.setCame_from_name(came_from_name);
        newDocument.setCame_from_id(came_from_id);
        newDocument.setCame_from_type(came_from_type);
        newDocument.setExternal_reg_date(external_reg_date);
        newDocument.setExternal_document_number(external_document_number);
        newDocument.setExternal_security_stamp_id(external_security_stamp_id);
        newDocument.setSigner_name(signer_name);
        newDocument.setSigner_id(signer_id);
        newDocument.setSigner_type(signer_type);
        newDocument.setRecipient_name(recipient_name);
        newDocument.setRecipient_id(recipient_id);
        newDocument.setRecipient_type(recipient_type);
        newDocument.setSed_document_type(sed_document_type);
        newDocument.setResolution(resolution);
        newDocument.setSed_note(sed_note);
        newDocument.setControl_from_official_id(control_from_official_id);
        newDocument.setControl_official_head_id(control_official_head_id);
        newDocument.setDocument_date(document_date);
        newDocument.setDocument_number(document_number);
        newDocument.setSecurity_stamp_id(security_stamp_id);
        newDocument.setNote(note);
        RootDocumentDto newDoc = BasicApi.post(API_DOCUMENTS, id, newDocument).as(RootDocumentDto.class);
        return newDoc;
    }

    public static RootErrorNoRights createDocumentErrorNoRights(int id, String skip_reg_date, String control_official_id,
                                                                                       int control_subject_id, String document_name, int document_execution_state_id,
                                                                                       int skip_document_type_id, boolean internal,String short_description) {
        RootRequestDocumentDto newDocument = new RootRequestDocumentDto(skip_reg_date, control_official_id,
                control_subject_id, document_name, document_execution_state_id, skip_document_type_id, internal,short_description);
        newDocument.setSkip_reg_date(skip_reg_date);
        newDocument.setControl_official_id(control_official_id);
        newDocument.setInternal(internal);
        newDocument.setControl_subject_id(control_subject_id);
        newDocument.setDocument_name(document_name);
        newDocument.setDocument_execution_state_id(document_execution_state_id);
        newDocument.setSkip_document_type_id(skip_document_type_id);
        newDocument.setInternal(internal);
        RootErrorNoRights newDType = BasicApi.postErrorNoRights(API_DOCUMENTS, id, newDocument).as(RootErrorNoRights.class);
        return newDType;
    }

    public static RootDocumentDto getDocument(int idAut,int idDoc){
        RootDocumentDto listDoc=BasicApi.get(API_DOCUMENTS+"/"+idDoc,idAut).as(RootDocumentDto.class);
        return listDoc;
    }
    public static RootError getDocumentError(int idAut, int idDoc){
        RootError listDoc=BasicApi.getErrorNoFound(API_DOCUMENTS+"/"+idDoc,idAut).as(RootError.class);
        return listDoc;
    }
    public static RootError getDocumentErrorNoRights(int idAut, int idDoc){
        RootError listDoc=BasicApi.getError(API_DOCUMENTS+"/"+idDoc,idAut).as(RootError.class);
        return listDoc;
    }

    public static RootDocumentDto updateDocumentWithOutShortDescription(int id,int idDocument, String skip_reg_date, String control_official_id,
                                                                                    int control_subject_id, String document_name, int document_execution_state_id,
                                                                                    int skip_document_type_id, boolean internal) {
        RootRequestDocumentWithOutShortDescription newDocument = new RootRequestDocumentWithOutShortDescription(skip_reg_date, control_official_id,
                control_subject_id, document_name, document_execution_state_id, skip_document_type_id, internal);
        newDocument.setSkip_reg_date(skip_reg_date);
        newDocument.setControl_official_id(control_official_id);
        newDocument.setInternal(internal);
        newDocument.setControl_subject_id(control_subject_id);
        newDocument.setDocument_name(document_name);
        newDocument.setDocument_execution_state_id(document_execution_state_id);
        newDocument.setSkip_document_type_id(skip_document_type_id);
        newDocument.setInternal(internal);
        RootDocumentDto newDType = BasicApi.put(API_DOCUMENTS+"/"+idDocument, id, newDocument).as(RootDocumentDto.class);
        return newDType;
    }

    public static RootErrorNoRights updateDocumentErrorNoRights(int id,int idDocument, String skip_reg_date, String control_official_id,
                                                                        int control_subject_id, String document_name, int document_execution_state_id,
                                                                        int skip_document_type_id, boolean internal) {
        RootRequestDocumentWithOutShortDescription newDocument = new RootRequestDocumentWithOutShortDescription(skip_reg_date, control_official_id,
                control_subject_id, document_name, document_execution_state_id, skip_document_type_id, internal);
        newDocument.setSkip_reg_date(skip_reg_date);
        newDocument.setControl_official_id(control_official_id);
        newDocument.setInternal(internal);
        newDocument.setControl_subject_id(control_subject_id);
        newDocument.setDocument_name(document_name);
        newDocument.setDocument_execution_state_id(document_execution_state_id);
        newDocument.setSkip_document_type_id(skip_document_type_id);
        newDocument.setInternal(internal);
        RootErrorNoRights newDType = BasicApi.putErrorsNoRights(API_DOCUMENTS+"/"+idDocument, id, newDocument).as(RootErrorNoRights.class);
        return newDType;
    }

    public static RootDocumentDto updateDocumentNoBody(int id,int idDocument) {
        RootDocumentDto newDType = BasicApi.putNoBody(API_DOCUMENTS+"/"+idDocument, id).as(RootDocumentDto.class);
        return newDType;
    }
}
