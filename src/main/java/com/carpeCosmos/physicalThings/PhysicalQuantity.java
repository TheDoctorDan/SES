package com.carpeCosmos.domain.physicalThings;

import com.carpeCosmos.domain.measurement.domain.FractionUnitMeasure;

public class PhysicalQuantity {
    private double scalar;
    private FractionUnitMeasure fractionUnitMeasure;

//    public PhysicalQuantity add(PhysicalQuantity other){
//        //verify unitMeasure fundamental types
//        List<UnitType> myNumeratorList =
//                this.fractionUnitMeasure.getNumeratorList().stream()
//                        .map(unitMeasurementV2 -> unitMeasurementV2.getUnitType()).sorted().collect(Collectors.toList());
//        List<UnitType> otherNumeratorList =
//                other.fractionUnitMeasure.getNumeratorList().stream()
//                        .map(unitMeasurementV2 -> unitMeasurementV2.getUnitType()).sorted().collect(Collectors.toList());
//        List<UnitType> myDenominatorList =
//                this.fractionUnitMeasure.getDenominatorList().stream()
//                        .map(unitMeasurementV2 -> unitMeasurementV2.getUnitType()).sorted().collect(Collectors.toList());
//        List<UnitType> otherDenominatorList =
//                other.fractionUnitMeasure.getDenominatorList().stream()
//                        .map(unitMeasurementV2 -> unitMeasurementV2.getUnitType()).sorted().collect(Collectors.toList());
//
//        //adjust unitMeasure prefix to match
//        //add scalars
//    }
}
