package com.carpeCosmos.domain.measurement;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.carpeCosmos.domain.measurement.UnitPrefix.*;
import static com.carpeCosmos.domain.measurement.UnitMeasureBaseType.*;

@Getter
public enum UnitMeasureDerivedType implements UnitMeasureType
{
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
            false
    ),

    JOULE("J",
            Arrays.asList(
                    new SimpleUnitMeasurement(NEWTON),
                    new SimpleUnitMeasurement(METER)
            ),
            Collections.emptyList()
            ,false
    ),

    WATT("W",
            Arrays.asList(
                    new SimpleUnitMeasurement(JOULE),
                    new SimpleUnitMeasurement(METER)
            ),
            Arrays.asList(new SimpleUnitMeasurement(SECOND)),
            false
    ),

    PASCAL("Pa",
            Arrays.asList(new SimpleUnitMeasurement(NEWTON)),
            Arrays.asList(
                    new SimpleUnitMeasurement(METER),
                    new SimpleUnitMeasurement(METER)
            ),
            false
    ),

    //Electrical
    VOLT("V", WATT, AMPERE, false),

    OHM("\u03A9", VOLT, AMPERE, false),

    //Angle
    RADIAN("rad", METER, METER, true)

    ;

    private String symbol;

    private List<SimpleUnitMeasurement> numeratorSimpleUnitMeasurementList;
    private List<SimpleUnitMeasurement> denominatorSimpleUnitMeasurementList;
    private boolean notToBeReduced;

    UnitMeasureDerivedType(String symbol, List<SimpleUnitMeasurement> numeratorSimpleUnitMeasurementList, List<SimpleUnitMeasurement> denominatorSimpleUnitMeasurementList, boolean notToBeReduced)
    {
        this.symbol = symbol;
        this.numeratorSimpleUnitMeasurementList = numeratorSimpleUnitMeasurementList;
        this.denominatorSimpleUnitMeasurementList = denominatorSimpleUnitMeasurementList;
        this.notToBeReduced = notToBeReduced;
    }

    UnitMeasureDerivedType(String symbol, UnitMeasureType numeratorUnitMeasureType, UnitMeasureType denominatorUnitMeasureType, boolean notToBeReduced)
    {
        this.symbol = symbol;
        this.numeratorSimpleUnitMeasurementList = Arrays.asList(new SimpleUnitMeasurement(numeratorUnitMeasureType));
        this.denominatorSimpleUnitMeasurementList = Arrays.asList(new SimpleUnitMeasurement(denominatorUnitMeasureType));
        this.notToBeReduced = notToBeReduced;
    }

    @Override
    public boolean isBaseUnitDimension()
    {
        return false;
    }


    @Override
    public List<SimpleUnitMeasurement> getNumeratorSimpleUnitMeasurementList()
    {
        return numeratorSimpleUnitMeasurementList;
    }

    @Override
    public List<SimpleUnitMeasurement> getDenominatorSimpleUnitMeasurementList()
    {
        return denominatorSimpleUnitMeasurementList;
    }
}
