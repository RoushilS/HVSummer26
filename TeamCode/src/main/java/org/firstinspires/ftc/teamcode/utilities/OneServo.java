package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ServoControl", group = "2- Utilities")
public class OneServo extends OpMode {

    private Servo servo;

    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "test");
    }

    @Override
    public void loop() {
        // Use left stick Y as speed, invert if needed
        double position = -gamepad1.left_stick_y+(gamepad1.a ? 1 : 0);


        // Set CRServo speed
        servo.setPosition(position);

        telemetry.addData("Servo Position", position);
        telemetry.update();
    }
}
