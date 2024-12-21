///*
// * @ {#} AccountConverter.java   1.0     20/12/2024
// *
// * Copyright (c) 2024 IUH. All rights reserved.
// */
//
//package vn.edu.iuh.fit.backend.converters;
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//import jakarta.persistence.Enumerated;
//import vn.edu.iuh.fit.backend.enums.AccountStatus;
//
///*
// * @description:
// * @author: Tran Hien Vinh
// * @date:   20/12/2024
// * @version:    1.0
// */
//@Converter(autoApply = true)
//public class AccountConverter implements AttributeConverter<AccountStatus, Byte> {
//    @Override
//    public Byte convertToDatabaseColumn(AccountStatus accountStatus) {
//        return (accountStatus!=null)?accountStatus.getValue():null;
//    }
//
//    @Override
//    public AccountStatus convertToEntityAttribute(Byte value) {
//        if (value==null){
//            return null;
//        }
//        for (AccountStatus accountStatus: AccountStatus.values()){
//            if (accountStatus.getValue()==value){
//                return accountStatus;
//            }
//        }
//        throw new IllegalArgumentException("Unknown value: "+value);
//    }
//}
