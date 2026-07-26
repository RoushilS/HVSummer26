package org.firstinspires.ftc.teamcode.java.subsystems;

import static com.pedropathing.ivy.Scheduler.schedule;
import static dev.nextftc.units.Units.Radians;

import com.pedropathing.ivy.Command;

import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.units.measuretypes.Angle;


public class Slides implements Mechanism {
    private final Angle anglePerTick = Radians.of(2 * Math.PI / 537.7);
    private final NextMotor left = new NextMotor("left", anglePerTick);
    private final NextMotor right = new NextMotor("right", anglePerTick);

    public Command up = instant(() -> left.setPositionSetpoint(ticksToAngle(4000.0)));
    public Command down = instant(() -> left.setPositionSetpoint(ticksToAngle(0.0)));
    public Command middle = instant(() -> left.setPositionSetpoint(ticksToAngle(2000.0)));

    public Angle ticksToAngle(double ticks){
        return anglePerTick.times(ticks);
    }

    public Slides(){
        right.follow(left, NextMotor.Direction.REVERSE);

        left.getPositionConstants().setKP(0.1);
        left.getPositionConstants().setKD(0.001);
        left.getPositionConstants().setKS(0.02);
        left.getPositionConstants().setKG(0.4);

        schedule(down);
    }
}
