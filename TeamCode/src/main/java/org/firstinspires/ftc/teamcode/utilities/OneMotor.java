package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "OneMotorControl", group = "2- Utilities")
public class OneMotor extends OpMode {

    private DcMotorEx motor;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotorEx.class, "test"); // initialize via hardwareMap
        motor.setDirection(DcMotorEx.Direction.FORWARD);  // optional: set direction
    }

    @Override
    public void loop() {
        double speed = -gamepad1.left_stick_y; // invert if needed
        motor.setPower(speed);
        telemetry.addData("Motor Position", motor.getCurrentPosition());
        telemetry.addData("Motor Velocity", motor.getVelocity());
        telemetry.addData("Motor Position", motor.getCurrentPosition());
        telemetry.update();
    }
}
