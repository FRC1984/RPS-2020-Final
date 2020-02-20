package frc.lib.control;

public class JawaPIDController {
    // Params
    private boolean isEnabled;
    private double maxSpeed,
                   minSpeed,
                   kP,
                   kI,
                   kD,
                   error,
                   lastError,
                   i,
                   d,
                   output;
    private int deadband;

    public JawaPIDController(double kP, double kI, double kD, int deadband) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.deadband = deadband;
        this.maxSpeed = 1.0; // TODO: Check these
        this.minSpeed = -1.0;
    }

    public JawaPIDController(double kP, double kI, double kD, int deadband, double maxSpeed, double minSpeed) {
        this(kP, kI, kD, deadband);
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
    }

    public double getOutput(double current, double target) {
        if(Math.abs(lastError) < deadband) {
            disable();
        }
        if(isEnabled) {
            error = target - current;
            i += error;
            d = error - lastError;

            output = 
            kP * error 
            + kI * i 
            + kD * d;

            // if(output > maxSpeed) {
            //     output = maxSpeed;
            // } else if(output < minSpeed) {
            //     output = minSpeed;
            // }

            lastError = error;

            return output;
        } else {
            return 0.0;
        }
    }

    public void disable() {
        isEnabled = false;
        reset();
    }

    public void enable() {
        isEnabled = true;
    }

    public void setPID(double p, double i, double d) {
        kP = p;
        kI = i;
        kD = d;
    }

    public void reset() {
        error = 0.0;
        lastError = 0.0;
        i = 0.0;
        d = 0.0;
    }

}