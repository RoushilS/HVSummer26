package org.firstinspires.ftc.teamcode.java.subsystems.Transfer;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;

public class KickerSubsystem implements Mechanism {

    NextServo[] kicker = {new NextServo(RobotController.controlHub(), 0), new NextServo(RobotController.controlHub(), 1), new NextServo(RobotController.controlHub(), 2)};


}
