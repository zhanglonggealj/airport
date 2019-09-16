//package com.esop.airport.domain.mongo;
//
//import com.esop.airport.domain.model.mongo.TStatDayCompany;
//import com.esop.airport.domain.model.mongo.TestMongo;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import java.util.List;
//
///**
// * @program: airport
// * @description:
// * @author: Mr.Li
// * @create: 2019-07-31 11:39
// **/
//public interface TestMongoRepository extends MongoRepository<TestMongo, String> {
//
//    List<TestMongo> findByCompanyidAndHisdateAndMeasurecodeAndMeterid(String cid, String hisdate, String measurecode, String meterid);
//
//    List<TestMongo> findByHisdateBetweenAndCompanyidAndMeasurecodeAndMeterid(
//            String startHisdate, String endHisdate, String cid, String measurecode, String meterid);
//}
