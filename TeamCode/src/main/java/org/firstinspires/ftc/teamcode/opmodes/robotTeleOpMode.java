package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.subsystems.dt.DrivetrainSubsystem;

import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.NextFTCOpMode;

public class robotTeleOpMode extends NextFTCOpMode {
    {
        addComponents(new SubsystemComponent(DrivetrainSubsystem.INSTANCE));
    }

    @Override public void onInit() { }
    @Override public void onWaitForStart() { }
    @Override public void onStartButtonPressed() { }
    @Override public void onUpdate(
            DrivetrainSubsystem.INSTANCE.update()
    ) { }
    @Override public void onStop() { }
}