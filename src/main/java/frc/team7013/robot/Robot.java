package frc.team7013.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

    public static Spark spark = new Spark(2);
    public static Spark spark2 = new Spark(3);
    public static PWMTalonSRX tele = new PWMTalonSRX(6);
    public static AnalogInput potentiometer = new AnalogInput(0);

    public static SmartDashboard smart_dash = new SmartDashboard();
    public static PID pid;

    public static double Kp, Ki, Kd;
    public static int setpoint, cuttoff;

    @Override
    public void robotInit() {
        Kp = 1;
        Kd = 1;
        Ki = 0;
        setpoint = 2105;
        cuttoff = 0;
        pid = new PID(Kp, Kd, Ki, cuttoff);
        pid.newSetpoint(setpoint);
        pid.doPID(potentiometer.getValue());

    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void testInit() {
    }



    @Override
    public void disabledPeriodic() {
        tele.set(-.2);
        pid.doPID(potentiometer.getValue());
        SmartDashBS();
    }
    
    @Override
    public void autonomousPeriodic() { }

    @Override
    public void teleopPeriodic() { }

    @Override
    public void testPeriodic() {
        SmartDashBS();
        tele.set(-.2);
        pid.doPID(potentiometer.getValue());

        spark.set(pid.getOutput());
        spark2.set(-pid.getOutput());


    }
    public void SmartDashBS(){
        smart_dash.putNumber("Current Position:", potentiometer.getValue());
        smart_dash.putNumber("Current Error:", pid.getError());
        smart_dash.putNumber("Current Output", pid.getOutput());
        smart_dash.putBoolean("Is calculating", pid.doPID(potentiometer.getValue()));
    }
}