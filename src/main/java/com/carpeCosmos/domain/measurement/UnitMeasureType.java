package com.carpeCosmos.domain.measurement;

import java.util.List;
import java.util.NoSuchElementException;

public interface UnitMeasureType
{


    public String name();

    public String getSymbol();

    public boolean isBaseUnitDimension();

    public List<SimpleUnitMeasurement> getNumeratorSimpleUnitMeasurementList();

    public List<SimpleUnitMeasurement> getDenominatorSimpleUnitMeasurementList();

    public boolean isNotToBeReduced();


    public static <T extends Enum<T> & UnitMeasureType> T findBySymbol(Class<T> enumClass, String symbol) throws NoSuchElementException
    {
        for (T unitDimensionType : enumClass.getEnumConstants())
        {
            if (unitDimensionType.getSymbol().equals(symbol))
                return unitDimensionType;
        }
        throw new NoSuchElementException("No " + enumClass.getSimpleName() + " Enum with symbol of " + symbol + ".");
    }

    ;

}
