package org.firstinspires.ftc.teamcode.math;

import com.pedropathing.math.Vector;

public class ExtendedVector {

    //region Basic Methods

    private Vector vector;
    private double theta;

    public ExtendedVector() {
        vector = new Vector();
    }

    public Vector getVector() {
        return vector;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    //endregion

    public static ExtendedVector convertJoystickIntoVector(double forward, double strafe, double turn) {
        ExtendedVector ev = new ExtendedVector();
        ev.getVector().setOrthogonalComponents(forward, strafe);
        ev.setTheta(turn);
        return ev;
    }
}