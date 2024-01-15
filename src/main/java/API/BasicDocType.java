package API;

import API.DTO.DocTypeDto.RootDocTypePostRequest;
import API.DTO.DocTypeDto.RootDocTypePostDto;
import API.DTO.DocTypeDto.RootDocTypePutDto;
import API.DTO.ErrorsDTO.RootErrorNameShortName;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;

public class BasicDocType {


    public static String API_DOC_TYPE = "http://api.skip.rtech.ru/v1/classifiers/document_types";


    public static RootNameError postErrorName(int id, String name, String short_name, boolean internal,
                                              String genitive_name, boolean excluded) {
        RootDocTypePostRequest docTypeNameError = new RootDocTypePostRequest(name, short_name, internal, genitive_name, excluded);
        docTypeNameError.setName(name);
        docTypeNameError.setShort_name(short_name);
        docTypeNameError.setInternal(internal);
        docTypeNameError.setGenitive_name(genitive_name);
        docTypeNameError.setExcluded(excluded);
        RootNameError docType = BasicApi.postError(API_DOC_TYPE, id, docTypeNameError)
                .as(RootNameError.class);
        return docType;

    }

    public static RootDocTypePostDto newDocType(int id, String name, String short_name, boolean internal,
                                                String genitive_name, boolean excluded) {
        RootDocTypePostRequest newDocType = new RootDocTypePostRequest(name, short_name, internal, genitive_name, excluded);
        newDocType.setName(name);
        newDocType.setShort_name(short_name);
        newDocType.setInternal(internal);
        newDocType.setGenitive_name(genitive_name);
        newDocType.setExcluded(excluded);
        RootDocTypePostDto newDType = BasicApi.post(API_DOC_TYPE, id, newDocType).as(RootDocTypePostDto.class);
        return newDType;
    }

    public static RootErrorNameShortName newDocTypeError(int id, String name, String short_name, boolean internal,
                                                         String genitive_name, boolean excluded) {
        RootDocTypePostRequest newDocTypeError = new RootDocTypePostRequest(name, short_name, internal, genitive_name, excluded);
        newDocTypeError.setName(name);
        newDocTypeError.setShort_name(short_name);
        newDocTypeError.setInternal(internal);
        newDocTypeError.setGenitive_name(genitive_name);
        newDocTypeError.setExcluded(excluded);
        RootErrorNameShortName newDTypeError = BasicApi.postError(API_DOC_TYPE, id, newDocTypeError)
                .as(RootErrorNameShortName.class);
        return newDTypeError;
    }

    public static void deleteDocType(int id, int idDocType) {
        BasicApi.delete(API_DOC_TYPE + "/" + idDocType, id);
        return;

    }
    public static RootErrorNoRights newDocTypeNoRightsError(int id, String name, String short_name, boolean internal,
                                                            String genitive_name, boolean excluded){
        RootDocTypePostRequest newDocTypeError = new RootDocTypePostRequest(name, short_name, internal, genitive_name, excluded);
        newDocTypeError.setName(name);
        newDocTypeError.setShort_name(short_name);
        newDocTypeError.setInternal(internal);
        newDocTypeError.setGenitive_name(genitive_name);
        newDocTypeError.setExcluded(excluded);
        RootErrorNoRights docsTypeAccessError=BasicApi.postErrorNoRights(API_DOC_TYPE, id,newDocTypeError)
                .as(RootErrorNoRights.class);
        return docsTypeAccessError;
    }

    public static RootDocTypePutDto updateDocType(int id,int idDocType, String name, String short_name, boolean internal,
                                              String genitive_name, boolean excluded){
        RootDocTypePostRequest updateDoc=new RootDocTypePostRequest(name,short_name,internal,genitive_name,excluded);
        updateDoc.setName(name);
        updateDoc.setShort_name(short_name);
        updateDoc.setInternal(internal);
        updateDoc.setGenitive_name(genitive_name);
        updateDoc.setExcluded(excluded);
        RootDocTypePutDto updateDocType=BasicApi.put(API_DOC_TYPE+"/"+idDocType,id,updateDoc)
                .as(RootDocTypePutDto.class);
        return updateDocType;
    }

    public static RootNameError updateNameErrorDt(int id,int idDocType, String name, String short_name, boolean internal,
                                                            String genitive_name, boolean excluded){
        RootDocTypePostRequest updateErDoc=new RootDocTypePostRequest(name,short_name,internal,genitive_name,excluded);
        updateErDoc.setName(name);
        updateErDoc.setShort_name(short_name);
        updateErDoc.setInternal(internal);
        updateErDoc.setGenitive_name(genitive_name);
        updateErDoc.setExcluded(excluded);
        RootNameError updateErDocType=BasicApi.putErrors(API_DOC_TYPE+"/"+idDocType,id,updateErDoc)
                .as(RootNameError.class);
        return updateErDocType;
    }

    public static RootErrorNoRights newDocTypeAccessPutError(int id,int idDoc, String name, String short_name, boolean internal,
                                                               String genitive_name, boolean excluded){
        RootDocTypePostRequest newDocTypePutError = new RootDocTypePostRequest(name, short_name, internal, genitive_name, excluded);
        newDocTypePutError.setName(name);
        newDocTypePutError.setShort_name(short_name);
        newDocTypePutError.setInternal(internal);
        newDocTypePutError.setGenitive_name(genitive_name);
        newDocTypePutError.setExcluded(excluded);
        RootErrorNoRights docsTypeAccessPutError=BasicApi.putErrorsNoRights(API_DOC_TYPE+"/"+idDoc,id,newDocTypePutError)
                .as(RootErrorNoRights.class);
        return docsTypeAccessPutError;
    }


}