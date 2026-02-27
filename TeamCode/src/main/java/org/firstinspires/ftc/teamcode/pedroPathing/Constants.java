
package org.firstinspires.ftc.teamcode.pedroPathing;

import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;

@IgnoreConfigurable
public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .forwardZeroPowerAcceleration(-48.23)
            .lateralZeroPowerAcceleration(-39.946)
            .useSecondaryTranslationalPIDF(true)
            .useSecondaryHeadingPIDF(true)
            .useSecondaryDrivePIDF(true)
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.0059,0,0.002,0.06,0.002)) //0.00255,0,0.00064,0.06,0.001
            .secondaryDrivePIDFCoefficients(new FilteredPIDFCoefficients(0.0235,0, 0.001089, 0.6, 0.001))
            .headingPIDFCoefficients(new PIDFCoefficients(0.75,0,0.032,0.017))
            .secondaryHeadingPIDFCoefficients(new PIDFCoefficients(1.5,0,0.05,0.01))
            .translationalPIDFCoefficients(new PIDFCoefficients(0.11,0,0.0095,0.008))
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0.0875,0,0.032,0.012))
            .mass(16.2);
    //drive'ı hanginiz yaptıysa scale sıkıntısı var

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .xVelocity(71.78)
            .yVelocity(57.7)
            .rightFrontMotorName(DrivetrainConstants.RightFrontName)
            .rightRearMotorName(DrivetrainConstants.RightBackName)
            .leftRearMotorName(DrivetrainConstants.LeftBackName)
            .leftFrontMotorName(DrivetrainConstants.LeftFrontName)
            .leftFrontMotorDirection(DrivetrainConstants.LeftFrontDirection)
            .leftRearMotorDirection(DrivetrainConstants.LeftBackDirection)
            .rightFrontMotorDirection(DrivetrainConstants.RightFrontDirection)
            .rightRearMotorDirection(DrivetrainConstants.RightBackDirection);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 0.9, 1);
    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(1.247) //inch old: -3.50881 1.247
            .strafePodX(-3.149) //inch old: -1.24755906
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)

                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .build();
    }
}