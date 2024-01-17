
package SKIP_API;


import API.BasicImageMarks;
import API.DTO.ErrorsDTO.RootErrorNameShortName;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.ImageMarksDto.RootElementImageMarks;
import API.DTO.ImageMarksDto.RootImageMarks;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SKIP_52 {
    RootNameError errValueName;
    RootElementImageMarks newValue, newValue2, newValueWithoutExcluded, newValueWithOnlyName;
    RootErrorNameShortName errValueNameSn;
    RootImageMarks listValues;
    RootErrorNoRights errNoRights, errNoRights2;

    @AfterClass
    public void delete() {
        BasicImageMarks.deleteImageMarksValue(1, newValue.data.id);
        BasicImageMarks.deleteImageMarksValue(1, newValue2.data.id);
        BasicImageMarks.deleteImageMarksValue(1, newValueWithoutExcluded.data.id);
        BasicImageMarks.deleteImageMarksValue(1, newValueWithOnlyName.data.id);
    }

    @Test
    public void step01() {
        errValueName = BasicImageMarks.createImageMarksErrNoName(1, "", "", true);
        Assert.assertTrue(errValueName.errors.name[0].equals("не может быть пустым"));
    }

    @Test
    public void step02() {
        newValue = BasicImageMarks.createImageMarks(1, "z", "", true);
        Assert.assertTrue(newValue.data.name.equals("z"));
        Assert.assertTrue(newValue.data.excluded);
        Assert.assertTrue(newValue.data.short_name.equals(""));
    }

    @Test
    public void step03() {
        newValue2 = BasicImageMarks.createImageMarks(1, "7", "t", false);
        Assert.assertTrue(newValue2.data.name.equals("7"));
        Assert.assertTrue(!newValue2.data.excluded);
        Assert.assertTrue(newValue2.data.short_name.equals("t"));
    }

    @Test

    public void step04() {
        errValueNameSn = BasicImageMarks.createImageMarksErrNoNameSn(1, "7", "t", false);
        Assert.assertTrue(errValueNameSn.errors.name[0].equals("уже существует"));
        Assert.assertTrue(errValueNameSn.errors.short_name[0].equals("уже существует"));
    }

    @Test

    public void step05() {
        listValues = BasicImageMarks.getImageMarksList(1);
        Assert.assertNotNull(listValues);
        Assert.assertEquals(1, listValues.data[0].priority);
    }

    @Test

    public void step06() {
        errNoRights = BasicImageMarks.createImageMarksErrNoRights(6, "7", "t", false);
        Assert.assertTrue(errNoRights.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test

    public void step07() {
        errNoRights2 = BasicImageMarks.createImageMarksErrNoRights(8, "7", "t", false);
        Assert.assertTrue(errNoRights2.error.equals("Доступ к ресурсу запрещен"));
    }

    @Test

    public void step08() {
        newValueWithoutExcluded = BasicImageMarks.createImageMarksWithoutExcluded(1, "vdsvv", "vsdv");
        Assert.assertTrue(newValueWithoutExcluded.data.name.equals("vdsvv"));
        Assert.assertTrue(newValueWithoutExcluded.data.short_name.equals("vsdv"));
    }

    @Test

    public void step09() {
        newValueWithOnlyName = BasicImageMarks.createImageMarksWithName(1, "gwsv");
        Assert.assertTrue(newValueWithOnlyName.data.name.equals("gwsv"));
    }
}

