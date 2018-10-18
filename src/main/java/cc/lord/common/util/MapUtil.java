package cc.lord.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.formula.functions.T;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapUtil {

    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map sortMapByKey(Map map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map sortMap = new TreeMap(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }

    public static void main(String[] args){
        String str="{\"msg\":{\"1\":[{\"mchId\":10001,\"mchName\":\"我的商户\",\"mchContacts\":\"zhw\",\"mchContactsTelephone\":\"123455678\",\"mchBusinesslicence\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"mchBusinesslicenceNumbe\":\"sdw24532523525\",\"mchImg\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"mchType\":1,\"mchTelephone\":\"12312444\",\"mchAddress\":\"北京市朝阳\",\"mchWeekdayBegin\":\"8:00\",\"mchWeekdayEnd\":\"21:00\",\"mchWeekendBegin\":\"9:00\",\"mchWeekendEnd\":\"18:00\",\"mchPerCapitaConsume\":\"288\",\"mchStatus\":1,\"mchCreateDate\":\"2018-10-15 17:53:55\",\"mchUpdateDate\":\"2018-10-15 17:54:59\",\"mchDelete\":0,\"mchComments\":\"\",\"mchDisable\":0,\"mchMap\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"merchantCoupons\":[{\"couponId\":10001,\"mchId\":10001,\"mchName\":\"我的商户\",\"couponType\":1,\"couponAmount\":10.0,\"couponUsageAmount\":50.0,\"couponPaymentType\":1,\"couponPaymentTypeName\":\"建行信用卡\",\"couponQrCode\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"couponTimeBegin\":\"2018-10-15 17:50:57\",\"couponTimeEnd\":\"2018-10-27 17:51:00\",\"couponCreateDate\":\"2018-10-15 17:51:03\",\"couponUpdateDate\":\"2018-10-15 17:55:08\",\"couponStatus\":1,\"couponDelete\":0,\"couponComments\":\"\",\"couponDisable\":\"0\",\"couponExpire\":0}],\"mchLabelVos\":[{\"labelId\":10001,\"mchId\":10001,\"labelName\":\"好吃吗\"},{\"labelId\":10002,\"mchId\":10001,\"labelName\":\"不好吃\"}]}],\"2\":[{\"mchId\":10002,\"mchName\":\"商户2\",\"mchContacts\":\"zhw\",\"mchContactsTelephone\":\"123455678\",\"mchBusinesslicence\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"mchBusinesslicenceNumbe\":\"sdw24532523525\",\"mchImg\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"mchType\":2,\"mchTelephone\":\"12312444\",\"mchAddress\":\"北京市朝阳\",\"mchWeekdayBegin\":\"8:00\",\"mchWeekdayEnd\":\"21:00\",\"mchWeekendBegin\":\"9:00\",\"mchWeekendEnd\":\"18:00\",\"mchPerCapitaConsume\":\"288\",\"mchStatus\":1,\"mchCreateDate\":\"2018-10-18 10:16:04\",\"mchUpdateDate\":\"2018-10-18 14:27:31\",\"mchDelete\":0,\"mchComments\":null,\"mchDisable\":0,\"mchMap\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"merchantCoupons\":[{\"couponId\":10002,\"mchId\":10002,\"mchName\":\"商户2\",\"couponType\":1,\"couponAmount\":10.0,\"couponUsageAmount\":50.0,\"couponPaymentType\":1,\"couponPaymentTypeName\":\"建行信用卡\",\"couponQrCode\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"couponTimeBegin\":\"2018-10-18 14:34:19\",\"couponTimeEnd\":\"2018-10-28 14:34:22\",\"couponCreateDate\":\"2018-10-18 14:34:24\",\"couponUpdateDate\":\"2018-10-18 14:44:51\",\"couponStatus\":1,\"couponDelete\":0,\"couponComments\":null,\"couponDisable\":\"0\",\"couponExpire\":0}],\"mchLabelVos\":[{\"labelId\":10002,\"mchId\":10002,\"labelName\":\"不好吃\"},{\"labelId\":10001,\"mchId\":10002,\"labelName\":\"好吃吗\"}]}],\"3\":[{\"mchId\":10003,\"mchName\":\"商户3\",\"mchContacts\":\"zhw\",\"mchContactsTelephone\":\"123455678\",\"mchBusinesslicence\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"mchBusinesslicenceNumbe\":\"sdw24532523525\",\"mchImg\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"mchType\":3,\"mchTelephone\":\"12312444\",\"mchAddress\":\"北京市朝阳\",\"mchWeekdayBegin\":\"8:00\",\"mchWeekdayEnd\":\"21:00\",\"mchWeekendBegin\":\"9:00\",\"mchWeekendEnd\":\"18:00\",\"mchPerCapitaConsume\":\"288\",\"mchStatus\":1,\"mchCreateDate\":\"2018-10-18 14:31:09\",\"mchUpdateDate\":\"2018-10-18 14:31:10\",\"mchDelete\":0,\"mchComments\":null,\"mchDisable\":0,\"mchMap\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"merchantCoupons\":[{\"couponId\":10003,\"mchId\":10003,\"mchName\":\"商户3\",\"couponType\":1,\"couponAmount\":10.0,\"couponUsageAmount\":50.0,\"couponPaymentType\":1,\"couponPaymentTypeName\":\"建行信用卡\",\"couponQrCode\":\"https://avatars0.githubusercontent.com/u/29118689?s=460&v=4\",\"couponTimeBegin\":\"2018-10-18 14:35:05\",\"couponTimeEnd\":\"2018-10-28 14:35:09\",\"couponCreateDate\":\"2018-10-18 14:35:12\",\"couponUpdateDate\":\"2018-10-18 14:44:54\",\"couponStatus\":1,\"couponDelete\":0,\"couponComments\":null,\"couponDisable\":\"0\",\"couponExpire\":0}],\"mchLabelVos\":[{\"labelId\":10001,\"mchId\":10003,\"labelName\":\"好吃吗\"},{\"labelId\":10002,\"mchId\":10003,\"labelName\":\"不好吃\"}]}],\"count\":3},\"code\":0}";
        //Map map = (Map<String, String>) JSON.parse(str);
        Map map = new HashMap();
       map.put(2, "kfc");
        map.put(1, "wnba");
        map.put(3, "nba");
        map.put("count", "count");
        map.put(4, "cba");

        Map resultMap = sortMapByKey(map);    //按Key进行排序

        System.out.println(resultMap);
    }

}
class MapKeyComparator implements Comparator<Object> {

    @Override
    public int compare(Object obj1, Object obj2) {
        String str1=null;
        if ( obj1 instanceof Integer){
            str1=String.valueOf(obj1);
        }
        if ( obj1 instanceof String){
            str1= (String) obj1;
        }

        String str2=null;
        if ( obj2 instanceof Integer){
            str2=String.valueOf(obj2);
        }
        if ( obj2 instanceof String){
            str2= (String) obj2;
        }


        return str1.compareTo(str2);
    }
}