package org.firstinspires.ftc.teamcode.java.subsystems;

import com.pedropathing.ivy.Command;

import java.util.Objects;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;

public class Intake implements Mechanism {

    enum Mode {
        ON,
        OFF
    }

    double throttle = 0;

    private Mode mode = Mode.OFF;

    private final NextMotor intake = new NextMotor(RobotController.controlHub(), 2);

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

    public Command updateThrottle(double t) {
        return Command.build()
                .setStart(()->throttle = t);
    }

    public void updateThrottleContinuous(double t) {
        throttle = t;
    }

    public Intake() {
        mode = Mode.ON;

    }

    @Override
    public void periodic() {
        switch(mode) {
            case ON:
                intake.setThrottle(throttle);
            case OFF:
                intake.setThrottle(0);
        }

    }
}
