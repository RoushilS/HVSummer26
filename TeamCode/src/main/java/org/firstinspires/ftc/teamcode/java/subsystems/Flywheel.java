package org.firstinspires.ftc.teamcode.java.subsystems;

import com.pedropathing.ivy.Command;

import java.util.Objects;

import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;

import static dev.nextftc.units.Units.RotationsPerMinute;

public class Flywheel implements Mechanism {

    enum Mode {
        ON,
        OFF
    }

    private Mode mode = Mode.OFF;

    private double target = 0;

    private final NextMotor left = new NextMotor("left");
    private final NextMotor right = new NextMotor("right");

    public Command setMode(String s) {
        return Command.build()
                .setStart(() -> {
                    if(Objects.equals(s, "ON")) {
                        mode = Mode.ON;
                    } else if(Objects.equals(s, "OFF")) {
                        mode = Mode.OFF;
                    }
                });
    }

    public void updateTarget(double t) {
        target = t;
    }


    public Flywheel() {
        right.follow(left, NextMotor.Direction.REVERSE);

        left.getVelocityConstants().setKP(0.01);
        left.getVelocityConstants().setKD(0.001);

    }

    @Override
    public void periodic() {
        switch(mode) {
            case ON:
                left.setVelocitySetpoint(RotationsPerMinute.of(target));
            case OFF:
                left.setVelocitySetpoint(RotationsPerMinute.of(0));
        }
    }
}