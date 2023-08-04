import java.util.Arrays;

public class PDA {
    public State states[];
    public TransitionFunction transitionFunctions[];
    public int trasationlength ;
    public PDAStack pdaStack;

    public PDA() {
        this.pdaStack = new PDAStack();
    }

    public PDA(State[] state, TransitionFunction[] transitionFunctions, PDAStack pdaStack , int trlength) {
        setStates(state);
        setTransitionFunctions(transitionFunctions);
        this.pdaStack = new PDAStack();
        trasationlength = trlength;
    }

    public State[] getStates() {
        return states;
    }

    public void setStates(State[] states) {
        this.states = states;
    }

    public TransitionFunction[] getTransitionFunctions() {
        return transitionFunctions;
    }

    public void setTransitionFunctions(TransitionFunction[] transitionFunctions) {
        this.transitionFunctions = transitionFunctions;
    }

    public PDAStack getPdaStack() {
        return pdaStack;
    }

    public void setPdaStack(PDAStack pdaStack) {
        this.pdaStack = pdaStack;
    }

    public boolean accept(String string) {
        State currentState = State.findStartState(states);
        if (pdaStack.isEmpty()){
            pdaStack.push("z");
        }else {
            pdaStack.clear();
            pdaStack.push("z");
        }
        return ffff(currentState,  "λ" + string + "λ");
    }

    public boolean ffff (State currentState, String string) {
        State tF[] = new State[1000];
        if(currentState.isFinal){
            return true;
        }
        for(int i = 0 ; i < string.length() ; i++) {
            int j = 0;
            for (int k = 0; k < currentState.transitionFunctions.length; k++) {
                if (currentState.transitionFunctions[k].getBeginningState() == currentState &&
                        (currentState.transitionFunctions[k].getInput()).equals(Character.toString(string.charAt(i))) &&
                        currentState.transitionFunctions[k].getTopNow().equals(Character.toString(pdaStack.peek()))) {
                    if(!currentState.transitionFunctions[k].getTopNext().equals("λ")) {
                        pdaStack.pop();
                    }
                    pdaStack.push(currentState.transitionFunctions[k].getTopNext());
                    tF[j] = currentState.transitionFunctions[k].getDestinationState();
                    j++;
                }
            }

            for (int l = 0; l < j; l++) {
                if(ffff(tF[l], string.substring(i + 1)))return true;
            }
        }
        return false;
    }

    @Override
    public java.lang.String toString() {
        String str;
        str = "PDA : \n" +
                "STATES : \n" ;
        for(int i = 0 ; i < states.length; i++){
             str = str.concat(states[i].toString()+ "\n" );
        }
        str = str.concat( "Transition Functions :");
        for(int i = 0 ; i < trasationlength; i++){
            str = str.concat("\n" + transitionFunctions[i].toString());
        }
        return str;
    }
}
