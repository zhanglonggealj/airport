package com.esop.airport.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author arvin liliqiang
 * @create 2018-06-13 下午4:41
 **/
public class BaseModel implements Serializable {


    public static void main(String[] args) {

      long a = 124;
      double b = a;
      System.out.println(b);

      BigDecimal aa = new BigDecimal(b);
      BigDecimal bb = new BigDecimal(a);

      System.out.println(aa.compareTo(bb));

    }

}
