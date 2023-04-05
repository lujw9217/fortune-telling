package com.lujw.fortunetelling.service;

import com.lujw.fortunetelling.entity.*;
import com.lujw.fortunetelling.enums.BaseEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FortuneTellService {

    public Response queryFortuneTell(User user) {
        Response response =new Response();
        response.setYear(user.getYear());
        response.setMonth(user.getMonth());
        response.setDay(user.getDay());
        response.setTime(BaseEnum.getEnumByCode(Integer.parseInt(user.getTime())).getTime2());

        //上卦
        Up up=new Up();
        //上卦数字
        List<Integer> upCodes=new ArrayList<>();
        //上卦五行
        List<String> upFiveElements=new ArrayList<>();
        //下卦
        Down down=new Down();
        //下卦数字
        List<Integer> downCodes=new ArrayList<>();
        //下卦五行
        List<String> downFiveElements=new ArrayList<>();

        /**处理年*/
        //取生肖-年份减3除以12取余数
        Integer code=(Integer.parseInt(user.getYear())-3)%12;
        upCodes.add(code);
        upFiveElements.add(BaseEnum.getEnumByCode(code).getFiveElements());
        Integer code1=handleCode(code);
        downCodes.add(code1);
        downFiveElements.add(BaseEnum.getEnumByCode(code1).getFiveElements());

        /**处理月*/
        Integer month=Integer.parseInt(user.getMonth());
        upCodes.add(month);
        upFiveElements.add(BaseEnum.getEnumByCode(month).getFiveElements());
        Integer month1=handleCode(month);
        downCodes.add(month1);
        downFiveElements.add(BaseEnum.getEnumByCode(month1).getFiveElements());

        /**处理日*/
        Integer day=Integer.parseInt(user.getDay());
        List<Integer> days=handleDay(day);
        for(Integer d:days){
            upCodes.add(d);
            upFiveElements.add(BaseEnum.getEnumByCode(d).getFiveElements());
            Integer d1=handleCode(d);
            downCodes.add(d1);
            downFiveElements.add(BaseEnum.getEnumByCode(d1).getFiveElements());
        }

        /**处理时*/
        Integer time=Integer.parseInt(user.getTime());
        upCodes.add(time);
        upFiveElements.add(BaseEnum.getEnumByCode(time).getFiveElements());
        Integer time1=handleCode(time);
        downCodes.add(time1);
        downFiveElements.add(BaseEnum.getEnumByCode(time1).getFiveElements());

        up.setCodes(upCodes);
        up.setFiveElements(upFiveElements);
        down.setCodes(downCodes);
        down.setFiveElements(downFiveElements);

        Map<String, Element> elementMap=handleText(up.getCodes(),down.getCodes());

        response.setUp(up);
        response.setDown(down);
        response.setElementMap(elementMap);


        return response;
    }


    private Integer handleCode(Integer code){
        return code>6?code-6:code+6;
    }

    private List<Integer> handleDay(Integer day){
        List<Integer> days=new ArrayList<>();
        if(day<=12){
            days.add(day);
        }else {
            days.add(day/10);
            days.add(10);
            Integer remainder= day%10;
            if(remainder>0){
                days.add(remainder);
            }
        }
        return days;
    }

    private Map<String, Element> handleText(List<Integer> upCodeList, List<Integer> downCodeList) {
        Map<String, Element> elementMap=new HashMap<>();

        for(int i=0;i<=upCodeList.size();i+=2){
            List<String> upCodes=new ArrayList<>();
            List<String> downCodes=new ArrayList<>();
            StringBuilder upCode=new StringBuilder();
            StringBuilder downCode=new StringBuilder();
            switch (i){
                case 0:
                    //处理父母宫
                    upCode.append(upCodeList.get(i)).append("-").append(upCodeList.get(i+1));
                    upCodes.add(upCode.toString());
                    downCode.append(downCodeList.get(i)).append("-").append(downCodeList.get(i+1));
                    downCodes.add(downCode.toString());
                    elementMap.put("父母宫", new Element(upCodes,downCodes));
                    continue;
                case 2:
                    //处理夫妻宫
                    upCode.append(upCodeList.get(i-1)).append("-").append(upCodeList.get(i));
                    upCodes.add(upCode.toString());
                    downCode.append(downCodeList.get(i-1)).append("-").append(downCodeList.get(i));
                    downCodes.add(downCode.toString());
                    elementMap.put("夫妻宫",new Element(upCodes,downCodes));
                    continue;
                case 4:
                    //处理子女宫
                    upCode.append(upCodeList.get(i-2)).append("-").append(upCodeList.get(i-1));
                    downCode.append(downCodeList.get(i-2)).append("-").append(downCodeList.get(i-1));

                    if(upCodeList.size()>=5){
                        upCode.append("-").append(upCodeList.get(i));
                        downCode.append("-").append(downCodeList.get(i));
                        if(upCodeList.size()==6){
                            upCodes.add(upCode.toString());
                            downCodes.add(downCode.toString());

                            upCode=new StringBuilder();
                            downCode=new StringBuilder();
                            upCode.append(upCodeList.get(i-1)).append("-").append(upCodeList.get(i)).append("-").append(upCodeList.get(i+1));
                            downCode.append(downCodeList.get(i-1)).append("-").append(downCodeList.get(i)).append("-").append(downCodeList.get(i+1));
                        }
                    }
                    upCodes.add(upCode.toString());
                    downCodes.add(downCode.toString());
                    elementMap.put("子女宫",new Element(upCodes,downCodes));
                    break;
            }

        }

        return elementMap;
    }
}
