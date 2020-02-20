package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.lib.control.JawaPIDController;

public class Arm {

    private TalonSRX armSRX;

    private JawaPIDController pid;

    public Arm(int SRX_ID) {
        armSRX = new TalonSRX(SRX_ID);
        pid = new JawaPIDController(0.1, 0.0, 0.0, 400);
        armSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        armSRX.setInverted(true);
         
    } 

    public void displayR() {
        pid.enable();
        armSRX.set(ControlMode.PercentOutput, pid.getOutput(ArmPositions.R.getPos(), getSensorAdjusted()));
    }

    public void displayP() {
        pid.enable();
        armSRX.set(ControlMode.PercentOutput, pid.getOutput(ArmPositions.P.getPos(), getSensorAdjusted()));
    }

    public void displayS() {
        pid.enable();
        armSRX.set(ControlMode.PercentOutput, pid.getOutput(ArmPositions.S.getPos(), getSensorAdjusted()));
    }
    
    public void setPID(double p, double i, double d) {
        pid.setPID(p, i, d);
    }

    public void spin() {
        armSRX.set(ControlMode.PercentOutput, 1.0);
    }

    public void stop() {
        pid.disable();
        armSRX.set(ControlMode.PercentOutput, 0.0);
    }

    public void reset() {
        armSRX.setSelectedSensorPosition(0);
    }

    public int getSensorAdjusted() {
        return (armSRX.getSelectedSensorPosition() / (65/18)) % 4096;
    }
    
}

enum ArmPositions {
    R(0),
    P(0),
    S(0);

    private int pos;

    private ArmPositions(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return this.pos;
    }

}