package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.pedropathing.control.PIDFCoefficients;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/**
 * Constants related to the drivetrain hardware and control.
 */
//@Configurable
public class DrivetrainConstants {

    @IgnoreConfigurable
    public static String LeftFrontName = "frontLeftMotor";
    @IgnoreConfigurable
    public static String RightFrontName = "frontRightMotor";
    @IgnoreConfigurable
    public static String LeftBackName = "backLeftMotor";
    @IgnoreConfigurable
    public static String RightBackName = "backRightMotor";
    @IgnoreConfigurable
    public static String ImuName = "imu";
    @IgnoreConfigurable
    public static DcMotorSimple.Direction LeftFrontDirection = DcMotorSimple.Direction.REVERSE;
    @IgnoreConfigurable
    public static DcMotorSimple.Direction RightFrontDirection = DcMotorSimple.Direction.FORWARD;
    @IgnoreConfigurable
    public static DcMotorSimple.Direction LeftBackDirection = DcMotorSimple.Direction.REVERSE;
    @IgnoreConfigurable
    public static DcMotorSimple.Direction RightBackDirection = DcMotorSimple.Direction.FORWARD;}