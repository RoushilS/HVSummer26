package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Slides;

import java.util.Set;

import dev.nextftc.control.drive.MecanumKinematics;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;
import dev.nextftc.robot.drive.DriveCommandsKt;

public class Robot implements NextRobot {

    public Drivetrain drivetrain = new Drivetrain();
    public Slides slides = new Slides();
    public Intake intake = new Intake();

    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(drivetrain, slides, intake);
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