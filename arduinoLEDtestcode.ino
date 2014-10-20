#include <Bridge.h>

/**
Set up variables for pins
**/
// pin assignment to power the LED to detect dust in sensor
int powerLED = 8; 
// pin for reading analog data set up
int analogData = A0;
// turn on LED for testing
int ledPin = 12;
// value that data is r  eturned to
int returnData =0;
int count = 0;
void setup() {
  Serial.begin(9600);
  
  Bridge.begin();
  //Set digital pin as output
  pinMode(powerLED, OUTPUT);
  pinMode(ledPin, OUTPUT);
}

void loop() {
//   // Turn on led in sensor
    digitalWrite(powerLED, HIGH);   // sets the LED on
    delay(0.32);  //this will need to be readjusted
  
  //Turn off led in sensor
    digitalWrite(powerLED, LOW);    // sets the LED off
    delay(10);                  // waits for a second
  
   returnData = analogRead(analogData);
    if(returnData!= 0 && count == 0){
          Serial.println(returnData);
          //count++;
          digitalWrite(ledPin, HIGH);
          delay(1000);
          digitalWrite(ledPin, LOW);
    }
    if(Serial.read() == 'x'){
      count = 1;
    }
    if(Serial.read() == 'c'){
      count = 0;
    }
  returnData = 0;
  }
