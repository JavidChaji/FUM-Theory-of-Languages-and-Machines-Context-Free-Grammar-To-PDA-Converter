import java.util.*;
public class Main {

    private static Map<String, List<String>> mapVariableProduction = new LinkedHashMap<>();
    private static TransitionFunction[] transitionFunctions = new TransitionFunction[100];
    private static PDAStack pdaStack;
    private static State q0 = new State("q0");
    private static State q1 = new State("q1");
    private static State qf = new State("qf");
    private static PDA pda = new PDA();
    private static String type;
    private static String output = "";
    private static int h = 0;


    public static void main(String[] args) {
        read();
        grammerProcessing();
        creatPDA();

        printMap();
        printTrasationFunction();
        printPDA();
        write(output);
        testInput();
    }


    public static void read(){
        GrammarReader grammarReader = new GrammarReader();
        try {
            mapVariableProduction = grammarReader.readFromFile("D:\\University\\Computer\\Term 4\\Languages and Automata\\Project\\Project 2\\FinalProject\\src\\input") ;
        }catch (Exception ex){
            System.out.println("I Can't Find File");
        }
    }

    public static void write(String string){
        OutputWriter outputWriter = new OutputWriter();
        try {
            outputWriter.writeToFile("D:\\University\\Computer\\Term 4\\Languages and Automata\\Project\\Project 2\\FinalProject\\src\\output",
                    string) ;
        }catch (Exception ex){
            System.out.println("I Can't Find File");
        }
    }

    public static void grammerProcessing(){
        transitionFunctions[h] = new TransitionFunction(q0, "λ", "z", q1, "Sz");
        TransitionFunction transitionFunctionQ0[] = new TransitionFunction[1];
        transitionFunctionQ0[0] = transitionFunctions[h];
        q0.setStart(true);
        q0.setTransitionFunctions(transitionFunctionQ0);
        h++;
        Iterator itr = mapVariableProduction.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            ArrayList<String> productionList = (ArrayList<String>) entry.getValue();
            for (int i = 0; i < productionList.size(); i++) {
                String temp = productionList.get(i);

                if(temp.length() > 1){
                    transitionFunctions[h] = new TransitionFunction(q1,
                                                        Character.toString(temp.charAt(0)),
                                                        entry.getKey().toString(),
                                                        q1,
                                                        temp.substring(1, temp.length()));
                    h++;
                }else {
                    if(temp.equals("λ")){
                        transitionFunctions[h] = new TransitionFunction(q1, "λ", entry.getKey().toString(),
                                                                        q1, "λ");
                        h++;
                    }else{
                        transitionFunctions[h] = new TransitionFunction(q1,
                                                        Character.toString(temp.charAt(0)),
                                                        entry.getKey().toString(),
                                                        q1,
                                                 "λ");
                        h++;
                    }
                }
            }
        }
        transitionFunctions[h] = new TransitionFunction(q1, "λ", "z", qf, "λ");
        h++;
        TransitionFunction transitionFunctionQ1[] = new TransitionFunction[h-1];
        for(int i = 0 ; i < h - 1 ; i++){
            transitionFunctionQ1[i] = transitionFunctions[i+1];
        }
        q1.setTransitionFunctions(transitionFunctionQ1);
        qf.setFinal(true);

    }
    private static void printTrasationFunction(){
        System.out.println("TRANSATION FUNCTION:");
        output = output.concat("TRANSATION FUNCTION:" + "\n");
        for( int i = 0 ; i < h; i++) {
            System.out.println(transitionFunctions[i].toString());
            output = output.concat(transitionFunctions[i].toString() + "\n");
        }
    }

    public static void creatPDA(){
        State sta[] = new State[3];
        sta[0] = q0; sta[1] = q1; sta[2] = qf;
        pdaStack = new PDAStack();
        pda = new PDA(sta, transitionFunctions, pdaStack , h);

    }

    public static void testInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter Your StringType :");
        String in = scanner.next();
        if(pda.accept(in)){
            System.out.println("Your String is in the PDA 👍✔");
        }else {
            System.out.println("Your String does not belong to PDA 👎✖ ");
        }
    }

    private static void printPDA() {
        System.out.println(pda.toString());
        output = output.concat(pda.toString() + "\n");
    }

    private static void printMap() {

        Iterator it = mapVariableProduction.entrySet().iterator();
        System.out.println("GRAMMER:");
        output = output.concat("GRAMMER:" + "\n");
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " -> " + pair.getValue());
            output = output.concat(pair.getKey() + " -> " + pair.getValue() + "\n");
        }
    }

}
