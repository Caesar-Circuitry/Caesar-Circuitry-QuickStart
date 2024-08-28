package org.firstinspires.ftc.Caesar_Circuitry_Teamcode.RoadRunner.files;

import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Twist2dDual;

public interface Localizer {
    Twist2dDual<Time> update();
}
