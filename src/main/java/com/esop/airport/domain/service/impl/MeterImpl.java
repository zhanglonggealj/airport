package com.esop.airport.domain.service.impl;

import com.esop.airport.common.ConstDef;
import com.esop.airport.domain.mapper.MeterMapper;
import com.esop.airport.domain.model.TBasMeter;
import com.esop.airport.domain.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-12 13:40
 **/
@Service
public class MeterImpl implements MeterService {

    @Autowired
    MeterMapper meterMapper;

    @Override
    public TBasMeter findMeterByMeterId(Long meterId) {
        TBasMeter find = new TBasMeter();

        find.setMeterId(meterId);
        find.setMeterCode(ConstDef.METER_RUN_STATUS);

        List<TBasMeter> list = meterMapper.select(find);

        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<TBasMeter> findMeterListByConsNo(List<String> consNo) {//正则。单表的正则操作。没有值返回null

        Example example = new Example(TBasMeter.class);

        example.createCriteria().andIn("consNo", consNo)
        .andEqualTo("meterCode", ConstDef.METER_RUN_STATUS);

        List<TBasMeter> list = meterMapper.selectByExample(example);

        return list;
    }

    /*@Override
    public TBasMeter findMeterByConsNoAndMeterMadeNo(String consNo, String meterMadeNo) {

        TBasMeter find = new TBasMeter();
        find.setConsNo(consNo);

        find.setMeterMadeNo(meterMadeNo);

        find.setMeterCode(ConstDef.METER_RUN_STATUS);

        List<TBasMeter> list=meterMapper.select(find);

        return list != null && list.size() > 0 ? list.get(0) : null;

    }*/

    @Override
    public TBasMeter findMeterByConsNo(String consNo) {

        TBasMeter find = new TBasMeter();

        find.setConsNo(consNo);
        find.setMeterCode(ConstDef.METER_RUN_STATUS);

        List<TBasMeter> list = meterMapper.select(find);

        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int saveMeter(TBasMeter meter) {

        return meterMapper.insertSelective(meter);
    }

    @Override
    public int updateMeter(TBasMeter meter) {
        return meterMapper.updateByPrimaryKeySelective(meter);
    }

    @Override
    public int updatePurchaseCount(Long id, Integer purchaseCount) {

        return meterMapper.updatePurchaseCount(id, purchaseCount);
    }

    @Override
    public int updateUseFlag(Long id) {
        return meterMapper.updateUseFlag(id);
    }
}
