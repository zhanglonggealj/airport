package com.esop.airport.domain.model.mongo;


import com.esop.airport.domain.model.BaseModel;
import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.CompoundIndex;
//import org.springframework.data.mongodb.core.index.CompoundIndexes;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

//@Document
//@CompoundIndexes(
//        {
//                @CompoundIndex(name = "companyid_hisdate_measurecode",def = "{'companyid':-1,'hisdate':-1,'measurecode':-1}")
//        })
public class TStatDayCompany extends BaseModel implements Serializable {

  private static final long serialVersionUID = 3485595770937832888L;


  @Id
  private String id;  //mongo 必须要加一个string id 作为主键来保证查询速度

  private String companyid;
  private Double hisdate;
  private String energytype;
  private String measurecode;
  private Double point1;
  private Double point2;
  private Double point3;
  private Double point4;
  private Double point5;
  private Double point6;
  private Double point7;
  private Double point8;
  private Double point9;
  private Double point10;
  private Double point11;
  private Double point12;
  private Double point13;
  private Double point14;
  private Double point15;
  private Double point16;
  private Double point17;
  private Double point18;
  private Double point19;
  private Double point20;
  private Double point21;
  private Double point22;
  private Double point23;
  private Double point24;
  private Double point25;
  private Double point26;
  private Double point27;
  private Double point28;
  private Double point29;
  private Double point30;
  private Double point31;
  private Double point32;
  private Double point33;
  private Double point34;
  private Double point35;
  private Double point36;
  private Double point37;
  private Double point38;
  private Double point39;
  private Double point40;
  private Double point41;
  private Double point42;
  private Double point43;
  private Double point44;
  private Double point45;
  private Double point46;
  private Double point47;
  private Double point48;
  private Double point49;
  private Double point50;
  private Double point51;
  private Double point52;
  private Double point53;
  private Double point54;
  private Double point55;
  private Double point56;
  private Double point57;
  private Double point58;
  private Double point59;
  private Double point60;
  private Double point61;
  private Double point62;
  private Double point63;
  private Double point64;
  private Double point65;
  private Double point66;
  private Double point67;
  private Double point68;
  private Double point69;
  private Double point70;
  private Double point71;
  private Double point72;
  private Double point73;
  private Double point74;
  private Double point75;
  private Double point76;
  private Double point77;
  private Double point78;
  private Double point79;
  private Double point80;
  private Double point81;
  private Double point82;
  private Double point83;
  private Double point84;
  private Double point85;
  private Double point86;
  private Double point87;
  private Double point88;
  private Double point89;
  private Double point90;
  private Double point91;
  private Double point92;
  private Double point93;
  private Double point94;
  private Double point95;
  private Double point96;
  private Double maxvalue;
  private Date maxtime;
  private Double minvalue;
  private Date mintime;
  private Double avgvalue;
  private Double sumvalue;
  private Double sharp;
  private Double peak;
  private Double flat;
  private Double cove;
  private Date stattime;

