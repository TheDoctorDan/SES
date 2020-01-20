package com.carpeCosmos.geometry;

import com.carpeCosmos.domain.direction.Direction;
import com.carpeCosmos.geometry.Scalar;

public class Vector
{
    private Scalar x;
    private Scalar y;
    private Scalar z;

    private Scalar getRadius(){ return x.squared().add(y.squared().add(z.squared())).squareRoot(); };
    private Scalar getTheta(){};
    private Scalar getPhi(){};
}
