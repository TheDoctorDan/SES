package com.carpeCosmos.domain.measurement;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.carpeCosmos.domain.measurement.UnitPrefix.UNO;
import static com.carpeCosmos.domain.measurement.UnitMeasureBaseType.EACH;

@Getter
public class UnitMeasurementFraction
{
    private List<SimpleUnitMeasurement> numeratorSimpleUnitMeasurementList;
    private List<SimpleUnitMeasurement> denominatorSimpleUnitMeasurementList;

    public UnitMeasurementFraction(List<SimpleUnitMeasurement> inputNumeratorSimpleUnitMeasurementList, List<SimpleUnitMeasurement> inputDenominatorSimpleUnitMeasurementList)
    {
        this.numeratorSimpleUnitMeasurementList = new ArrayList<>();
        this.denominatorSimpleUnitMeasurementList = new ArrayList<>();
        this.numeratorSimpleUnitMeasurementList.addAll(inputNumeratorSimpleUnitMeasurementList);
        this.denominatorSimpleUnitMeasurementList.addAll(inputDenominatorSimpleUnitMeasurementList);
    }


    public UnitMeasurementFraction()
    {
        this.numeratorSimpleUnitMeasurementList = new ArrayList<>();
        this.denominatorSimpleUnitMeasurementList = new ArrayList<>();
    }


    public static UnitMeasurementFraction reduceUnitTypes(UnitMeasurementFraction inputUnitMeasurementFraction, UnitPrefix inputUnitPrefix)
    {

        UnitMeasurementFraction resultUnitMeasurementFraction = new UnitMeasurementFraction();

        if (!inputUnitPrefix.equals(UNO))
        {
            resultUnitMeasurementFraction.numeratorSimpleUnitMeasurementList.add(new SimpleUnitMeasurement(inputUnitPrefix, EACH));
            resultUnitMeasurementFraction.denominatorSimpleUnitMeasurementList.add(new SimpleUnitMeasurement(UNO, EACH));
        }

        //reduce all to base types
        for (SimpleUnitMeasurement simpleUnitMeasurement : inputUnitMeasurementFraction.getNumeratorSimpleUnitMeasurementList())
        {
            if (simpleUnitMeasurement.getUnitMeasureType().isNotToBeReduced())
            {
                resultUnitMeasurementFraction.numeratorSimpleUnitMeasurementList.add(new SimpleUnitMeasurement(simpleUnitMeasurement.getUnitPrefix(), simpleUnitMeasurement.getUnitMeasureType()));
            } else
            {
                UnitMeasurementFraction tempUnitMeasurementFraction = reduceUnitTypes(
                        simpleUnitMeasurement.getUnitMeasureType().getNumeratorSimpleUnitMeasurementList(),
                        simpleUnitMeasurement.getUnitMeasureType().getDenominatorSimpleUnitMeasurementList(),
                        simpleUnitMeasurement.getUnitPrefix());
                resultUnitMeasurementFraction.numeratorSimpleUnitMeasurementList.addAll(tempUnitMeasurementFraction.getNumeratorSimpleUnitMeasurementList());
                resultUnitMeasurementFraction.denominatorSimpleUnitMeasurementList.addAll(tempUnitMeasurementFraction.getDenominatorSimpleUnitMeasurementList());
            }
        }

        for (SimpleUnitMeasurement simpleUnitMeasurement : inputUnitMeasurementFraction.getDenominatorSimpleUnitMeasurementList())
        {
            if (simpleUnitMeasurement.getUnitMeasureType().isBaseUnitDimension())
            {
                resultUnitMeasurementFraction.denominatorSimpleUnitMeasurementList.add(new SimpleUnitMeasurement(simpleUnitMeasurement.getUnitPrefix(), simpleUnitMeasurement.getUnitMeasureType()));
            } else
            {
                UnitMeasurementFraction tempUnitMeasurementFraction = reduceUnitTypes(
                        simpleUnitMeasurement.getUnitMeasureType().getNumeratorSimpleUnitMeasurementList(),
                        simpleUnitMeasurement.getUnitMeasureType().getDenominatorSimpleUnitMeasurementList(),
                        simpleUnitMeasurement.getUnitPrefix());
                resultUnitMeasurementFraction.numeratorSimpleUnitMeasurementList.addAll(tempUnitMeasurementFraction.getDenominatorSimpleUnitMeasurementList());
                resultUnitMeasurementFraction.denominatorSimpleUnitMeasurementList.addAll(tempUnitMeasurementFraction.getNumeratorSimpleUnitMeasurementList());
            }
        }
        return resultUnitMeasurementFraction;
    }


