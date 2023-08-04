public class TransitionFunction {
    public State beginningState;
    public String input;
    public String topNow;
    public State destinationState;
    public String topNext;

    public TransitionFunction() {
    }

    public TransitionFunction(State beginningState) {

    }

    public TransitionFunction(State beginningState,
                              String input,
                              String topNow,
                              State destinationState,
                              String topNext) {
        setBeginningState(beginningState);
        setInput(input);
        setTopNow(topNow);
        setDestinationState(destinationState);
        setTopNext(topNext);
    }

    public State getNextState(){
        return destinationState;
    }

    public State getBeginningState() {
        return beginningState;
    }

    public void setBeginningState(State beginningState) {
        this.beginningState = beginningState;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getTopNow() {
        return topNow;
    }

    public void setTopNow(String topNow) {
        this.topNow = topNow;
    }

    public State getDestinationState() {
        return destinationState;
    }

    public void setDestinationState(State destinationState) {
        this.destinationState = destinationState;
    }

    public String getTopNext() {
        return topNext;
    }

    public void setTopNext(String topNext) {
        this.topNext = topNext;
    }


    @Override
    public String toString() {
        return "δ(" + beginningState +
                ", "+ input +
                ", " + topNow +
                ") = {(" + destinationState +
                ", " + topNext +
                ")}";
    }
}
