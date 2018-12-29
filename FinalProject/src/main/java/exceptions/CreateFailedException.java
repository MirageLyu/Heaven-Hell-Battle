package exceptions;

import output.Output;

public class CreateFailedException extends Exception{
    public CreateFailedException(String s){
        super(s);
    }
    public void printErrorMessage(){
        Output.outputCreateFailedMessage(this.getMessage());
    }
}
