package com.lujw.fortunetelling.enums;

public enum BaseEnum {

 ONE("1","鼠","水",new Integer[]{23,1},"子时","中男","青色","正北","坎"),
 TWO("2","牛","水",new Integer[]{1,3},"丑时","流男","兰色","北东","小艮"),
 THREE("3","虎","土",new Integer[]{3,5},"寅时","少男","白色","东北","大艮"),
 FOUR("4","兔","木",new Integer[]{5,7},"卯时","长男","紫色","正东","震"),
 FIVE("5","龙","木",new Integer[]{7,9},"辰时","长女","绿色","东南","小巽"),
 SIX("6","蛇","火",new Integer[]{9,11},"巳时","流女","浅红","南东","大巽"),
 SEVEN("7","马","火",new Integer[]{11,13},"午时","中女","深红","正南","离"),
 EIGHT("8","羊","土",new Integer[]{13,15},"未时","少妇","浅黄","南西","小坤"),
 NINE("9","猴","土",new Integer[]{15,17},"申时","老妇","深黄","西南","大坤"),
 TEN("10","鸡","金",new Integer[]{17,19},"酉时","少女","浅黑","正西","兑"),
 ELEVEN("11","狗","金",new Integer[]{19,21},"戌时","少老男","深黑","西北","小乾"),
 TWELVE("12","猪","水",new Integer[]{21,23},"亥时","老男","棕色","北西","大乾");

 //数字
 private final String code;
 //生肖
 private final String animals;
 //五行
 private final String fiveElements;
 //时间
 private final Integer[] time;
 //时辰
 private final String time2;
 //人物
 private final String person;
 //颜色
 private final String colour;
 //方位
 private final String azimuth;
 //八卦方位
 private final String azimuth2;

 public static BaseEnum getEnumByCode(Integer code) {
  for (BaseEnum baseEnum : BaseEnum.values()) {
   if (baseEnum.getCode().equals(code.toString())) {
    return baseEnum;
   }
  }
  return null;
 }

 BaseEnum(String code, String animals, String fiveElements, Integer[] time, String time2, String person, String colour, String azimuth, String azimuth2) {
  this.code = code;
  this.animals = animals;
  this.fiveElements = fiveElements;
  this.time = time;
  this.time2 = time2;
  this.person = person;
  this.colour = colour;
  this.azimuth = azimuth;
  this.azimuth2 = azimuth2;
 }

 public String getCode() {
  return code;
 }

 public String getAnimals() {
  return animals;
 }

 public String getFiveElements() {
  return fiveElements;
 }

 public Integer[] getTime() {
  return time;
 }

 public String getTime2() {
  return time2;
 }

 public String getPerson() {
  return person;
 }

 public String getColour() {
  return colour;
 }

 public String getAzimuth() {
  return azimuth;
 }

 public String getAzimuth2() {
  return azimuth2;
 }
}
