 
/**
Set up variables for pins
**/
// pin assignment to power the LED to detect dust in sensor
int powerLED = 8; 
// pin for reading analog data set up
int analogData = 0;
// turn on LED for testing
int ledPin = 12;
// value that data is returned to
int returnData =0;

void setup() {
  // put your setup code here, to run once:
 
  //Set digital pin as output
  pinMode(powerLED, OUTPUT);
  pinMode(ledPin, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  // Turn on led in sensor
    digitalWrite(powerLED, HIGH);   // sets the LED on
    delay(1000);  //this will need to be readjusted
  
  //Turn off led in sensor
    digitalWrite(powerLED, LOW);    // sets the LED off
    delay(1000);                  // waits for a second
  
   // Read data from pins
   returnData = analogRead(analogData);
 
 // If analogdata is not 0 then turn on test LED
    if(returnData != 0){
      digitalWrite(ledPin, HIGH);
      delay(1000);
    }else{
      digitalWrite(ledPin, LOW);
      delay(1000);
    }

returnData = 0;
  //TODO timings for the pins and LED

}
