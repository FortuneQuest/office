package cn.oc.server.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : DateConverter
 * @Author: oc
 * @Date: 2022/03/07/20:19
 * @Description:
 **/
public class DateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String s) {
        try {
            return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
