package org.firstinspires.ftc.teamcode.subsystems.dt;


import com.pedropathing.math.Vector;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.math.DrivetrainMath;
import org.firstinspires.ftc.teamcode.math.ExtendedVector;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.LambdaCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.hardware.impl.MotorEx;


public class DrivetrainSubsystem implements Subsystem {

    //region Boilerplate
    public static final DrivetrainSubsystem INSTANCE = new DrivetrainSubsystem();
    private DrivetrainSubsystem() { }

    //endregion

    //region Definitions: Internal
    private ExtendedVector joystickVector = new ExtendedVector();

    public enum DriveEnum {
        OFF,
        VECTOR,
        ROBO
    }
    DriveEnum driveEnum = DriveEnum.OFF;

    public void setJoystickVector( ExtendedVector v) {
        joystickVector = v;
    }

    //endregion

    //region Definitions: Hardware
    public MotorEx frontLeftMotor;
    public MotorEx backLeftMotor;
    public MotorEx frontRightMotor;
    public MotorEx backRightMotor;

    //endregion

    //region Commands
    public Command setDriveMethod(DriveEnum driveEnum) {
        return new LambdaCommand()
                .setStart(() -> {
                    this.driveEnum = driveEnum;
                })
                .setIsDone(() -> true)
                .setInterruptible(true)
                .named("Set Drive Method");
    }

    //endregion

    public void update(ExtendedVector v) {
        setJoystickVector(v);
    }

    @Override
    public void initialize() {
        frontLeftMotor = new MotorEx("frontLeft").brakeMode();
        backLeftMotor = new MotorEx("backLeft").brakeMode();
        frontRightMotor = new MotorEx("frontRight").brakeMode();
        backRightMotor = new MotorEx("backRight").brakeMode();
        driveEnum = DriveEnum.VECTOR;
    }

    @Override
    public void periodic() {
        if (ActiveOpMode.isStarted()) {
            switch (driveEnum) {
                case ROBO:
                    frontLeftMotor.setPower(DrivetrainMath.calculateMotorPower(joystickVector, "fl"));
                    frontRightMotor.setPower(DrivetrainMath.calculateMotorPower(joystickVector, "fr"));
                    backLeftMotor.setPower(DrivetrainMath.calculateMotorPower(joystickVector, "bl"));
                    backRightMotor.setPower(DrivetrainMath.calculateMotorPower(joystickVector, "br"));
                    break;
                case OFF:
                    frontLeftMotor.setPower(0);
                    frontRightMotor.setPower(0);
                    backLeftMotor.setPower(0);
                    backRightMotor.setPower(0);
                    break;
                default:
                    break;
            }
        }
    }
}
