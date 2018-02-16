package frc.team7013.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

    public static Spark spark = new Spark(1);
    public static AnalogInput potentiometer = new AnalogInput(0);

    public static SmartDashboard smart_dash = new SmartDashboard();
    public static PID pid;

    public static double Kp, Ki, Kd;
    public static int setpoint, cuttoff;

    @Override
    public void robotInit() { }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit() { }

    @Override
    public void teleopInit() { }

    @Override
    public void testInit() { }


    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() { }

    @Override
    public void teleopPeriodic() { }

    @Override
    public void testPeriodic() {
        smart_dash.putNumber("Current Position:", potentiometer.getValue());
        smart_dash.putNumber("Current Error:", pid.getError());
        smart_dash.putNumber("Current Output", pid.getOutput());
        smart_dash.putBoolean("Is calculating", pid.doPID(potentiometer.getValue()));
        smart_dash
        Kp = 1;
        Kd = 0;
        Ki = 0;

        setpoint = 1;
        cuttoff = 1;

        pid = new PID(Kp, Kd, Ki, cuttoff);
        pid.newSetpoint(setpoint);

        spark.set(pid.getOutput());
    }
}