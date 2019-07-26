package com.carpeCosmos.domain.physicalThings;

import com.carpeCosmos.domain.measurement.SimpleUnitMeasurement;
import com.carpeCosmos.domain.measurement.UnitMeasurementFraction;

import java.util.List;
import java.util.stream.Collectors;

public class PhysicalQuantity {
    private double scalar;
    private UnitMeasurementFraction unitMeasurementFraction;

    public PhysicalQuantity add(PhysicalQuantity other){
        //verify unitMeasure fundamental types
        List<SimpleUnitMeasurement> myNumeratorList =
                this.unitMeasurementFraction.getNumeratorSimpleUnitMeasurementList().stream().map().sorted().collect(Collectors.toList());
        List<SimpleUnitMeasurement> otherNumeratorList =
                other.unitMeasurementFraction.getNumeratorSimpleUnitMeasurementList().stream().sorted().collect(Collectors.toList());
        List<SimpleUnitMeasurement> myDenominatorList =
                this.unitMeasurementFraction.getDenominatorSimpleUnitMeasurementList().stream().sorted().collect(Collectors.toList());
        List<SimpleUnitMeasurement> otherDenominatorList =
                other.unitMeasurementFraction.getDenominatorSimpleUnitMeasurementList().stream().sorted().collect(Collectors.toList());

        //adjust unitMeasure prefix to match
        //add scalars
    }
}
