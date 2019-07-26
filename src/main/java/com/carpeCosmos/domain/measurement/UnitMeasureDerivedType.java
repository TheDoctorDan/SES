package com.carpeCosmos.domain.measurement;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.carpeCosmos.domain.measurement.UnitMeasureBaseType.*;
import static com.carpeCosmos.domain.measurement.UnitPrefix.KILO;
import static com.carpeCosmos.domain.measurement.UnitPrefix.UNO;

@Getter
public enum UnitMeasureDerivedType implements UnitMeasureType {
    //Mechanical

    NEWTON("n",
            Arrays.asList(
                    new SimpleUnitMeasurement(KILO, GRAM),
                    new SimpleUnitMeasurement(METER)
            ),
            Arrays.asList(
                    new SimpleUnitMeasurement(SECOND),
                    new SimpleUnitMeasurement(SECOND)
            ),
            UNO),

    JOULE("J",
            Arrays.asList(
                    new SimpleUnitMeasurement(NEWTON),
                    new SimpleUnitMeasurement(METER)
            ),
            Collections.emptyList(),
            UNO),

    WATT("W",
            Arrays.asList(
                    new SimpleUnitMeasurement(JOULE),
                    new SimpleUnitMeasurement(METER)
            ),
            Arrays.asList(new SimpleUnitMeasurement(SECOND)),
            UNO),

    PASCAL("Pa",
            Arrays.asList(new SimpleUnitMeasurement(NEWTON)),
            Arrays.asList(
                    new SimpleUnitMeasurement(METER),
                    new SimpleUnitMeasurement(METER)
            ),
            UNO),

    //Electrical
    VOLT("V", WATT, AMPERE, UNO),

    OHM("\u03A9", VOLT, AMPERE, UNO),

    //Angle
    RADIAN("rad", METER, METER, UNO);

    private String symbol;

    private List<SimpleUnitMeasurement> numeratorSimpleUnitMeasurementList;
    private List<SimpleUnitMeasurement> denominatorSimpleUnitMeasurementList;
    private UnitPrefix defaultUnitPrefix;

    UnitMeasureDerivedType(String symbol, List<SimpleUnitMeasurement> numeratorSimpleUnitMeasurementList, List<SimpleUnitMeasurement> denominatorSimpleUnitMeasurementList, UnitPrefix defaultUnitPrefix) {
        this.symbol = symbol;
        this.numeratorSimpleUnitMeasurementList = numeratorSimpleUnitMeasurementList;
        this.denominatorSimpleUnitMeasurementList = denominatorSimpleUnitMeasurementList;
        this.defaultUnitPrefix = defaultUnitPrefix;
    }

    UnitMeasureDerivedType(String symbol, UnitMeasureType numeratorUnitMeasureType, UnitMeasureType denominatorUnitMeasureType, UnitPrefix defaultUnitPrefix) {
        this.symbol = symbol;
        this.numeratorSimpleUnitMeasurementList = Arrays.asList(new SimpleUnitMeasurement(numeratorUnitMeasureType));
        this.denominatorSimpleUnitMeasurementList = Arrays.asList(new SimpleUnitMeasurement(denominatorUnitMeasureType));
        this.defaultUnitPrefix = defaultUnitPrefix;
    }


    @Override
    public boolean isBaseUnitDimension() {
        return false;
    }

    @Override
    public List<SimpleUnitMeasurement> getNumeratorSimpleUnitMeasurementList() {
        return numeratorSimpleUnitMeasurementList;
    }

    @Override
    public List<SimpleUnitMeasurement> getDenominatorSimpleUnitMeasurementList() {
        return denominatorSimpleUnitMeasurementList;
    }

    @Override
    public UnitPrefix getDefaultUnitPrefix() {
        return this.defaultUnitPrefix;
    }


}
