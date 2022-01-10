package utils;

import pojo.Order;

/**
 * Util класс для парсинга срока аренды из Json в объект {@link pojo.Order} и наоборот
 *
 * @version 1.1
 * @autor Stanislav Trebnikov
 */
public class ParseOrderTime {

    /**
     * Статическая функция парсинга срока аренды {@link Order#getTime()}} из объекта {@link Order} для Json файла
     *
     * @return возвращает готовую строку для записи в Json файл
     */
    public static String parseTimeToJson(int time) {
        String resultTime = null;
        if (time < 7) {
            if (time == 1) {
                resultTime = time + " day";
            } else {
                resultTime = time + " days";
            }
        }
        if (time >= 7) {
            if (time % 7 == 0) {
                if (time / 7 == 1) {
                    resultTime = time + " week";
                } else if (time / 7 > 1) {
                    resultTime = time / 7 + " weeks";
                }
            } else {
                if (time / 7 == 1 && time % 7 == 1) {
                    resultTime = time / 7 + " week " + time % 7 + " day";
                } else if (time / 7 == 1 && time % 7 > 1) {
                    resultTime = time / 7 + " week " + time % 7 + " days";
                } else if (time / 7 > 1 && time % 7 == 1) {
                    resultTime = time / 7 + " weeks " + time % 7 + " day";
                } else if (time / 7 > 1 && time % 7 > 1) {
                    resultTime = time / 7 + " weeks " + time % 7 + " days";
                }
            }
        }
        return resultTime;
    }

    /**
     * Статическая функция парсинга срока аренды {@link Order#getTime()}} из Json файла в объект {@link Order}
     *
     * @return возвращает готовую строку для записи в объект {@link Order}
     */
    public static int parseTimeFromJson(String time) {
        int resultTime = 0;
        final String[] s = time.split(" ");
        if (s.length == 4) {
            if ((s[1].equals("week") && s[3].equals("day"))
                    || (s[1].equals("week") && s[3].equals("days"))) {
                resultTime = (Integer.parseInt(s[0]) * 7) + Integer.parseInt(s[2]);
            } else if ((s[1].equals("weeks") && s[3].equals("day"))
                    || (s[1].equals("weeks") && s[3].equals("days"))) {
                resultTime = (Integer.parseInt(s[0]) * 7) + Integer.parseInt(s[2]);
            }
        }
        if (s.length == 2) {
            switch (s[1]) {
                case "week":
                case "weeks":
                    resultTime = (Integer.parseInt(s[0]) * 7);
                    break;
                case "day":
                case "days":
                    resultTime = Integer.parseInt(s[0]);
                    break;
            }
        }
        return resultTime;
    }
}
