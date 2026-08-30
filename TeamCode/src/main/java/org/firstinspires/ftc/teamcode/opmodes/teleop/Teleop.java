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
    }

    @Override
    public void periodic() {
        Telemetry.log("Status", "Running");
    }
}