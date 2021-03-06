/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.Swerve;

import java.lang.Math;

/**
 * Add your docs here.
 */
public class SwerveMath {
    private final double length;
    private final double width;
    private final double diagonal;

    public SwerveMath(double width, double length){
        // frDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        // flDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        // rrDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        // rlDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        // frSteer.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        // flSteer.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        // rrSteer.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        // rlSteer.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    
        this.width = width;
        this.length = length;
        diagonal = Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
    }

    public void move(double fwd, double str, double rcw, double gyroVal, SwerveDirective[] swerveDirectives){
    double a = str - rcw*(length/diagonal);
    double b = str + rcw*(length/diagonal);
    double c = fwd - rcw*(width/diagonal);
    double d = fwd + rcw*(width/diagonal);

    double frs = Math.sqrt((Math.pow(b,2)+Math.pow(c,2))); 
    double fls = Math.sqrt(Math.pow(b,2)+Math.pow(d,2));
    double rrs = Math.sqrt(Math.pow(a,2)+Math.pow(d,2));
    double rls = Math.sqrt(Math.pow(a,2)+Math.pow(c,2));

    double fra = Math.atan2(b,c)*180/Math.PI;
    double fla = Math.atan2(b,d)*180/Math.PI;
    double rra = Math.atan2(a,d)*180/Math.PI;
    double rla = Math.atan2(a,c)*180/Math.PI;
    //double temp = (fwd*Math.cos(gyro.getAngle())) + str*Math.sin(gyro.getAngle());
    double max = frs; 
    if(fls>max){
    	max = fls;
    }
    if(rls>max){
    	max = rls;
    }
    if(rrs>max){
    	max = rrs;
    }
    if(max>1){
    	frs/=max;
    	fls/=max;
    	rrs/=max;
    	rls/=max;
    }

    setDirective(swerveDirectives[0], fra, frs);
    setDirective(swerveDirectives[1], fla, fls);
    setDirective(swerveDirectives[2], rra, rrs);
    setDirective(swerveDirectives[3], rla, rls);

    }

private void setDirective(SwerveDirective swerveDirective, double angle, double speed){
    swerveDirective.setAngle(angle);
    swerveDirective.setSpeed(speed);
}

}