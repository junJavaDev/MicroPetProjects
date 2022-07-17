package config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Objects;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Setting {

    private int[][] iBody;
    private int[][] jBody;
    private int[][] lBody;
    private int[][] oBody;
    private int[][] sBody;
    private int[][] tBody;
    private int[][] zBody;

    //============================ SAFE_THREAD_SINGLETON ======================================
    private static volatile Setting setting;

    public static Setting get() {
        Setting setting = Setting.setting;
        if (Objects.isNull(setting)) {
            synchronized (Setting.class) {
                if (Objects.isNull(setting = Setting.setting)) {
                    setting = Setting.setting = new Setting();
                }
            }
        }
        return setting;
    }
    //============================ /SAFE_THREAD_SINGLETON ====================================

//    //================================ INIT ========================================
    private Setting() {
        loadFromDefault();
//        updateFromYAML();
    }
//
    private void loadFromDefault() {
        Field[] settingFields = Setting.class.getDeclaredFields();
        Field[] defaultFields = Default.class.getDeclaredFields();
        for (Field settingField : settingFields) {
            String settingFieldName = settingField.getName().toLowerCase();
            for (Field defaultField : defaultFields) {
                defaultField.setAccessible(true);
                String defaultFieldName = defaultField.getName().replaceAll("_", "").toLowerCase();
                if (settingFieldName.equals(defaultFieldName)) {
                    try {
                        settingField.set(this, defaultField.get(null));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                defaultField.setAccessible(false);
            }
        }
    }
//
//    private void updateFromYAML() {
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ObjectReader readerForSettingUpdating = mapper.readerForUpdating(this);
//        URL settingResource = Setting.class.getClassLoader().getResource(SETTING_YAML);
//        URL foodRationResource = Setting.class.getClassLoader().getResource(FOOD_RATION_YAML);
//        if (Objects.nonNull(settingResource) && Objects.nonNull(foodRationResource)) {
//            try {
//                readerForSettingUpdating.readValue(settingResource.openStream());
//                readerForSettingUpdating.readValue(foodRationResource.openStream());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//    //=============================== /INIT ========================================

}
