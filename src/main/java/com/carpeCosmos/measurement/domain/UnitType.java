package com.carpeCosmos.measurement.domain;

import lombok.Getter;

import java.util.NoSuchElementException;

import static com.carpeCosmos.measurement.domain.MeasurementType.*;
import static com.carpeCosmos.measurement.domain.UnitPrefix.*;

@Getter
public enum UnitType
{
    METER("m", null, LENGTH, UNO),
    GRAM("g", null, MASS, KILO),
    SECOND("s", null, TIME, UNO),
    AMPERE("A", null, ELECTRICAL_CURRENT, UNO),
    KELVIN("K", null, TEMPERATURE, UNO),
    MOLE("mole", null, AMOUNT_OF_SUBSTANCE, UNO),
    CANDELA("cd", null, LUMINOUS_INTENSITY, UNO),
    EACH("ea", null, UNIT_LESS_NUMBER, UNO),

    NEWTON("n", FractionUnitMeasure.builder()
            .numerator(KILO, GRAM)
            .numerator(METER)
            .denominator(SECOND)
            .denominator(SECOND)
            .build(), null, UNO),


    JOULE("J", FractionUnitMeasure.builder()
            .numerator(NEWTON)
            .numerator(METER)
            .build(), null, UNO),

    WATT("W", FractionUnitMeasure.builder()
            .numerator(JOULE)
            .numerator(METER)
            .denominator(SECOND)
            .build(), null, UNO),


    PASCAL("Pa", FractionUnitMeasure.builder()
            .numerator(NEWTON)
            .denominator(METER)
            .denominator(METER)
            .build(), null, UNO),

    //Space elevator
    YURI("Yi", FractionUnitMeasure.builder()
            .numerator(PASCAL)
            .denominator(FractionUnitMeasure.builder()
                    .numerator(KILO, GRAM)
                    .denominator(METER)
                    .denominator(METER)
                    .denominator(METER)
                    .build())
            .build(), null, UNO),


    //Electrical
    VOLT("V", FractionUnitMeasure.builder()
            .numerator(WATT)
            .denominator(AMPERE)
            .build(), null, UNO),

    OHM("\u03A9", FractionUnitMeasure.builder()
            .numerator(VOLT)
            .denominator(AMPERE)
            .build(), null, UNO),


    //Angle
    RADIAN("rad", null, ANGLE, UNO),

    //Volume
    LITER("l", FractionUnitMeasure.builder()
            .numerator(CENTI, METER)
            .numerator(CENTI, METER)
            .numerator(CENTI, METER)
            .powerOf10Exponent(3)
            .build(), null, UNO);


    private String symbol;
    private FractionUnitMeasure fractionUnitMeasure;
    private MeasurementType measurementType;
    private UnitPrefix nominalUnitPrefix;

    UnitType(String symbol, FractionUnitMeasure fractionUnitMeasure, MeasurementType measurementType, UnitPrefix nominalUnitPrefix)
    {
        this.symbol = symbol;
        this.fractionUnitMeasure = fractionUnitMeasure;
        this.measurementType = measurementType;
        this.nominalUnitPrefix = nominalUnitPrefix;
    }

    public boolean isBaseUnitDimension()
    {
        return this.fractionUnitMeasure == null;
    }

    public FractionUnitMeasure expand()
    {
        if (this.fractionUnitMeasure == null)
        {
            return FractionUnitMeasure.builder().numerator(this).build();
        } else
        {
            return this.fractionUnitMeasure.expand();
        }
    }


    public static UnitType findBySymbol(String symbol) throws NoSuchElementException
    {
        for (UnitType unitType : UnitType.class.getEnumConstants())
        {
            if (unitType.getSymbol().equals(symbol))
                return unitType;
        }
        throw new NoSuchElementException("No such " + UnitType.class.getSimpleName() + " Enum with symbol of " + symbol + ".");
    }


    public static UnitType findByFundamentalMeasurementType(MeasurementType measurementType) throws NoSuchElementException
    {
        for (UnitType unitType : UnitType.class.getEnumConstants())
        {
            if (unitType.getMeasurementType() != null)
            {
                if (unitType.getMeasurementType().equals(measurementType))
                {
                    return unitType;
                }
            }
        }
        throw new NoSuchElementException("No such " + UnitType.class.getSimpleName() + " Enum with measurementType of " + measurementType + ".");

    }


}
