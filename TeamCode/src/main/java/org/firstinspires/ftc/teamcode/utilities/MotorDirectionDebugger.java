package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "MotorDirectionDebugger", group = "2- Utilities")
public class MotorDirectionDebugger extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("leftFront");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("leftBack");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rightFront");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("rightBack");

        // Optional: set initial directions if known
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Brake when power = 0
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addLine(" Motor Direction Debugger Ready");
        telemetry.addLine("Press:");
        telemetry.addLine("A → Front Left");
        telemetry.addLine("B → Front Right");
        telemetry.addLine("X → Back Left");
        telemetry.addLine("Y → Back Right");
        telemetry.addLine("Left Bumper → Reverse all directions");
        telemetry.addLine("Release all buttons to stop");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        boolean reversed = false;

        while (opModeIsActive()) {
            double testPower = 0.5;

            if (gamepad1.left_bumper) {
                reversed = !reversed;
                frontLeftMotor.setDirection(reversed ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
                backLeftMotor.setDirection(reversed ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
                frontRightMotor.setDirection(reversed ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
                backRightMotor.setDirection(reversed ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
                sleep(300);
            }

            // Stop all motors by default
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);

            // Activate one motor at a time
            if (gamepad1.a) {
                frontLeftMotor.setPower(testPower);
                telemetry.addData("Testing", "Front Left Motor");
            } else if (gamepad1.b) {
                frontRightMotor.setPower(testPower);
                telemetry.addData("Testing", "Front Right Motor");
            } else if (gamepad1.x) {
                backLeftMotor.setPower(testPower);
                telemetry.addData("Testing", "Back Left Motor");
            } else if (gamepad1.y) {
                backRightMotor.setPower(testPower);
                telemetry.addData("Testing", "Back Right Motor");
            } else {
                telemetry.addData("Testing", "None");
            }

            telemetry.addData("Directions",
                    "FL:%s | FR:%s | BL:%s | BR:%s",
                    frontLeftMotor.getDirection(),
                    frontRightMotor.getDirection(),
                    backLeftMotor.getDirection(),
                    backRightMotor.getDirection()
            );
            telemetry.addData("Reversed Mode", reversed ? "YES" : "NO");
            telemetry.addLine("Confirm positive power spins forward");
            telemetry.update();
        }
    }
}