  @Override
  public String toString() {
    return "TStatDayCompany{" +
            "id='" + id + '\'' +
            ", companyid='" + companyid + '\'' +
            ", hisdate=" + hisdate +
            ", energytype='" + energytype + '\'' +
            ", measurecode='" + measurecode + '\'' +
            ", point1=" + point1 +
            ", point2=" + point2 +
            ", point3=" + point3 +
            ", point4=" + point4 +
            ", point5=" + point5 +
            ", point6=" + point6 +
            ", point7=" + point7 +
            ", point8=" + point8 +
            ", point9=" + point9 +
            ", point10=" + point10 +
            ", point11=" + point11 +
            ", point12=" + point12 +
            ", point13=" + point13 +
            ", point14=" + point14 +
            ", point15=" + point15 +
            ", point16=" + point16 +
            ", point17=" + point17 +
            ", point18=" + point18 +
            ", point19=" + point19 +
            ", point20=" + point20 +
            ", point21=" + point21 +
            ", point22=" + point22 +
            ", point23=" + point23 +
            ", point24=" + point24 +
            ", point25=" + point25 +
            ", point26=" + point26 +
            ", point27=" + point27 +
            ", point28=" + point28 +
            ", point29=" + point29 +
            ", point30=" + point30 +
            ", point31=" + point31 +
            ", point32=" + point32 +
            ", point33=" + point33 +
            ", point34=" + point34 +
            ", point35=" + point35 +
            ", point36=" + point36 +
            ", point37=" + point37 +
            ", point38=" + point38 +
            ", point39=" + point39 +
            ", point40=" + point40 +
            ", point41=" + point41 +
            ", point42=" + point42 +
            ", point43=" + point43 +
            ", point44=" + point44 +
            ", point45=" + point45 +
            ", point46=" + point46 +
            ", point47=" + point47 +
            ", point48=" + point48 +
            ", point49=" + point49 +
            ", point50=" + point50 +
            ", point51=" + point51 +
            ", point52=" + point52 +
            ", point53=" + point53 +
            ", point54=" + point54 +
            ", point55=" + point55 +
            ", point56=" + point56 +
            ", point57=" + point57 +
            ", point58=" + point58 +
            ", point59=" + point59 +
            ", point60=" + point60 +
            ", point61=" + point61 +
            ", point62=" + point62 +
            ", point63=" + point63 +
            ", point64=" + point64 +
            ", point65=" + point65 +
            ", point66=" + point66 +
            ", point67=" + point67 +
            ", point68=" + point68 +
            ", point69=" + point69 +
            ", point70=" + point70 +
            ", point71=" + point71 +
            ", point72=" + point72 +
            ", point73=" + point73 +
            ", point74=" + point74 +
            ", point75=" + point75 +
            ", point76=" + point76 +
            ", point77=" + point77 +
            ", point78=" + point78 +
            ", point79=" + point79 +
            ", point80=" + point80 +
            ", point81=" + point81 +
            ", point82=" + point82 +
            ", point83=" + point83 +
            ", point84=" + point84 +
            ", point85=" + point85 +
            ", point86=" + point86 +
            ", point87=" + point87 +
            ", point88=" + point88 +
            ", point89=" + point89 +
            ", point90=" + point90 +
            ", point91=" + point91 +
            ", point92=" + point92 +
            ", point93=" + point93 +
            ", point94=" + point94 +
            ", point95=" + point95 +
            ", point96=" + point96 +
            ", maxvalue=" + maxvalue +
            ", maxtime=" + maxtime +
            ", minvalue=" + minvalue +
            ", mintime=" + mintime +
            ", avgvalue=" + avgvalue +
            ", sumvalue=" + sumvalue +
            ", sharp=" + sharp +
            ", peak=" + peak +
            ", flat=" + flat +
            ", cove=" + cove +
            ", stattime=" + stattime +
            '}';
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCompanyid() {
    return companyid;
  }

  public void setCompanyid(String companyid) {
    this.companyid = companyid;
  }

  public Double getHisdate() {
    return hisdate;
  }

  public void setHisdate(Double hisdate) {
    this.hisdate = hisdate;
  }

  public String getEnergytype() {
    return energytype;
  }

  public void setEnergytype(String energytype) {
    this.energytype = energytype;
  }

  public String getMeasurecode() {
    return measurecode;
  }

  public void setMeasurecode(String measurecode) {
    this.measurecode = measurecode;
  }

  public Double getPoint1() {
    return point1;
  }

  public void setPoint1(Double point1) {
    this.point1 = point1;
  }

  public Double getPoint2() {
    return point2;
  }

  public void setPoint2(Double point2) {
    this.point2 = point2;
  }

  public Double getPoint3() {
    return point3;
  }

  public void setPoint3(Double point3) {
    this.point3 = point3;
  }

  public Double getPoint4() {
    return point4;
  }

  public void setPoint4(Double point4) {
    this.point4 = point4;
  }

  public Double getPoint5() {
    return point5;
  }

  public void setPoint5(Double point5) {
    this.point5 = point5;
  }

  public Double getPoint6() {
    return point6;
  }

  public void setPoint6(Double point6) {
    this.point6 = point6;
  }

  public Double getPoint7() {
    return point7;
  }

  public void setPoint7(Double point7) {
    this.point7 = point7;
  }

  public Double getPoint8() {
    return point8;
  }

  public void setPoint8(Double point8) {
    this.point8 = point8;
  }

  public Double getPoint9() {
    return point9;
  }

  public void setPoint9(Double point9) {
    this.point9 = point9;
  }

  public Double getPoint10() {
    return point10;
  }

  public void setPoint10(Double point10) {
    this.point10 = point10;
  }

  public Double getPoint11() {
    return point11;
  }

  public void setPoint11(Double point11) {
    this.point11 = point11;
  }

  public Double getPoint12() {
    return point12;
  }

  public void setPoint12(Double point12) {
    this.point12 = point12;
  }

  public Double getPoint13() {
    return point13;
  }

  public void setPoint13(Double point13) {
    this.point13 = point13;
  }

  public Double getPoint14() {
    return point14;
  }

  public void setPoint14(Double point14) {
    this.point14 = point14;
  }

  public Double getPoint15() {
    return point15;
  }

  public void setPoint15(Double point15) {
    this.point15 = point15;
  }

  public Double getPoint16() {
    return point16;
  }

  public void setPoint16(Double point16) {
    this.point16 = point16;
  }

  public Double getPoint17() {
    return point17;
  }

  public void setPoint17(Double point17) {
    this.point17 = point17;
  }

  public Double getPoint18() {
    return point18;
  }

  public void setPoint18(Double point18) {
    this.point18 = point18;
  }

  public Double getPoint19() {
    return point19;
  }

  public void setPoint19(Double point19) {
    this.point19 = point19;
  }

  public Double getPoint20() {
    return point20;
  }

  public void setPoint20(Double point20) {
    this.point20 = point20;
  }

  public Double getPoint21() {
    return point21;
  }

  public void setPoint21(Double point21) {
    this.point21 = point21;
  }

  public Double getPoint22() {
    return point22;
  }

  public void setPoint22(Double point22) {
    this.point22 = point22;
  }

  public Double getPoint23() {
    return point23;
  }

  public void setPoint23(Double point23) {
    this.point23 = point23;
  }

  public Double getPoint24() {
    return point24;
  }

  public void setPoint24(Double point24) {
    this.point24 = point24;
  }

  public Double getPoint25() {
    return point25;
  }

  public void setPoint25(Double point25) {
    this.point25 = point25;
  }

  public Double getPoint26() {
    return point26;
  }

  public void setPoint26(Double point26) {
    this.point26 = point26;
  }

  public Double getPoint27() {
    return point27;
  }

  public void setPoint27(Double point27) {
    this.point27 = point27;
  }

  public Double getPoint28() {
    return point28;
  }

  public void setPoint28(Double point28) {
    this.point28 = point28;
  }

  public Double getPoint29() {
    return point29;
  }

  public void setPoint29(Double point29) {
    this.point29 = point29;
  }

  public Double getPoint30() {
    return point30;
  }

  public void setPoint30(Double point30) {
    this.point30 = point30;
  }

  public Double getPoint31() {
    return point31;
  }

  public void setPoint31(Double point31) {
    this.point31 = point31;
  }

  public Double getPoint32() {
    return point32;
  }

  public void setPoint32(Double point32) {
    this.point32 = point32;
  }

  public Double getPoint33() {
    return point33;
  }

  public void setPoint33(Double point33) {
    this.point33 = point33;
  }

  public Double getPoint34() {
    return point34;
  }

  public void setPoint34(Double point34) {
    this.point34 = point34;
  }

  public Double getPoint35() {
    return point35;
  }

  public void setPoint35(Double point35) {
    this.point35 = point35;
  }

  public Double getPoint36() {
    return point36;
  }

  public void setPoint36(Double point36) {
    this.point36 = point36;
  }

  public Double getPoint37() {
    return point37;
  }

  public void setPoint37(Double point37) {
    this.point37 = point37;
  }

  public Double getPoint38() {
    return point38;
  }

  public void setPoint38(Double point38) {
    this.point38 = point38;
  }

  public Double getPoint39() {
    return point39;
  }

  public void setPoint39(Double point39) {
    this.point39 = point39;
  }

  public Double getPoint40() {
    return point40;
  }

  public void setPoint40(Double point40) {
    this.point40 = point40;
  }

  public Double getPoint41() {
    return point41;
  }

  public void setPoint41(Double point41) {
    this.point41 = point41;
  }

  public Double getPoint42() {
    return point42;
  }

  public void setPoint42(Double point42) {
    this.point42 = point42;
  }

  public Double getPoint43() {
    return point43;
  }

  public void setPoint43(Double point43) {
    this.point43 = point43;
  }

  public Double getPoint44() {
    return point44;
  }

  public void setPoint44(Double point44) {
    this.point44 = point44;
  }

  public Double getPoint45() {
    return point45;
  }

  public void setPoint45(Double point45) {
    this.point45 = point45;
  }

  public Double getPoint46() {
    return point46;
  }

  public void setPoint46(Double point46) {
    this.point46 = point46;
  }

  public Double getPoint47() {
    return point47;
  }

  public void setPoint47(Double point47) {
    this.point47 = point47;
  }

  public Double getPoint48() {
    return point48;
  }

  public void setPoint48(Double point48) {
    this.point48 = point48;
  }

  public Double getPoint49() {
    return point49;
  }

  public void setPoint49(Double point49) {
    this.point49 = point49;
  }

  public Double getPoint50() {
    return point50;
  }

  public void setPoint50(Double point50) {
    this.point50 = point50;
  }

  public Double getPoint51() {
    return point51;
  }

  public void setPoint51(Double point51) {
    this.point51 = point51;
  }

  public Double getPoint52() {
    return point52;
  }

  public void setPoint52(Double point52) {
    this.point52 = point52;
  }

  public Double getPoint53() {
    return point53;
  }

  public void setPoint53(Double point53) {
    this.point53 = point53;
  }

  public Double getPoint54() {
    return point54;
  }

  public void setPoint54(Double point54) {
    this.point54 = point54;
  }

  public Double getPoint55() {
    return point55;
  }

  public void setPoint55(Double point55) {
    this.point55 = point55;
  }

  public Double getPoint56() {
    return point56;
  }

  public void setPoint56(Double point56) {
    this.point56 = point56;
  }

  public Double getPoint57() {
    return point57;
  }

  public void setPoint57(Double point57) {
    this.point57 = point57;
  }

  public Double getPoint58() {
    return point58;
  }

  public void setPoint58(Double point58) {
    this.point58 = point58;
  }

  public Double getPoint59() {
    return point59;
  }

  public void setPoint59(Double point59) {
    this.point59 = point59;
  }

  public Double getPoint60() {
    return point60;
  }

  public void setPoint60(Double point60) {
    this.point60 = point60;
  }

  public Double getPoint61() {
    return point61;
  }

  public void setPoint61(Double point61) {
    this.point61 = point61;
  }

  public Double getPoint62() {
    return point62;
  }

  public void setPoint62(Double point62) {
    this.point62 = point62;
  }

  public Double getPoint63() {
    return point63;
  }

  public void setPoint63(Double point63) {
    this.point63 = point63;
  }

  public Double getPoint64() {
    return point64;
  }

  public void setPoint64(Double point64) {
    this.point64 = point64;
  }

  public Double getPoint65() {
    return point65;
  }

  public void setPoint65(Double point65) {
    this.point65 = point65;
  }

  public Double getPoint66() {
    return point66;
  }

  public void setPoint66(Double point66) {
    this.point66 = point66;
  }

  public Double getPoint67() {
    return point67;
  }

  public void setPoint67(Double point67) {
    this.point67 = point67;
  }

  public Double getPoint68() {
    return point68;
  }

  public void setPoint68(Double point68) {
    this.point68 = point68;
  }

  public Double getPoint69() {
    return point69;
  }

  public void setPoint69(Double point69) {
    this.point69 = point69;
  }

  public Double getPoint70() {
    return point70;
  }

  public void setPoint70(Double point70) {
    this.point70 = point70;
  }

  public Double getPoint71() {
    return point71;
  }

  public void setPoint71(Double point71) {
    this.point71 = point71;
  }

  public Double getPoint72() {
    return point72;
  }

  public void setPoint72(Double point72) {
    this.point72 = point72;
  }

  public Double getPoint73() {
    return point73;
  }

  public void setPoint73(Double point73) {
    this.point73 = point73;
  }

  public Double getPoint74() {
    return point74;
  }

  public void setPoint74(Double point74) {
    this.point74 = point74;
  }

  public Double getPoint75() {
    return point75;
  }

  public void setPoint75(Double point75) {
    this.point75 = point75;
  }

  public Double getPoint76() {
    return point76;
  }

  public void setPoint76(Double point76) {
    this.point76 = point76;
  }

  public Double getPoint77() {
    return point77;
  }

  public void setPoint77(Double point77) {
    this.point77 = point77;
  }

  public Double getPoint78() {
    return point78;
  }

  public void setPoint78(Double point78) {
    this.point78 = point78;
  }

  public Double getPoint79() {
    return point79;
  }

  public void setPoint79(Double point79) {
    this.point79 = point79;
  }

  public Double getPoint80() {
    return point80;
  }

  public void setPoint80(Double point80) {
    this.point80 = point80;
  }

  public Double getPoint81() {
    return point81;
  }

  public void setPoint81(Double point81) {
    this.point81 = point81;
  }

  public Double getPoint82() {
    return point82;
  }

  public void setPoint82(Double point82) {
    this.point82 = point82;
  }

  public Double getPoint83() {
    return point83;
  }

  public void setPoint83(Double point83) {
    this.point83 = point83;
  }

  public Double getPoint84() {
    return point84;
  }

  public void setPoint84(Double point84) {
    this.point84 = point84;
  }

  public Double getPoint85() {
    return point85;
  }

  public void setPoint85(Double point85) {
    this.point85 = point85;
  }

  public Double getPoint86() {
    return point86;
  }

  public void setPoint86(Double point86) {
    this.point86 = point86;
  }

  public Double getPoint87() {
    return point87;
  }

  public void setPoint87(Double point87) {
    this.point87 = point87;
  }

  public Double getPoint88() {
    return point88;
  }

  public void setPoint88(Double point88) {
    this.point88 = point88;
  }

  public Double getPoint89() {
    return point89;
  }

  public void setPoint89(Double point89) {
    this.point89 = point89;
  }

  public Double getPoint90() {
    return point90;
  }

  public void setPoint90(Double point90) {
    this.point90 = point90;
  }

  public Double getPoint91() {
    return point91;
  }

  public void setPoint91(Double point91) {
    this.point91 = point91;
  }

  public Double getPoint92() {
    return point92;
  }

  public void setPoint92(Double point92) {
    this.point92 = point92;
  }

  public Double getPoint93() {
    return point93;
  }

  public void setPoint93(Double point93) {
    this.point93 = point93;
  }

  public Double getPoint94() {
    return point94;
  }

  public void setPoint94(Double point94) {
    this.point94 = point94;
  }

  public Double getPoint95() {
    return point95;
  }

  public void setPoint95(Double point95) {
    this.point95 = point95;
  }

  public Double getPoint96() {
    return point96;
  }

  public void setPoint96(Double point96) {
    this.point96 = point96;
  }

  public Double getMaxvalue() {
    return maxvalue;
  }

  public void setMaxvalue(Double maxvalue) {
    this.maxvalue = maxvalue;
  }

  public Date getMaxtime() {
    return maxtime;
  }

  public void setMaxtime(Date maxtime) {
    this.maxtime = maxtime;
  }

  public Double getMinvalue() {
    return minvalue;
  }

  public void setMinvalue(Double minvalue) {
    this.minvalue = minvalue;
  }

  public Date getMintime() {
    return mintime;
  }

  public void setMintime(Date mintime) {
    this.mintime = mintime;
  }

  public Double getAvgvalue() {
    return avgvalue;
  }

  public void setAvgvalue(Double avgvalue) {
    this.avgvalue = avgvalue;
  }

  public Double getSumvalue() {
    return sumvalue;
  }

  public void setSumvalue(Double sumvalue) {
    this.sumvalue = sumvalue;
  }

  public Double getSharp() {
    return sharp;
  }

  public void setSharp(Double sharp) {
    this.sharp = sharp;
  }

  public Double getPeak() {
    return peak;
  }

  public void setPeak(Double peak) {
    this.peak = peak;
  }

  public Double getFlat() {
    return flat;
  }

  public void setFlat(Double flat) {
    this.flat = flat;
  }

  public Double getCove() {
    return cove;
  }

  public void setCove(Double cove) {
    this.cove = cove;
  }

  public Date getStattime() {
    return stattime;
  }

  public void setStattime(Date stattime) {
    this.stattime = stattime;
  }
}
