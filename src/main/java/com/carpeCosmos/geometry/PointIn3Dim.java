package com.carpeCosmos.domain.geometry;

import lombok.Builder;

@Builder
public class PointIn3Dim {
    private double x;
    private double y;
    private double z;

    public static PointIn3Dim add(PointIn3Dim first, PointIn3Dim second)
    {
        return PointIn3Dim.builder()
                .x(first.x+second.x)
                .y(first.y+second.y)
                .z(first.z+second.z)
                .build();
    }
}
