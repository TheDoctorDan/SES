package com.carpeCosmos.measurement.domain;

import org.junit.Test;

import static com.carpeCosmos.measurement.domain.UnitPrefix.GIGA;
import static com.carpeCosmos.measurement.domain.UnitPrefix.KILO;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class FractionUnitMeasureTest
{

    @Test
    public void builder()
    {
        FractionUnitMeasure.Builder actualBuilder = FractionUnitMeasure.builder();
        assertEquals("FractionUnitMeasure.Builder(numeratorList=[], denominatorList=[], powerOf10Exponent=0)",
                actualBuilder.toString());
    }

    @Test
    public void builderBuild()
    {
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder().build();
        assertEquals("FractionUnitMeasure(numeratorList=[], denominatorList=[], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }

    @Test
    public void toBuilder()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder().build();
        FractionUnitMeasure.Builder actualBuilder = subjectFractionUnitMeasure.toBuilder();
        assertEquals("FractionUnitMeasure.Builder(numeratorList=[], denominatorList=[], powerOf10Exponent=0)",
                actualBuilder.toString());

    }


    @Test
    public void builder_numeratorList()
    {
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder()
                .numeratorList(asList(
                        new UnitMeasurement(KILO, UnitType.GRAM),
                        new UnitMeasurement(UnitPrefix.MEGA, UnitType.METER),
                        new UnitMeasurement(UnitPrefix.DECA, UnitType.SECOND)
                ))
                .build();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=MEGA, unitType=METER), UnitMeasurement(unitPrefix=DECA, unitType=SECOND)], denominatorList=[], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }


    @Test
    public void builder_numeratorUnprefixedList()
    {
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder()
                .numeratorUnprefixedList(asList(
                        UnitType.GRAM,
                        UnitType.METER,
                        UnitType.SECOND
                ))
                .build();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=UNO, unitType=GRAM), UnitMeasurement(unitPrefix=UNO, unitType=METER), UnitMeasurement(unitPrefix=UNO, unitType=SECOND)], denominatorList=[], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }

    @Test
    public void builder_numerator_AsUnitMeasurementV2()
    {
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder()
                .numerator(new UnitMeasurement(KILO, UnitType.GRAM))
                .numerator(new UnitMeasurement(UnitPrefix.MEGA, UnitType.METER))
                .numerator(new UnitMeasurement(UnitPrefix.DECA, UnitType.SECOND))
                .build();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=MEGA, unitType=METER), UnitMeasurement(unitPrefix=DECA, unitType=SECOND)], denominatorList=[], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }


    @Test
    public void builder_numerator_AsUnitPrefixAndUnprefixedUnitMeasurementV2()
    {
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder()
                .numerator(KILO, UnitType.GRAM)
                .numerator(UnitPrefix.MEGA, UnitType.METER)
                .numerator(UnitPrefix.DECA, UnitType.SECOND)
                .build();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=MEGA, unitType=METER), UnitMeasurement(unitPrefix=DECA, unitType=SECOND)], denominatorList=[], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }

    @Test
    public void builder_numerator_AsFractionUnitMeasure()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder()
                .numerator(KILO, UnitType.GRAM)
                .numerator(UnitPrefix.MEGA, UnitType.METER)
                .numerator(UnitPrefix.DECA, UnitType.SECOND)
                .denominator(KILO, UnitType.NEWTON)
                .denominator(UnitType.VOLT)
                .build();
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder()
                .numerator(subjectFractionUnitMeasure)
                .build();

        assertEquals(
                "FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=MEGA, unitType=METER), UnitMeasurement(unitPrefix=DECA, unitType=SECOND)], denominatorList=[UnitMeasurement(unitPrefix=KILO, unitType=NEWTON), UnitMeasurement(unitPrefix=UNO, unitType=VOLT)], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }


    @Test
    public void builder_denominatorList()
    {
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder()
                .denominatorList(asList(
                        new UnitMeasurement(KILO, UnitType.GRAM),
                        new UnitMeasurement(UnitPrefix.MEGA, UnitType.METER),
                        new UnitMeasurement(UnitPrefix.DECA, UnitType.SECOND)
                ))
                .build();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[], denominatorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=MEGA, unitType=METER), UnitMeasurement(unitPrefix=DECA, unitType=SECOND)], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }


    @Test
    public void builder_denominatorUnprefixedList()
    {
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder()
                .denominatorUnprefixedList(asList(
                        UnitType.GRAM,
                        UnitType.METER,
                        UnitType.SECOND
                ))
                .build();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[], denominatorList=[UnitMeasurement(unitPrefix=UNO, unitType=GRAM), UnitMeasurement(unitPrefix=UNO, unitType=METER), UnitMeasurement(unitPrefix=UNO, unitType=SECOND)], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }

    @Test
    public void builder_denominator_AsUnitMeasurementV2()
    {
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder()
                .denominator(new UnitMeasurement(KILO, UnitType.GRAM))
                .denominator(new UnitMeasurement(UnitPrefix.MEGA, UnitType.METER))
                .denominator(new UnitMeasurement(UnitPrefix.DECA, UnitType.SECOND))
                .build();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[], denominatorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=MEGA, unitType=METER), UnitMeasurement(unitPrefix=DECA, unitType=SECOND)], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }


    @Test
    public void builder_denominator_AsUnitPrefixAndUnprefixedUnitMeasurementV2()
    {
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder()
                .denominator(KILO, UnitType.GRAM)
                .denominator(UnitPrefix.MEGA, UnitType.METER)
                .denominator(UnitPrefix.DECA, UnitType.SECOND)
                .build();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[], denominatorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=MEGA, unitType=METER), UnitMeasurement(unitPrefix=DECA, unitType=SECOND)], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }

    @Test
    public void builder_denominator_AsFractionUnitMeasure()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder()
                .numerator(KILO, UnitType.GRAM)
                .numerator(UnitPrefix.MEGA, UnitType.METER)
                .numerator(UnitPrefix.DECA, UnitType.SECOND)
                .denominator(KILO, UnitType.NEWTON)
                .denominator(UnitType.VOLT)
                .build();
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder()
                .denominator(subjectFractionUnitMeasure)
                .build();

        assertEquals(
                "FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=KILO, unitType=NEWTON), UnitMeasurement(unitPrefix=UNO, unitType=VOLT)], denominatorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=MEGA, unitType=METER), UnitMeasurement(unitPrefix=DECA, unitType=SECOND)], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }


    @Test
    public void builder_powerOf10Exponent()
    {
        FractionUnitMeasure actualFractionUnitMeasure = FractionUnitMeasure.builder()
                .powerOf10Exponent(123)
                .build();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[], denominatorList=[], powerOf10Exponent=123)",
                actualFractionUnitMeasure.toString());
    }


    @Test
    public void expand_GRAM()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder()
                .numerator(UnitType.GRAM)
                .build();

        FractionUnitMeasure actualFractionUnitMeasure = subjectFractionUnitMeasure.expand();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=UNO, unitType=GRAM)], denominatorList=[], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }

    @Test
    public void expand_perGRAM()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder()
                .denominator(UnitType.GRAM)
                .build();

        FractionUnitMeasure actualFractionUnitMeasure = subjectFractionUnitMeasure.expand();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[], denominatorList=[UnitMeasurement(unitPrefix=UNO, unitType=GRAM)], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }

    @Test
    public void expand_NEWTON()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder()
                .numerator(UnitType.NEWTON)
                .build();

        FractionUnitMeasure actualFractionUnitMeasure = subjectFractionUnitMeasure.expand();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=UNO, unitType=METER)], denominatorList=[UnitMeasurement(unitPrefix=UNO, unitType=SECOND), UnitMeasurement(unitPrefix=UNO, unitType=SECOND)], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }


    @Test
    public void expand_perNEWTON()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder()
                .denominator(UnitType.NEWTON)
                .build();

        FractionUnitMeasure actualFractionUnitMeasure = subjectFractionUnitMeasure.expand();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=UNO, unitType=SECOND), UnitMeasurement(unitPrefix=UNO, unitType=SECOND)], denominatorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=UNO, unitType=METER)], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }


    @Test
    public void expand_YURI()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder()
                .numerator(UnitType.YURI)
                .build();

        FractionUnitMeasure actualFractionUnitMeasure = subjectFractionUnitMeasure.expand();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=UNO, unitType=METER), UnitMeasurement(unitPrefix=UNO, unitType=METER), UnitMeasurement(unitPrefix=UNO, unitType=METER), UnitMeasurement(unitPrefix=UNO, unitType=METER)], denominatorList=[UnitMeasurement(unitPrefix=UNO, unitType=SECOND), UnitMeasurement(unitPrefix=UNO, unitType=SECOND), UnitMeasurement(unitPrefix=UNO, unitType=METER), UnitMeasurement(unitPrefix=UNO, unitType=METER), UnitMeasurement(unitPrefix=KILO, unitType=GRAM)], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());
    }


    @Test
    public void cancelLikeTerms_Empty()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder().build();

        FractionUnitMeasure actualFractionUnitMeasure = subjectFractionUnitMeasure.cancelLikeTerms();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[], denominatorList=[], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());

    }


    @Test
    public void cancelLikeTerms_SimpleNumeratorList()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder()
                .numerator(KILO, UnitType.GRAM)
                .numerator(GIGA, UnitType.METER)
                .build();

        FractionUnitMeasure actualFractionUnitMeasure = subjectFractionUnitMeasure.cancelLikeTerms();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=GIGA, unitType=METER)], denominatorList=[], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());

    }

    @Test
    public void cancelLikeTerms_SimpleDenominatorList()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder()
                .denominator(KILO, UnitType.GRAM)
                .denominator(GIGA, UnitType.METER)
                .build();

        FractionUnitMeasure actualFractionUnitMeasure = subjectFractionUnitMeasure.cancelLikeTerms();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[], denominatorList=[UnitMeasurement(unitPrefix=KILO, unitType=GRAM), UnitMeasurement(unitPrefix=GIGA, unitType=METER)], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());

    }

    @Test
    public void cancelLikeTerms_1()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder()
                .numerator(KILO, UnitType.METER)
                .numerator(UnitType.SECOND)
                .numerator(UnitType.METER)
                .denominator(UnitType.METER)
                .denominator(UnitType.METER)
                .denominator(KILO, UnitType.SECOND)
                .build();

        FractionUnitMeasure actualFractionUnitMeasure = subjectFractionUnitMeasure.expand().cancelLikeTerms();
        assertEquals(
                "FractionUnitMeasure(numeratorList=[], denominatorList=[], powerOf10Exponent=0)",
                actualFractionUnitMeasure.toString());

    }

    @Test
    public void cancelLikeTerms_YURI()
    {
        FractionUnitMeasure subjectFractionUnitMeasure = FractionUnitMeasure.builder()
                .numerator(KILO, UnitType.YURI)
                .build();

        FractionUnitMeasure actualFractionUnitMeasure = subjectFractionUnitMeasure.expand().cancelLikeTerms();
        assertEquals(
"FractionUnitMeasure(numeratorList=[UnitMeasurement(unitPrefix=UNO, unitType=METER), UnitMeasurement(unitPrefix=UNO, unitType=METER)], denominatorList=[UnitMeasurement(unitPrefix=UNO, unitType=SECOND), UnitMeasurement(unitPrefix=UNO, unitType=SECOND)], powerOf10Exponent=3)",
                actualFractionUnitMeasure.toString());

    }

}