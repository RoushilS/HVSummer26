package org.firstinspires.ftc.teamcode.opmodes.teleop;


import org.firstinspires.ftc.teamcode.Robot;

import dev.nextftc.robot.Telemetry;
import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;

@NextTeleop(name = "My Teleop")
public class Teleop extends NextOpMode {

    private final Robot robot;

    public Teleop(Robot robot) {
        super(robot, BulkReadHook.INSTANCE);
        this.robot = robot;
    }

    @Override
    public void start() {
        robot.startDrive(gamepad1);
        robot.slides.setMode("CONT").schedule();
        robot.intake.setMode("OFF").schedule();
    }

    @Override
    public void periodic() {
        robot.slides.updateThrottleContinuous(-gamepad1.right_stick_y);
        robot.intake.updateThrottleContinuous(gamepad1.right_bumper ? 1.0 : 0.0);

        Telemetry.log("Status", "Running");
    }
}