package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "CRServoControl", group = "2- Utilities")
public class OneCRServo extends OpMode {

    private CRServo crServo;

    @Override
    public void init() {
        crServo = hardwareMap.get(CRServo.class, "test");
    }

    @Override
    public void loop() {
        // Use left stick Y as speed, invert if needed
        double speed = -gamepad1.left_stick_y;

        // Set CRServo speed
        crServo.setPower(speed);

        telemetry.addData("CRServo Speed", speed);
        telemetry.update();
    }
}
