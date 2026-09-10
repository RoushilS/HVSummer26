package org.firstinspires.ftc.teamcode.subsystems;

import com.pedropathing.ivy.Command;

import java.util.Objects;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.units.Units;
import dev.nextftc.units.measuretypes.Angle;
import dev.nextftc.units.measuretypes.Distance;
import dev.nextftc.units.measuretypes.Per;
import dev.nextftc.units.unittypes.AngleUnit;
import dev.nextftc.units.unittypes.DistanceUnit;

public class Slides implements Mechanism {

    double throttle = 0;

    enum Mode {
        CONT,
        SET,
        OFF
    }

    Angle positionSetpoint = Units.Rotations.of(0);

    private Mode mode = Mode.OFF;

    public Command setMode(String s) {
        return Command.build()
                .setStart(() -> {
                    if(Objects.equals(s, "CONT")) {
                        mode = Mode.CONT;
                    }
                    else if (Objects.equals(s, "SET")) {
                        mode = Mode.SET;
                    }
                    else if(Objects.equals(s, "OFF")) {
                        mode = Mode.OFF;
                    }
                });
    }

    private final NextMotor slides =
            new NextMotor(RobotController.controlHub(), 0);

    private final Per<DistanceUnit, AngleUnit> distPerRotation =
            Units.Inches.of(3.5).div(Units.Rotations.of(1));

    public Command updateThrottle(double t) {
        return Command.build()
                .setStart(() -> throttle = t);
    }

    public void updateThrottleContinuous(double t) {
        throttle = t;
    }

    public Command setPosition(Distance position) {
        return Command.build()
                .setStart(() -> {
                    Angle angle = Units.Rotations.of(
                            position.into(Units.Inches) / distPerRotation.getMagnitude());
                    positionSetpoint = angle;
                });
    }

    public Slides() {
        mode = Mode.CONT;
    }

    @Override
    public void periodic() {
        switch(mode) {
            case CONT:
                slides.setThrottle(throttle);
                break;
            case SET:
                slides.setPositionSetpoint(positionSetpoint);
                break;
            case OFF:
                slides.setThrottle(0);
                break;
        }

    }
}