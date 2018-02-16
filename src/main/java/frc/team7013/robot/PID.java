package frc.team7013.robot;

public class PID {

    private double Kp, Kd, Ki, output, previous_Kd, accum_Ki, previous_error;
    private int error, setpoint, cutoff_val;

    public PID(double Kp, double Kd, double Ki, int cutoff_val){
        this.Kp = Kp;
        this.Kd = Kd;
        this.Ki = Ki;
        this.cutoff_val = cutoff_val;
        setpoint = 0;
        previous_error = 0;
    }

    public void newSetpoint(int setpoint){
        this.setpoint = setpoint;
        error = 0;
        output = 0;
        previous_Kd = 0;
        accum_Ki = 0;
        previous_error = 0;
    }

    public boolean doPID(int current){

        error = setpoint - current; //calculate error

        accum_Ki += error * Ki; //calculate the integral of the Ki term
        previous_Kd = previous_error - error;

        output = (error * Kp) + (accum_Ki) + (previous_Kd * Kd);

        previous_error = error;

        if(error > cutoff_val) {
            if(Math.abs(output) > 1)
                output = (output < 0)?-1:1;
            return false;
        }
        else {
            output = 0;
            return true;
        }


    }
    public double getOutput(){ return output; }
    public double getError(){ return error; }
}
