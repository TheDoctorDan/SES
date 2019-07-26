package com.carpeCosmos.domain.measurement;

import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static com.carpeCosmos.domain.measurement.FundamentalMeasurementType.*;
import static com.carpeCosmos.domain.measurement.UnitPrefix.KILO;
import static com.carpeCosmos.domain.measurement.UnitPrefix.UNO;
import static java.util.Arrays.asList;

@Getter
public enum UnitMeasureBaseType implements UnitMeasureType {
    METER("m", LENGTH, UNO),
    GRAM("g", MASS, KILO),
    SECOND("s", TIME, UNO),
    AMPERE("A", ELECTRICAL_CURRENT, UNO),
    KELVIN("K", TEMPERATURE, UNO),
    MOLE("mole", AMOUNT_OF_SUBSTANCE, UNO),
    CANDELA("cd", LUMINOUS_INTENSITY, UNO),
    EACH("ea", UNIT_LESS_NUMBER, UNO);

    private String symbol;
    private FundamentalMeasurementType fundamentalMeasurementType;
    private UnitPrefix defaultUnitPrefix;

    UnitMeasureBaseType(String symbol, FundamentalMeasurementType fundamentalMeasurementType, UnitPrefix defaultUnitPrefix) {
        this.symbol = symbol;
        this.fundamentalMeasurementType = fundamentalMeasurementType;
        this.defaultUnitPrefix = defaultUnitPrefix;
    }

    @Override
    public boolean isBaseUnitDimension() {
        return true;
    }


    public static UnitMeasureBaseType findByFundamentalMeasurementType(FundamentalMeasurementType fundamentalMeasurementType) throws NoSuchElementException {
        for (UnitMeasureBaseType unitMeasureBaseType : values()) {
            if (unitMeasureBaseType.fundamentalMeasurementType.equals(fundamentalMeasurementType))
                return unitMeasureBaseType;
        }
        throw new NoSuchElementException("No UnitMeasureBaseType Enum with fundamentalMeasurementType of " + fundamentalMeasurementType + ".");
    }

    @Override
    public List<SimpleUnitMeasurement> getNumeratorSimpleUnitMeasurementList() {
        return asList(new SimpleUnitMeasurement(UNO, this));
    }

    @Override
    public List<SimpleUnitMeasurement> getDenominatorSimpleUnitMeasurementList() {
        return Collections.emptyList();
    }

    @Override
    public UnitPrefix getDefaultUnitPrefix() {
        return this.defaultUnitPrefix;
    }

}
