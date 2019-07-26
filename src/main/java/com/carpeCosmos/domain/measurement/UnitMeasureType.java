package com.carpeCosmos.domain.measurement;

import java.util.List;
import java.util.NoSuchElementException;

public interface UnitMeasureType {


    String name();

    String getSymbol();

    boolean isBaseUnitDimension();

    List<SimpleUnitMeasurement> getNumeratorSimpleUnitMeasurementList();

    List<SimpleUnitMeasurement> getDenominatorSimpleUnitMeasurementList();

    UnitPrefix getDefaultUnitPrefix();


    static <T extends Enum<T> & UnitMeasureType> T findBySymbol(Class<T> enumClass, String symbol) throws NoSuchElementException {
        for (T unitDimensionType : enumClass.getEnumConstants()) {
            if (unitDimensionType.getSymbol().equals(symbol))
                return unitDimensionType;
        }
        throw new NoSuchElementException("No " + enumClass.getSimpleName() + " Enum with symbol of " + symbol + ".");
    }


}
