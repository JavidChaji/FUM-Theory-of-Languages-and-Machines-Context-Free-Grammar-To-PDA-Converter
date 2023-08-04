public class State {
    public String name;
    public TransitionFunction[] transitionFunctions;
    public boolean isStart;
    public boolean isFinal;


    public State(String name) {
        this.name = name;
        transitionFunctions = null;
        isStart = false;
        isFinal = false;
    }

    public State(String name,
                 TransitionFunction[] transitionFunctions,
                 boolean isStart, boolean isFinal) {
        this.name = name;
        this.transitionFunctions = transitionFunctions;
        this.isStart = isStart;
        this.isFinal = isFinal;
    }

    public void addTransition (){

    }

    public void removeTransation(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTransitionFunctions(TransitionFunction[] transitionFunctions) {
        this.transitionFunctions = transitionFunctions;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public String getName() {
        return name;
    }

    public TransitionFunction[] getTransitionFunctions() {
        return transitionFunctions;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public static State findStartState(State[] states){
        int i = 0;
        while (!states[i].isStart())i++;
        return states[i];
    }

    @Override
    public String toString() {
        return  name;
    }
}
