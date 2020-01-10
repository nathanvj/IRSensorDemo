import TI.*;

public class Demo 
{
    
    static Servo leftServo = new Servo(12);
    static Servo rightServo = new Servo(13);

    static int middleLineSensorAnalogValue;
    static int leftLineSensorAnalogValue;
    static int rightLineSensorAnalogValue;
    
    static int middleLineSensorDigitalValue;
    static int leftLineSensorDigitalValue;
    static in rightLineSensorDigitalValue;

    public static void main(String[] args) 
    {
        System.out.println("Detecting black lines...");
        
        while(true) 
        {
            middleLineSensorAnalogValue = BoeBot.analogRead(0);
            leftLineSensorAnalogValue = BoeBot.analogRead(1);
            rightLineSensorAnalogValue = BoeBot.analogRead(2);
            
            middleLineSensorDigitalValue = BoeBot.digitalRead(0);
            leftLineSensorDigitalValue = BoeBot.digitalRead(1);
            rightLineSensorDigitalValue = BoeBot.digitalRead(2);

            System.out.print("Left line sensor, analog value: " + leftLineSensorAnalogValue);
            System.out.print("Middle line sensor, analog value: " + middleLineSensorAnalogValue);
            System.out.print("Right line sensor, analog value: " + rightLineSensorAnalogValue);
            System.out.println();
            
            System.out.print("Left line sensor, digital value: " + leftLineSensorDigitalValue);
            System.out.print("Middle line sensor, digital value: " + middleLineSensorDigitalValue);
            System.out.print("Right line sensor, digital value: " + rightLineSensorDigitalValue);
            System.out.println();

            BoeBot.wait(50);
            
            if(middleLineSensorValue >= 200 && leftLineSensorValue < 200 && rightLineSensorValue < 200)
            {
                // Keep going forward
                leftServo.update(1540);
                rightServo.update(1460);
            }
            else if(leftLineSensorValue >= 200 && middleLineSensorValue < 200 && rightLineSensorValue < 400)
            {
                // Adjust to left
                int rightPulseWidth = rightServo.getPulseWidth();
                leftServo.update(1600);
            }
            else if(rightLineSensorValue >= 400 && middleLineSensorValue < 400 && leftLineSensorValue < 400)
            {
                // Adjust to right
                int leftPulseWidth = leftServo.getPulseWidth();
                rightServo.update(1400);
            }
            else if(leftLineSensorValue >= 400 && middleLineSensorValue >= 400 && rightLineSensorValue < 400)
            {
                // Turn -90 degrees
                rightServo.update(1500);
                leftServo.update(1650);
                BoeBot.wait(500);
            }
            else if(rightLineSensorValue >= 400 && middleLineSensorValue >= 400 && leftLineSensorValue < 400)
            {
                // Turn 90 degrees
                leftServo.update(1500);
                rightServo.update(1350);
                BoeBot.wait(500);
            }
            else if(rightLineSensorValue >= 400 && middleLineSensorValue >= 400 && leftLineSensorValue >= 400)
            {
                // Intersection, stop
                emergencyBreak();
            }

            BoeBot.wait(50);
        }
    }
}