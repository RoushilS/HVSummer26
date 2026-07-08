package org.firstinspires.ftc.teamcode.math;

public class DrivetrainMath {

    public static double calculateMotorPower(ExtendedVector v, String motor) {
        double x = v.getVector().getXComponent();
        double y = v.getVector().getYComponent();
        double turn = v.getVector().getTheta();

        switch (motor) {
            case "fl":
                return y + x + turn;

            case "fr":
                return y - x - turn;

            case "bl":
                return y - x + turn;

            case "br":
                return y + x - turn;

            default:
                throw new IllegalArgumentException("Unknown motor: " + motor);
        }
    }
}