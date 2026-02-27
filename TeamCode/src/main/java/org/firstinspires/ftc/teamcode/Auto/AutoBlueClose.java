package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.TelemetryManager;
import com.bylazar.telemetry.PanelsTelemetry;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.PathChain;
import com.pedropathing.geometry.Pose;

import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Pedro Intake", group = "Autonomous")
@Configurable
public class AutoBlueClose extends OpMode {

    private TelemetryManager panelsTelemetry;
    public Follower follower;
    private Paths paths;

    private DcMotor intake;

    enum AutoPathState {
        ILKPATH,
        IKINCIPATH,
        DONE
    }

    AutoPathState autoPathState = AutoPathState.ILKPATH;

    @Override
    public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(72, 8, Math.toRadians(90)));

        paths = new Paths(follower);

        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setPower(0);

        panelsTelemetry.addLine("Initialized");
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void start() {
        autoPathState = AutoPathState.ILKPATH;
        follower.followPath(paths.KrmzKarelkToplar);
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();

        panelsTelemetry.debug("State", autoPathState);
        panelsTelemetry.debug("Intake Power", intake.getPower());
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }

    public static class Paths {
        public PathChain KrmzKarelkToplar;
        public PathChain LK3ATI;

        public Paths(Follower follower) {

            KrmzKarelkToplar = follower.pathBuilder()
                    .addPath(new BezierLine(
                            new Pose(56.000, 8.000),
                            new Pose(24.158, 35.526)
                    ))
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(0))
                    .build();

            LK3ATI = follower.pathBuilder()
                    .addPath(new BezierCurve(
                            new Pose(24.158, 35.526),
                            new Pose(69.184, 62.645),
                            new Pose(70.000, 104.711)
                    ))
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(135))
                    .build();
        }
    }

    public void autonomousPathUpdate() {

        switch (autoPathState) {

            case ILKPATH:
                intake.setPower(1.0);

                if (!follower.isBusy()) {
                    intake.setPower(0);
                    follower.followPath(paths.LK3ATI);
                    autoPathState = AutoPathState.IKINCIPATH;
                }
                break;

            case IKINCIPATH:
                if (!follower.isBusy()) {
                    autoPathState = AutoPathState.DONE;
                }
                break;

            case DONE:
                intake.setPower(0);
                break;
        }
    }
}