    public static UnitMeasurementFraction reduceUnitTypes(List<SimpleUnitMeasurement> inputNumeratorSimpleUnitMeasurementList, List<SimpleUnitMeasurement> inputDenominatorSimpleUnitMeasurementList, UnitPrefix inputUnitPrefix)
    {
        UnitMeasurementFraction tempUnitMeasurementFraction = new UnitMeasurementFraction(inputNumeratorSimpleUnitMeasurementList, inputDenominatorSimpleUnitMeasurementList);
        return reduceUnitTypes(tempUnitMeasurementFraction, inputUnitPrefix);
    }


    public static UnitMeasurementFraction cancelUnitTypes(UnitMeasurementFraction inputUnitMeasurementFraction)
    {
        List<SimpleUnitMeasurement> tempDenominatorSimpleUnitMeasurementList = new ArrayList<>();
        tempDenominatorSimpleUnitMeasurementList.addAll(inputUnitMeasurementFraction.getDenominatorSimpleUnitMeasurementList());
        UnitMeasurementFraction resultUnitMeasurementFraction = new UnitMeasurementFraction();
        for (SimpleUnitMeasurement simpleUnitMeasurement : inputUnitMeasurementFraction.getNumeratorSimpleUnitMeasurementList())
        {
            for (SimpleUnitMeasurement simpleUnitMeasurement1 : tempDenominatorSimpleUnitMeasurementList)
            {
                if (simpleUnitMeasurement.getUnitMeasureType().equals(simpleUnitMeasurement1.getUnitMeasureType()))
                {
                    if (simpleUnitMeasurement.getUnitPrefix().equals(simpleUnitMeasurement1.getUnitPrefix()))
                    {
                        tempDenominatorSimpleUnitMeasurementList.remove(simpleUnitMeasurement1);
                        break;
                    } else
                    {
                        tempDenominatorSimpleUnitMeasurementList.remove(simpleUnitMeasurement1);
                        tempDenominatorSimpleUnitMeasurementList.add(new SimpleUnitMeasurement(simpleUnitMeasurement1.getUnitPrefix(), EACH));
                        resultUnitMeasurementFraction.getNumeratorSimpleUnitMeasurementList().add(new SimpleUnitMeasurement(simpleUnitMeasurement.getUnitPrefix(), EACH));
                    }
                } else
                {
                    resultUnitMeasurementFraction.getNumeratorSimpleUnitMeasurementList().add(simpleUnitMeasurement);
                }
            }

        }
        resultUnitMeasurementFraction.getDenominatorSimpleUnitMeasurementList().addAll(tempDenominatorSimpleUnitMeasurementList);
        return resultUnitMeasurementFraction;

    }


    public static double factorNumericUnitPrefixes(UnitMeasurementFraction outputUnitMeasurementFraction, UnitMeasurementFraction inputUnitMeasurementFraction)
    {
        int powerOf10 = 0;

        for (SimpleUnitMeasurement simpleUnitMeasurement : inputUnitMeasurementFraction.getNumeratorSimpleUnitMeasurementList())
        {
            if (simpleUnitMeasurement.getUnitMeasureType().equals(EACH))
            {
                powerOf10 += simpleUnitMeasurement.getUnitPrefix().getPowerOf10();
            } else
            {
                SimpleUnitMeasurement alteredSimpleUnitMeasurement;
                try
                {
                    alteredSimpleUnitMeasurement = simpleUnitMeasurement.sumUnitPrefixWithPowerOf10(powerOf10);
                    outputUnitMeasurementFraction.getNumeratorSimpleUnitMeasurementList().add(alteredSimpleUnitMeasurement);
                    powerOf10 = 0;
                } catch (NoSuchElementException e)
                {
                    outputUnitMeasurementFraction.getNumeratorSimpleUnitMeasurementList().add(simpleUnitMeasurement);
                }
            }
        }

        for (SimpleUnitMeasurement simpleUnitMeasurement : inputUnitMeasurementFraction.getDenominatorSimpleUnitMeasurementList())
        {
            if (simpleUnitMeasurement.getUnitMeasureType().equals(EACH))
            {
                powerOf10 -= simpleUnitMeasurement.getUnitPrefix().getPowerOf10();
            } else
            {
                SimpleUnitMeasurement alteredSimpleUnitMeasurement;
                try {
                    alteredSimpleUnitMeasurement = simpleUnitMeasurement.sumUnitPrefixWithPowerOf10(powerOf10);
                    outputUnitMeasurementFraction.getDenominatorSimpleUnitMeasurementList().add(alteredSimpleUnitMeasurement);
                    powerOf10 = 0;
                } catch(NoSuchElementException e)
                {
                    outputUnitMeasurementFraction.getDenominatorSimpleUnitMeasurementList().add(simpleUnitMeasurement);
                }
            }
        }

        return powerOf10 == 0 ? 1D : Math.pow(10., powerOf10);
    }
}
