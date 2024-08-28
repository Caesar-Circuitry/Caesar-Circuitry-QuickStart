package org.firstinspires.ftc.Caesar_Circuitry_Teamcode.robot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
@Disabled
public class TeleOpSample extends LinearOpMode {
    private DcMotor frontleft, frontright, backleft, backright;

    @Override
    public void runOpMode() throws InterruptedException {
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        backright = hardwareMap.get(DcMotor.class, "backright");

        frontright.setDirection(DcMotorSimple.Direction.REVERSE);
        backright.setDirection(DcMotorSimple.Direction.REVERSE);

        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while(opModeIsActive()){
            double x = -gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double turn = -gamepad1.right_stick_x;

            double theta = (float)Math.atan2(y,x);
            double power = (float)Math.hypot(x,y);

            double sin = Math.sin(theta -Math.PI/4);
            double cos = Math.cos(theta -Math.PI/4);
            double max = Math.max(Math.abs(sin), Math.abs(cos));

            double lf_power = power * cos/max + turn;
            double lb_power = power * sin/max + turn;
            double rf_power = power * sin/max - turn;
            double rb_power = power * cos/max - turn;

            if((power + Math.abs(turn)) > 1) {
                lf_power /= power + turn;
                lb_power /= power + turn;
                rf_power /= power + turn;
                rb_power /= power + turn;
            }

            frontleft.setPower(lf_power);
            backleft.setPower(lb_power);
            frontright.setPower(rf_power);
            backright.setPower(rb_power);
        }
    }
}
