package com.carpeCosmos.domain.measurement;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static com.carpeCosmos.domain.measurement.UnitPrefix.UNO;
import static com.carpeCosmos.domain.measurement.FundamentalMeasurementType.*;

@Getter
public enum UnitMeasureBaseType implements UnitMeasureType
{
    METER   ("m",       LENGTH),
    GRAM    ("g",       MASS),
    SECOND  ("s",       TIME),
    AMPERE  ("A",       ELECTRICAL_CURRENT),
    KELVIN  ("K",       TEMPERATURE),
    MOLE    ("mole",    AMOUNT_OF_SUBSTANCE),
    CANDELA ("cd",      LUMINOUS_INTENSITY),
    EACH    ("ea",      UNIT_LESS_NUMBER);

    private String symbol;
    private FundamentalMeasurementType fundamentalMeasurementType;

    UnitMeasureBaseType(String symbol, FundamentalMeasurementType fundamentalMeasurementType)
    {
        this.symbol = symbol;
        this.fundamentalMeasurementType = fundamentalMeasurementType;
    }

    @Override
    public boolean isBaseUnitDimension()
    {
        return true;
    }


    public static UnitMeasureBaseType findByFundamentalMeasurementType(FundamentalMeasurementType fundamentalMeasurementType) throws NoSuchElementException
    {
        for (UnitMeasureBaseType unitMeasureBaseType : values())
        {
            if(unitMeasureBaseType.fundamentalMeasurementType.equals(fundamentalMeasurementType))
                return unitMeasureBaseType;
        }
        throw new NoSuchElementException("No UnitMeasureBaseType Enum with fundamentalMeasurementType of " + fundamentalMeasurementType + ".");
    }

    @Override
    public List<SimpleUnitMeasurement> getNumeratorSimpleUnitMeasurementList()
    {
        return Arrays.asList(new SimpleUnitMeasurement(UNO, this));
    }

    @Override
    public List<SimpleUnitMeasurement> getDenominatorSimpleUnitMeasurementList()
    {
        return Collections.emptyList();
    }


}
