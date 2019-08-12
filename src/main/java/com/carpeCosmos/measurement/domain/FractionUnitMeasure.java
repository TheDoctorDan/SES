package com.carpeCosmos.measurement.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@ToString
//numerator list and denominator list of unit Measures with exponent
public class FractionUnitMeasure
{
    private List<UnitMeasurement> numeratorList;
    private List<UnitMeasurement> denominatorList;
    private int powerOf10Exponent;


    private FractionUnitMeasure(Builder builder)
    {
        numeratorList = builder.numeratorList;
        denominatorList = builder.denominatorList;
        powerOf10Exponent = builder.powerOf10Exponent;
    }


    public static Builder builder()
    {
        return new Builder();
    }

    public Builder toBuilder()
    {
        Builder builder = new Builder();
        builder.numeratorList = this.getNumeratorList();
        builder.denominatorList = this.getDenominatorList();
        builder.powerOf10Exponent = this.getPowerOf10Exponent();
        return builder;
    }


    @ToString
    public static final class Builder
    {
        private List<UnitMeasurement> numeratorList = new ArrayList<>();
        private List<UnitMeasurement> denominatorList = new ArrayList<>();
        private int powerOf10Exponent = 0;

        private Builder()
        {
        }

        public Builder numeratorList(List<UnitMeasurement> unitMeasurementList)
        {
            this.numeratorList.addAll(unitMeasurementList);
            return this;
        }

        public Builder numeratorUnprefixedList(List<UnitType> unitTypeList)
        {
            this.numeratorList.addAll(unitTypeList.stream().map(UnitMeasurement::new).collect(Collectors.toList()));
            return this;
        }

        public Builder numerator(UnitMeasurement unitMeasurement)
        {
            this.numeratorList.add(unitMeasurement);
            return this;
        }

        public Builder numerator(UnitPrefix unitPrefix, UnitType unitType)
        {
            this.numeratorList.add(new UnitMeasurement(unitPrefix, unitType));
            return this;
        }


        public Builder numerator(UnitType unitType)
        {
            this.numeratorList.add(new UnitMeasurement(unitType));
            return this;
        }

        public Builder numerator(FractionUnitMeasure fractionUnitMeasure)
        {
            this.numeratorList.addAll(fractionUnitMeasure.getNumeratorList());
            this.denominatorList.addAll(fractionUnitMeasure.getDenominatorList());
            return this;
        }


        public Builder denominatorList(List<UnitMeasurement> unitMeasurementList)
        {
            this.denominatorList.addAll(unitMeasurementList);
            return this;
        }


        public Builder denominatorUnprefixedList(List<UnitType> unitTypeList)
        {
            this.denominatorList.addAll(unitTypeList.stream().map(UnitMeasurement::new).collect(Collectors.toList()));
            return this;
        }

        public Builder denominator(UnitMeasurement unitMeasurement)
        {
            this.denominatorList.add(unitMeasurement);
            return this;
        }

        public Builder denominator(UnitPrefix unitPrefix, UnitType unitType)
        {
            this.denominatorList.add(new UnitMeasurement(unitPrefix, unitType));
            return this;
        }

        public Builder denominator(UnitType unitType)
        {
            this.denominatorList.add(new UnitMeasurement(unitType));
            return this;
        }


        public Builder denominator(FractionUnitMeasure fractionUnitMeasure)
        {
            this.numeratorList.addAll(fractionUnitMeasure.getDenominatorList());
            this.denominatorList.addAll(fractionUnitMeasure.getNumeratorList());
            return this;
        }


        public Builder powerOf10Exponent(int powerOf10Exponent)
        {
            this.powerOf10Exponent = powerOf10Exponent;
            return this;
        }

        public FractionUnitMeasure build()
        {
            return new FractionUnitMeasure(this);
        }
    }


    //expand complex unit measures into basic unit measures
    public FractionUnitMeasure expand()
    {

        //lets expand
        //setup result vars
        int powerOf10Exponent = 0;
        List<UnitMeasurement> numeratorList = new ArrayList<>();
        List<UnitMeasurement> denominatorList = new ArrayList<>();

        //check each numerator
        for (UnitMeasurement unitMeasurement : this.numeratorList)
        {
            if (!unitMeasurement.unitType.isBaseUnitDimension())
            {
                FractionUnitMeasure fractionUnitMeasure = unitMeasurement.unitType.expand();
                powerOf10Exponent += unitMeasurement.getUnitPrefix().getPowerOf10();
                numeratorList.addAll(fractionUnitMeasure.getNumeratorList());
                denominatorList.addAll(fractionUnitMeasure.getDenominatorList());
                powerOf10Exponent += fractionUnitMeasure.getPowerOf10Exponent();
            } else
            {
                numeratorList.add(unitMeasurement);
            }
        }

        //check each denominator
        for (UnitMeasurement unitMeasurement : this.denominatorList)
        {
            if (!unitMeasurement.unitType.isBaseUnitDimension())
            {
                FractionUnitMeasure fractionUnitMeasure = unitMeasurement.unitType.expand();
                powerOf10Exponent -= unitMeasurement.getUnitPrefix().getPowerOf10();
                denominatorList.addAll(fractionUnitMeasure.getNumeratorList());
                numeratorList.addAll(fractionUnitMeasure.getDenominatorList());
                powerOf10Exponent -= fractionUnitMeasure.getPowerOf10Exponent();
            } else
            {
                denominatorList.add(unitMeasurement);
            }
        }
        return FractionUnitMeasure.builder()
                .numeratorList(numeratorList)
                .denominatorList(denominatorList)
                .powerOf10Exponent(powerOf10Exponent)
                .build();

    }

    //cancel like terms in numerator and denominator
    public FractionUnitMeasure cancelLikeTerms()
    {
        FractionUnitMeasure.Builder myBuilder = FractionUnitMeasure.builder();
        //save current exponent
        myBuilder.powerOf10Exponent(this.powerOf10Exponent);
        //make temp list of denominator
        List<UnitMeasurement> tempDenominatorList = new ArrayList<>(this.denominatorList);

        //check each numerator against them remaining denominator list
        for (UnitMeasurement numeratorUnitMeasurement : this.numeratorList)
        {
            boolean found = false;
            UnitMeasurement matchingDenominatorUnitMeasurementV = null;

            for (UnitMeasurement denominatorUnitMeasurement : tempDenominatorList)
            {
                if (numeratorUnitMeasurement.getUnitType()
                        .equals(denominatorUnitMeasurement.getUnitType()))
                {
                    //found a match, break loop
                    found = true;
                    matchingDenominatorUnitMeasurementV = denominatorUnitMeasurement;

                    break;
                }
            }

            if (!found)
            {
                //not found, keep numerator
                myBuilder.numerator(numeratorUnitMeasurement);
            } else
            {
                //found match, capture exponents
                myBuilder.powerOf10Exponent(myBuilder.powerOf10Exponent
                        + numeratorUnitMeasurement.getUnitPrefix().getPowerOf10()
                        - matchingDenominatorUnitMeasurementV.getUnitPrefix().getPowerOf10()
                );
                //remove denominator from temp denominatorList
                UnitMeasurement finalMatchingDenominatorUnitMeasurementV = matchingDenominatorUnitMeasurementV;
                tempDenominatorList = tempDenominatorList
                        .stream()
                        .filter(unitMeasurement -> !unitMeasurement.equals(finalMatchingDenominatorUnitMeasurementV))
                        .collect(Collectors.toList());

            }
        }
        //capture remaining denominators
        myBuilder.denominatorList(tempDenominatorList);
        return myBuilder.build();
    }


}
