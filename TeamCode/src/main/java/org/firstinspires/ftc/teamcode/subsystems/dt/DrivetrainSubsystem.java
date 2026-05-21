package org.firstinspires.ftc.teamcode.subsystems.dt;

import static org.firstinspires.ftc.teamcode.subsystems.dt.DrivetrainSubsystem.ChassisCalculations.MAX_FORWARD_SPEED;
import static org.firstinspires.ftc.teamcode.subsystems.dt.DrivetrainSubsystem.ChassisCalculations.MAX_STRAFE_SPEED;

import com.pedropathing.math.Vector;
import com.qualcomm.robotcore.hardware.DcMotor;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.LambdaCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;


public class DrivetrainSubsystem implements Subsystem {

    public class ChassisCalculations {
        public ChassisCalculations() {}

        public final static double MAX_STRAFE_SPEED = 30;
        public final static double MAX_FORWARD_SPEED = 40;
    }

    public ChassisCalculations chassisCalculations = new ChassisCalculations();

    public static DrivetrainSubsystem INSTANCE = new DrivetrainSubsystem();

    private MotorEx frontRight = new MotorEx("frontRight").brakeMode();


    private Vector desiredDriveVector = new Vector();

    private Vector joystickVector = new Vector();

    public enum DriveEnum {
        OFF,
        VECTOR,
        ROBO
    }
    DriveEnum driveEnum = DriveEnum.OFF;

    public void setJoystickVector( Vector v) {
        joystickVector = v;
    }

    public Command setDriveMethod(DriveEnum driveEnum) {
        return new LambdaCommand()
                .setStart(() -> {
                    this.driveEnum = driveEnum;
                })
                .setIsDone(() -> true) // Returns if the command has finished
                .setInterruptible(true)
                .named("Set Drive Method");
    }

    @Override
    public void initialize() {
        driveEnum = DriveEnum.VECTOR;
    }

    @Override
    public void periodic() {
        switch (driveEnum) {
            case VECTOR:
                desiredDriveVector.setOrthogonalComponents(
                        joystickVector.getXComponent() * ChassisCalculations.MAX_STRAFE_SPEED,
                        joystickVector.getYComponent() * ChassisCalculations.MAX_FORWARD_SPEED
                );
        }
    }
}
