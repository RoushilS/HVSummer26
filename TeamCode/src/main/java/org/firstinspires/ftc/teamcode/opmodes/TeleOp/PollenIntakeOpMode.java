package org.firstinspires.ftc.teamcode.opmodes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import dev.nextftc.hardware.impl.ServoEx;
@TeleOp
public class PollenIntakeOpMode extends OpMode {

    private DcMotorEx intake;

    private Servo lift1;
    private Servo lift2;

    @Override
    public void init() {
        intake = hardwareMap.get(DcMotorEx.class, "intake"); // initialize via hardwareMap
        lift1 = hardwareMap.get(Servo.class, "lift1"); // initialize via hardwareMap
        //lift2 = hardwareMap.get(Servo.class, "lift2"); // initialize via hardwareMap
    }

    @Override
    public void loop() {
        double speed = gamepad1.right_trigger-gamepad1.left_trigger; // invert if needed
        intake.setPower(speed);
        if (gamepad1.dpad_up) {
            lift1.setPosition(0.45);
        }
        else {
            lift1.setPosition(0.608);
        }
        telemetry.update();
    }
}
