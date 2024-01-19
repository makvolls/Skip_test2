package SKIP_API;

import API.BasicDocuments;
import API.DTO.DocumentsDto.RootDocumentDto;
import API.DTO.ErrorsDTO.DocumentsError.RootErrorsAllKey;
import API.DTO.ErrorsDTO.DocumentsError.RootErrorsShortDescriptions;
import API.DTO.ErrorsDTO.RootError;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SKIP_118 {

    int idAut1 = 1;
    int idAut2 = 6;

    int id;
    RootDocumentDto newValue, newValue2, newValue3, actualValue, updateValue, updateValue2;
    RootErrorsShortDescriptions errorShortDescription;
    RootErrorsAllKey errorsAllKey;
    RootErrorNoRights errorNoRights1, errorNoRights2;
    RootError actualValueError, errorNoRight;

    @Test
    public void step01() {
        newValue = BasicDocuments.createDocument(idAut1, "2023-12-20T06:06:31.485Z",
                "59e9be1f83c0000040000001", 47, "Тест 205", 1,
                1, false, "Test 205");
        Assert.assertTrue(newValue.data.skip_reg_date.equals("2023-12-20T06:06:31.485Z"));
        Assert.assertTrue(newValue.data.control_official.id.equals("59e9be1f83c0000040000001"));
        Assert.assertEquals(47, newValue.data.control_subject.id);
        Assert.assertTrue(newValue.data.document_name.equals("Тест 205"));
        Assert.assertEquals(1, newValue.data.document_execution_state.id);
        Assert.assertEquals(1, newValue.data.skip_document_type.id);
        Assert.assertTrue(!newValue.data.internal);
        Assert.assertTrue(newValue.data.short_description.equals("Test 205"));
    }

    @Test
    public void step02() {
        newValue2 = BasicDocuments.createDocument(idAut1, "2023-12-20T06:06:31.485Z",
                "6572d9f914b4000005000001", 122, "Тест 205", 2,
                2, false, "");
        Assert.assertTrue(newValue2.data.skip_reg_date.equals("2023-12-20T06:06:31.485Z"));
        Assert.assertTrue(newValue2.data.control_official.id.equals("6572d9f914b4000005000001"));
        Assert.assertEquals(122, newValue2.data.control_subject.id);
        Assert.assertTrue(newValue2.data.document_name.equals("Тест 205"));
        Assert.assertEquals(2, newValue2.data.document_execution_state.id);
        Assert.assertEquals(2, newValue2.data.skip_document_type.id);
        Assert.assertTrue(!newValue2.data.internal);
        Assert.assertTrue(newValue2.data.short_description.equals(""));
    }

    @Test
    public void step03() {
        errorShortDescription = BasicDocuments.createDocumentWithOutShortDescription(idAut1, "2023-12-20T06:06:31.485Z",
                "6572d9f914b4000005000001", 122, "Тест 205", 2,
                2, false);
        Assert.assertTrue(errorShortDescription.errors.short_description[0].equals("не может быть пустым"));
    }


    @Test
    public void step04() {
        errorsAllKey = BasicDocuments.createDocumentOnlyShortDescription(idAut1, "hort_description test");
        Assert.assertTrue(errorsAllKey.errors.skip_reg_date[0].equals("не может быть пустым"));
        Assert.assertTrue(errorsAllKey.errors.control_official_id[0].equals("не может быть пустым"));
        Assert.assertTrue(errorsAllKey.errors.control_subject_id[0].equals("не может быть пустым"));
        Assert.assertTrue(errorsAllKey.errors.document_name[0].equals("не может быть пустым"));
        Assert.assertTrue(errorsAllKey.errors.document_execution_state_id[0].equals("не может быть пустым"));
        Assert.assertTrue(errorsAllKey.errors.skip_document_type_id[0].equals("не может быть пустым"));
        Assert.assertTrue(errorsAllKey.errors.internal[0].equals("не может быть пустым"));
    }

    @Test
    public void step05() {

        newValue3 = BasicDocuments.createDocumentAllField(idAut1, "2023-12-25T06:44:34.553Z",
                "short_description test", "5b60ec5c6a38000008000001",
                122, "document_name test", 1, 1,
                false, true, "string", "2023-12-25T06:44:34.553Z",
                1, "string", "2023-12-25T06:44:34.553Z",
                "string", 1, 1, "string"
                , "string", "string", "2023-12-25T06:44:34.553Z",
                "string", 1, "string"
                , "string", "string", "string", "string", "string",
                "string", "string", "string"
                , "60b580365f25000008000001", "5a985fe56a4b000027000001",
                "2023-12-25T06:44:34.553Z", "string", 1, "string");
        Assert.assertTrue(newValue3.data.skip_reg_date.equals("2023-12-25T06:44:34.553Z"));
        Assert.assertTrue(newValue3.data.short_description.equals("short_description test"));
        Assert.assertTrue(newValue3.data.control_official.id.equals("5b60ec5c6a38000008000001"));
        Assert.assertTrue(newValue3.data.control_subject.id == 122);
        Assert.assertTrue(newValue3.data.document_name.equals("document_name test"));
        Assert.assertEquals(1, newValue3.data.document_execution_state.id);
        Assert.assertEquals(1, newValue3.data.skip_document_type.id);
        Assert.assertTrue(!newValue3.data.internal);
        Assert.assertTrue(newValue3.data.not_sed_document);
        Assert.assertTrue(newValue3.data.sed_reg_number.equals("string"));
        Assert.assertTrue(newValue3.data.sed_document_date.equals("2023-12-25T06:44:34.553Z"));
        Assert.assertTrue(newValue3.data.sed_security_stamp.id == 1);
        Assert.assertTrue(newValue3.data.single_number.equals("string"));
        Assert.assertTrue(newValue3.data.ddo_external_document_date.equals("2023-12-25T06:44:34.553Z"));
        Assert.assertTrue(newValue3.data.ddo_external_document_number.equals("string"));
        Assert.assertTrue(newValue3.data.ddo_security_stamp.id == 1);
        Assert.assertTrue(newValue3.data.urgency.id == 1);
        Assert.assertTrue(newValue3.data.came_from_name.equals("string"));
        Assert.assertTrue(newValue3.data.came_from_id.equals("string"));
        Assert.assertTrue(newValue3.data.came_from_type.equals("string"));
        Assert.assertTrue(newValue3.data.external_reg_date.equals("2023-12-25T06:44:34.553Z"));
        Assert.assertTrue(newValue3.data.external_document_number.equals("string"));
        Assert.assertTrue(newValue3.data.external_security_stamp.id == 1);
        Assert.assertTrue(newValue3.data.signer_name.equals("string"));
        Assert.assertTrue(newValue3.data.signer_id.equals("string"));
        Assert.assertTrue(newValue3.data.signer_type.equals("string"));
        Assert.assertTrue(newValue3.data.recipient_name.equals("string"));
        Assert.assertTrue(newValue3.data.recipient_id.equals("string"));
        Assert.assertTrue(newValue3.data.recipient_type.equals("string"));
        Assert.assertTrue(newValue3.data.sed_document_type.equals("string"));
        Assert.assertTrue(newValue3.data.resolution.equals("string"));
        Assert.assertTrue(newValue3.data.sed_note.equals("string"));
        Assert.assertTrue(newValue3.data.control_from_official.id.equals("60b580365f25000008000001"));
        Assert.assertTrue(newValue3.data.control_official_head.id.equals("5a985fe56a4b000027000001"));
        Assert.assertTrue(newValue3.data.document_date.equals("2023-12-25T06:44:34.553Z"));
        Assert.assertTrue(newValue3.data.document_number.equals("string"));
        Assert.assertTrue(newValue3.data.security_stamp.id == 1);
        Assert.assertTrue(newValue3.data.note.equals("string"));
        id = newValue3.data.id;

    }

    @Test
    public void step06() {
        errorNoRights1 = BasicDocuments.createDocumentErrorNoRights(idAut2,
                "2023-12-20T06:06:31.485Z",
                "6572d9f914b4000005000001", 122,
                "Тест 205 польз 6", 2,
                2, false, "123");
        Assert.assertTrue(errorNoRights1.error.equals("Доступ к ресурсу запрещен"));
    }


    @Test
    public void step07() {
        actualValue = BasicDocuments.getDocument(idAut1, id);
        Assert.assertTrue(actualValue.data.skip_reg_date.equals("2023-12-25T06:44:34.553Z"));
        Assert.assertTrue(actualValue.data.short_description.equals("short_description test"));
        Assert.assertTrue(actualValue.data.control_official.id.equals("5b60ec5c6a38000008000001"));
        Assert.assertTrue(actualValue.data.control_subject.id == 122);
        Assert.assertTrue(actualValue.data.document_name.equals("document_name test"));
        Assert.assertEquals(1, actualValue.data.document_execution_state.id);
        Assert.assertEquals(1, actualValue.data.skip_document_type.id);
        Assert.assertTrue(!actualValue.data.internal);
        Assert.assertTrue(actualValue.data.not_sed_document);
        Assert.assertTrue(actualValue.data.sed_reg_number.equals("string"));
        Assert.assertTrue(actualValue.data.sed_document_date.equals("2023-12-25T06:44:34.553Z"));
        Assert.assertTrue(actualValue.data.sed_security_stamp.id == 1);
        Assert.assertTrue(actualValue.data.single_number.equals("string"));
        Assert.assertTrue(actualValue.data.ddo_external_document_date.equals("2023-12-25T06:44:34.553Z"));
        Assert.assertTrue(actualValue.data.ddo_external_document_number.equals("string"));
        Assert.assertTrue(actualValue.data.ddo_security_stamp.id == 1);
        Assert.assertTrue(actualValue.data.urgency.id == 1);
        Assert.assertTrue(actualValue.data.came_from_name.equals("string"));
        Assert.assertTrue(actualValue.data.came_from_id.equals("string"));
        Assert.assertTrue(actualValue.data.came_from_type.equals("string"));
        Assert.assertTrue(actualValue.data.external_reg_date.equals("2023-12-25T06:44:34.553Z"));
        Assert.assertTrue(actualValue.data.external_document_number.equals("string"));
        Assert.assertTrue(actualValue.data.external_security_stamp.id == 1);
        Assert.assertTrue(actualValue.data.signer_name.equals("string"));
        Assert.assertTrue(actualValue.data.signer_id.equals("string"));
        Assert.assertTrue(actualValue.data.signer_type.equals("string"));
        Assert.assertTrue(actualValue.data.recipient_name.equals("string"));
        Assert.assertTrue(actualValue.data.recipient_id.equals("string"));
        Assert.assertTrue(actualValue.data.recipient_type.equals("string"));
        Assert.assertTrue(actualValue.data.sed_document_type.equals("string"));
        Assert.assertTrue(actualValue.data.resolution.equals("string"));
        Assert.assertTrue(actualValue.data.sed_note.equals("string"));
        Assert.assertTrue(actualValue.data.control_from_official.id.equals("60b580365f25000008000001"));
        Assert.assertTrue(actualValue.data.control_official_head.id.equals("5a985fe56a4b000027000001"));
        Assert.assertTrue(actualValue.data.document_date.equals("2023-12-25T06:44:34.553Z"));
        Assert.assertTrue(actualValue.data.document_number.equals("string"));
        Assert.assertTrue(actualValue.data.security_stamp.id == 1);
        Assert.assertTrue(actualValue.data.note.equals("string"));
    }

    @Test
    public void step08() {

        actualValueError = BasicDocuments.getDocumentError(idAut1, 1000);
        Assert.assertTrue(actualValueError.error.equals("Запись для Documents::Document с id = 1000 не найдена"));
    }

    @Test
    public void step09() {

        errorNoRight = BasicDocuments.getDocumentErrorNoRights(idAut2, id);
        Assert.assertTrue(errorNoRight.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step10() {
        updateValue = BasicDocuments.updateDocumentWithOutShortDescription(idAut1, id, "2023-12-20T06:06:31.485Z",
                "59e9be1f83c0000040000001", 47, "Тест 205", 1,
                1, false);
        Assert.assertTrue(updateValue.data.skip_reg_date.equals("2023-12-20T06:06:31.485Z"));
        Assert.assertTrue(updateValue.data.control_official.id.equals("59e9be1f83c0000040000001"));
        Assert.assertEquals(47, updateValue.data.control_subject.id);
        Assert.assertTrue(updateValue.data.document_name.equals("Тест 205"));
        Assert.assertEquals(1, updateValue.data.document_execution_state.id);
        Assert.assertEquals(1, updateValue.data.skip_document_type.id);
        Assert.assertTrue(!updateValue.data.internal);
        Assert.assertEquals(id, updateValue.data.id);
    }

    @Test
    public void step11() {

        errorNoRights2 = BasicDocuments.updateDocumentErrorNoRights(idAut2, id, "2023-12-20T06:06:31.485Z",
                "59e9be1f83c0000040000001", 47, "Тест 205", 1,
                1, false);
        Assert.assertTrue(errorNoRights2.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test
    public void step12() {

        updateValue2 = BasicDocuments.updateDocumentNoBody(idAut1, id);
        Assert.assertTrue(updateValue2.data.skip_reg_date.equals("2023-12-20T06:06:31.485Z"));
        Assert.assertTrue(updateValue2.data.control_official.id.equals("59e9be1f83c0000040000001"));
        Assert.assertEquals(47, updateValue2.data.control_subject.id);
        Assert.assertTrue(updateValue2.data.document_name.equals("Тест 205"));
        Assert.assertEquals(1, updateValue2.data.document_execution_state.id);
        Assert.assertEquals(1, updateValue2.data.skip_document_type.id);
        Assert.assertTrue(!updateValue2.data.internal);
        Assert.assertEquals(id, updateValue2.data.id);

    }


}
