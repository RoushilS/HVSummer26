package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.List;
@TeleOp
public class TwoWheelOpMode extends OpMode {
    DcMotor left;
    DcMotor right;


    public void init() {
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
    }

    public void loop() {
        right.setPower(gamepad1.right_stick_y);
        left.setPower(-gamepad1.left_stick_y);
    }
}

