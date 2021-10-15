package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="attachment_TeleOp")
public class attachment_TeleOp extends OpMode {
    mecanumHardware robot = new mecanumHardware();
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        //driving code
        double r = Math.hypot(-gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = -gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        robot.frontLeft.setPower(-1 * v1);
        robot.frontRight.setPower(-1 * v2);
        robot.backLeft.setPower(-1 * v3);
        robot.backRight.setPower(-1 * v4);

        telemetry.addData("fLPower", -1 * v1);
        telemetry.addData("fRPower", -1 * v2);
        telemetry.addData("bLPower", -1 * v3);
        telemetry.addData("bRPower", -1 * v4);

        telemetry.addData("Encoder port 1 back left", robot.backLeft.getCurrentPosition());
        telemetry.addData("Encoder port 2 front right", robot.frontRight.getCurrentPosition());
        telemetry.addData("Encoder port 3 back right", robot.backRight.getCurrentPosition());
        telemetry.addData("Encoder port 4 back left", robot.backLeft.getCurrentPosition());

        // attachment code

        if (gamepad1.right_bumper) {
            robot.grabberServo.setPosition(mecanumHardware.grabber_max);
        } else {
            robot.grabberServo.setPosition(mecanumHardware.grabber_min);
        }

        if (gamepad1.a) {
            robot.carousel.setPower(1);
        }
        if (gamepad1.b) {
            robot.carousel.setPower(0);
        }
        if (gamepad1.left_trigger > 0.5) {
            robot.extenderServo.setPower(1);
        }
        if (gamepad1.right_trigger > 0.5) {
            robot.extenderServo.setPower(0);
        }
        if (gamepad1.dpad_up) {
            robot.pulleyMotor.setPower(1);
        } else if (gamepad1.dpad_down) {
            robot.pulleyMotor.setPower(-1);
        } else {
            robot.pulleyMotor.setPower(0);
        }
    }
}
