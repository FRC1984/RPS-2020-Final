/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.oi.JawaXboxController;

public class Robot extends TimedRobot {

  private static Arm arm;
  public static JawaXboxController cont;

  private RPSMatch currentMatch;
  private char action;

  @Override
  public void robotInit() {
    arm = new Arm(5); 
    cont = new JawaXboxController(0);

    currentMatch = new RPSMatch();
    action = 'n';
  }

  @Override
  public void robotPeriodic() {
    if(cont.getBackButton()) {
      arm.reset();
    }
    SmartDashboard.putNumber("Enc Value", arm.getSensorAdjusted());
    arm.setPID(Preferences.getInstance().getDouble("P", 0.1),
               Preferences.getInstance().getDouble("I", 0.0),
               Preferences.getInstance().getDouble("D", 0.0));
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopPeriodic() {

    if(cont.getYButton()) {
      currentMatch.resetAll();
      action = 'n';
    } else {
      if(cont.getXButton()) {
        action = currentMatch.humanShoots('r');
      } else if(cont.getAButton()) {
        action = currentMatch.humanShoots('p');
      } else if(cont.getBButton()) {
        action = currentMatch.humanShoots('s');
      } else if(cont.getStickButton(GenericHID.Hand.kRight)) {
        action = 'n';
      }
    }

    switch(action) {
      case 'r':
        arm.displayR();
        break;
      case 'p':
        arm.displayP();
        break;
      case 's':
        arm.displayS();
        break;
      default:
        arm.spin();
        break;
    }
  }

  @Override
  public void testPeriodic() {
  }
}
