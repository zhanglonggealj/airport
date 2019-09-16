package com.esop.airport.api.third.controller;

import com.esop.airport.api.third.facade.ConsInfoPushFacade;
import com.esop.airport.api.third.vo.AddCons;
import com.esop.airport.api.third.vo.BuyNotifiy;
import com.esop.airport.api.third.vo.ChangeCons;
import com.esop.airport.api.third.vo.ChangeMeter;
import com.esop.airport.common.*;
import com.esop.airport.domain.model.TBasCons;
import com.esop.airport.domain.model.TBasMeter;
import com.esop.airport.utils.StringUtils;
import com.esop.airport.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-13 17:20
 **/
@RestController
@RequestMapping("/third/cons/")
public class ConsPushController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(ConsPushController.class);

    @Autowired
    ConsInfoPushFacade consInfoPushFacade;

    /**
     * 下发平台添加商户
     * @param addCons
     * @param bind
     * @return
     */
    @RequestMapping(value = "/addCons", method = RequestMethod.POST)
    public JsonResult addCons(@RequestBody @Valid AddCons addCons, BindingResult bind) {


        JsonResult bindErrMsg = getBindErrMsg(bind);

        if (bindErrMsg != null) {
            return bindErrMsg;
        }

        AddCons.ConsMessEntity consMess = addCons.getConsMess();

        AddCons.MeterMessEntity meterMess = addCons.getMeterMess();


        TBasCons cons = new TBasCons();

        cons.setMobilePhone(consMess.getMobilePhone());//这点手机格式没有处理
        cons.setConsNo(consMess.getConsNo());

        Date date = TimeUtils.strToDateLong(consMess.getBuildDate());
        if (date == null) {
            throw new MyRequestExecption("buildDate 时间格式错误 正确时间 2019-07-12 11:11:11");
        }
        cons.setBuildDate(date);
        cons.setConsAddress(consMess.getConsAddress());
        cons.setConsType(consMess.getConsType());
        if (StringUtils.isNotBlank(consMess.getContractCap())) {
            cons.setContractCap(new BigDecimal(consMess.getContractCap()));
        }
        cons.setLineId(consMess.getLineId());
        cons.setConsName(consMess.getConsName());
        cons.setTariffId(consMess.getTariffId());
        cons.setOrgNo(consMess.getOrgNo());
        cons.setStatusCode(consMess.getStatusCode());
        cons.setTariffName(consMess.getTariffName());
        cons.setVoltCode(consMess.getVoltCode());
        cons.setTgId(consMess.getTgId());
        cons.setRemark(consMess.getRemark());

        TBasMeter meter = new TBasMeter();

        meter.setConsNo(consMess.getConsNo());
        meter.setInstLoc(meterMess.getInstLoc());
        meter.setInitMoney(meterMess.getInitMoney());
        meter.setPrepayFlag(meterMess.getPrepayFlag());
        meter.setManufacturer(meterMess.getManufacturer());
        meter.setMeterMadeNo(meterMess.getMeterMadeNo());
        meter.setMeterId(meterMess.getMeterId());
        meter.setRemark(meterMess.getRemark());
        meter.setMeterCode(meterMess.getMeterCode());
        meter.setTypeCode(meterMess.getTypeCode());

        if (StringUtils.isNotBlank(meterMess.gettFactor())) {
            meter.settFactor(new BigDecimal(meterMess.gettFactor()));
        }
        meter.setCommAddr(meterMess.getCommAddr());
        meter.setProtocolCode(meterMess.getProtocolCode());
        meter.setCommMode(meterMess.getCommMode());
        meter.setMadeDate(meterMess.getMadeDate());
        meter.setVoltCode(meterMess.getVoltCode());
        meter.setRatedCurrent(meterMess.getRatedCurrent());
        meter.setCarrierWaveId(meterMess.getCarrierWaveId());
        meter.setWiringMode(meterMess.getWiringMode());
        int ret = 0;

        ret = consInfoPushFacade.addConsAndMeter(cons, meter);

        return ret > 0 ? new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg)
                : new JsonResult<>(ResultDef.CERR_CONS_PUSH_ERR.code, ResultDef.CERR_CONS_PUSH_ERR.msg);

    }

    /**
     * 下发平台删除商户
     * @param map
     * @return
     */
    @RequestMapping(value = "/delCons", method = RequestMethod.POST)
    public JsonResult delCons(@RequestBody Map<String, String> map) {

        String meterId = map.get("meterId");
        String consNo = map.get("consNo");
        String residueMoney = map.get("residueMoney");

        if (StringUtils.isBlank(meterId, consNo, residueMoney)) {
            return new JsonResult<>(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        int ret = 0;

        long mId;

        try {
            mId = Long.parseLong(meterId);
        } catch (Exception e) {
            return new JsonResult<>(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }


        ret = consInfoPushFacade.delcons(mId, consNo, residueMoney);

        return ret > 0 ? new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg)
                : new JsonResult<>(ResultDef.CERR_CONS_PUSH_ERR.code, ResultDef.CERR_CONS_PUSH_ERR.msg);
    }

    /***
     * 更换电表操作
     * @param changeMeter
     * @param bind
     * @return
     */
    @RequestMapping(value = "/changeMeter", method = RequestMethod.POST)
    public JsonResult changeMeter(@RequestBody @Valid ChangeMeter changeMeter, BindingResult bind) {

        JsonResult bindErrMsg = getBindErrMsg(bind);

        if (bindErrMsg != null) {
            return bindErrMsg;
        }

        int ret = 0;

        TBasMeter newMeter = new TBasMeter();
        newMeter.setConsNo(changeMeter.getConsNo());
        newMeter.setInstLoc(changeMeter.getNewMeterMess().getInstLoc());
        newMeter.setInitMoney(changeMeter.getNewMeterMess().getInitMoney());
        newMeter.setPrepayFlag(changeMeter.getNewMeterMess().getPrepayFlag());
        newMeter.setManufacturer(changeMeter.getNewMeterMess().getManufacturer());
        newMeter.setMeterMadeNo(changeMeter.getNewMeterMess().getMeterMadeNo());
        newMeter.setMeterId(changeMeter.getNewMeterMess().getMeterId());
        newMeter.setRemark(changeMeter.getNewMeterMess().getRemark());
        newMeter.setMeterCode(changeMeter.getNewMeterMess().getMeterCode());
        newMeter.setTypeCode(changeMeter.getNewMeterMess().getTypeCode());

        if (StringUtils.isNotBlank(changeMeter.getNewMeterMess().gettFactor())) {
            newMeter.settFactor(new BigDecimal(changeMeter.getNewMeterMess().gettFactor()));
        }
        newMeter.setCommAddr(changeMeter.getNewMeterMess().getCommAddr());
        newMeter.setProtocolCode(changeMeter.getNewMeterMess().getProtocolCode());
        newMeter.setCommMode(changeMeter.getNewMeterMess().getCommMode());
        newMeter.setMadeDate(changeMeter.getNewMeterMess().getMadeDate());
        newMeter.setVoltCode(changeMeter.getNewMeterMess().getVoltCode());
        newMeter.setRatedCurrent(changeMeter.getNewMeterMess().getRatedCurrent());
        newMeter.setCarrierWaveId(changeMeter.getNewMeterMess().getCarrierWaveId());
        newMeter.setWiringMode(changeMeter.getNewMeterMess().getWiringMode());
//        newMeter.setUsePrepayFlag(0);
//        newMeter.setUseAdditionFlag(0);
//        newMeter.setUseCarryFlag(1);
        //更换电表的时候也可能有预支金
        if (newMeter.getInitMoney() == null || newMeter.getInitMoney() == 0) {
            newMeter.setUsePrepayFlag(1);//1 是不用处理
        } else {
            newMeter.setUsePrepayFlag(0);
        }

        if (StringUtils.isNotBlank(changeMeter.getAdditionMoney())) {
            BigDecimal additionMoney = new BigDecimal(changeMeter.getAdditionMoney());
            newMeter.setAdditionMoney(additionMoney);
            //这里需要我继续处理补加扣减
            newMeter.setUseAdditionFlag(0);
        }

        //todo  确认这里不需要操作
        if (StringUtils.isNotBlank(changeMeter.getCarryMoney())) {
            BigDecimal carryMoney = new BigDecimal(changeMeter.getCarryMoney());
            newMeter.setAdditionMoney(carryMoney);
            //结转的金额不需要我这边做处理 所以直接标记成已经处理
            newMeter.setUseAdditionFlag(1);
        }

        ret = consInfoPushFacade.changeMeter(changeMeter.getConsNo(), changeMeter.getOldMeterId(), newMeter);

        return ret > 0 ? new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg)
                : new JsonResult<>(ResultDef.CERR_CONS_PUSH_ERR.code, ResultDef.CERR_CONS_PUSH_ERR.msg);
    }

    /**
     * 更改商户信息
     * @param changeCons
     * @param bind
     * @return
     */
    @RequestMapping(value = "/changeCons", method = RequestMethod.POST)
    public JsonResult changeCons(@RequestBody @Valid ChangeCons changeCons, BindingResult bind) {
        JsonResult bindErrMsg = getBindErrMsg(bind);

        if (bindErrMsg != null) {
            return bindErrMsg;
        }

        int ret = 0;

        ret = consInfoPushFacade.changeCons(changeCons.getConsNo(), changeCons.getNewcons());

        return ret > 0 ? new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg)
                : new JsonResult<>(ResultDef.CERR_CONS_PUSH_ERR.code, ResultDef.CERR_CONS_PUSH_ERR.msg);
    }

    /**
     * 更改电价操作
     * @param map
     * @return
     */
    @RequestMapping(value = "/changePrice", method = RequestMethod.POST)
    public JsonResult changePrice(@RequestBody Map<String, String> map) {

        String consNo = map.get("consNo");
        String meterId = map.get("meterId");
        String newTariffId = map.get("newTariffId");
        String newTariffName = map.get("newTariffName");

        if (StringUtils.isBlank(consNo, meterId, newTariffId, newTariffName)) {
            return new JsonResult<>(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg, "请检查参数 consNo、meterId、newTariffId、newTariffName");
        }

        long mId;
        long tid;

        try {
            mId = Long.parseLong(meterId);
            tid = Long.parseLong(newTariffId);
        } catch (Exception e) {
            return new JsonResult<>(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg, "请检查参数 meterId、newTariffId");
        }

        int ret = consInfoPushFacade.changePrice(consNo, mId, tid, newTariffName);

        return ret > 0 ? new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg)
                : new JsonResult<>(ResultDef.CERR_CONS_PUSH_ERR.code, ResultDef.CERR_CONS_PUSH_ERR.msg);

    }

    /**
     * 下发购电结果通知
     * @param buyNotifiy
     * @param bind
     * @return
     */
    @RequestMapping(value = "/buyNotifiy", method = RequestMethod.POST)
    public JsonResult buyNotifiy(@RequestBody @Valid BuyNotifiy buyNotifiy,  BindingResult bind) {


        JsonResult bindErrMsg = getBindErrMsg(bind);

        if (bindErrMsg != null) {
            return bindErrMsg;
        }

        if (SqlInjectionCheck.Check(buyNotifiy)) {
            return new JsonResult<>(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        int ret = consInfoPushFacade.buyNotifiy(buyNotifiy);

        return ret > 0 ? new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg)
                : new JsonResult<>(ResultDef.CERR_CONS_PUSH_ERR.code, ResultDef.CERR_CONS_PUSH_ERR.msg);
    }

    /**
     * 需要同步通知的数据
     * @param map
     * @return
     */
    @RequestMapping(value = "/dataNotify", method = RequestMethod.POST)
    public JsonResult dataNotify(@RequestBody Map<String, String> map) {

        String dataDate = map.get("dataDate");
        String batchNoStr = map.get("batchNo");
        String dataItem = map.get("dataItem");

        if (StringUtils.isBlank(dataDate, batchNoStr, dataItem)) {
            return new JsonResult<>(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        Long batchNo ;

        try {
            batchNo = Long.parseLong(batchNoStr);
        } catch (Exception e) {
            return new JsonResult<>(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        int ret = 0;

        switch (dataItem) {
            case "freeze":  //日冻结
                ret = consInfoPushFacade.freezeHandler(dataDate, batchNo);
                break;
            case "residue": //商户余额
                ret = consInfoPushFacade.residueHandler(dataDate, batchNo);
                break;
            case "bill":    //对账单
                ret = consInfoPushFacade.billHandler(dataDate, batchNo);
                break;
            default:
                return new JsonResult<>(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        return ret > 0 ? new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg)
                : new JsonResult<>(ResultDef.CERR_CONS_PUSH_ERR.code, ResultDef.CERR_CONS_PUSH_ERR.msg);
    }
}
