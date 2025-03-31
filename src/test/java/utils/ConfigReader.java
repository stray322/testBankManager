package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Утилита для чтения конфигурационных параметров из файла config.properties.
 */
public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл config.properties не найден!");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Ошибка чтения config.properties", e));
        }
    }

    /**
     * Возвращает значение свойства по ключу.
     * @param key Ключ свойства (например, "base.url").
     * @return Значение свойства.
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(String.format("Свойство " + key + " не найдено в config.properties!"));
        }
        return value;
    }

    public static int getTimeout(){
        return Integer.parseInt(getProperty("timeout"));
    }
}
