/*
 * @ {#} CountryCodeNumbericConverter.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.converters;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
// Dùng để chuyển đổi giữa kiểu dữ liệu CountryCode và kiểu dữ liệu Integer
@Converter(autoApply = true)
public class CountryCodeNumbericConverter implements AttributeConverter<CountryCode, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CountryCode countryCode) {
        return countryCode != null ? countryCode.getNumeric() : null;
    }

    @Override
    public CountryCode convertToEntityAttribute(Integer integer) {
        return integer != null ? CountryCode.getByCode(integer) : null;
    }
}

