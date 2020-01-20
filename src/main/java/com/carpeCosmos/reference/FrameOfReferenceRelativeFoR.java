package com.carpeCosmos.reference;

import com.carpeCosmos.geometry.PointIn3Dim;
import lombok.Getter;

@Getter
public class FrameOfReferenceRelativeFoR implements FrameOfReferenceInterface
{
    private FrameOfReferenceInterface parentFrameOfReference;
    private PointIn3Dim centerOffset;

    private PointIn3Dim xAxisFromCenter; //rightAssention

    private PointIn3Dim zAxisFromCenter; //declination


    @Override
    public PointIn3Dim getCenter()
    {
        return PointIn3Dim.add(parentFrameOfReference.getCenter(), centerOffset);
    }
}
