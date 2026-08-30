package org.firstinspires.ftc.teamcode.subsystems;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.robot.Mechanism;

public class Drivetrain implements Mechanism {
    public Drivetrain(){}

    public final NextMotor frontLeft = new NextMotor(RobotController.controlHub(), 0);
    public final NextMotor frontRight = new NextMotor(RobotController.controlHub(), 1);
    public final NextMotor backLeft = new NextMotor(RobotController.controlHub(), 2);
    public final NextMotor backRight = new NextMotor(RobotController.controlHub(), 3);

}