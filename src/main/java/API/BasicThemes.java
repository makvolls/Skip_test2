package API;

import API.DTO.ThemesDto.CreateValueClassThemes;
import API.DTO.ThemesDto.RootElementThemes;
import API.DTO.ThemesDto.RootThemes;

public class BasicThemes {
    public static String API_THEMES = "http://api.skip.rtech.ru/v1/classifiers/themes";

    /**
     * Получение значений справочника
     *
     * @param id - id авторизуемого пользователя
     **/

    public static RootThemes getThemesList(int id) {
        RootThemes actualThemes = BasicApi.get(API_THEMES, id).body().as(RootThemes.class);
        return actualThemes;
    }

    /**
     * Создание значение справочника
     *
     * @param id       - id авторизованного пользователя
     * @param name     - Название справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public static RootElementThemes createThemes(int id, String name, boolean excluded) {
        CreateValueClassThemes value = new CreateValueClassThemes(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);
        RootElementThemes createdValue = BasicApi.post(API_THEMES, id, value).body().as(RootElementThemes.class);
        return createdValue;

    }

    /**
     * Изменение значения справочника
     *
     * @param id       - id авторизованного пользователя
     * @param idValue  - id значения , в котором изменяется значение
     * @param name     - Название справочника
     * @param excluded - элемент исключения значения из справочника
     **/

    public static RootElementThemes updateThemesValue(int id, int idValue, String name, boolean excluded) {
        CreateValueClassThemes value = new CreateValueClassThemes(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);
        RootElementThemes newValue = BasicApi.put(API_THEMES + "/" + idValue, id, value).body().as(RootElementThemes.class);
        return newValue;

    }
    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public static void deleteThemesValue(int id, int idValue){
        BasicApi.delete(API_THEMES + "/" + idValue, id);
        return;

    }
}
