package cn.madchick.oee.domain.support.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author：jagger
 * @date: 2024/8/5
 * @Copyright： jagger - www.madchick.cn
 */
public class TimestampToDate {

    public static Date convertToDate(Long timestamp) {
        if (timestamp != null) {
            // 创建一个 Date 对象
            Date date = new Date(timestamp);

            // 创建一个 SimpleDateFormat 对象，指定日期格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 将 Date 对象格式化为字符串
            // String formattedDate = sdf.format(date);

            return date;
        }
        else {
            return null;
        }
    }
}
