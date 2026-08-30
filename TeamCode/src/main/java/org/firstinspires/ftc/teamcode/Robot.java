package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

import java.util.Set;

import dev.nextftc.control.drive.MecanumKinematics;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;
import dev.nextftc.robot.drive.DriveCommandsKt;

public class Robot implements NextRobot {

    Drivetrain drivetrain = new Drivetrain();

    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(drivetrain);
    }

    public void startDrive(Gamepad gamepad1) {
        DriveCommandsKt.mecanumDrive(
                drivetrain.frontLeft,
                drivetrain.frontRight,
                drivetrain.backLeft,
                drivetrain.backRight,
                gamepad1,
                new MecanumKinematics()
        ).schedule();
    }


}